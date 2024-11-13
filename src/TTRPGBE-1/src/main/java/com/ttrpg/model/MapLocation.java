package com.ttrpg.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;


@Embeddable
public class MapLocation {
	
	
    private Double lat;
    private Double lng;
	
	public Double getLat() {
		return lat;
	}
	public void setLat(Double lat) {
		this.lat = lat;
	}
	public Double getLng() {
		return lng;
	}
	public void setLng(Double lng) {
		this.lng = lng;
	}
	public MapLocation(Double lat, Double lng) {
		super();
		
		this.lat = lat;
		this.lng = lng;
	}
	
	public MapLocation() {
		
	}

    
	
	
    // Getters and setters
}
