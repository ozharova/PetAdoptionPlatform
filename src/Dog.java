public class Dog extends Pet {

    public Dog(String name, String type, int age, boolean adopted, Shelter shelter) {
        super(name, type, age, adopted, shelter);
    }

    @Override
    public String getPetSound() {
        return "Woof!";
    }
}
