public class Main {
    public static void main(String[] args) {

        // --- Create shelters ---
        Shelter shelter1 = new Shelter("Test1", "52 Turan Ave.", 4.6);
        Shelter shelter2 = new Shelter("Paw patrol", "14 Tole Bi St.", 3.9);
        Shelter shelter3 = new Shelter("Zootopia", "123 Uly Dala St.", 5.0);

        // --- Create pets ---
        Dog pet1 = new Dog("Coco", "Chihuahua", 5, false, shelter2);
        Cat pet2 = new Cat("Leo", "Sphynx", 1, true, shelter1);
        Dog pet3 = new Dog("Jack", "Border Collie", 3, false, shelter1);
        Cat pet4 = new Cat("Mimi", "Munchkin", 2, false, shelter3);
        Dog pet5 = new Dog("Joy", "Golden Retriever", 3, true, shelter3);

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
        PetRepository repo = new PetRepository();
        repo.addPet(pet1);
        repo.addPet(pet2);
        repo.addPet(pet3);
        repo.addPet(pet4);
        repo.addPet(pet5);

        System.out.println("\n--- AVAILABLE PETS ---");
        for (Pet p : repo.getAvailablePets()) {
            System.out.println(p);
        }

        System.out.println("\n--- SEARCH RESULT (Mimi) ---");
        System.out.println(repo.findByName("Mimi"));

        repo.sortByAge();
        System.out.println("\n--- SORTED BY AGE ---");
        for (Pet p : repo.getAllPets()) {
            System.out.println(p);
        }

        System.out.println("\n--- POLYMORPHISM ---");
        System.out.println(pet1.getName() + " says: " + pet1.getPetSound());
        System.out.println(pet2.getName() + " says: " + pet2.getPetSound());
    }
}
