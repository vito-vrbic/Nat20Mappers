package com.ttrpg.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;

import com.ttrpg.model.MapLocation;

@Data
public class SaveGameEditRequestDTO {
    private Long id;
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
    private List<Map<String, String>> formQuestions;
    private int currentPlayerCount;
    private int maxPlayerCount;
    private String communicationChannel;
    private boolean isHomebrew;
}
