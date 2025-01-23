package com.ttrpg.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.*;

// Ova klasa predstavlja lokaliziranu igru, koja naslijeđuje sve osobine klase Igra
@Entity
@DiscriminatorValue("LokaliziranaIgra")  // Označava vrijednost koja se koristi za identifikaciju ove klase u bazi podataka
public class LokaliziranaIgra extends Igra {

    // Konstruktor bez argumenata koji poziva konstruktor nadklase
    public LokaliziranaIgra() {
        super();
        // TODO Auto-generated constructor stub
    }

    public LokaliziranaIgra(Long id, String gameName, String availability,
                            Korisnik dungeonMaster, String complexity,
                            String estimatedLength, String startTimestamp,
                            String description, String ruleset, Boolean requiresForm,
                            Integer maxPlayerCount, String communicationChannel, Boolean isHomebrew,
                            MapLocation realLocation, MapLocation fakeLocation) {
        super(id, gameName, availability, dungeonMaster, complexity, estimatedLength, startTimestamp, description, ruleset, requiresForm, maxPlayerCount, communicationChannel, isHomebrew);
        this.realLocation = realLocation;
        this.fakeLocation = fakeLocation;
    }
    public LokaliziranaIgra(String gameName, String availability,
                            Korisnik dungeonMaster, String complexity,
                            String estimatedLength, String startTimestamp,
                            String description, String ruleset, Boolean requiresForm,
                            Integer maxPlayerCount, String communicationChannel, Boolean isHomebrew,
                            MapLocation realLocation, MapLocation fakeLocation) {
        super(gameName, availability, dungeonMaster, complexity, estimatedLength, startTimestamp, description, ruleset, requiresForm, maxPlayerCount, communicationChannel, isHomebrew);
        this.realLocation = realLocation;
        this.fakeLocation = fakeLocation;
    }

    // Definiranje atributa za lokaliziranu igru
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "latitude", column = @Column(name = "real-lat")),
            @AttributeOverride(name = "longitude", column = @Column(name = "real-lng"))
    })
    private MapLocation realLocation;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "latitude", column = @Column(name = "fake-lat")),
            @AttributeOverride(name = "longitude", column = @Column(name = "fake-lng"))
    })
    private MapLocation fakeLocation;

    public MapLocation getRealLocation() {
        return realLocation;
    }

    public void setRealLocation(MapLocation realLocation) {
        this.realLocation = realLocation;
    }

    public MapLocation getFakeLocation() {
        return fakeLocation;
    }

    public void setFakeLocation(MapLocation fakeLocation) {
        this.fakeLocation = fakeLocation;
    }
}
