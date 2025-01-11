package com.ttrpg.controller;


import com.ttrpg.dto.ValidationResponseDTO;
import com.ttrpg.model.Korisnik;
import com.ttrpg.repository.KorisnikRepository;
import com.ttrpg.service.jwtUtil;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;


@RestController
@RequestMapping("/api/auth/verify-token")
public class VerificationController {

    @Autowired
    KorisnikRepository korisnikRepository;
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);   //za praćenje aktivnosti

    @GetMapping
    public ResponseEntity<ValidationResponseDTO> validateSession(@RequestHeader("Authorization") String tokenToParse) {
        logger.info("validating session");
        if(tokenToParse != null && tokenToParse.startsWith("Bearer ")) {      //sve iza Bearer u autorizacijskom dijelu headera je token
            String jwt = tokenToParse.substring(7); //izbacujemo "Bearer "
            logger.info("JWT: {}", jwt);
            try {
                Claims claims = jwtUtil.validateJWT(jwt);      //uključuje podatke poput koji je subjekt tokena, kada ističe,...
                String username = claims.getSubject();          //claims uključuje i username koji je dekodiran iz jwta pa možemo tražiti usera u repozitoriju
                Korisnik korisnik = korisnikRepository.findByUsername(username).getFirst();  //
                ValidationResponseDTO responseDTO = new ValidationResponseDTO("The token is valid", korisnik);
                return new ResponseEntity<>(responseDTO,HttpStatus.OK);    //šaljemo poruku, user data i status

            } catch (RuntimeException e) {

                return new ResponseEntity<>(new ValidationResponseDTO("Invalid or expired token", null) ,HttpStatus.UNAUTHORIZED);
            }



        }
        return new ResponseEntity<>(new ValidationResponseDTO("Token missing", null),HttpStatus.BAD_REQUEST);





    }
}
