package com.ttrpg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ttrpg.model.Korisnik;
import com.ttrpg.repository.KorisnikRepository;

@Service
public class KorisnikService {
	@Autowired
	 KorisnikRepository kr;
	
	
	public boolean authenticate(String username, String password) {
		List<Korisnik> korisnici = kr.findByUsername(username);
		if(korisnici.size() !=0) {
		Korisnik korisnik = kr.findByUsername(username).get(0);
		if(korisnik.getPassword().equals(password)) {
			return true;
		}
		else return false;
		}
		return false;
	}

	
	
	public void sDataLoader () {
		
		
		kr.save(new Korisnik(1, "Marko58","beb1@gmail.com","Apple","Private"));
	    kr.save(new Korisnik(2, "Marko59","beb2@gmail.com","Apple2","Business"));
		
		
	}
}
