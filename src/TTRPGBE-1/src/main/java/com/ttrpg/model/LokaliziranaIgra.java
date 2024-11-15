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

    // Definiranje atributa za lokaliziranu igru
    @Column(name = "radius")  // Kolona u bazi koja pohranjuje radijus
    private int radiussy;

    @Column(name = "center-coordinate", length = 20)  // Kolona u bazi koja pohranjuje središnje koordinatne vrijednosti
    private String centercoordinate;

    @Column(name = "coordinate", length = 20)  // Kolona u bazi koja pohranjuje koordinate igre
    private String coordinate;

    // Getter i Setter za radiussy (radijus)
    public int getRadiussy() {
        return radiussy;
    }

    public void setRadiussy(int radiussy) {
        this.radiussy = radiussy;
    }

    // Getter i Setter za centercoordinate (središnje koordinate)
    public String getCentercoordinate() {
        return centercoordinate;
    }

    public void setCentercoordinate(String centercoordinate) {
        this.centercoordinate = centercoordinate;
    }

    // Getter i Setter za coordinate (koordinate)
    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }
}
