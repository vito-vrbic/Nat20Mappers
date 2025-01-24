package com.ttrpg.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

// Ova klasa predstavlja lokaliziranu igru, koja naslijeđuje sve osobine klase Igra
@Setter
@Getter
@Entity
public class LokaliziranaIgra extends Igra {

    // Konstruktor bez argumenata koji poziva konstruktor nadklase
    public LokaliziranaIgra() {
        super();
        // TODO Auto-generated constructor stub
    }

    public LokaliziranaIgra(Long id, String gameName, String availability,
                            Korisnik dungeonMaster, boolean applicationRequired, String complexity,
                            String estimatedLength, String startTimestamp,
                            String description, String ruleset, Boolean requiresForm,
                            Integer maxPlayerCount, String communicationChannel, Boolean isHomebrew,
                            MapLocation realLocation, MapLocation fakeLocation) {
        super(id, gameName, availability, dungeonMaster, applicationRequired,complexity, estimatedLength, startTimestamp, description, ruleset, requiresForm, maxPlayerCount, communicationChannel, isHomebrew);
        this.realLocation = realLocation;
        this.fakeLocation = fakeLocation;
    }
    public LokaliziranaIgra(String gameName, String availability,
                            Korisnik dungeonMaster, String complexity,
                            String estimatedLength, String startTimestamp,
                            String description, String ruleset, Boolean requiresForm, boolean applicationRequired,
                            Integer maxPlayerCount, String communicationChannel, Boolean isHomebrew,
                            MapLocation realLocation, MapLocation fakeLocation) {
        super(gameName, availability, dungeonMaster, applicationRequired, complexity, estimatedLength, startTimestamp, description, ruleset, requiresForm, maxPlayerCount, communicationChannel, isHomebrew);
        this.realLocation = realLocation;
        this.fakeLocation = fakeLocation;
    }

    // Definiranje atributa za lokaliziranu igru
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "lat", column = @Column(name = "realLat")),
            @AttributeOverride(name = "lng", column = @Column(name = "realLng"))
    })
    private MapLocation realLocation;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "lat", column = @Column(name = "fakeLat")),
            @AttributeOverride(name = "lng", column = @Column(name = "fakeLng"))
    })
    private MapLocation fakeLocation;

}
