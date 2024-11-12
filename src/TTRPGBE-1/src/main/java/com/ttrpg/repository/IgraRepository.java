package com.ttrpg.repository;

import java.security.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ttrpg.model.Igra;

@Repository

public interface IgraRepository extends JpaRepository <Igra,Long>{
	
	
   
	@Query("SELECT i FROM Igra i WHERE " +
		       "(:gameName IS NULL OR i.gameName LIKE %:gameName%) AND " +
		       "(:maxPlayer IS NULL OR i.maxPlayer = :maxPlayer) AND " +
		       "(:isPrivate IS NULL OR i.isPrivate = :isPrivate) AND " +
		       "(:isHomebrew IS NULL OR i.isHomebrew = :isHomebrew) AND " +
		       "(:requiresForm IS NULL OR i.requiresForm = :requiresForm) AND " +
		       "(:startTs IS NULL OR i.startTs >= :startTs) AND " +
		       "(:estLength IS NULL OR i.estLength LIKE %:estLength%) AND " +
		       "(:recExp IS NULL OR i.recExp LIKE %:recExp%) AND " +
		       "(:commChannel IS NULL OR i.commChannel LIKE %:commChannel%) AND " +
		       "(:rulesetId IS NULL OR i.rulesetId = :rulesetId) AND " +
		       "(:sysId IS NULL OR i.sysId = :sysId) AND " +
		       "(:gmUserId IS NULL OR i.gmUserId = :gmUserId) AND " +
		       "(:templateLoc IS NULL OR i.templateLoc LIKE %:templateLoc%)")
		List<Igra> searchIgre(
		        @Param("gameName") String gameName,
		        @Param("maxPlayer") Integer maxPlayer,
		        @Param("isPrivate") Boolean isPrivate,
		        @Param("isHomebrew") Boolean isHomebrew,
		        @Param("requiresForm") Boolean requiresForm,
		        @Param("startTs") String startTs,
		        @Param("estLength") String estLength,
		        @Param("recExp") String recExp,
		        @Param("commChannel") String commChannel,
		        @Param("rulesetId") Integer rulesetId,
		        @Param("sysId") Integer sysId,
		        @Param("gmUserId") Integer gmUserId,
		        @Param("templateLoc") String templateLoc);
}