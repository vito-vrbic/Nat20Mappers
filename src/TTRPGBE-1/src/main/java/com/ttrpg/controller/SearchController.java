package com.ttrpg.controller;


import com.ttrpg.model.Igra;
import com.ttrpg.service.IgraService;
import com.ttrpg.service.SearchRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Timestamp;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/data/search")
public class SearchController {

    @Autowired
    private IgraService igraService;

    

    @PostMapping
    public ResponseEntity<HashMap<String,List<Igra>>> searchIgre22(@RequestBody SearchRequest sr){
    	
    	
    	List<Igra> li= igraService.searchIgraService(sr.getGameTitle());
    	HashMap<String,List<Igra>> hm = new HashMap<>();
    	hm.put("games", li);
    	System.out.println("Ovo se izvršava " + li.size() +" "+sr.getGameTitle());
		return ResponseEntity.ok( hm);
    	
    	
    }
    
    
    /*
    
    public ResponseEntity<List<Igra>> searchIgre(
    	    @RequestParam(required = false) String gameName,            // gameName
    	    @RequestParam(required = false) String type,                // type
    	    @RequestParam(required = false) String location,            // location
    	    @RequestParam(required = false) String availability,        // availability
    	    @RequestParam(required = false) String createdBy,           // createdBy
    	    @RequestParam(required = false) Boolean applicationRequired, // applicationRequired
    	    @RequestParam(required = false) String complexity,          // complexity
    	    @RequestParam(required = false) String estimatedLength,     // estimatedLength
    	    @RequestParam(required = false) String startTimestamp,      // startTimestamp
    	    @RequestParam(required = false) String description,         // description
    	    @RequestParam(required = false) String pravilnik,           // pravilnik
    	    @RequestParam(required = false) Boolean requiresForm,       // requiresForm
    	    @RequestParam(required = false) Integer currentPlayerCount,  // currentPlayerCount
    	    @RequestParam(required = false) Integer maxPlayerCount,      // maxPlayerCount
    	    @RequestParam(required = false) String communicationChannel, // communicationChannel
    	    @RequestParam(required = false) Boolean isHomebrew          // isHomebrew{
){
    	 List<Igra> results = igraService.searchIgre(
    	            gameName, type, location, availability, createdBy, 
    	            applicationRequired, complexity, estimatedLength, 
    	            startTimestamp, description, pravilnik, requiresForm, 
    	            currentPlayerCount, maxPlayerCount, communicationChannel, 
    	            isHomebrew
    	    );

       
    
            return ResponseEntity.ok(results);
        }*/
    }

    /*@GetMapping("/searchByName")
    public List<Igra> searchGamesByName(@RequestParam String name) {
        return IgraService.findByName(name);*/
    

