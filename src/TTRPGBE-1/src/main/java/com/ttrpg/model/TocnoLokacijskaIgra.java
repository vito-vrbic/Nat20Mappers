package com.ttrpg.model;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@DiscriminatorValue("TocnoLokacijskaIgra")  // Oznaka tipa igre u tabeli (nasljeđivanje u Single Table strategiji)
public class TocnoLokacijskaIgra extends Igra {
    @Embedded
    private MapLocation location;

    public TocnoLokacijskaIgra() {

    }

    public TocnoLokacijskaIgra(String gameName, String availability, Korisnik dungeonMaster, String complexity,
                               String estimatedLength, String startTimestamp, String description, String ruleset, Boolean requiresForm,
                               Integer maxPlayerCount, String communicationChannel, Boolean isHomebrew, boolean applicationRequired,
                               MapLocation location) {
        super(gameName, availability, dungeonMaster,applicationRequired, complexity, estimatedLength, startTimestamp, description, ruleset, requiresForm, maxPlayerCount, communicationChannel, isHomebrew);
        this.location = location;
    }

	public MapLocation getLocation() {
		return location;
	}

	public void setLocation(MapLocation location) {
		this.location = location;
	}
    
    
}
