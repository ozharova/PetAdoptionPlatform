package com.petadoption.model;

public class PetFactory {
    public static Pet createPet(String type, String name, int age, boolean adopted, Shelter shelter) {
        if (type.equalsIgnoreCase("Dog")) {
            return new Dog(name, type, age, adopted, shelter);
        } else if (type.equalsIgnoreCase("Cat")) {
            return new Cat(name, type, age, adopted, shelter);
        }
        throw new IllegalArgumentException("Unknown pet type: " + type);
    }
}
