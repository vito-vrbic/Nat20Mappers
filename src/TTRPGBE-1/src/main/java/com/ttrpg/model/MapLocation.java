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

    
	@Override
	public boolean equals(Object o) {
		MapLocation mapLoc= (MapLocation)o;
		return  mapLoc.lat.equals(this.lat) && mapLoc.lng.equals(this.lng);
		
	}
	
	public boolean inRadius(MapLocation ml, Double radiusTrazenja) {
		
		///////////////
		if ( Double.valueOf( radiusTrazenja) ==0.0) {
			radiusTrazenja=100000.0;
		}
		
		
		//////////////
		
		
		/*Preko Haversinove formule */
		final double R = 6371.0;
	    Double lat1 = Math.toRadians(ml.lat);
	    Double   lon1 = Math.toRadians(ml.lng);
	    Double   lat2 = Math.toRadians(this.lat);
	    Double lon2 = Math.toRadians(this.lng);

	        // Haversine formula
	        double dlat = lat2 - lat1;
	        double dlon = lon2 - lon1;
	        double a = Math.sin(dlat / 2) * Math.sin(dlat / 2) +
	                   Math.cos(lat1) * Math.cos(lat2) *
	                   Math.sin(dlon / 2) * Math.sin(dlon / 2);
	        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

	        // Distance in kilometers
	       return   R*c < radiusTrazenja ;
		
		
		
	}
    // Getters and setters
}
