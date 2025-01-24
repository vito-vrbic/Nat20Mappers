package com.ttrpg.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.*;

@Entity
@DiscriminatorValue("LokaliziranaIgra") 
public class LokaliziranaIgra extends Igra {
	
	@Column(name="radius")
	private int radiussy;
	@Column(name="center-coordinate", length=20)
	private String centercoordinate;
	@Column(name="coordinate", length=20)
	private String coordinate;
	
	
	
	
	public int getRadiussy() {
		return radiussy;
	}
	public void setRadiussy(int radiussy) {
		this.radiussy = radiussy;
	}
	public String getCentercoordinate() {
		return centercoordinate;
	}
	public void setCentercoordinate(String centercoordinate) {
		this.centercoordinate = centercoordinate;
	}
	public String getCoordinate() {
		return coordinate;
	}
	public void setCoordinate(String coordinate) {
		this.coordinate = coordinate;
	}
	
	
	
	
	
	
	

}
