package com.ttrpg.model;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

@Entity
@DiscriminatorValue("TocnoLokacijskaIgra")  // Oznaka tipa igre u tabeli (nasljeđivanje u Single Table strategiji)
public class TocnoLokacijskaIgra extends Igra {

    @Column(name="Coordinate", length=20)  // Polje za pohranu koordinata u obliku stringa, maksimalne duljine 20 znakova
    private String coordinate;  // Koordinate koje definiraju točno mjesto igre

    // Getter za koordinatu
    public String getCoordinate() {
        return coordinate;  // Vraća vrijednost koordinata
    }

    // Setter za koordinatu
    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;  // Postavlja vrijednost koordinata
    }
}
