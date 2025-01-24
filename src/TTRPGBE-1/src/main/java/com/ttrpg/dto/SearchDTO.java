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
}
