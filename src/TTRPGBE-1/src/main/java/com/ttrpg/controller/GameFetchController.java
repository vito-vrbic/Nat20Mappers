package com.ttrpg.controller;

import com.ttrpg.dto.CreatedGamesDTO;
import com.ttrpg.model.*;
import com.ttrpg.repository.IgraRepository;
import com.ttrpg.repository.KorisnikRepository;
import com.ttrpg.repository.PitanjeRepository;
import com.ttrpg.repository.PrijavaRepository;
import com.ttrpg.service.IgraService;
import com.ttrpg.service.KorisnikService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ttrpg.util.JwtUtil;
import org.springframework.web.client.HttpServerErrorException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/games")
public class GameFetchController {
    @Autowired
    KorisnikRepository korisnikRepository;
    @Autowired
    KorisnikService korisnikService;
    @Autowired
    private IgraRepository igraRepository;
    private IgraService igraService;
    @Autowired
    private PitanjeRepository pitanjeRepository;
    @Autowired
    private PrijavaRepository prijavaRepository;

    @GetMapping("/created")
    public ResponseEntity<?> createdGames(@RequestHeader("Authorization") String tokenToParse) {
        if(tokenToParse== null || !korisnikService.isValidToken(tokenToParse.replace("Bearer ", ""))) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message","Unable to fetch created games. Please try again later." ));

        }
        try {
            Claims claim = JwtUtil.validateJWT(tokenToParse.replace("Bearer ", ""));
            String username = claim.getSubject();
            Korisnik korisnik = korisnikRepository.findByUsername(username).getFirst();
            int id = korisnik.getUserId();
            List<Igra> games = igraRepository.findByCreatedBy(korisnik);
            CreatedGamesDTO fullGames = new CreatedGamesDTO();
            fullGames.setGames(new ArrayList<CreatedGamesDTO.CreatedGameDTO>());
            for(Igra igra : games) {
                CreatedGamesDTO.CreatedGameDTO gameDTO = new CreatedGamesDTO.CreatedGameDTO();
                gameDTO.setGameId(igra.getId());
                gameDTO.setTitle(igra.getTitle());
                gameDTO.setDescription(igra.getDescription());
                gameDTO.setAvailability(igra.getAvailability());
                gameDTO.setApplicationRequired(igra.getApplicationRequired());
                gameDTO.setComplexity(igra.getComplexity());
                gameDTO.setEstimatedLength(igra.getEstimatedLength());
                gameDTO.setStartTimestamp(igra.getStartTimestamp());
                gameDTO.setPravilnik(igra.getRuleset());
                gameDTO.setMaxPlayerCount(igra.getMaxPlayerCount());
                gameDTO.setCurrentPlayerCount(igraService.getPlayerCount(id));
                gameDTO.setCommunicationChannel(igra.getCommunicationChannel());
                gameDTO.setHomebrew(igra.getIsHomebrew());
                if(korisnik instanceof PrivatniKorisnik) {
                    gameDTO.setCreatedBy("private");
                }
                else gameDTO.setCreatedBy("business");
                List<Pitanje> pitanja= pitanjeRepository.findByIdGameId(igra.getId());
                gameDTO.setFormQuestions(new ArrayList<String>());
                for(Pitanje pitanje : pitanja) {
                    gameDTO.addQuestion(pitanje.getId().getQuestionText());
                }
                if(igra instanceof OnlineIgra) {
                    gameDTO.setType("online");
                    gameDTO.setTimezone(((OnlineIgra) igra).getTimezone());
                }
                else if(igra instanceof LokaliziranaIgra) {
                    gameDTO.setType("local");
                    gameDTO.setLocation(((LokaliziranaIgra) igra).getRealLocation());
                }
                else if(igra instanceof TocnoLokacijskaIgra) {
                    gameDTO.setType("exact");
                    gameDTO.setLocation(((TocnoLokacijskaIgra) igra).getLocation());
                }
                fullGames.addGame(gameDTO);
            }
            return ResponseEntity.ok(fullGames);

        }
        catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message","Unable to fetch created games. Please try again later." ));
        }
    }
    @GetMapping("/applied")
    public ResponseEntity<?> appliedGames(@RequestHeader("Authorization") String tokenToParse) {
        if(tokenToParse== null || !korisnikService.isValidToken(tokenToParse.replace("Bearer ", ""))) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message","Unable to fetch created games. Please try again later." ));

        }
        try {
            Claims claim = JwtUtil.validateJWT(tokenToParse.replace("Bearer ", ""));
            String username = claim.getSubject();
            Korisnik korisnik = korisnikRepository.findByUsername(username).getFirst();
            int id = korisnik.getUserId();
            List<Prijava> applications = prijavaRepository.findByIdUserId(id);
            //List<Igra> games = igraRepository.findByCreatedBy(korisnik);
            CreatedGamesDTO fullGames = new CreatedGamesDTO();
            fullGames.setGames(new ArrayList<CreatedGamesDTO.CreatedGameDTO>());
            for(Prijava prijava : applications) {
                CreatedGamesDTO.CreatedGameDTO gameDTO = new CreatedGamesDTO.CreatedGameDTO();
                gameDTO.setGameId(prijava.getPrijavaId().getGameId());
                Igra igra = igraRepository.findGameById(prijava.getPrijavaId().getGameId()).getFirst();
                gameDTO.setTitle(igra.getTitle());
                gameDTO.setDescription(igra.getDescription());
                gameDTO.setAvailability(igra.getAvailability());
                gameDTO.setApplicationRequired(igra.getApplicationRequired());
                gameDTO.setComplexity(igra.getComplexity());
                gameDTO.setEstimatedLength(igra.getEstimatedLength());
                gameDTO.setStartTimestamp(igra.getStartTimestamp());
                gameDTO.setPravilnik(igra.getRuleset());
                gameDTO.setMaxPlayerCount(igra.getMaxPlayerCount());
                gameDTO.setCurrentPlayerCount(igraService.getPlayerCount(id));
                gameDTO.setCommunicationChannel(igra.getCommunicationChannel());
                gameDTO.setHomebrew(igra.getIsHomebrew());
                if(korisnik instanceof PrivatniKorisnik) {
                    gameDTO.setCreatedBy("private");
                }
                else gameDTO.setCreatedBy("business");
                List<Pitanje> pitanja= pitanjeRepository.findByIdGameId(igra.getId());
                gameDTO.setFormQuestions(new ArrayList<String>());
                for(Pitanje pitanje : pitanja) {
                    gameDTO.addQuestion(pitanje.getId().getQuestionText());
                }
                if(igra instanceof OnlineIgra) {
                    gameDTO.setType("online");
                    gameDTO.setTimezone(((OnlineIgra) igra).getTimezone());
                }
                else if(igra instanceof LokaliziranaIgra) {
                    gameDTO.setType("local");
                    gameDTO.setLocation(((LokaliziranaIgra) igra).getRealLocation());
                }
                else if(igra instanceof TocnoLokacijskaIgra) {
                    gameDTO.setType("exact");
                    gameDTO.setLocation(((TocnoLokacijskaIgra) igra).getLocation());
                }
                fullGames.addGame(gameDTO);
            }
            return ResponseEntity.ok(fullGames);

        }
        catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message","Unable to fetch created games. Please try again later." ));
        }

    }

}
