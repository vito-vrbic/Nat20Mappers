package com.ttrpg.controller;


import com.ttrpg.dto.DecisionDTO;
import com.ttrpg.model.Korisnik;
import com.ttrpg.model.Prijava;
import com.ttrpg.model.PrivatniKorisnik;
import com.ttrpg.repository.KorisnikRepository;
import com.ttrpg.repository.PrijavaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/application-decision")
public class ApplicationDecisionController {

    @Autowired
    private PrijavaRepository prijavaRepository;
    @Autowired
    private KorisnikRepository korisnikRepository;

    @PostMapping
    public ResponseEntity<?> applicationDecision(@RequestBody DecisionDTO dto) {
    	
    	
    	
        try {
            Korisnik korisnik = korisnikRepository.findByUserId(dto.getUserId()).get(0);
            if(!(korisnik instanceof PrivatniKorisnik)) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(Map.of("message", "Failed to send decision"));
            }
            else {
                PrivatniKorisnik privatniKorisnik = (PrivatniKorisnik) korisnik;
                Prijava prijava = prijavaRepository.findByGameIdAndPrivateUser(dto.getGameId(), privatniKorisnik).get(0);
                if(dto.getDecision().equalsIgnoreCase("Accept") || dto.getDecision().equalsIgnoreCase("Deny")) {
                    if(dto.getDecision().equalsIgnoreCase("Accept"))
                        prijava.setStatus("Accepted");
                    else prijava.setStatus("Denied");
                    prijavaRepository.save(prijava);
                }
                else {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body(Map.of("message", "Failed to send decision"));
                }

            }

        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "Failed to send decision"));
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("message", "Failed to send decision"));
    }

	public PrijavaRepository getPrijavaRepository() {
		return prijavaRepository;
	}

	public void setPrijavaRepository(PrijavaRepository prijavaRepository) {
		this.prijavaRepository = prijavaRepository;
	}

	public KorisnikRepository getKorisnikRepository() {
		return korisnikRepository;
	}

	public void setKorisnikRepository(KorisnikRepository korisnikRepository) {
		this.korisnikRepository = korisnikRepository;
	}
}
