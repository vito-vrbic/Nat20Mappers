package com.ttrpg.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.List;
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

    private List<Map<String, String>> formQuestions;

    private int currentPlayerCount = 0;

    private int maxPlayerCount;

    @NotBlank
    private String communicationChannel;

    private boolean isHomebrew;

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

	public List<Map<String, String>> getFormQuestions() {
		return formQuestions;
	}

	public void setFormQuestions(List<Map<String, String>> formQuestions) {
		this.formQuestions = formQuestions;
	}

	public int getCurrentPlayerCount() {
		return currentPlayerCount;
	}

	public void setCurrentPlayerCount(int currentPlayerCount) {
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
