package com.ttrpg.controller;

import com.ttrpg.model.Igra;
import com.ttrpg.repository.IgraRepository;
import com.ttrpg.repository.KorisnikRepository;
import com.ttrpg.service.KorisnikService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ttrpg.util.JwtUtil;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/games")
public class GameFetchController {
    @Autowired
    KorisnikRepository korisnikRepository;
    KorisnikService korisnikService;
    @Autowired
    private IgraRepository igraRepository;

    @GetMapping("/created")
    public ResponseEntity<?> createdGames(@RequestHeader("Authorization") String tokenToParse) {
        if(tokenToParse== null || !korisnikService.isValidToken(tokenToParse.replace("Bearer ", ""))) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message","Unable to fetch created games. Please try again later." ));

        }
        try {
            Claims claim = JwtUtil.validateJWT(tokenToParse.replace("Bearer ", ""));
            String username = claim.getSubject();
            List<Igra> games = igraRepository.findByCreatedBy(username);
            return ResponseEntity.ok(Map.of(games, games));

        }
        catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message","Unable to fetch created games. Please try again later." ));
        }








    }

}
