public abstract class Pet {
    // Attributes
    private String name;
    private String type;
    private int age;
    private boolean adopted;
    private Shelter shelter;
    // Constructors
    public Pet(String name, String type, int age, boolean adopted, Shelter shelter) {
        this.name = name;
        this.type = type;
        this.age = age;
        this.adopted = adopted;
        this.shelter = shelter;
    }
    // ---- Getters and Setters -----
    // for name:
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    // for type:
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    // for age:
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    // for adopted:
    public boolean getAdopted() {
        return adopted;
    }
    public void setAdopted(boolean adopted) {
        this.adopted = adopted;
    }

    //for Shelters
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
            System.out.println("Name: " + name + ", Type: " + type +
                    ", Age: " + age + ", Status: Looking for a family, Shelter: \"" +
                    shelter.getName() + "\"");
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
        return name.equals(pet.name) && type.equals(pet.type);
    }

    @Override
    public int hashCode() {
        return name.hashCode() + type.hashCode();
    }
}
