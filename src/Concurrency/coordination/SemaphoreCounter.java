package Concurrency.coordination;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Demonstrates Semaphore - Controls access with limited number of permits
 *
 * Use case: Limiting concurrent access to limited resources (connection pool, etc)
 * Advantage: Flexible control over number of concurrent threads
 * Disadvantage: More overhead than simple locks, can be complex to manage
 */
public class SemaphoreCounter {

    private final AtomicLong counter = new AtomicLong(0);
    private final Semaphore semaphore;

    public SemaphoreCounter(int maxConcurrentAccess) {
        this.semaphore = new Semaphore(maxConcurrentAccess);
    }

    /**
     * Acquire permit before incrementing
     * If no permits available, thread waits
     */
    public void increment() {
        try {
            semaphore.acquire();  // Wait for permit
            counter.incrementAndGet();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            semaphore.release();  // Release permit
        }
    }

    /**
     * Try to acquire permit without blocking
     */
    public boolean tryIncrement() {
        if (semaphore.tryAcquire()) {
            try {
                counter.incrementAndGet();
                return true;
            } finally {
                semaphore.release();
            }
        }
        return false;
    }

    public long getCounter() {
        return counter.get();
    }

    public int availablePermits() {
        return semaphore.availablePermits();
    }

    /**
     * Main demonstrates semaphore with limited concurrent access
     */
    static void main() throws InterruptedException {
        int maxConcurrentAccess = 3;
        SemaphoreCounter counter = new SemaphoreCounter(maxConcurrentAccess);
        int numThreads = 10;
        int operationsPerThread = 10;
        Thread[] threads = new Thread[numThreads];
        for (int i = 0; i < numThreads; i++) {
            final int threadId = i;
            threads[i] = new Thread(() -> {
                for (int j = 0; j < operationsPerThread; j++) {
                    counter.increment();
                    if (j % 5 == 0) {
                        System.out.println("Thread-" + threadId + " incremented, permits left: "
                                + counter.availablePermits());
                    }
                }
            });
            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println("Semaphore - Final counter: " + counter.getCounter());
        System.out.println("Expected: " + (numThreads * operationsPerThread));
        System.out.println("Match: " + (counter.getCounter() == numThreads * operationsPerThread));

        demonstrateConnectionPool();
    }

    private static void demonstrateConnectionPool() throws InterruptedException {
        final Semaphore connectionPool = new Semaphore(2);
        Thread[] workers = new Thread[4];

        for (int i = 0; i < workers.length; i++) {
            final int id = i;
            workers[i] = new Thread(() -> {
                try {
                    System.out.println("Task-" + id + " waiting for connection");
                    connectionPool.acquire();
                    try {
                        System.out.println("Task-" + id + " acquired connection");
                        Thread.sleep(100);
                    } finally {
                        connectionPool.release();
                        System.out.println("Task-" + id + " released connection");
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
            workers[i].start();
        }

        for (Thread worker : workers) {
            worker.join();
        }
    }
}

