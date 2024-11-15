package com.ttrpg.model;

import jakarta.persistence.Embeddable;

@Embeddable  // Ova anotacija označava da je MapLocation ugrađeni tip (embeddable) koji se koristi unutar drugih entiteta
public class MapLocation {

    private Double lat;  // Skladišti širinu geografske lokacije
    private Double lng;  // Skladišti dužinu geografske lokacije

    // Getter za lat (širinu)
    public Double getLat() {
        return lat;
    }

    // Setter za lat (širinu)
    public void setLat(Double lat) {
        this.lat = lat;
    }

    // Getter za lng (dužinu)
    public Double getLng() {
        return lng;
    }

    // Setter za lng (dužinu)
    public void setLng(Double lng) {
        this.lng = lng;
    }

    // Konstruktor koji prima lat i lng
    public MapLocation(Double lat, Double lng) {
        super();
        this.lat = lat;
        this.lng = lng;
    }

    // Podrazumijevani konstruktor
    public MapLocation() {}

    // Metoda koja provodi usporedbu dviju MapLocation objekata
    @Override
    public boolean equals(Object o) {
        MapLocation mapLoc = (MapLocation) o;
        return mapLoc.lat.equals(this.lat) && mapLoc.lng.equals(this.lng);
    }

    // Metoda koja provjerava je li trenutna lokacija unutar zadanog radijusa
    public boolean inRadius(MapLocation ml, Double radiusTrazenja) {

        // Preko Haversinove formule za izračun udaljenosti između dviju geografski definiranih točaka
        final double R = 6371.0;  // Polumjer Zemlje u kilometrima
        Double lat1 = Math.toRadians(ml.lat);  // Pretvaranje u radijane
        Double lon1 = Math.toRadians(ml.lng);  // Pretvaranje u radijane
        Double lat2 = Math.toRadians(this.lat);  // Pretvaranje u radijane
        Double lon2 = Math.toRadians(this.lng);  // Pretvaranje u radijane

        // Haversine formula
        double dlat = lat2 - lat1;
        double dlon = lon2 - lon1;
        double a = Math.sin(dlat / 2) * Math.sin(dlat / 2) +
                   Math.cos(lat1) * Math.cos(lat2) *
                   Math.sin(dlon / 2) * Math.sin(dlon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        // Izračun udaljenosti u kilometrima i provjera da li je udaljenost manja od zadanog radijusa
        return R * c < radiusTrazenja;
    }

    // Getteri i setteri se generiraju automatski na temelju instancijskih varijabli
}
