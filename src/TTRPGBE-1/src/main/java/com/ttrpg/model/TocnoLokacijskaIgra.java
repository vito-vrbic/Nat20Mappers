package com.ttrpg.model;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

@Entity
@DiscriminatorValue("TocnoLokacijskaIgra")  // Oznaka tipa igre u tabeli (nasljeđivanje u Single Table strategiji)
public class TocnoLokacijskaIgra extends Igra {
    @Embedded
    private MapLocation location;
    public MapLocation getLocation() {
        return location;
    }
    public void setLocation(MapLocation location) {
        this.location = location;
    }

    public TocnoLokacijskaIgra(String gameName, String availability, Korisnik dungeonMaster, String complexity,
                               String estimatedLength, String startTimestamp, String description, String ruleset, Boolean requiresForm,
                               Integer maxPlayerCount, String communicationChannel, Boolean isHomebrew,
                               MapLocation location) {
        super(gameName, availability, dungeonMaster, complexity, estimatedLength, startTimestamp, description, ruleset, requiresForm, maxPlayerCount, communicationChannel, isHomebrew);
        this.location = location;
    }
}
