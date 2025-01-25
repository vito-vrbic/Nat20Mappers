package com.ttrpg.dto;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class ApplyDTO {
    private Long gameId;
    private int userId;
    private Map<String, String> answersToQuestions;

    public ApplyDTO() {
        answersToQuestions = new HashMap<String, String>();
    }

	public Long getGameId() {
		return gameId;
	}

	public void setGameId(Long gameId) {
		this.gameId = gameId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Map<String, String> getAnswersToQuestions() {
		return answersToQuestions;
	}

	public void setAnswersToQuestions(Map<String, String> answersToQuestions) {
		this.answersToQuestions = answersToQuestions;
	}
    
    
}
