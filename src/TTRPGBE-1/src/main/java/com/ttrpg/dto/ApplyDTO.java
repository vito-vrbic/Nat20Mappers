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
}
