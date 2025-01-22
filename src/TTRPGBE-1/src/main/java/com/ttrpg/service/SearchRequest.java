package com.ttrpg.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ttrpg.model.MapLocation;

public class SearchRequest {

	@JsonProperty("gameTitle")
	private String gameTitle;

	@JsonProperty("gameType")
	private String gameType;

	@JsonProperty("includeFullGames")
	private Boolean includeFullGames;

	@JsonProperty("applicationRequired")
	private Boolean applicationRequired;

	@JsonProperty("includeUserMadeGames")
	private Boolean includeUserMadeGames;

	@JsonProperty("includeBusinessMadeGames")
	private Boolean includeBusinessMadeGames;

	@JsonProperty("gameAvailability")
	private String gameAvailability;

	@JsonProperty("mapLocation")
	private MapLocation mapLocation;

	@JsonProperty("radius")
	private String radius;

	@JsonProperty("page")
	private Integer page;

	public String getGameTitle() {
		return gameTitle;
	}

	public void setGameTitle(String gameTitle) {
		this.gameTitle = gameTitle;
	}

	public String getGameType() {
		return gameType;
	}

	public void setGameType(String gameType) {
		this.gameType = gameType;
	}

	public Boolean getIncludeFullGames() {
		return includeFullGames;
	}

	public void setIncludeFullGames(Boolean includeFullGames) {
		this.includeFullGames = includeFullGames;
	}

	public Boolean getApplicationRequired() {
		return applicationRequired;
	}

	public void setApplicationRequired(Boolean applicationRequired) {
		this.applicationRequired = applicationRequired;
	}

	public Boolean getIncludeUserMadeGames() {
		return includeUserMadeGames;
	}

	public void setIncludeUserMadeGames(Boolean includeUserMadeGames) {
		this.includeUserMadeGames = includeUserMadeGames;
	}

	public Boolean getIncludeBusinessMadeGames() {
		return includeBusinessMadeGames;
	}

	public void setIncludeBusinessMadeGames(Boolean includeBusinessMadeGames) {
		this.includeBusinessMadeGames = includeBusinessMadeGames;
	}

	public String getGameAvailability() {
		return gameAvailability;
	}

	public void setGameAvailability(String gameAvailability) {
		this.gameAvailability = gameAvailability;
	}

	public MapLocation getMapLocation() {
		return mapLocation;
	}

	public void setMapLocation(MapLocation mapLocation) {
		this.mapLocation = mapLocation;
	}

	public String getRadius() {
		return radius;
	}

	public void setRadius(String radius) {
		this.radius = radius;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public SearchRequest(String gameTitle, String gameType, Boolean includeFullGames, Boolean applicationRequired,
			Boolean includeUserMadeGames, Boolean includeBusinessMadeGames, String gameAvailability,
			MapLocation mapLocation,
			String radius, Integer page) {
		super();
		this.gameTitle = gameTitle;
		this.gameType = gameType;
		this.includeFullGames = includeFullGames;
		this.applicationRequired = applicationRequired;
		this.includeUserMadeGames = includeUserMadeGames;
		this.includeBusinessMadeGames = includeBusinessMadeGames;
		this.gameAvailability = gameAvailability;
		this.mapLocation = mapLocation;
		this.radius = radius;
		this.page = page;
	}

}