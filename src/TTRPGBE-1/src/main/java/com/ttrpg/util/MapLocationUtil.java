package com.ttrpg.util;

import com.ttrpg.model.MapLocation;

import java.util.Random;

public class MapLocationUtil {
    private static final double EARTH_RADIUS = 6371000;
    public static MapLocation generateRandomLocation(MapLocation mapLocation, double radiusInMeters) {
        Random r = new Random();
        double radiusInDegrees = radiusInMeters / 111320;
        double angle = 2*Math.PI * r.nextDouble();
        double randomRadius = radiusInDegrees * Math.sqrt(r.nextDouble());
        double offsetLatitude = randomRadius * Math.sin(angle);
        double offsetLongitude = randomRadius * Math.cos(angle) / Math.cos(Math.toRadians(mapLocation.getLat()));
        double newLatitude = offsetLatitude + mapLocation.getLat();
        double newLongitude = offsetLongitude + mapLocation.getLng();
        return new MapLocation(newLatitude, newLongitude);

    }
}
