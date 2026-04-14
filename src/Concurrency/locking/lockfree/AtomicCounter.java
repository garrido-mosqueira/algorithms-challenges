package Concurrency.locking.lockfree;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Demonstrates Atomic Variables - Lock-free thread-safe operations
 *
 * Use case: Simple atomic operations on single variables
 * Advantage: Better performance, no locks/blocking, uses CAS (Compare-And-Swap)
 * Disadvantage: Only works for individual variables, not complex data structures
 */
public class AtomicCounter {

    private final AtomicLong counter = new AtomicLong(0);

    /**
     * Atomically increments by 1 using Compare-And-Swap
     * No explicit lock needed!
     */
    public void increment() {
        counter.incrementAndGet();
    }

    /**
     * Atomically adds value
     */
    public void addValue(long delta) {
        counter.addAndGet(delta);
    }

    /**
     * Atomically retrieve value
     */
    public long getCounter() {
        return counter.get();
    }

    /**
     * Compare and set: Only set new value if current value matches expected
     * Returns true if successful, false otherwise
     */
    public boolean compareAndSet(long expected, long newValue) {
        return counter.compareAndSet(expected, newValue);
    }

    /**
     * Main demonstrates atomic operations performance
     */
    static void main() throws InterruptedException {
        AtomicCounter counter = new AtomicCounter();
        int numThreads = 10;
        int incrementsPerThread = 100000;

        Thread[] threads = new Thread[numThreads];
        for (int i = 0; i < numThreads; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < incrementsPerThread; j++) {
                    counter.increment();
                }
            });
            threads[i].start();
        }

        long startTime = System.currentTimeMillis();

        // Wait for all threads to complete
        for (Thread thread : threads) {
            thread.join();
        }

        long endTime = System.currentTimeMillis();

        System.out.println("Atomic - Final counter: " + counter.getCounter());
        System.out.println("Expected: " + (numThreads * incrementsPerThread));
        System.out.println("Match: " + (counter.getCounter() == numThreads * incrementsPerThread));
        System.out.println("Time taken: " + (endTime - startTime) + "ms");

        // Demonstrate Compare-And-Set
        System.out.println("\n--- Compare-And-Set Example ---");
        AtomicCounter casCounter = new AtomicCounter();
        casCounter.addValue(5);
        System.out.println("Current value: " + casCounter.getCounter());

        boolean success = casCounter.compareAndSet(5, 10);
        System.out.println("CAS(5->10) success: " + success + ", new value: " + casCounter.getCounter());

        success = casCounter.compareAndSet(5, 20);
        System.out.println("CAS(5->20) success: " + success + ", value unchanged: " + casCounter.getCounter());
    }
}


