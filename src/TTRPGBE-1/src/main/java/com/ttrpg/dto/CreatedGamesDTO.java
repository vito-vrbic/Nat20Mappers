package com.ttrpg.dto;

import com.ttrpg.model.MapLocation;
import lombok.Data;

import java.util.ArrayList;
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
		public Long getGameId() {
			return gameId;
		}
		public void setGameId(Long gameId) {
			this.gameId = gameId;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public MapLocation getLocation() {
			return location;
		}
		public void setLocation(MapLocation location) {
			this.location = location;
		}
		public String getTimezone() {
			return timezone;
		}
		public void setTimezone(String timezone) {
			this.timezone = timezone;
		}
		public String getAvailability() {
			return availability;
		}
		public void setAvailability(String availability) {
			this.availability = availability;
		}
		public String getCreatedBy() {
			return createdBy;
		}
		public void setCreatedBy(String createdBy) {
			this.createdBy = createdBy;
		}
		public boolean isApplicationRequired() {
			return applicationRequired;
		}
		public void setApplicationRequired(boolean applicationRequired) {
			this.applicationRequired = applicationRequired;
		}
		public String getComplexity() {
			return complexity;
		}
		public void setComplexity(String complexity) {
			this.complexity = complexity;
		}
		public String getEstimatedLength() {
			return estimatedLength;
		}
		public void setEstimatedLength(String estimatedLength) {
			this.estimatedLength = estimatedLength;
		}
		public String getStartTimestamp() {
			return startTimestamp;
		}
		public void setStartTimestamp(String startTimestamp) {
			this.startTimestamp = startTimestamp;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public String getPravilnik() {
			return pravilnik;
		}
		public void setPravilnik(String pravilnik) {
			this.pravilnik = pravilnik;
		}
		public boolean isRequiresForm() {
			return requiresForm;
		}
		public void setRequiresForm(boolean requiresForm) {
			this.requiresForm = requiresForm;
		}
		public List<String> getFormQuestions() {
			return formQuestions;
		}
		public void setFormQuestions(List<String> formQuestions) {
			this.formQuestions = formQuestions;
		}
		public Long getCurrentPlayerCount() {
			return currentPlayerCount;
		}
		public void setCurrentPlayerCount(Long currentPlayerCount) {
			this.currentPlayerCount = currentPlayerCount;
		}
		public int getMaxPlayerCount() {
			return maxPlayerCount;
		}
		public void setMaxPlayerCount(int maxPlayerCount) {
			this.maxPlayerCount = maxPlayerCount;
		}
		public String getCommunicationChannel() {
			return communicationChannel;
		}
		public void setCommunicationChannel(String communicationChannel) {
			this.communicationChannel = communicationChannel;
		}
		public boolean isHomebrew() {
			return isHomebrew;
		}
		public void setHomebrew(boolean isHomebrew) {
			this.isHomebrew = isHomebrew;
		}
        
        
    }
	public List<CreatedGameDTO> getGames() {
		return games;
	}
	public void setGames(List<CreatedGameDTO> games) {
		this.games = games;
	}


}
