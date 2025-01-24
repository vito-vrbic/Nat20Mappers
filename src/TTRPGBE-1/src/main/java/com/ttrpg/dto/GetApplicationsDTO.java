package com.ttrpg.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Data
public class GetApplicationsDTO {

    private List<GetApplicationDTO> appliedUsers;

    public void addAppliedUsers(GetApplicationDTO appliedUser) {
        appliedUsers.add(appliedUser);
    }
    public GetApplicationsDTO() {
        appliedUsers = new ArrayList<GetApplicationDTO>();
    }
    @Data
    public static class GetApplicationDTO {
        private Long id;
        private int userId;
        private String username;
        private Map<String, String> answers;

        public void addEntry(String question, String answer) {
            answers.put(question, answer);
        }
        public GetApplicationDTO() {
            answers = new HashMap<>();
        }
    }

}
