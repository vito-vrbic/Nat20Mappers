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
}
