# Interface and Abstract Class

## Assignment 2.1 (What if implement 2 Interface that have same default method?)
We can't implement 2 interface that have same default method. However we can implement default method in other 2 ways:
1. `Create new method that will override all of the default method before.`

```java
public interface FirstInterface {
    void firstMethod(String string);

    default void log(String string) {
        System.out.println("This method is default implementation " + string);
    }
}

public interface SecondInterface {
    void secondMethod(String string);

    default void log(String str) {
        System.out.println("This method is default implementation " + str);
    }
}

public class MyClass implements FirstInterface, SecondInterface {

    @Override
    public void firstMethod(String string) {
        System.out.println("FirstMethod implementation: " + string);
    }

    @Override
    public void secondMethod(String string) {
        System.out.println("SecondMethod implementation: " + string);
    }

    @Override
    public void log(String string) {
        System.out.println(string);
    }
}

public class Main {
    public static void main(String[] args) {
        MyClass myclass = new MyClass();
        myclass.log("Implement new log");
    }
}
```

2. `Override the default method using either super form firstMethod or secondMethod`

```java
public interface FirstInterface {
    void firstMethod(String string);

    default void log(String string) {
        System.out.println("This method is default implementation " + string);
    }
}

public interface SecondInterface {
    void secondMethod(String string);

    default void log(String str) {
        System.out.println("This method is default implementation " + str);
    }
}

public class MyClass implements FirstInterface, SecondInterface {

    @Override
    public void firstMethod(String string) {
        System.out.println("FirstMethod implementation: " + string);
    }

    @Override
    public void secondMethod(String string) {
        System.out.println("SecondMethod implementation: " + string);
    }

    //Just choose one, using First Interface or Second Interface
    //Using First Interface
    @Override
    public void log(String string) {
        FirstInterface.super.log(string);
    }

    //Using Second Interface
    @Override
    public void log(String string) {
        SecondInterface.super.log(string);
    }
}

public class Main {
    public static void main(String[] args) {
        MyClass myclass = new MyClass();
        myclass.log("Implement new log");
    }
}
```

## Assignment 2.2 (Difference Between Abstract Class and Interface)

`Abstract Class` is a class that can't be  instantiated directly and it intended to be extended by other classes. It can have both,abstract methods (without implementation) and concrete methods (with implementation).

`Interface Class` is a reference type like a normal class, it can hold constants, method declarations, default methods, static methods, and nested types, but it can't have instance variables or constructors.

### Table Difference Between Abstract and Interface Class

| Abstract | Interface |
|-|-|
| Share code among related classes | Specify methods a class must implement |
| Use `abstract` keyword | Use `interface` keyword |
| Abstract class extended using keyword `extends` | Interface implemented ng keyword `implements` |
| Doesn't support multiple inheritance | Supports multiple inheritance |

### Example Syntax Code Abstract Class

```java
abstract class Vehicle {
    private String name;

    public Vehicle(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // Abstract method (no implementation)
    public abstract void move();

    // Concrete method (with implementation)
    public void displayInfo() {
        System.out.println("Vehicle Name: " + name);
    }
}

class Car extends Vehicle {
    public Car(String name) {
        super(name);
    }

    @Override
    public void move() {
        System.out.println(getName() + " is moving on four wheels.");
    }
}

public class Main {
    public static void main(String[] args) {
        Vehicle car = new Car("Car");
        car.displayInfo();
        car.move();
    }
}
```

### Example Syntax Code Interface Class

```java
interface Animal {
    void eat();

    default void sound() {
        System.out.println("Animal is making a sound.");
    }
}

interface Pet {
    void play();
}

class Dog implements Animal, Pet {
    @Override
    public void eat() {
        System.out.println("Dog is eating.");
    }

    @Override
    public void play() {
        System.out.println("Dog is playing.");
    }

    @Override
    public void sound() {
        System.out.println("Dog says: Woof");
    }
}

public class Main {
    public static void main(String[] args) {
        Dog dog = new Dog();
        dog.eat();
        dog.play();
        dog.sound();
    }
}
```

## Assignment 2.3 (Research @FunctionalInterface)

`Functional Interface` is used to indicate that an interface is intended to be a functional interface. It is known as Single Abstract Method Interfaces or also known as SAM interfaces, that exactly one abstract method.

### Example code

```java
@FunctionalInterface
interface MyFunctionalInterface {
    void myMethod();

    default void defaultMethod() {
        System.out.println("Default method in functional interface");
    }

    static void staticMethod() {
        System.out.println("Static method in functional interface");
    }
}

public class Main {
    public static void main(String[] args) {
        MyFunctionalInterface functionalInterface = () -> {
            System.out.println("Implementing myMethod in lambda expression");
        };

        functionalInterface.myMethod(); 
        functionalInterface.defaultMethod(); 
        MyFunctionalInterface.staticMethod();
    }
}

```