package com.ttrpg.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.util.Map;

import com.ttrpg.model.MapLocation;

@Data
public class CreateGameRequestDTO {
    
    @NotBlank
    private String title;

    @NotBlank
    private String type; // "online", "local", "exact"

    private MapLocation location; // Lokacija je null za online igre

    private String timezone; // Timezone je prazan za local/exact igre

    @NotBlank
    private String availability; // "private" ili "public"

    @NotBlank
    private String createdBy; // "private" ili "business"

    private boolean applicationRequired;

    @NotBlank
    private String complexity; // "low", "medium" ili "high"

    @NotBlank
    private String estimatedLength;

    private String startTimestamp;

    private String description;

    @NotBlank
    private String pravilnik;

    private boolean requiresForm;

    private Map<String, String> formQuestions;

    private int currentPlayerCount = 0;

    private int maxPlayerCount;

    @NotBlank
    private String communicationChannel;

    private boolean isHomebrew;
}
