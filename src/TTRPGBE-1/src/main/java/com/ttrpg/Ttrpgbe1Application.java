package com.ttrpg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.ttrpg.model.Korisnik;
import com.ttrpg.repository.KorisnikRepository;
import com.ttrpg.service.KorisnikService;






@SpringBootApplication
public class Ttrpgbe1Application {
	
	
	

	


	public static void main(String[] args) {
		
		 ApplicationContext context = SpringApplication.run(Ttrpgbe1Application.class, args);

	        // Retrieve the KorisnikService bean from the context
	        KorisnikService korisnikService = context.getBean(KorisnikService.class);
		
		
		
		
		
		
		
		korisnikService.sramotaDataLoader();
	}

}
