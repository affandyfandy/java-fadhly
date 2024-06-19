import java.util.Arrays;

public class App {
    public static void main(String[] args) {
        //Assignment 4.2 Bank Account
        BankAccount account = new BankAccount(100);

        Thread depositThread = new Thread(new DepositThread(account));
        Thread withdrawThread = new Thread(new WithdrawThread(account));

        depositThread.start();
        withdrawThread.start();

        //Assignment 4.3 Sorting Array Integer
        int[] array = {64, 34, 25, 12, 22, 11, 90};

        System.out.println("Original Array: " + Arrays.toString(array));
        
        int numThreads = 2;
        MultiThreadsBubbleSort.bubbleSortParallel(array, numThreads);

        System.out.println("Sorted Array: " + Arrays.toString(array));

        //Assignment 4.5 ReadWriteLock Interface
        SharedResource resource = new SharedResource();

        Thread writer = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                resource.addItem(i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }, "Writer");

        Thread reader1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                resource.readItems();
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }, "Reader 1");

        Thread reader2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                resource.readItems();
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }, "Reader 2");

        // Start threads
        writer.start();
        reader1.start();
        reader2.start();
    }
}
