# Threading

## Assignment 4.1 (Explanation about Deadlock)

`Deadlock` in general can be define as a situation where a set of proccess are blocked because each proccess holding and waiting for other proccess to complete.

In code `deadlock` will occurs when two or more proccesses or threads are blocked forever, and each waiting for other to release the resource that they need.

### How to Prevent Deadlock

1. `Resource Ordering:` This prevents circular waiting where one process holds a resource while waiting for another resource.
2. `Resource Allocation Graph:` Use a resource allocation graph to detect potential deadlocks before they occur.
3. `Timeouts and Reclamation:` Implement timeouts for acquiring resources.
4. `Resource Hierarchies:` A process can only request resources at a level lower or equal to where it currently holds resources.
5. `Deadlock Detection and Recovery:` Use techniques like process termination, resource preemption, or rollback to recover.
6. `Avoidance of Circular Waits:` Ensure that processes do not wait in a circular chain for resources.

### Example Code Deadlock
```java
public class Main {
    public static void main(String[] args) throws Exception {
        final Object resource1 = new Object();
        final Object resource2 = new Object();
        Thread t1 = new Thread() {
            public void run() {
                synchronized (resource1) {
                    System.out.println("Thread 1: locked resource 1");
                    try {
                        Thread.sleep(100);
                    } catch (Exception e) {}
                    synchronized (resource2) {
                        System.out.println("Thread 1: locked resource 2");
                    }
                }
            }
        };
        Thread t2 = new Thread() {
            public void run() {
                synchronized (resource2) {
                    System.out.println("Thread 2: locked resource 2");
                    try {
                        Thread.sleep(100);
                    } catch (Exception e) {}
                    synchronized (resource1) {
                        System.out.println("Thread 2: locked resource 1");
                    }
                }
            }
        };
        t1.start();
        t2.start();
    }  
}
```
![Deadlock Prevented](img/Deadlock%20Thread.png)

### Example Code to Prevent Deadlock
```java
public class App {
    public static void main(String[] args) throws Exception {
        final Object resource1 = new Object();
        final Object resource2 = new Object();
        Thread t1 = new Thread(() -> {
            synchronized (resource1) {
                System.out.println("Thread 1: locked resource 1");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (resource2) {
                    System.out.println("Thread 1: locked resource 2");
                }
            }
        });
        Thread t2 = new Thread(() -> {
            synchronized (resource1) {
                System.out.println("Thread 2: locked resource 1");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (resource2) {
                    System.out.println("Thread 2: locked resource 2");
                }
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("Both threads finished");
    }
}
```
![Deadlock Prevented](img/Deadlock%20Resolved.png)

## Assignment 4.2 (Bank Account with Threads)

`The code can be run from the src source from this folder.`

![Deadlock Prevented](img/Bank%20Account.png)

## Assignment 4.3 (Sort an Array Integer using Multiple Threads)

`The code can be run from the src source from this folder.`

![Deadlock Prevented](img/Sort%20Array%20Int.png)

## Assignment 4.4 (Multiple Threads)

After using `Multiple Threads` in the task before, I found some advantages and disadvantages for using `Multiple Threads`

### Advantages using Multiple Thread

1. `It will improved the performance by task can be run concurrently that divided into independent sub-tasks and executed in parallel.`
2. `It will be more efficiency in resource because threads process share the same memory space.`
3. `The most important think is Multi Threads will simplified the design because it can handle multiple tasks simultaneously, such like  server handling multiple client connections.`

### Disadvantages using Multiple Thread

1. `There is syncronized issues that to prevent race conditions.`
2. `It is hard to debug due due to the non-deterministic nature of thread execution.`
3. `There is pottential for `Deadlocks`, where two or more threads waiting for each other resource.`

## Assignment 4.5 (ReadWriteLock interface for concurrent read-write access to a shared resource)

`ReadWriteLock Interface` is resource that provide a pair of locks for controlling access to a shared resource like `read-only operations` and `write operations`. It allows multiple thread to read the resource simultaneously.

1. `Read Lock` is a function that will allows threads to read simultaneously and if there is a thread read lock, no thread can write lock.
2. `Write Lock` is a function that will allows only one thread to write the resource and if there is a thread write lock, no thread can write and read lock.

### Example Code for ReadWriteLock

`The code can be run from the src source from this folder.`

![Deadlock Prevented](img/Shared%20Resource.png)