package com.ttrpg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ttrpg.model.Igra;

@Repository
public interface IgraRepository extends JpaRepository<Igra, Long> {

	@Query("SELECT i FROM Igra i WHERE i.gameName LIKE %:gameName%")
	
	/*
	@Query("SELECT i FROM Igra i WHERE i.gameName IS NULL OR i.gameName LIKE %:gameName%  AND " + 
		       
		       
		       "(:type IS NULL OR i.type LIKE %:type%) AND " + 
		       "(:availability IS NULL OR i.availability = :availability) AND " + 
		       "(:createdBy IS NULL OR i.createdBy LIKE %:createdBy%) AND " + 
		       "(:applicationRequired IS NULL OR i.applicationRequired = :applicationRequired) AND " + 
		       "(:complexity IS NULL OR i.complexity = :complexity) AND " + 
		       "(:estimatedLength IS NULL OR i.estimatedLength = :estimatedLength) AND " + 
		       "(:startTimestamp IS NULL OR i.startTimestamp = :startTimestamp) AND " + 
		       "(:description IS NULL OR i.description LIKE %:description%) AND " + 
		       "(:pravilnik IS NULL OR i.pravilnik LIKE %:pravilnik%) AND " + 
		       "(:requiresForm IS NULL OR i.requiresForm = :requiresForm) AND " + 
		       "(:currentPlayerCount IS NULL OR i.currentPlayerCount = :currentPlayerCount) AND " + 
		       "(:maxPlayerCount IS NULL OR i.maxPlayerCount = :maxPlayerCount) AND " + 
		       "(:communicationChannel IS NULL OR i.communicationChannel LIKE %:communicationChannel%) AND " + 
		       "(:isHomebrew IS NULL OR i.isHomebrew = :isHomebrew) AND " + 
		       "(:location IS NULL OR i.location = :location)")  // longitude filtering in Location*/
   /* List<Igra> searchIgre(
            @Param("gameName") String gameName,
            @Param("type") String type,
            @Param("availability") String availability,
            @Param("createdBy") String createdBy,
            @Param("applicationRequired") Boolean applicationRequired,
            @Param("complexity") String complexity,
            @Param("estimatedLength") String estimatedLength,
            @Param("startTimestamp") String startTimestamp,
            @Param("description") String description,
            @Param("pravilnik") String pravilnik,
            @Param("requiresForm") Boolean requiresForm,
            @Param("currentPlayerCount") Integer currentPlayerCount,
            @Param("maxPlayerCount") Integer maxPlayerCount,
            @Param("communicationChannel") String communicationChannel,
            @Param("isHomebrew") Boolean isHomebrew,
            @Param("location") String location);*/
            
	        List<Igra> findByGameNameContaining(@Param("gameName") String gameName);
			
}
