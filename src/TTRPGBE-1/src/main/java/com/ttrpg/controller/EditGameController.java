package com.ttrpg.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ttrpg.model.Igra;
import com.ttrpg.service.IgraCatcher;
import com.ttrpg.service.IgraService;
import com.ttrpg.service.KorisnikService;
import com.ttrpg.service.UserData2;

@RestController
@RequestMapping("./game-to-edit")

public class EditGameController {
	
	
	 private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	    @Autowired
	    private IgraService is;

	    @PostMapping
	    public ResponseEntity<?> Dohvati(@RequestBody Long id) {
	    	try		{
	    		
	    	  Igra i= 	is.findById(  id ).orElse(new Igra()) ;
	    	  
	    	  return (ResponseEntity<?>) ResponseEntity.ok().body( new IgraCatcher(
	    			  
	    			  )
	    			  
	    			  
	    			  
	    			  
	    			  
	    			  );
	    		
	    	}
	    	catch(Exception e) {
	    		
	    		return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
	    		
	    	}
	    	
	    	
	    	
	    	
	
	    }
	
/*
	Address: ./game-to-edit
	Type: GET
	Header:
	Content-Type: application/json
	Authorization: Bearer {authToken} (if required for an authenticated API)
	Body:
	{
	"id": gameId
	}
	-----
	Expected response:
	SUCCESS (Status Code 200):
	Body: {
	"id": gameId,
	"title": "gameTitle",
	"type": "online || local || exact",
	"location": { "lat": 45.813 || null, "lng": 15.978 || null },
	"timezone": timeZone || "",
	"availability": "private || public",
	"createdBy": "private || business",
	"applicationRequired": true || false,
	"complexity": "low || medium || high",
	"estimatedLength": "estimatedLength",
	"startTimestamp": "startTimestamp || "" ",
	"description": "description || "" ",
	"pravilnik": "rules",
	"requiresForm": true || false,
	"formQuestions": { "question": "User_question"},
	"currentPlayerCount": "Player count",
	"maxPlayerCount": "maxNumOfPlayers || "" "
	"communicationChannel": "communicationChannel",
	"isHomebrew": true || false
	}
	FAILURE (500):
	Body: {
	"message": "Failed to fetch data"
	}
	
	*/
}
