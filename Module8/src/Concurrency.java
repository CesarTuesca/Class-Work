import java.util.Random;
/**
 * 
 * @author Cesar
 *
 */
public class Concurrency {
    // Define the array size to 200 mil and number of threads you want to use
    private static final int arraySize = 200000000;
    private static final int numberOfThreads = 10;

/**
 *     // Create an array for the random generated numbers and a variable to represent the sum of the random numbers
 */
    private static int[] arr = new int[arraySize];
    private static int sum = 0;
/**
 * 
 * @param args for main method
 */
    public static void main(String[] args) {
        // Generate the 200 mil random numbers between 0 and 10 and put them in an array
        Random rand = new Random();
        for (int i = 0; i < arraySize; i++) {
            arr[i] = rand.nextInt(10) + 1;
        }

        // Compute sum using multiple threads
        long startTime = System.currentTimeMillis();
        Thread[] threads = new Thread[numberOfThreads];
        for (int i = 0; i < numberOfThreads; i++) {
            // Divide the array into equal parts for each thread
            int startIdx = i * (arraySize / numberOfThreads);
            int endIdx = (i == numberOfThreads - 1) ? arraySize : (i + 1) * (arraySize / numberOfThreads);
            // Create a new thread to sum the values in the subarray
            threads[i] = new SumThread(startIdx, endIdx);
            threads[i].start();
        }
        for (int i = 0; i < numberOfThreads; i++) {
            // Wait for all threads, threads[i] to finish by using .join(); before you move on the adding up the sums
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println(numberOfThreads + " Threads Sum: " + sum);
        System.out.println(numberOfThreads + " Threads Time: " + (endTime - startTime) + " milliseconds");

        // Sum using a single thread
        startTime = System.currentTimeMillis();
        int singleThreadSum = 0;
        for (int i = 0; i < arraySize; i++) {
            // Sum all values in the array using a single thread
            singleThreadSum += arr[i];
        }
        endTime = System.currentTimeMillis();
        System.out.println("Single thread sum: " + singleThreadSum);
        System.out.println("Single thread time: " + (endTime - startTime) + " milliseconds");
    }

    // Class to handle summing values in a subarray
    private static class SumThread extends Thread {
        private int startIdx;
        private int endIdx;

        public SumThread(int startIdx, int endIdx) {
            this.startIdx = startIdx;
            this.endIdx = endIdx;
        }

        public void run() {
            // Sum all values in the subarray assigned to this thread
            int threadSum = 0;
            for (int i = startIdx; i < endIdx; i++) {
                threadSum += arr[i];
            }
            // Add the sum from this thread to the total sum (using synchronization to avoid race conditions)
            synchronized (Concurrency.class) {
                sum += threadSum;
            }
        }
    }
}