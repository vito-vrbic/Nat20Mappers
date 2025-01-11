package com.ttrpg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ttrpg.dto.BusinessProfileDetails;
import com.ttrpg.dto.BusinessProfileUpdateRequest;
import com.ttrpg.model.Korisnik;
import com.ttrpg.model.OrgProfil;
import com.ttrpg.model.PoslovniKorisnik;
import com.ttrpg.model.Slika;
import com.ttrpg.repository.KorisnikRepository;
import com.ttrpg.util.jwtUtil;

@Service  // Oznaka da je ovo servis klasa koja se koristi u Spring aplikaciji
public class KorisnikService {

    @Autowired  // Automatsko injektiranje KorisnikRepository ovisnosti
    KorisnikRepository kr;

    // Metoda za autentifikaciju korisnika prema korisničkom imenu i lozinki
    public boolean authenticate(String username, String password) {
        List<Korisnik> korisnici = kr.findByUsername(username);  // Pretražuje korisnike po korisničkom imenu
        if(korisnici.size() != 0) {  // Ako postoji korisnik s tim korisničkim imenom
            Korisnik korisnik = kr.findByUsername(username).get(0);  // Uzima prvog korisnika s tim imenom
            if(korisnik.getPassword().equals(password)) {  // Provjerava lozinku
                return true;  // Ako lozinka odgovara, vraća true
            } else {
                return false;  // Ako lozinka ne odgovara, vraća false
            }
        }
        return false;  // Ako korisnik nije pronađen, vraća false
    }

    // Metoda za učitavanje početnih podataka (dva korisnika u bazu)
    public void sDataLoader() {
        kr.save(new Korisnik(1, "Marko58","beb1@gmail.com","Apple","Private"));  // Spremanje korisnika u bazu
        kr.save(new Korisnik(2, "Marko59","beb2@gmail.com","Apple2","Business"));  // Spremanje drugog korisnika u bazu
    }
    
    public BusinessProfileDetails getProfileByUsername(String username) {
        Korisnik korisnik = kr.findByUsername(username).get(0);  // Pronalazi korisnika po korisničkom imenu

        if (korisnik == null || !(korisnik instanceof PoslovniKorisnik)) {  // Ako korisnik nije pronađen ili nije poslovni korisnik
            return null;  // Vraća null
        }

         PoslovniKorisnik poslovniKorisnik = (PoslovniKorisnik) korisnik;
        OrgProfil orgProfil = poslovniKorisnik.getCompany();

        if (orgProfil == null) {
            return null; // Ako nema povezanog org profila, vraćamo null
        }

        // Mapiranje podataka u BusinessProfileDetails
        BusinessProfileDetails details = new BusinessProfileDetails();
        details.setUsername(poslovniKorisnik.getUsername());
        details.setLogo(orgProfil.getCompanyLogos() != null && !orgProfil.getCompanyLogos().isEmpty() 
            ? orgProfil.getCompanyLogos().get(orgProfil.getCompanyLogos().size() - 1).getImageUrl() 
            : null); // Uzmi zadnji logo
        details.setCompanyPhone(orgProfil.getCompanyPhone());
        details.setCompanyDes(orgProfil.getCompanyDes());
        details.setCompanyWeb(orgProfil.getCompanyWeb());
        details.setCompanyAddress(orgProfil.getCompanyAdress());

        return details;
    }

    public boolean isValidToken(String authToken) {
        try {
            // Ako `validateJWT` ne baci iznimku, token je valjan
            jwtUtil.validateJWT(authToken);
            return true;
        } catch (RuntimeException e) {
            // Token je nevažeći ili istekao
            return false;
        }
    }

    public BusinessProfileDetails updateProfile(String authToken, BusinessProfileUpdateRequest updateRequest) {
        if (!isValidToken(authToken)) {
            throw new SecurityException("Invalid or expired token");
        }
    
        // Dohvati korisničko ime iz tokena
        String username = jwtUtil.validateJWT(authToken).getSubject();

        Korisnik korisnik = kr.findByUsername(username).stream().findFirst().orElse(null);
        PoslovniKorisnik poslovniKorisnik = (PoslovniKorisnik) korisnik;
        OrgProfil orgProfil = poslovniKorisnik.getCompany();

        if (orgProfil == null) {
            throw new IllegalStateException("Business profile not set up");
        }

        // Ažuriraj podatke profila
        if (updateRequest.getLogo() != null) {
            Slika newLogo = new Slika(); // Pretpostavimo da `Slika` ima polje URL
            newLogo.setImageUrl(updateRequest.getLogo());
            orgProfil.getCompanyLogos().add(newLogo);
        }
        if (updateRequest.getCompanyPhone() != null) {
            orgProfil.setCompanyPhone(updateRequest.getCompanyPhone());
        }
        if (updateRequest.getCompanyDes() != null) {
            orgProfil.setCompanyDes(updateRequest.getCompanyDes());
        }
        if (updateRequest.getCompanyWeb() != null) {
            orgProfil.setCompanyWeb(updateRequest.getCompanyWeb());
        }
        if (updateRequest.getCompanyAddress() != null) {
            orgProfil.setCompanyAdress(updateRequest.getCompanyAddress());
        }

        // Spremi ažurirani profil
        kr.save(poslovniKorisnik);

        // Vrati ažurirani profil
        return getProfileByUsername(poslovniKorisnik.getUsername());
    }
}
