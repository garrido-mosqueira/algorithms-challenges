package Concurrency.locking.lockfree;

import java.util.concurrent.atomic.LongAdder;

/**
 * Demonstrates LongAdder - Highly scalable lock-free counter
 *
 * Use case: High-contention environments where many threads frequently update a common sum
 * Advantage: Significantly better performance than AtomicLong under high contention
 * Disadvantage: Slightly higher memory footprint; get() method is not strictly atomic (eventual consistency)
 */
public class LongAdderExample {

    // LongAdder internally maintains an array of variables (cells) to reduce contention
    // Threads update different cells, and the total sum is calculated when needed.
    private final LongAdder adder = new LongAdder();

    /**
     * Adds the given value
     */
    public void add(long value) {
        adder.add(value);
    }

    /**
     * Increments by 1
     */
    public void increment() {
        adder.increment();
    }

    /**
     * Retrieves the current sum.
     * Note: This operation is not strictly atomic if concurrent updates are happening.
     * It iterates through internal cells and sums them up.
     */
    public long getSum() {
        return adder.sum();
    }

    /**
     * Main method demonstrating LongAdder performance
     */
    static void main() throws InterruptedException {
        LongAdderExample example = new LongAdderExample();
        int numThreads = 10;
        int incrementsPerThread = 1000000; // High number to simulate contention

        Thread[] threads = new Thread[numThreads];
        for (int i = 0; i < numThreads; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < incrementsPerThread; j++) {
                    example.increment();
                }
            });
        }

        long startTime = System.currentTimeMillis();

        // Start all threads
        for (Thread thread : threads) {
            thread.start();
        }

        // Wait for all threads to complete
        for (Thread thread : threads) {
            thread.join();
        }

        long endTime = System.currentTimeMillis();

        System.out.println("LongAdder - Final sum: " + example.getSum());
        System.out.println("Expected: " + (numThreads * incrementsPerThread));
        System.out.println("Match: " + (example.getSum() == numThreads * incrementsPerThread));
        System.out.println("Time taken: " + (endTime - startTime) + "ms");
        
        System.out.println("\nWhy use LongAdder over AtomicLong?");
        System.out.println("Under heavy contention, AtomicLong threads waste CPU cycles continuously");
        System.out.println("failing and retrying Compare-And-Swap (CAS) operations.");
        System.out.println("LongAdder solves this by distributing updates across an array of variables (cells).");
        System.out.println("When a thread fails a CAS, it tries another cell, minimizing contention.");
    }
}
