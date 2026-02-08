package com.petadoption.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.Objects;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "type",
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Dog.class, name = "Dog"),
        @JsonSubTypes.Type(value = Cat.class, name = "Cat")
})
public abstract class Pet {
    // Attributes
    private String name;
    private String type;
    private int age;
    private boolean adopted;
    private Shelter shelter;

    // Constructors
    protected Pet() {
    }

    public Pet(String name, String type, int age, boolean adopted, Shelter shelter) {
        this.name = name;
        this.type = type;
        this.age = age;
        this.adopted = adopted;
        this.shelter = shelter;
    }

    // ---- Getters and Setters -----
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public boolean getAdopted() {
        return adopted;
    }
    public void setAdopted(boolean adopted) {
        this.adopted = adopted;
    }

    public Shelter getShelter() {
        return shelter;
    }
    public void setShelter(Shelter shelter) {
        this.shelter = shelter;
    }

    // --- Abstract method (abstraction + polymorphism) ---
    public abstract String getPetSound();

    // --- Methods ---
    public void printInfo() {
        if (adopted) {
            System.out.println("Name: " + name + ", Type: " + type +
                    ", Age: " + age + ", Status: Already adopted");
        } else {
            String shelterName = (shelter != null) ? shelter.getName() : "Unknown";
            System.out.println("Name: " + name + ", Type: " + type +
                    ", Age: " + age + ", Status: Looking for a family, Shelter: \"" +
                    shelterName + "\"");
        }
    }

    // --- toString / equals / hashCode ---
    @Override
    public String toString() {
        return "Pet{name='" + name + "', type='" + type +
                "', age=" + age + ", adopted=" + adopted + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pet)) return false;
        Pet pet = (Pet) o;
        return Objects.equals(name, pet.name) && Objects.equals(type, pet.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type);
    }
}
