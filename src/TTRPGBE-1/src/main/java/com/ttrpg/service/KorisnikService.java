package com.ttrpg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ttrpg.model.Korisnik;
import com.ttrpg.repository.KorisnikRepository;

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
}
