package com.ttrpg.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ttrpg.model.Korisnik;

@RestController
@RequestMapping("/auth/logout") // Definira osnovnu rutu za odjavu korisnika
public class LogoutController {

    @PostMapping // Obrada POST zahtjeva za odjavu
    public ResponseEntity<?> login(@RequestBody Korisnik korisnik) { // Prijem korisničkih podataka (ako su potrebni)
        return ResponseEntity.ok("Logout Successful"); // Vraća poruku o uspješnoj odjavi
    }
}
