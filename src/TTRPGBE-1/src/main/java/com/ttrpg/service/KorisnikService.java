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

	
   
    public boolean isUsernameTaken(String username) {
        List<Korisnik> korisnici = kr.findByUsername(username);
        return !korisnici.isEmpty(); // Returns true if the username is taken
    }

    // Method to check if an email is already taken
    public boolean isEmailTaken(String email) {
        List<Korisnik> korisnici =  kr.findByEmail(email);
        return !korisnici.isEmpty(); // Returns true if the email is taken
    }
	
	
	public <S extends Korisnik > S save(Korisnik k){
		
		return kr.save(k);
	}
	
	
	
	
	
	
	public void sDataLoader () {
		
		
		kr.save(new Korisnik("Marko58","beb1@gmail.com","Apple","Private",null));
	    kr.save(new Korisnik("Marko59","beb2@gmail.com","Apple2","Business","Braća i Buderi, a brothers company"));
		
		
	}
}
