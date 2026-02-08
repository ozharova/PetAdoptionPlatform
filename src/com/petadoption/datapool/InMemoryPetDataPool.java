package com.petadoption.datapool;

import com.petadoption.model.Pet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Component
public class InMemoryPetDataPool implements PetDataPool {
    private final List<Pet> pets = new ArrayList<>();

    @Override
    public void load(List<Pet> pets) {
        this.pets.clear();
        if (pets != null) {
            this.pets.addAll(pets);
        }
    }

    @Override
    public void add(Pet pet) {
        pets.add(pet);
    }

    @Override
    public void update(Pet pet) {
        removeByName(pet.getName());
        pets.add(pet);
    }

    @Override
    public void removeByName(String name) {
        pets.removeIf(p -> p.getName().equalsIgnoreCase(name));
    }

    @Override
    public Pet findByName(String name) {
        return pets.stream()
                .filter(p -> p.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Pet> getAll() {
        return new ArrayList<>(pets);
    }

    @Override
    public List<Pet> getAvailable() {
        return pets.stream()
                .filter(p -> !p.getAdopted())
                .collect(Collectors.toList());
    }

    @Override
    public void sortByAge() {
        pets.sort(Comparator.comparingInt(Pet::getAge));
    }

    @Override
    public List<Pet> filter(Predicate<Pet> criteria) {
        return pets.stream()
                .filter(criteria)
                .collect(Collectors.toList());
    }
}
