package com.petadoption.model;

import java.util.Objects;

public class Adopter {
    // Attributes
    private String name;
    private int age;
    private Pet pet;

    // Constructors
    public Adopter(String name, int age, Pet pet) {
        this.name = name;
        this.age = age;
        this.pet = pet;
    }

    // ---- Getters and Setters -----
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public Pet getPet() {
        return pet;
    }
    public void setPet(Pet pet) {
        this.pet = pet;
    }

    // ---- Methods ----
    public void printInfo() {
        System.out.println("Name: " + name + ", Age: " + age + ", Adopted pet: " + pet.getName());
    }

    @Override
    public String toString() {
        return "Adopter{name='" + name + "', age=" + age + ", pet=" + (pet != null ? pet.getName() : "None") + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Adopter)) return false;
        Adopter adopter = (Adopter) o;
        return Objects.equals(name, adopter.name) && Objects.equals(pet, adopter.pet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, pet);
    }
}
