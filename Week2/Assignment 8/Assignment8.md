# Assignment 8

## Assignment 8.1 (serialVersionUID)

`serialVersionUID` is static final member variable that is used to ensure that the serialized version of a class is compatible with the existing serialized objects.

1. `serialVersionUID` acts as a version control mechanism for serialized objects, used to ensure that the class that serialized an object is the same class that deserializes it.
2. `Explicit Declaration` the system automatically calculates one based on various aspects of the class if do not explicitly declare a `serialVersionUID` in the class.
3. `Compatibility` used to verify that the sender and receiver of the object have loaded classes for that object that are compatible with respect to serialization.

```java
import java.io.Serializable;

public class MyClass implements Serializable {
    private static final long serialVersionUID = 1L;
}
```

`serialVersionUID` is a crucial part of Java serialization that ensures compatibility between different versions of serialized objects and helps in preventing unexpected InvalidClassException during deserialization.

## Assignment 8.2 (Serialization and Deserialization in write list of object)

`The code can be run from the src source from this folder.`

![Serialization and Deserialization](img/Lab8_2%20Serialization%20and%20Deserialization.PNG)