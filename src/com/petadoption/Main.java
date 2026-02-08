package com.petadoption;

import com.petadoption.exception.InvalidInputException;
import com.petadoption.exception.ResourceNotFoundException;
import com.petadoption.model.*;
import com.petadoption.repository.IPetRepository;
import com.petadoption.service.PetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Main implements CommandLineRunner {

    private final PetService petService;
    private final IPetRepository repo;

    public Main(PetService petService, IPetRepository repo) {
        this.petService = petService;
        this.repo = repo;
    }

    @Override
    public void run(String... args) {
        // --- Create shelters ---
        Shelter shelter1 = new Shelter("Test1", "52 Turan Ave.", 4.6);
        Shelter shelter2 = new Shelter("Paw patrol", "14 Tole Bi St.", 3.9);
        Shelter shelter3 = new Shelter("Zootopia", "123 Uly Dala St.", 5.0);

        shelter1.setId(1);
        shelter2.setId(2);
        shelter3.setId(3);

        // --- Create pets using Factory and Builder ---
        Pet pet1 = PetFactory.createPet("Dog", "Coco", 5, false, shelter2);
        Pet pet2 = new PetBuilder()
                .setName("Leo")
                .setType("Cat")
                .setAge(1)
                .setAdopted(true)
                .setShelter(shelter1)
                .build();
        Pet pet3 = PetFactory.createPet("Dog", "Jack", 3, false, shelter1);
        Pet pet4 = new PetBuilder()
                .setName("Mimi")
                .setType("Cat")
                .setAge(2)
                .setAdopted(false)
                .setShelter(shelter3)
                .build();
        Pet pet5 = PetFactory.createPet("Dog", "Joy", 3, true, shelter3);

        // --- Create adopters ---
        Adopter adopter1 = new Adopter("Chloe", 25, pet2);
        Adopter adopter2 = new Adopter("Bruno", 44, pet5);

        // --- Output ---
        System.out.println("\n=== PET ADOPTION SYSTEM ===");

        System.out.println("\n--- PETS ---");
        pet1.printInfo();
        pet2.printInfo();
        pet3.printInfo();
        pet4.printInfo();
        pet5.printInfo();

        System.out.println("\n--- ADOPTERS ---");
        adopter1.printInfo();
        adopter2.printInfo();

        System.out.println("\n--- SHELTERS ---");
        shelter1.printInfo();
        shelter2.printInfo();
        shelter3.printInfo();

        // --- Shelter comparison ---
        Shelter[] shelters = {shelter1, shelter2, shelter3};
        Shelter bestShelter = shelters[0];
        for (int i = 0; i < shelters.length; i++) {
            if (shelters[i].getRating() > bestShelter.getRating()) {
                bestShelter = shelters[i];
            }
        }
        System.out.println("\nShelter with the best rating: \"" +
                bestShelter.getName() + "\" (Rating: " + bestShelter.getRating() + ")");

        // --- Repository usage ---
        System.out.println("\n--- DATABASE SYNCHRONIZATION ---");
        IPetRepository.printRepositoryInfo(); // Static interface method usage
        repo.logRepositoryAccess(); // Default interface method usage
        try {
            petService.registerPet(pet1);
            petService.registerPet(pet2);
            petService.registerPet(pet3);
            petService.registerPet(pet4);
            petService.registerPet(pet5);
        } catch (InvalidInputException e) {
            System.err.println("Error registering pet: " + e.getMessage());
        }

        System.out.println("\n--- AVAILABLE PETS ---");
        for (Pet p : petService.getAvailablePets()) {
            System.out.println(p);
        }

        System.out.println("\n--- SEARCH RESULT (Mimi) ---");
        try {
            System.out.println(petService.getPetByName("Mimi"));
        } catch (ResourceNotFoundException e) {
            System.err.println(e.getMessage());
        }

        petService.sortByAge();
        System.out.println("\n--- SORTED BY AGE ---");
        for (Pet p : petService.getAllPets()) {
            System.out.println(p);
        }

        System.out.println("\n--- POLYMORPHISM ---");
        System.out.println(pet1.getName() + " says: " + pet1.getPetSound());
        System.out.println(pet2.getName() + " says: " + pet2.getPetSound());

        System.out.println("\n--- LAMBDA EXPRESSION (Filter Age > 2) ---");
        petService.filterPets(p -> p.getAge() > 2).forEach(System.out::println);
    }
}
