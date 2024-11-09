package com.ttrpg.controller;


import com.ttrpg.model.Igra;
import com.ttrpg.service.IgraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/games")
public class GameController {

    @Autowired
    private IgraService IgraService;

    // Endpoint for searching games by genre
    @GetMapping("/search")
    public List<Igra> searchGames(@RequestParam String genre) {
        return IgraService.findByGenre(genre);
    }

    // Endpoint for searching games by name
    @GetMapping("/searchByName")
    public List<Igra> searchGamesByName(@RequestParam String name) {
        return IgraService.findByName(name);
    }
}
