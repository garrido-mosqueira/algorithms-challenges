package Concurrency.locking.mutex;

/**
 * Demonstrates synchronized block - Monitor lock at object level
 *
 * Use case: When you only need to protect specific code sections, not entire methods.
 * Advantage: More granular control than method synchronization
 * Disadvantage: Can be error-prone if not used carefully
 */
public class SynchronizedBlockCounter {

    private long counter = 0;
    private final Object lock = new Object();  // Separate lock object

    /**
     * Only the critical section is synchronized
     */
    public void increment() {
        synchronized (lock) {
            counter++;
        }
    }

    public long getCounter() {
        synchronized (lock) {
            return counter;
        }
    }

    /**
     * Main demonstrates thread safety with synchronized blocks
     */
    public static void main(String[] args) throws InterruptedException {
        SynchronizedBlockCounter counter = new SynchronizedBlockCounter();
        int numThreads = 10;
        int incrementsPerThread = 10000;

        Thread[] threads = new Thread[numThreads];
        for (int i = 0; i < numThreads; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < incrementsPerThread; j++) {
                    counter.increment();
                }
            });
            threads[i].start();
        }

        // Wait for all threads to complete
        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println("SynchronizedBlock - Final counter: " + counter.getCounter());
        System.out.println("Expected: " + (numThreads * incrementsPerThread));
        System.out.println("Match: " + (counter.getCounter() == numThreads * incrementsPerThread));
    }
}


