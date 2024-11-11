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
@RequestMapping("api/auth/login")
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private KorisnikService userService;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody Korisnik korisnik) {
        logger.info("Login attempt for user: {}", korisnik.getUsername());

        if (userService.authenticate(korisnik.getUsername(), korisnik.getPassword())) {
            // Generate or fetch token (replace with secure method)
            String token = "9472037428374928372387498237498237";

            UserData ud = new UserData(
                String.valueOf(korisnik.getUserId()),
                korisnik.getUsername(),
                korisnik.getEmail(),
                "private",
                null
            );

            LoginResponse response = new LoginResponse("Login successful!", token, ud);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials!");
        }
    }
}
