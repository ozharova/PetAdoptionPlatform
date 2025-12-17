public class Main {
    public static void main(String[] args) {
        // --- Create objects (instances of classes) ---
        Shelter shelter1 = new Shelter("Test1", "52 Turan Ave.", 4.6);
        Shelter shelter2 = new Shelter("Paw patrol", "14 Tole Bi St.", 3.9);
        Shelter shelter3 = new Shelter("Zootopia", "123 Uly Dala St.", 5.0);
        Pet pet1 = new Pet("Coco", "Dog", 5, false, shelter2);
        Pet pet2 = new Pet("Leo", "Cat", 1, true, shelter1);
        Pet pet3 = new Pet("Jack", "Dog", 3, false, shelter1);
        Pet pet4 = new Pet("Mimi", "Cat", 2, false, shelter3);
        Pet pet5 = new Pet("Joy", "Dog", 3, true, shelter3);
        Adopter adopter1 = new Adopter("Chloe", 25, pet2);
        Adopter adopter2 = new Adopter("Bruno", 44, pet5);
        // Output
        System.out.println("\n === PET ADOPTION SYSTEM ====");
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
        // Comparison
        System.out.println("\n--- SHELTER COMPARISON ---");
        Shelter[] shelters = {shelter1, shelter2, shelter3};
        Shelter bestShelter = shelters[0];
        for (int i = 0; i < shelters.length; i++) {
            if (shelters[i].getRating() > bestShelter.getRating()) {
                bestShelter = shelters[i];
            }
        }
        System.out.println("Shelter with the best rating: " + "\"" + bestShelter.getName() + "\"" +
                " (Rating: " + bestShelter.getRating() + ")");

    }
}