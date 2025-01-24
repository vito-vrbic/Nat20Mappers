package com.ttrpg.service;

import java.util.ArrayList;
import java.util.List;

import com.ttrpg.model.*;
import com.ttrpg.repository.IgraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ttrpg.dto.BusinessProfileDetailsDTO;
import com.ttrpg.dto.BusinessProfileUpdateRequestDTO;
import com.ttrpg.repository.KorisnikRepository;
import com.ttrpg.repository.OrgRepository;
import com.ttrpg.util.JwtUtil;

import jakarta.transaction.Transactional;

@Service  // Oznaka da je ovo servis klasa koja se koristi u Spring aplikaciji
public class KorisnikService {

    @Autowired  // Automatsko injektiranje KorisnikRepository ovisnosti
    KorisnikRepository kr;

    @Autowired
    OrgRepository or;

    @Autowired
    IgraRepository ir;

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
    @Transactional
    public void sDataLoader() {
        OrgProfil org= new OrgProfil("business ralph", "0914682525", "Ralph is businessing all over the place", "beep", "boop", "bap");
        or.save(org);
        kr.save(new PrivatniKorisnik(1, "Marko58", "beb1@gmail.com", "Apple"));  // Spremanje korisnika u bazu
        PoslovniKorisnik poslovniKorisnik = new PoslovniKorisnik("Marko59", "beb2@gmail.com", "Apple2",org);  // Spremanje drugog korisnika u bazu
        kr.save(poslovniKorisnik);  // Spremanje drugog korisnika u bazu
        List<Igra> games = new ArrayList<>();

        // Generic game without location
        games.add(new Igra(
                "Classic Fantasy Adventure", "online", poslovniKorisnik, true,
                "medium", "4 hours", "2025-01-25T18:00:00", "Explore the classic fantasy world!",
                "D&D 5e", false, 6, "Discord", true));

        // Localized game inside Zagreb
        MapLocation realLocation1 = new MapLocation(45.8150, 15.9819); // Coordinates for Zagreb
        MapLocation fakeLocation1 = new MapLocation(45.8125, 15.9770); // Slightly different fake location

        games.add(new LokaliziranaIgra(
                "Zagreb Mystery Quest", "public", poslovniKorisnik, "medium",
                "3 hours", "2025-01-26T14:00:00", "Solve a thrilling mystery in Zagreb!",
                "Homebrew Rules", true, true, 5, "WhatsApp", true,
                realLocation1, fakeLocation1));

        // Exact location game inside Zagreb
        MapLocation exactLocation1 = new MapLocation(45.8132, 15.9760); // Zagreb center

        games.add(new TocnoLokacijskaIgra(
                "Urban Chase in Zagreb", "private", poslovniKorisnik, "high",
                "2 hours", "2025-01-27T16:00:00", "Catch the thief in Zagreb's streets!",
                "Modern Chase System", true, 4, "Signal", false, true,
                exactLocation1));

        // Another localized game inside Zagreb
        MapLocation realLocation2 = new MapLocation(45.8100, 15.9870); // Another Zagreb location
        MapLocation fakeLocation2 = new MapLocation(45.8120, 15.9890); // Slightly different fake location

        games.add(new LokaliziranaIgra(
                "Zagreb Treasure Hunt", "public", poslovniKorisnik, "low",
                "2.5 hours", "2025-01-28T11:00:00", "Find hidden treasures across Zagreb!",
                "Custom Rules", false, true, 10, "Telegram", true,
                realLocation2, fakeLocation2));
        for(Igra game : games) {
            ir.save(game);
        }
    }
    
    public BusinessProfileDetailsDTO getProfileByUsername(String username) {
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
        BusinessProfileDetailsDTO details = new BusinessProfileDetailsDTO();
        details.setUsername(poslovniKorisnik.getUsername());
        //details.setLogo(orgProfil.getCompanyLogos() != null && !orgProfil.getCompanyLogos().isEmpty()
        //    ? orgProfil.getCompanyLogos().get(orgProfil.getCompanyLogos().size() - 1).getImageUrl()
        //    : null); // Uzmi zadnji logo
        details.setLogo(orgProfil.getCompanyLogo());
        details.setCompanyPhone(orgProfil.getCompanyPhone());
        details.setCompanyDes(orgProfil.getCompanyDes());
        details.setCompanyWeb(orgProfil.getCompanyWeb());
        details.setCompanyAddress(orgProfil.getCompanyAdress());

        return details;
    }

    public boolean isValidToken(String authToken) {
        try {
            // Ako `validateJWT` ne baci iznimku, token je valjan
            JwtUtil.validateJWT(authToken);
            return true;
        } catch (RuntimeException e) {
            // Token je nevažeći ili istekao
            return false;
        }
    }
    public boolean isUsernameTaken(String username) {
        return kr.findByUsername(username) != null && (long) kr.findByUsername(username).size() >0;
    }

    public BusinessProfileDetailsDTO updateProfile(String authToken, BusinessProfileUpdateRequestDTO updateRequest) {
        if (!isValidToken(authToken)) {
            throw new SecurityException("Invalid or expired token");
        }
    
        // Dohvati korisničko ime iz tokena
        String username = JwtUtil.validateJWT(authToken).getSubject();

        Korisnik korisnik = kr.findByUsername(username).stream().findFirst().orElse(null);
        PoslovniKorisnik poslovniKorisnik = (PoslovniKorisnik) korisnik;
        OrgProfil orgProfil = poslovniKorisnik.getCompany();

        if (orgProfil == null) {
            throw new IllegalStateException("Business profile not set up");
        }

        // Ažuriraj podatke profila
        //if (updateRequest.getLogo() != null) {
        //    Slika newLogo = new Slika(); // Pretpostavimo da `Slika` ima polje URL
        //    newLogo.setImageUrl(updateRequest.getLogo());
        //    orgProfil.getCompanyLogos().add(newLogo);
        //}
        orgProfil.setCompanyLogo(updateRequest.getLogo());
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
            orgProfil.setCompanyAddress(updateRequest.getCompanyAddress());
        }

        // Spremi ažurirani profil
        kr.save(poslovniKorisnik);

        // Vrati ažurirani profil
        return getProfileByUsername(poslovniKorisnik.getUsername());
    }

    public void createDummyCompany() {
        // Kreiraj dummy profil kompanije
        OrgProfil orgProfil = new OrgProfil();
        orgProfil.setCompanyName("Dummy Company Inc.");
        orgProfil.setCompanyPhone("987-654-3210");
        orgProfil.setCompanyDes("Innovators in the tech industry.");
        orgProfil.setCompanyWeb("https://dummycompany.com");
        orgProfil.setCompanyAddress("456 Innovation Blvd, Tech City");
    
        // Dodaj logo kompanije
        //Slika logo = new Slika();
        //logo.setImageUrl("https://dummycompany.com/logo.png");
        orgProfil.setCompanyLogo("https://dummycompany.com/logo.png");
    
        // Spremi OrgProfil
        or.save(orgProfil); // Ovo osigurava da je OrgProfil spremljen prije reference
    
        // Kreiraj dummy poslovnog korisnika
        PoslovniKorisnik poslovniKorisnik = new PoslovniKorisnik();
        poslovniKorisnik.setUserId(3); // Unikatni ID
        poslovniKorisnik.setUsername("dummyBusinessUser");
        poslovniKorisnik.setPassword("password123");
        poslovniKorisnik.setEmail("dummybusiness@example.com");
        //poslovniKorisnik.setRole("Business");
    
        // Poveži korisnika s profilom
        poslovniKorisnik.setCompany(orgProfil);
    
        // Spremi PoslovniKorisnik
        kr.save(poslovniKorisnik);
    }
}
