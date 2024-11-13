package com.ttrpg.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ttrpg.model.Korisnik;
@RestController
@RequestMapping("api/auth/logout")

public class LogoutController {
	
	@PostMapping
    public ResponseEntity<?> login(@RequestBody Korisnik korisnik) {
		return ResponseEntity.ok("Logout Successful");
}

	
}