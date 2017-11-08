package com.example.user.showlocation.model;

/**
 * Created by USER on 10/17/2017.
 */

public class LatLongModel {
    private double latitude;
    private double longitude;

    public LatLongModel() {
    }

    public LatLongModel(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public LatLongModel(String lat, String lon) {
        this.latitude = Double.parseDouble(lat);
        this.longitude = Double.parseDouble(lon);
    }
}
