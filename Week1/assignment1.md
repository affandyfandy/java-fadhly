# Compare Value Types and References Types When Passing as Parameters to Functions.

## Value Types

Value types is like Primitive types, that will hold their values directly in memory and when changing value of one variable doesn't affect other variables. For example `int`, `double`, `char`, `boolean` are Primitive or Values types.

```java
public class Main {
    public static void main(String[] args) {
        int num = 10;
        changeValue(num);
        System.out.println("Original value: " + num); // Output: 10
    }

    public static void changeValue(int value) {
        value = 20; // Changes made to 'value' parameter
    }
}
```

## References Types

References types store memory addresses to object in memory and store the memory address of the object rather than the object itself. For example `Classes`, `Arrays`, `Interfaces`

```java
class MyClass {
    int num;
    MyClass(int num) {
        this.num = num;
    }
}

public class Main {
    public static void main(String[] args) {
        MyClass obj = new MyClass(10);
        modifyObject(obj);
        System.out.println("Original object value: " + obj.num); // Output: 20
    }

    public static void modifyObject(MyClass obj) {
        obj.num = 20; // Changes made to object's state
    }
}
```

## Other Characteristic from Value Types and References Types

| | Value Types | Reference Types |
|-|-|-|
| Stored | Store values directly in memory | Store references (memory addresses) to objects in memory |
| Value | Store the actual value | Store the memory address of the object |
| Size | Fixed amount of memory determined by the type | Fixed amount of memory to store the reference, regardless of the size of the object |
| Operations | Directly manipulate their values | Manipulate the references, not the objects themselves |
| Immutability | Changes the value of one variable does not affect other variables | Changes to an object through one reference affect all references to that object |