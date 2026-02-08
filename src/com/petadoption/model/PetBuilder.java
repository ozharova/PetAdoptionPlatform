package com.petadoption.model;

public class PetBuilder {
    private String name;
    private String type;
    private int age;
    private boolean adopted;
    private Shelter shelter;

    public PetBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public PetBuilder setType(String type) {
        this.type = type;
        return this;
    }

    public PetBuilder setAge(int age) {
        this.age = age;
        return this;
    }

    public PetBuilder setAdopted(boolean adopted) {
        this.adopted = adopted;
        return this;
    }

    public PetBuilder setShelter(Shelter shelter) {
        this.shelter = shelter;
        return this;
    }

    public Pet build() {
        if (type.equalsIgnoreCase("Dog")) {
            return new Dog(name, type, age, adopted, shelter);
        } else if (type.equalsIgnoreCase("Cat")) {
            return new Cat(name, type, age, adopted, shelter);
        }
        throw new IllegalArgumentException("Unknown pet type: " + type);
    }
}
