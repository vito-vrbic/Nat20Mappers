package com.ttrpg.service;

import java.security.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ttrpg.model.Igra;
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

    public List<Igra> searchIgre(String title, Integer maxPlayer, Boolean isPrivate, Boolean isHomebrew,
            Boolean requiresForm, String startTs, String estLength, String recExp,
            String commChannel, Integer rulesetId, Integer sysId, Integer gmUserId, String templateLoc) {
      return igraRepository.searchIgre(title, maxPlayer, isPrivate, isHomebrew, requiresForm, startTs,
                     estLength, recExp, commChannel, rulesetId, sysId, gmUserId, templateLoc);
}
    
    
    public void s2DataLoader() {

        // Example Igre (games)
        igraRepository.save(new Igra(1, "Game1", "Description of Game1", 4, true, false, true, 
                                     null, "2-3 hours", "Beginner", 
                                     "Discord", 1, 2, 3, "Location1"));

        igraRepository.save(new Igra(2, "Game2", "Description of Game2", 6, false, true, false, 
        		null, "3-4 hours", "Advanced", 
                                     "Skype", 2, 1, 4, "Location2"));

        igraRepository.save(new Igra(3, "Game3", "Description of Game3", 2, true, false, false, 
        		null, "1 hour", "Beginner", 
                                     "Zoom", 3, 3, 5, "Location3"));

        // Add more sample data as needed
    }
    
    
    
    
    
    
    
}
    
