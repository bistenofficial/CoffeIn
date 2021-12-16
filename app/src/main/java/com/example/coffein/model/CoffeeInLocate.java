package com.example.coffein.model;

import java.util.ArrayList;

public class CoffeeInLocate {

    private double latitude;
    private double longitude;

    public CoffeeInLocate(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public static ArrayList<CoffeeInLocate> createList() {
        ArrayList<CoffeeInLocate> states = new ArrayList<>();
        states.add(new CoffeeInLocate(59.926334,30.297289));
        states.add(new CoffeeInLocate(59.978283 ,30.349302));
        states.add(new CoffeeInLocate(59.833926 ,30.404899));
        return states;
    }
}