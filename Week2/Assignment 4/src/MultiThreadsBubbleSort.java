import java.util.Arrays;

public class MultiThreadsBubbleSort {
    public static void bubbleSortParallel(int[] array, int numThreads) {
        int len = array.length;
        int numElements = (int) Math.ceil((double) len / numThreads);

        Thread[] threads = new Thread[numThreads];

        for (int i = 0; i < numThreads; i++) {
            final int start = i * numElements;
            final int end = Math.min(start + numElements, len);

            threads[i] = new Thread(() -> {
                bubbleSort(array, start, end);
            });

            threads[i].start();
        }

        // Wait for all threads to complete
        for (int i = 0; i < numThreads; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void bubbleSort(int[] array, int start, int end) {
        for (int i = start; i < end - 1; i++) {
            for (int j = start; j < end - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }
}
