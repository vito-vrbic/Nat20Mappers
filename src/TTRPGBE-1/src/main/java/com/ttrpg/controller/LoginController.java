package com.ttrpg.controller;



import com.ttrpg.service.KorisnikService;
import com.ttrpg.model.Korisnik;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    private KorisnikService userService;

    
    @PostMapping
    public String login(@RequestBody Korisnik korisnik) {
        
        if (userService.authenticate(korisnik.getUsername(), korisnik.getPassword())) {
            return "Login successful!";
        } else {
            return "Invalid credentials!";
        }
    }
}

