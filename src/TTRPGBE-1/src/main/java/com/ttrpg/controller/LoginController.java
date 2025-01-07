package com.ttrpg.controller;

import com.ttrpg.service.KorisnikService;
import com.ttrpg.service.LoginResponse;
import com.ttrpg.service.UserData;
import com.ttrpg.model.Korisnik;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth/login") // Postavlja osnovnu rutu za prijavu korisnika
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class); // Logger za praćenje aktivnosti

    @Autowired
    private KorisnikService userService; // Servis za rukovanje korisničkim operacijama

    @PostMapping // Obrada POST zahtjeva za prijavu
    public ResponseEntity<?> login(@RequestBody Korisnik korisnik) { // Prijem korisničkih podataka iz zahtjeva
        logger.info("Login attempt for user: {}", korisnik.getUsername()); // Bilježi pokušaj prijave u log

        // Provjerava jesu li korisničko ime i lozinka ispravni
        if (userService.authenticate(korisnik.getUsername(), korisnik.getPassword())) {
            
            // Generira privremeni token za korisnika
            String token = "9472037428374928372387498237498237";

            // Stvara podatke o korisniku koji će biti uključeni u odgovor
            UserData ud = new UserData(
                String.valueOf(korisnik.getUserId()),
                korisnik.getUsername(),
                korisnik.getEmail(),
                "private", // Privatnost korisničkih podataka
                null
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
