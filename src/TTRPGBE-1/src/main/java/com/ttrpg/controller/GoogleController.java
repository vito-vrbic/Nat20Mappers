package com.ttrpg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ttrpg.model.Korisnik;
import com.ttrpg.repository.KorisnikRepository;
import com.ttrpg.service.KorisnikService;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class GoogleController {
	@Autowired
	KorisnikRepository kr;
    @PostMapping("/google-login")
    public Map<String, Object> googleLogin(@AuthenticationPrincipal OAuth2User oauth2User) {
    	
    	
        String email = oauth2User.getAttribute("email");
        String name = oauth2User.getAttribute("name");

        boolean userExists = kr.findByUsername(name).size() >0 ;

        if (!userExists) {
            return Map.of("message", "Invalid credentials");
        }

        
        String token = ""+oauth2User.hashCode();
        Korisnik k= kr.findByUsername(name).get(0);
        // Return the expected response
        return Map.of(
            "message", "Google login successful",
            "token", token,
            "userData", Map.of(
                "id", k.getUserId(), 
                "username", name,
                "email", email,
                "role", "user-role" 
            )
        );
    }

    @PostMapping("/google-signin")
    public Map<String, Object> googleSignIn(@AuthenticationPrincipal OAuth2User oauth2User) {
        
        String email = oauth2User.getAttribute("email");
        String name = oauth2User.getAttribute("name");


        boolean userExists = kr.findByUsername(name).size() >0 ;

        if (userExists) {
            return Map.of("message", "User already exists");
        }

       
      kr.save(new Korisnik(name ,email, null, null, null));

      
      Korisnik k= kr.findByUsername(name).get(0);
        
        String token =""+ k.hashCode();

        // Return the expected response
        return Map.of(
            "message", "Google sign-in successful",
            "token", token,
            "userData", Map.of(
                "id", k.getUserId(),
                "username", name,
                "email", email,
                "role", "private" 
            )
        );
    }

  
   
}