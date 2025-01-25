package com.ttrpg.dto;

import lombok.Data;

@Data
public class DecisionDTO {

    private Long gameId;
    private int userId;
    private String decision;
    public DecisionDTO(Long gameId, int userId, String decision) {
        this.gameId = gameId;
        this.userId = userId;
        this.decision = decision;
    }
    public DecisionDTO() {
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
	public String getDecision() {
		return decision;
	}
	public void setDecision(String decision) {
		this.decision = decision;
	}
    
    
}
