package com.ttrpg.controller;

import com.ttrpg.dto.CreateGameRequestDTO;
import com.ttrpg.dto.CreatedGamesDTO;
import com.ttrpg.dto.SaveGameEditRequestDTO;
import com.ttrpg.model.*;
import com.ttrpg.repository.IgraRepository;
import com.ttrpg.repository.KorisnikRepository;
import com.ttrpg.repository.PitanjeRepository;
import com.ttrpg.repository.PrijavaRepository;
import com.ttrpg.service.IgraService;
import com.ttrpg.service.KorisnikService;
import io.jsonwebtoken.Claims;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ttrpg.util.JwtUtil;
import org.springframework.web.client.HttpServerErrorException;

import java.util.ArrayList;
import java.util.Collections;
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
    @Autowired
    private IgraService gameService;
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class); // Logger za praćenje aktivnosti



    @GetMapping("/created")
    public ResponseEntity<?> createdGames(@RequestHeader("Authorization") String tokenToParse) {
        if (tokenToParse == null || !korisnikService.isValidToken(tokenToParse.replace("Bearer ", ""))) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", "Unable to fetch created games. Please try again later."));

        }
        try {
            logger.info("Fetching created games");
            Claims claim = JwtUtil.validateJWT(tokenToParse.replace("Bearer ", ""));
            String username = claim.getSubject();
            logger.info("Fetching created games for user {}", username);
            Korisnik korisnik = korisnikRepository.findByUsername(username).getFirst();
            int id = korisnik.getUserId();
            if (igraRepository.findByCreatedBy(korisnik) == null) {
                CreatedGamesDTO response = new CreatedGamesDTO();
                return ResponseEntity.ok(response);
            }
            List<Igra> games = igraRepository.findByCreatedBy(korisnik);
            CreatedGamesDTO fullGames = new CreatedGamesDTO();
            fullGames.setGames(new ArrayList<CreatedGamesDTO.CreatedGameDTO>());
            if(games.isEmpty()) return ResponseEntity.ok(fullGames);
            logger.info("iterating through list");

            for (Igra igra : games) {
                CreatedGamesDTO.CreatedGameDTO gameDTO = new CreatedGamesDTO.CreatedGameDTO();
                logger.info("dto created");
                gameDTO.setGameId(igra.getId());
                gameDTO.setTitle(igra.getTitle());
                if(igra.getDescription()!= null)gameDTO.setDescription(igra.getDescription());
                gameDTO.setAvailability(igra.getAvailability());
                gameDTO.setApplicationRequired(igra.getApplicationRequired());
                gameDTO.setComplexity(igra.getComplexity());
                gameDTO.setEstimatedLength(igra.getEstimatedLength());
                if(igra.getStartTimestamp()!= null)gameDTO.setStartTimestamp(igra.getStartTimestamp());
                gameDTO.setPravilnik(igra.getRuleset());
                gameDTO.setMaxPlayerCount(igra.getMaxPlayerCount());
                logger.info("getting player count");
                //gameDTO.setCurrentPlayerCount(igraService.getPlayerCount(igra.getId()));
                gameDTO.setCurrentPlayerCount(0L);
                gameDTO.setCommunicationChannel(igra.getCommunicationChannel());
                gameDTO.setHomebrew(igra.getIsHomebrew());
                logger.info("checking instances");
                if (korisnik instanceof PrivatniKorisnik) {
                    gameDTO.setCreatedBy("private");
                } else gameDTO.setCreatedBy("business");
                List<Pitanje> pitanja = pitanjeRepository.findByIdGameId(igra.getId());
                gameDTO.setFormQuestions(new ArrayList<String>());
                for (Pitanje pitanje : pitanja) {
                    gameDTO.addQuestion(pitanje.getId().getQuestionText());
                }
                if (igra instanceof OnlineIgra) {
                    gameDTO.setType("online");
                    gameDTO.setTimezone(((OnlineIgra) igra).getTimezone());
                } else if (igra instanceof LokaliziranaIgra) {
                    gameDTO.setType("local");
                    gameDTO.setLocation(((LokaliziranaIgra) igra).getRealLocation());
                } else if (igra instanceof TocnoLokacijskaIgra) {
                    gameDTO.setType("exact");
                    gameDTO.setLocation(((TocnoLokacijskaIgra) igra).getLocation());
                }
                logger.info("adding");
                fullGames.addGame(gameDTO);
            }
            return ResponseEntity.ok(fullGames);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", "Unable to fetch created games. Please try again later."));
        }
    }

    @GetMapping("/applied")
    public ResponseEntity<?> appliedGames(@RequestHeader("Authorization") String tokenToParse) {
        if (tokenToParse == null || !korisnikService.isValidToken(tokenToParse.replace("Bearer ", ""))) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", "Unable to fetch created games. Please try again later."));

        }
        try {
            Claims claim = JwtUtil.validateJWT(tokenToParse.replace("Bearer ", ""));
            String username = claim.getSubject();
            Korisnik korisnik = korisnikRepository.findByUsername(username).getFirst();
            int id = korisnik.getUserId();
            List<Prijava> applications = prijavaRepository.findByIdUserId(id);
            if (prijavaRepository.findByIdUserId(id) == null) {
                CreateGameRequestDTO response = new CreateGameRequestDTO();
                return ResponseEntity.ok(response);
            }
            //List<Igra> games = igraRepository.findByCreatedBy(korisnik);
            CreatedGamesDTO fullGames = new CreatedGamesDTO();
            fullGames.setGames(new ArrayList<CreatedGamesDTO.CreatedGameDTO>());
            for (Prijava prijava : applications) {
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
                if (korisnik instanceof PrivatniKorisnik) {
                    gameDTO.setCreatedBy("private");
                } else gameDTO.setCreatedBy("business");
                List<Pitanje> pitanja = pitanjeRepository.findByIdGameId(igra.getId());
                gameDTO.setFormQuestions(new ArrayList<String>());
                for (Pitanje pitanje : pitanja) {
                    gameDTO.addQuestion(pitanje.getId().getQuestionText());
                }
                if (igra instanceof OnlineIgra) {
                    gameDTO.setType("online");
                    gameDTO.setTimezone(((OnlineIgra) igra).getTimezone());
                } else if (igra instanceof LokaliziranaIgra) {
                    gameDTO.setType("local");
                    gameDTO.setLocation(((LokaliziranaIgra) igra).getRealLocation());
                } else if (igra instanceof TocnoLokacijskaIgra) {
                    gameDTO.setType("exact");
                    gameDTO.setLocation(((TocnoLokacijskaIgra) igra).getLocation());
                }
                fullGames.addGame(gameDTO);
            }
            return ResponseEntity.ok(fullGames);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", "Unable to fetch created games. Please try again later."));
        }


    }

    @PostMapping("/create-new-game")
    public ResponseEntity<?> createGame(@Valid @RequestBody CreateGameRequestDTO request, @RequestHeader("Authorization") String tokenToParse) {

        if (tokenToParse == null || !korisnikService.isValidToken(tokenToParse.replace("Bearer ", ""))) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", "Cannot find the user"));
        }
        Korisnik korisnik;
        try {
            Claims claim = JwtUtil.validateJWT(tokenToParse.replace("Bearer ", ""));
            String username = claim.getSubject();
            korisnik = korisnikRepository.findByUsername(username).getFirst();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "Failed to find the user"));
        }

        try {
            Igra createdGame = gameService.createGame(request, korisnik);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdGame);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "Failed to create new game"));
        }
    }


    @PostMapping("/save-edit")
    public ResponseEntity<?> saveGameEdit(@RequestBody SaveGameEditRequestDTO request,
                                          @RequestHeader(value = "Authorization", required = false) String authToken) {
        try {
            gameService.saveGameEdit(request);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            e.printStackTrace(); // Dodano za ispisivanje greške u konzolu
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("message", "Failed to save changes"));
        }
    }
}

