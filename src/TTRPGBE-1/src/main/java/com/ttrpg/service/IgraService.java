package com.ttrpg.service;

import java.security.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ttrpg.model.Igra;
import com.ttrpg.model.MapLocation;
import com.ttrpg.repository.IgraRepository;
@Service
public class IgraService {

	public List<Igra> findByGenre(String genre) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Igra> findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

    @Autowired
    private IgraRepository igraRepository;
/*
    public List<Igra> searchIgre(String title, Integer maxPlayer, Boolean isPrivate, Boolean isHomebrew,
            Boolean requiresForm, String startTs, String estLength, String recExp,
            String commChannel, Integer rulesetId, Integer sysId, Integer gmUserId, String templateLoc,
            String location) {
return igraRepository.searchIgre(title,      // gameName
                   null,       // type (if not filtering)
                   null,       // availability (if not filtering)
                   null,       // createdBy (if not filtering)
                   isPrivate,  // applicationRequired -> 'isPrivate'
                   null,       // complexity (if not filtering)
                   estLength,  // estimatedLength
                   startTs,    // startTimestamp
                   recExp,     // description (recExp here matches description)
                   null,       // pravilnik (if not filtering)
                   requiresForm,  // requiresForm
                   null,       // currentPlayerCount (if not filtering)
                   maxPlayer,  // maxPlayerCount
                   commChannel, // communicationChannel
                   isHomebrew,  // isHomebrew
                   location);
}*/

    
    public void s2DataLoader() {

    	Igra game1 = new Igra(
    		    1L, "Game 1", "online",new MapLocation(45.8131, 15.978), "public", "user1", 
    		    true, "Medium", "2 hours", "2024-11-10T15:00:00Z", "A fun and engaging online strategy game.", 
    		    "Rules: Players must strategize to defeat opponents.", true, 5, 20, "Discord", false);

    		Igra game2 = new Igra(
    		    2L, "Game 2", "offline", new MapLocation(44.8131, 16.978), "private", "user2", 
    		    false, "Easy", "1 hour", "2024-11-12T18:00:00Z", "A quick and fun card game.", 
    		    "Rules: Be the first to get rid of all your cards.", false, 3, 10, "Zoom", true);

    		Igra game3 = new Igra(
    		    3L, "Game 3", "online", new MapLocation (46.8131, 17.978), "public", "user3", 
    		    true, "Hard", "3 hours", "2024-11-14T16:00:00Z", "A complex online strategy game with multiple factions.", 
    		    "Rules: Work with your team to control the board.", true, 10, 30, "Discord", false);

    		
    	
    	
    	
    	
    	
    	
    	
        // Example Igre (games)
        igraRepository.save(game1);

        igraRepository.save(game2);

        igraRepository.save(game3);

        // Add more sample data as needed
    }

/*	public List<Igra> searchIgre(String gameName, String type, String location, String availability, String createdBy,
			Boolean applicationRequired, String complexity, String estimatedLength, String startTimestamp,
			String description, String pravilnik, Boolean requiresForm, Integer currentPlayerCount,
			Integer maxPlayerCount, String communicationChannel, Boolean isHomebrew) {
		// TODO Auto-generated method stub
		return null;}*/
	
	
	public List<Igra> searchIgraService (String gameName ){
		return igraRepository.findByGameNameContaining(gameName);
		
	}

	
    
    
    
    
    
    
    
}
    
