import java.util.ArrayList;
import java.util.List;

public class PetRepository {

    private List<Pet> pets = new ArrayList<>();

    public void addPet(Pet pet) {
        pets.add(pet);
    }

    // FILTER
    public List<Pet> getAvailablePets() {
        List<Pet> result = new ArrayList<>();
        for (Pet pet : pets) {
            if (!pet.getAdopted()) {
                result.add(pet);
            }
        }
        return result;
    }

    // SEARCH
    public Pet findByName(String name) {
        for (Pet pet : pets) {
            if (pet.getName().equalsIgnoreCase(name)) {
                return pet;
            }
        }
        return null;
    }

    // SORT
    public void sortByAge() {
        pets.sort((p1, p2) -> p1.getAge() - p2.getAge());
    }

    public List<Pet> getAllPets() {
        return pets;
    }
}
