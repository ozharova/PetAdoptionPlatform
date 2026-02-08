package com.petadoption.model;

public class Cat extends Pet {

    public Cat() {
        super();
    }

    public Cat(String name, String type, int age, boolean adopted, Shelter shelter) {
        super(name, type, age, adopted, shelter);
    }

    @Override
    public String getPetSound() {
        return "Meow!";
    }
}
