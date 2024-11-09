package com.ttrpg.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ttrpg.model.Igra;

@Repository

public interface IgraRepository extends JpaRepository <Igra,Long>{
	
	
/*	List<Korisnik> findByUsername(String username);
	List<Korisnik> findByEmailContaining(String email);
	Optional<Korisnik> findByUsernameAndEmail(String username, String email);
	*/

}