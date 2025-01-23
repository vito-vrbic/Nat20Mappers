package com.ttrpg.controller;

import java.util.Collections;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ttrpg.dto.CreateGameRequestDTO;
import com.ttrpg.dto.SaveGameEditRequestDTO;
import com.ttrpg.model.Igra;
import com.ttrpg.service.IgraService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/games")
public class GameController {

    private final IgraService gameService;

    public GameController(IgraService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/create-new-game")
    public ResponseEntity<?> createGame(@Valid @RequestBody CreateGameRequestDTO request) {
        try {
            Igra createdGame = gameService.createGame(request);
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