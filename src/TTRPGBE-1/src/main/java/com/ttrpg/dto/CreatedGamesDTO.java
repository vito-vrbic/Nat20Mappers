package com.ttrpg.dto;

import com.ttrpg.model.MapLocation;
import lombok.Data;

import java.util.List;
@Data
public class CreatedGamesDTO {

    private List<CreatedGameDTO> games;
    public void addGame(CreatedGameDTO game) {
        games.add(game);
    }
    @Data
    public static class CreatedGameDTO {
        private Long gameId;
        private String title;
        private String type;
        private MapLocation location;
        private String timezone;
        private String availability;
        private String createdBy;
        private boolean applicationRequired;
        private String complexity;
        private String estimatedLength;
        private String startTimestamp;
        private String description;
        private String pravilnik;
        private boolean requiresForm;
        private List<String> formQuestions;
        private Long currentPlayerCount;
        private int maxPlayerCount;
        private String communicationChannel;
        private boolean isHomebrew;
    }


}
