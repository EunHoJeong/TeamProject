package com.example.teamproject;

public class MarkerItem {
    double lat;
    double lon;
    String motelName;

    public MarkerItem(double lat,double lon,String motelName){
        this.lat=lat;
        this.lon=lon;
        this.motelName=motelName;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getMotelName() {
        return motelName;
    }

    public void setMotelName(String motelName) {
        this.motelName = motelName;
    }
}
