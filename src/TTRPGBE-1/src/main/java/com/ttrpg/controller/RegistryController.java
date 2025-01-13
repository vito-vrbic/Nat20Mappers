package com.ttrpg.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ttrpg.model.Korisnik;
import com.ttrpg.service.KorisnikService;
import com.ttrpg.service.LoginResponse;
import com.ttrpg.service.UserData;
import com.ttrpg.service.UserData2;
@RestController
@RequestMapping("api/auth/signup")
public class RegistryController {
	
	
	
	
	    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	    @Autowired
	    private KorisnikService userService;

	    @PostMapping
	    public ResponseEntity<?> Rega(@RequestBody UserData2 ud2) {
	    	boolean imeUnutra =false;
	    	boolean emailUnutra= false;
	       // logger.info("Login attempt for user: {}", korisnik.getUsername());

	      
	        imeUnutra = userService.isUsernameTaken(ud2.getUsername());
	        emailUnutra = userService.isEmailTaken(ud2.getEmail());
	        
	        if(imeUnutra==true) {
	        	
	        	return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username already in use");
	        			//ResponseEntity<>("FAILURE", HttpStatus.BAD_REQUEST);
	        }
	        else if(imeUnutra!=true && emailUnutra==true) {
	        	return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email already in use"); 
	        	
	        }
	        else {
	        	
	        	
	       userService.save(new Korisnik(ud2.getUsername(),ud2.getEmail(), ud2.getPassword(), ud2.getRole(), ud2.getOrganizationName()));
	        	
	        	return new ResponseEntity<>("SUCCESS", HttpStatus.CREATED);
	        	
	        }
	        
	        
	        
	        
	        
	        
	        
	        
	    
	  
	    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*  Address: ./signup-submit
    Type: POST
    Header:
      Content-Type: application/json
      Authorization: Bearer {authToken} (if required for an authenticated API)
    Body:
      {
        "username": "john_doe",
        "email": "john.doe@example.com",
        "password": "password123",
        "role": "business || private"
        "organizationName": "name || null"
      }
    -----
    Expected response:
      SUCCESS (Status Code 201).
      FAILURE (400 Bad Request):
        Body: {
          "message": "Issue to showcase on frontend to user"
        }*/
	
	

}
