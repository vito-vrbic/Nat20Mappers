package com.ttrpg.dto;

import com.ttrpg.model.MapLocation;
import lombok.Data;

@Data
public class SearchDTO {

    private Long id;
    private String title;
    private String type;
    private MapLocation location;
    private String availability;
    private String createdBy;
    private boolean applicationRequired;

    public SearchDTO(){}
    public SearchDTO(Long id, String title, String type, MapLocation location, String availability, boolean applicationRequired) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.location = location;
        this.availability = availability;
        this.applicationRequired = applicationRequired;
    }
    public SearchDTO(Long id, String title, boolean applicationRequired) {
        this.id = id;
        this.title = title;
        this.applicationRequired = applicationRequired;
    }
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
    
    
}
