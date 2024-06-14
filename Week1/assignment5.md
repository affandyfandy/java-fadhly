# Implement thread-safe Singleton

Singleton is design pattern in programming to ensure a class that one instance and provides global point to access the instance. Thread-safe is a piece of code that functions correctly when multiple threads access it simultaneously. So thread-safe singleton is multiple threads try to access the instance simultaneously, but only one instance is created and used.

## Three ways to achive thread safety for singleton

1. `Create the instance variable at the time of class loading.`
2. `Synchronize the getInstance() method.`
3. `Use synchronized block inside the if loop and volatile variable.`

But there were some pros and cons to using that tree ways:

| Pros | Cons |
|-|-|
|  |  |
|  |  |
|  |  |
|  |  |
|  |  |

## Example Code

```java
public class Singleton {

    private static volatile Singleton instance = new Singleton();

    private Singleton() {
    }

    public static Singleton getInstance() {
        Singleton result = instance;
        if (result == null) {
            synchronized (Singleton.class) {
                result = instance;
                if (result == null) {
                    instance = result = new Singleton();
                }
            }
        }
        return result;
    }
}
```