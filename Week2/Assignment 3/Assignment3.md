# Try Catch Exception

## Assignment 3.1 (try-with-resource)

`try-with-resource` statement is a management resource is such Java language programming. It should can be manage such as file, network connections, or database connections. It could be handle multiple statement from one code.

The benefits of using this statement: 
1. `Code will be more cleaner because it will eliminated the need for explicit resource close calls`
2. `Automatic handling that will closed automatically`
3. `Exception handling`

### Examples Code

1. `Read from file .txt`
```java
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TryWithResourcesExample1 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("example.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

2. `Write to file .txt`
```java
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class TryWithResourcesExample2 {
    public static void main(String[] args) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("example.txt"))) {
            bw.write("Hello, World!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```
3. `Combine that read also write file .txt`
```java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TryWithResourcesExample3 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"));
             BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"))) {

            String line;
            while ((line = br.readLine()) != null) {
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

## Assignment 3.2 (throw vs throws)

### throw
`throw` is used to explicity throw and exception from a method or any block code. When using It, It will create a exception object and give it to the runtime system.

```java
public class ThrowExample {
    public static void checkAge(int age) {
        if (age < 18) {
            throw new IllegalArgumentException("Age must be 18 or older");
        } else {
            System.out.println("Age is valid");
        }
    }

    public static void main(String[] args) {
        checkAge(15); // This will throw an IllegalArgumentException
    }
}
```

This code below that throw will be created a new object from exception called IllegalArgumentException, that will called when the input age is lower than 18. This throw is in inside the method.

### throws

`throws` is used in the signature of method that might be indicate throw one or more exceptions that form all kind of exception. It will inform about potential exceptions and the caller is using try-catch block.

```java
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ThrowsExample {
    public static void readFile(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
        br.close();
    }

    public static void main(String[] args) {
        try {
            readFile("example.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

This code bellow will be inisiated first the throws in the signature of the method, It indicated that the error will be an IOException. It will be running if the path is wrong or the file isn't there. It will printed the default message from IOException.

### Difference

| throw | throws |
|-|-|
| Used in the method body | Used in the method declaration |
| Transfer control to `try-catch` or default exception | Inform the caller about exception that need to be handled |
| Can't declare multiple exception | Can declare multiple exception |
| Used within a method to actually throw an exception | Used in a method signature to declare that the method can throw certain exceptions |

## Assignment 3.3 ()

## Assignment 3.4 ()

## Assignment 3.5 ()