package com.petadoption.datapool;

import com.petadoption.model.Pet;

import java.util.List;
import java.util.function.Predicate;

public interface PetDataPool {
    void load(List<Pet> pets);
    void add(Pet pet);
    void update(Pet pet);
    void removeByName(String name);
    Pet findByName(String name);
    List<Pet> getAll();
    List<Pet> getAvailable();
    void sortByAge();
    List<Pet> filter(Predicate<Pet> criteria);
}
