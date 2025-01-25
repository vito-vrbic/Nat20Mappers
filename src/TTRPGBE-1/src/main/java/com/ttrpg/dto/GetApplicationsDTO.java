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
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public int getUserId() {
			return userId;
		}
		public void setUserId(int userId) {
			this.userId = userId;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public Map<String, String> getAnswers() {
			return answers;
		}
		public void setAnswers(Map<String, String> answers) {
			this.answers = answers;
		}
        
        
    }
	public List<GetApplicationDTO> getAppliedUsers() {
		return appliedUsers;
	}
	public void setAppliedUsers(List<GetApplicationDTO> appliedUsers) {
		this.appliedUsers = appliedUsers;
	}

}
