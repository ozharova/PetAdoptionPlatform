package com.petadoption.service;

import com.petadoption.datapool.PetDataPool;
import com.petadoption.exception.InvalidInputException;
import com.petadoption.exception.ResourceNotFoundException;
import com.petadoption.model.Pet;
import com.petadoption.repository.IPetRepository;
import com.petadoption.repository.IShelterRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;

@Service
public class PetService {
    private final IPetRepository petRepository;
    private final PetDataPool petDataPool;
    private final IShelterRepository shelterRepository;

    public PetService(IPetRepository petRepository, PetDataPool petDataPool, IShelterRepository shelterRepository) {
        this.petRepository = petRepository;
        this.petDataPool = petDataPool;
        this.shelterRepository = shelterRepository;
        this.petDataPool.load(petRepository.findAll());
    }

    public void registerPet(Pet pet) {
        validatePet(pet);
        boolean created = petRepository.create(pet);
        if (!created) {
            throw new InvalidInputException("Pet could not be created (possibly duplicate name).");
        }
        petDataPool.add(pet);
    }

    public Pet getPetByName(String name) {
        Pet pet = petDataPool.findByName(name);
        if (pet == null) {
            throw new ResourceNotFoundException("Pet with name " + name + " not found.");
        }
        return pet;
    }

    public List<Pet> filterPets(Predicate<Pet> criteria) {
        return petDataPool.filter(criteria);
    }
    
    public List<Pet> getAllPets() {
        return petDataPool.getAll();
    }

    public List<Pet> getAvailablePets() {
        return petDataPool.getAvailable();
    }

    public void sortByAge() {
        petDataPool.sortByAge();
    }

    public void updatePet(String name, Pet updatedPet) {
        validatePet(updatedPet);
        if (petDataPool.findByName(name) == null) {
            throw new ResourceNotFoundException("Pet with name " + name + " not found.");
        }
        boolean updated = petRepository.updateByName(name, updatedPet);
        if (!updated) {
            throw new ResourceNotFoundException("Pet with name " + name + " not found in database.");
        }
        petDataPool.update(updatedPet);
    }

    public void deletePet(String name) {
        if (petDataPool.findByName(name) == null) {
            throw new ResourceNotFoundException("Pet with name " + name + " not found.");
        }
        boolean deleted = petRepository.deleteByName(name);
        if (!deleted) {
            throw new ResourceNotFoundException("Pet with name " + name + " not found in database.");
        }
        petDataPool.removeByName(name);
    }

    private void validatePet(Pet pet) {
        if (pet.getAge() < 0) {
            throw new InvalidInputException("Age cannot be negative.");
        }
        if (pet.getName() == null || pet.getName().isEmpty()) {
            throw new InvalidInputException("Name cannot be empty.");
        }
        if (pet.getType() == null || pet.getType().isEmpty()) {
            throw new InvalidInputException("Type cannot be empty.");
        }
        if (pet.getShelter() != null && pet.getShelter().getId() > 0) {
            if (!shelterRepository.existsById(pet.getShelter().getId())) {
                throw new InvalidInputException("Shelter with id " + pet.getShelter().getId() + " not found.");
            }
        }
    }
}
