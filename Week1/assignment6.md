# Stack & Heap

Stack memory is static memory allocation, it stores method calls, local variables, and  reference variables.

Heap memory is dynamic memory allocation, it stores objects and arrays that are created during the execution of the program

## Passing Reference Types

### Example 1

```java
public class Main {
    public static void main(String[] args) {
        String body = "small";
        int wheels = 4;
        String engine = "race";

        Myclass car = new Myclass(body, wheels, engine);
        modifyObject(car);
        System.out.println("car.body after modifyObject: " + car.body);
    }

    public static void modifyObject(Myclass car) {
        car.body = "big";
    }
}

public class Myclass {
    private String body;
    private int wheels;
    private String engine;

    public Myclass(String body, int wheels, String engine) {
        this.body = body;
        this.wheels = wheels;
        this.engine = engine;
    }
}
```

### Example 2

```java
public class Main {
    public static void main(String[] args) {
        String body = "small";
        int wheels = 4;
        String engine = "race";

        Myclass car = new Myclass(body, wheels, engine);
        changeReference(car);
        System.out.println("car.body after modifyObject: " + car.body);
    }

    public static void changeReference(Myclass car) {
        Myclass car = new Myclass("small", 4, "race")
        car.body = "big";
    }
}

public class Myclass {
    private String body;
    private int wheels;
    private String engine;

    public Myclass(String body, int wheels, String engine) {
        this.body = body;
        this.wheels = wheels;
        this.engine = engine;
    }
}
```