package com.ttrpg.controller;

import com.ttrpg.dto.LoginRequestDTO;
import com.ttrpg.model.PoslovniKorisnik;
import com.ttrpg.repository.KorisnikRepository;
import com.ttrpg.service.KorisnikService;
import com.ttrpg.service.LoginResponse;
import com.ttrpg.service.UserData;
import com.ttrpg.model.Korisnik;
import com.ttrpg.util.JwtUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/login") // Postavlja osnovnu rutu za prijavu korisnika
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class); // Logger za praćenje aktivnosti

    @Autowired
    private KorisnikService userService; // Servis za rukovanje korisničkim operacijama
    @Autowired
    private KorisnikRepository korisnikRepository;

    @PostMapping // Obrada POST zahtjeva za prijavu
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO korisnik) { // Prijem korisničkih podataka iz zahtjeva
        //backend prima samo korisničko ime i lozinku pa se body sprema u dto umjesto u Korisnik tip podataka
        logger.info("Login attempt for user: {}", korisnik.getUsername()); // Bilježi pokušaj prijave u log
        // Provjerava jesu li korisničko ime i lozinka ispravni
        if (userService.authenticate(korisnik.getUsername(), korisnik.getPassword())) {

            Korisnik trueKorisnik = korisnikRepository.findByUsername(korisnik.getUsername()).get(0); //dto ne sadrži sve podatke pa tražimo puni user
            // Generira privremeni token za korisnika
            String token = JwtUtil.generateJWT(korisnik.getUsername());   //token koji će se spremiti u lokalno spremište
            String companyName = null;
            String role = "private";
            if(trueKorisnik instanceof PoslovniKorisnik ) {
                companyName= ((PoslovniKorisnik)trueKorisnik).getCompany().getCompanyName();  //ako korisnik nije poslovni ime kompanije ostaje null
                role = "business";
            }

            // Stvara podatke o korisniku koji će biti uključeni u odgovor
            UserData ud = new UserData(
                String.valueOf(trueKorisnik.getUserId()),
                trueKorisnik.getUsername(),
                trueKorisnik.getEmail(),
                    role,// Privatnost korisničkih podataka
                    companyName
            );

            // Priprema uspješan odgovor s porukom, tokenom i korisničkim podacima
            LoginResponse response = new LoginResponse("Login successful!", token, ud);
            return ResponseEntity.ok(response); // Vraća uspješan odgovor
        } else {
            // Vraća odgovor sa statusom 401 (Unauthorized) ako su podaci netočni
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials!");
        }
    }
}
