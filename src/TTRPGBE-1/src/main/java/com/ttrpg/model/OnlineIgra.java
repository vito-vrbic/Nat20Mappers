package com.ttrpg.model;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

@Entity
@DiscriminatorValue("OnlineIgra") 
public class OnlineIgra extends Igra{

	
	
	@Column(name="timezone",length=20)
	private String timezone;
}
