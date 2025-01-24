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
    @Autowired
    private IgraService igraService;
    @Autowired
    private PitanjeRepository pitanjeRepository;
    @Autowired
    private PrijavaRepository prijavaRepository;

    @GetMapping("/created")
    public ResponseEntity<?> createdGames(@RequestHeader("Authorization") String tokenToParse) {
        if (tokenToParse == null || !korisnikService.isValidToken(tokenToParse.replace("Bearer ", ""))) {
            System.out.println("Invalid token!");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "Unable to fetch created games. Please try again later."));

        }
        try {
            Claims claim = JwtUtil.validateJWT(tokenToParse.replace("Bearer ", ""));
            String username = claim.getSubject();
            System.out.println("Token valid! User: " + username);
            Korisnik korisnik = korisnikRepository.findByUsername(username).getFirst();

            if (korisnik == null) {
                System.out.println("User not found in database!");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(Map.of("message", "Unable to fetch created games. Please try again later."));
            }

            System.out.println("User found: " + korisnik.getUsername() + " (ID: " + korisnik.getUserId() + ")");
            List<Igra> games = igraRepository.findByCreatedBy(korisnik);
            if (games == null) {
                System.out.println("ERROR: findByCreatedBy() returned null! Converting to empty list.");
                games = new ArrayList<>();
            }
            CreatedGamesDTO fullGames = new CreatedGamesDTO();
            fullGames.setGames(new ArrayList<>());

            for (Igra igra : games) {
                CreatedGamesDTO.CreatedGameDTO gameDTO = new CreatedGamesDTO.CreatedGameDTO();
                gameDTO.setGameId(igra.getId());
                gameDTO.setTitle(igra.getTitle());
                gameDTO.setType(igra instanceof OnlineIgra ? "online"
                              : igra instanceof LokaliziranaIgra ? "local"
                              : igra instanceof TocnoLokacijskaIgra ? "exact" : "unknown");
                gameDTO.setLocation(igra instanceof LokaliziranaIgra ? ((LokaliziranaIgra) igra).getRealLocation()
                                  : igra instanceof TocnoLokacijskaIgra ? ((TocnoLokacijskaIgra) igra).getLocation() : null);
                gameDTO.setTimezone(igra instanceof OnlineIgra ? ((OnlineIgra) igra).getTimezone() : null);
                gameDTO.setAvailability(igra.getAvailability());
                gameDTO.setCreatedBy(username);
                gameDTO.setApplicationRequired(igra.getApplicationRequired());
                gameDTO.setComplexity(igra.getComplexity());
                gameDTO.setEstimatedLength(igra.getEstimatedLength());
                gameDTO.setStartTimestamp(igra.getStartTimestamp());
                gameDTO.setDescription(igra.getDescription());
                gameDTO.setPravilnik(igra.getRuleset());
                gameDTO.setCurrentPlayerCount(igraService.getPlayerCount(korisnik.getUserId()));
                gameDTO.setMaxPlayerCount(igra.getMaxPlayerCount());
                gameDTO.setCommunicationChannel(igra.getCommunicationChannel());
                gameDTO.setHomebrew(igra.getIsHomebrew());

                List<Pitanje> pitanja = pitanjeRepository.findByIdGameId(igra.getId());
                List<String> formQuestions = new ArrayList<>();
                for (Pitanje pitanje : pitanja) {
                    formQuestions.add(pitanje.getId().getQuestionText());
                }
                gameDTO.setFormQuestions(formQuestions);

                fullGames.addGame(gameDTO);
            }
            return ResponseEntity.ok(fullGames);

        } catch (Exception e) {
            System.out.println("EXCEPTION OCCURRED! Check logs for details.");
            e.printStackTrace(); // OVDE se ispisuje puni stack trace u logovima
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "Unable to fetch created games. Please try again later."));
        }
    }

    @GetMapping("/applied")
    public ResponseEntity<?> appliedGames(@RequestHeader("Authorization") String tokenToParse) {
        if (tokenToParse == null || !korisnikService.isValidToken(tokenToParse.replace("Bearer ", ""))) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "Unable to fetch created games. Please try again later."));

        }
        try {
            Claims claim = JwtUtil.validateJWT(tokenToParse.replace("Bearer ", ""));
            String username = claim.getSubject();
            Korisnik korisnik = korisnikRepository.findByUsername(username).getFirst();

            if (korisnik == null) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(Map.of("message", "Unable to fetch created games. Please try again later."));
            }

            List<Prijava> applications = prijavaRepository.findByIdUserId(korisnik.getUserId());

            if (applications.isEmpty()) {
                return ResponseEntity.ok(Map.of("games", new ArrayList<>()));
            }

            // List<Igra> games = igraRepository.findByCreatedBy(korisnik);
            CreatedGamesDTO fullGames = new CreatedGamesDTO();
            fullGames.setGames(new ArrayList<>());

            for (Prijava prijava : applications) {
                Igra igra = igraRepository.findGameById(prijava.getPrijavaId().getGameId()).getFirst();
                if (igra == null)
                    continue;

                CreatedGamesDTO.CreatedGameDTO gameDTO = new CreatedGamesDTO.CreatedGameDTO();
                gameDTO.setGameId(igra.getId());
                gameDTO.setTitle(igra.getTitle());
                gameDTO.setType(igra instanceof OnlineIgra ? "online"
                              : igra instanceof LokaliziranaIgra ? "local"
                              : igra instanceof TocnoLokacijskaIgra ? "exact" : "unknown");
                gameDTO.setLocation(igra instanceof LokaliziranaIgra ? ((LokaliziranaIgra) igra).getRealLocation()
                                  : igra instanceof TocnoLokacijskaIgra ? ((TocnoLokacijskaIgra) igra).getLocation() : null);
                gameDTO.setTimezone(igra instanceof OnlineIgra ? ((OnlineIgra) igra).getTimezone() : "");
                gameDTO.setAvailability(igra.getAvailability());
                gameDTO.setCreatedBy(igra.getCreatedBy().getUsername());
                gameDTO.setApplicationRequired(igra.getApplicationRequired());
                gameDTO.setComplexity(igra.getComplexity());
                gameDTO.setEstimatedLength(igra.getEstimatedLength());
                gameDTO.setStartTimestamp(igra.getStartTimestamp());
                gameDTO.setDescription(igra.getDescription());
                gameDTO.setPravilnik(igra.getRuleset());
                gameDTO.setCurrentPlayerCount(igraService.getPlayerCount(igra.getId()));
                gameDTO.setMaxPlayerCount(igra.getMaxPlayerCount());
                gameDTO.setCommunicationChannel(igra.getCommunicationChannel());
                gameDTO.setHomebrew(igra.getIsHomebrew());
                
                List<Pitanje> pitanja = pitanjeRepository.findByIdGameId(igra.getId());
                List<String> formQuestions = new ArrayList<>();
                for (Pitanje pitanje : pitanja) {
                    formQuestions.add(pitanje.getId().getQuestionText());
                }
                gameDTO.setFormQuestions(formQuestions);

                fullGames.addGame(gameDTO);
            }
            return ResponseEntity.ok(fullGames);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "Unable to fetch created games. Please try again later."));
        }

    }

}
