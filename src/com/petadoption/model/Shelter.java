package com.petadoption.model;

import java.util.Objects;

public class Shelter {
    // Attributes
    private String name;
    private String location;
    private double rating;
    private int id;

    // Constructors
    public Shelter() {
    }

    public Shelter(String name, String location, double rating) {
        this.name = name;
        this.location = location;
        this.rating = rating;
    }

    // ---- Getters and Setters -----
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    public double getRating() {
        return rating;
    }
    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    // ---- Methods ----
    public void printInfo() {
        System.out.println("Shelter: \"" + name + "\"" + ", Location: " + location +
                ", Rating: " + rating);
    }

    @Override
    public String toString() {
        return "Shelter{name='" + name + "', location='" + location + "', rating=" + rating + ", id=" + id + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Shelter)) return false;
        Shelter shelter = (Shelter) o;
        return Objects.equals(name, shelter.name) && Objects.equals(location, shelter.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, location);
    }
}
