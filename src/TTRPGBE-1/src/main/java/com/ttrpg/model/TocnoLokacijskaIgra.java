package com.ttrpg.model;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

@Entity
@DiscriminatorValue("TocnoLokacijskaIgra") 
public class TocnoLokacijskaIgra extends Igra{

	@Column(name="Coordinate", length=20)
	private String coordinate;
}
