public class Dog {
    private String color;
    private String name;
    private String breed;

    public Dog(String color, String name, String breed) {
        this.color = color;
        this.name = name;
        this.breed = breed;
    }

    public String getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    public String getbreed() {
        return breed;
    }

    public void waggingTheTail() {
        System.out.println("wagging Wagging Wagging.....");
    }

    public void barking() {
        System.out.println("Bark Bark Bark.....");
    }

    public void eating() {
        System.out.println("Nom Nom Nom.....");
    }
}
