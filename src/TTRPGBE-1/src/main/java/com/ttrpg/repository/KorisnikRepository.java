package com.ttrpg.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ttrpg.model.Korisnik;

@Repository

public interface KorisnikRepository extends JpaRepository <Korisnik,Long>{
	
	
     List<Korisnik> findByUsername(String username);
	/*List<Korisnik> findByEmailContaining(String email);
	Optional<Korisnik> findByUsernameAndEmail(String username, String email);*/
	

}
