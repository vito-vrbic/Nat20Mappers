package com.ttrpg.controller;


import com.ttrpg.model.Igra;
import com.ttrpg.service.IgraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/api")
public class SearchController {

    @Autowired
    private IgraService IgraService;

    

       @PostMapping("/data/search")
        public ResponseEntity<List<Igra>> searchIgre(
                @RequestParam(required = false) String gameName,
                @RequestParam(required = false) Integer maxPlayer,
                @RequestParam(required = false) Boolean isPrivate,
                @RequestParam(required = false) Boolean isHomebrew,
                @RequestParam(required = false) Boolean requiresForm,
                @RequestParam(required = false) String startTs,
                @RequestParam(required = false) String estLength,
                @RequestParam(required = false) String recExp,
                @RequestParam(required = false) String commChannel,
                @RequestParam(required = false) Integer rulesetId,
                @RequestParam(required = false) Integer sysId,
                @RequestParam(required = false) Integer gmUserId,
                @RequestParam(required = false) String templateLoc) {

            List<Igra> results = IgraService.searchIgre(gameName, maxPlayer, isPrivate, isHomebrew, requiresForm, 
                                                         startTs, estLength, recExp, commChannel, rulesetId, 
                                                         sysId, gmUserId, templateLoc);
            return ResponseEntity.ok(results);
        }
    }

    /*@GetMapping("/searchByName")
    public List<Igra> searchGamesByName(@RequestParam String name) {
        return IgraService.findByName(name);*/
    

