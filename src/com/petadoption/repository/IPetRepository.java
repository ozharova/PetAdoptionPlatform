package com.petadoption.repository;

import com.petadoption.model.Pet;
import java.util.List;

public interface IPetRepository {
    boolean create(Pet pet);
    boolean updateByName(String name, Pet pet);
    boolean deleteByName(String name);
    Pet findByName(String name);
    List<Pet> findAll();

    // Default method (Language Feature)
    default void logRepositoryAccess() {
        System.out.println("[Log] Accessing Pet Repository...");
    }

    // Static method (Language Feature)
    static void printRepositoryInfo() {
        System.out.println("[Info] Pet Repository Interface v1.0");
    }
}
