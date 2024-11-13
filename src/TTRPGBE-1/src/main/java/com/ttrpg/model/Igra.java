package com.ttrpg.model;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;
@Entity
@Table(name = "Igra")  // The name of the table in the database
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DTYPE", discriminatorType = DiscriminatorType.STRING)
public class Igra {

	
	
	
	

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "game_id")
	    
	    private Long id;

	    @JsonProperty("title")
	    private String gameName;

	    @JsonProperty("type")
	    private String type;

	    @Embedded
	    private MapLocation location;

	    @JsonProperty("availability")
	    private String availability;

	    @JsonProperty("createdBy")
	    private String createdBy;

	    @JsonProperty("applicationRequired")
	    private Boolean applicationRequired;

	    @JsonProperty("complexity")
	    private String complexity;

	    @JsonProperty("estimatedLength")
	    private String estimatedLength;

	    @JsonProperty("startTimestamp")
	    private String startTimestamp;

	    @JsonProperty("description")
	    private String description;

	    @JsonProperty("pravilnik")
	    private String pravilnik;

	    @JsonProperty("requiresForm")
	    private Boolean requiresForm;

	    @JsonProperty("currentPlayerCount")
	    private Integer currentPlayerCount;

	    @JsonProperty("maxPlayerCount")
	    private Integer maxPlayerCount;

	    @JsonProperty("communicationChannel")
	    private String communicationChannel;

	    @JsonProperty("isHomebrew")
	    private Boolean isHomebrew;

	    // Getters and Setters

	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public String getTitle() {
	        return gameName;
	    }

	    public void setGameName(String gameName) {
	        this.gameName = gameName;
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

	    public Boolean getApplicationRequired() {
	        return applicationRequired;
	    }

	    public void setApplicationRequired(Boolean applicationRequired) {
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

	    public Boolean getRequiresForm() {
	        return requiresForm;
	    }

	    public void setRequiresForm(Boolean requiresForm) {
	        this.requiresForm = requiresForm;
	    }

	    public Integer getCurrentPlayerCount() {
	        return currentPlayerCount;
	    }

	    public void setCurrentPlayerCount(Integer currentPlayerCount) {
	        this.currentPlayerCount = currentPlayerCount;
	    }

	    public Integer getMaxPlayerCount() {
	        return maxPlayerCount;
	    }

	    public void setMaxPlayerCount(Integer maxPlayerCount) {
	        this.maxPlayerCount = maxPlayerCount;
	    }

	    public String getCommunicationChannel() {
	        return communicationChannel;
	    }

	    public void setCommunicationChannel(String communicationChannel) {
	        this.communicationChannel = communicationChannel;
	    }

	    public Boolean getIsHomebrew() {
	        return isHomebrew;
	    }

	    public void setIsHomebrew(Boolean isHomebrew) {
	        this.isHomebrew = isHomebrew;
	    }
	
	    
	    
	    
	    
	    
	 public Igra(Long id, String gameName, String type, MapLocation location, String availability, String createdBy,
				Boolean applicationRequired, String complexity, String estimatedLength, String startTimestamp,
				String description, String pravilnik, Boolean requiresForm, Integer currentPlayerCount,
				Integer maxPlayerCount, String communicationChannel, Boolean isHomebrew) {
			super();
			this.id = id;
			this.gameName = gameName;
			this.type = type;
			this.location = location;
			this.availability = availability;
			this.createdBy = createdBy;
			this.applicationRequired = applicationRequired;
			this.complexity = complexity;
			this.estimatedLength = estimatedLength;
			this.startTimestamp = startTimestamp;
			this.description = description;
			this.pravilnik = pravilnik;
			this.requiresForm = requiresForm;
			this.currentPlayerCount = currentPlayerCount;
			this.maxPlayerCount = maxPlayerCount;
			this.communicationChannel = communicationChannel;
			this.isHomebrew = isHomebrew;
		}

	public Igra() {}

}
