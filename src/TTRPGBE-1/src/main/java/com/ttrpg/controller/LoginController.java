package com.ttrpg.controller;



import com.ttrpg.service.KorisnikService;
import com.ttrpg.service.LoginResponse;
import com.ttrpg.service.UserData;
import com.ttrpg.model.Korisnik;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth/login")
public class LoginController {

    @Autowired
    private KorisnikService userService;

    
    @PostMapping
    public ResponseEntity login(@RequestBody Korisnik korisnik) {
    final Logger logger = LoggerFactory.getLogger(LoginController.class);
    logger.info("Login attempt for user: {}", korisnik.getUsername());
    	 if (userService.authenticate(korisnik.getUsername(), korisnik.getPassword())) {
             // Create a response object with token and user data (modify as needed)
             // Example token for now
             String token = "9472037428374928372387498237498237"; 
                             
             UserData ud= new UserData( 
            		 ""+korisnik.getUserId(),
            		 
            		 korisnik.getUsername(),korisnik.getEmail(),"private",null);
             LoginResponse response = new LoginResponse("Login successful!", token, ud);
             return 
             ResponseEntity.ok(response);
        
         } else {
             // Return a 400 with an error message
        	 return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials!");
            		
         }
    }
}

