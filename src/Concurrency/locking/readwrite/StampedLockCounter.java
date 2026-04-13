package Concurrency.locking.readwrite;

import java.util.concurrent.locks.StampedLock;

/**
 * Demonstrates StampedLock - Optimized version of ReadWriteLock (Java 8+)
 *
 * Use case: High-concurrency scenarios with many readers and few writers
 * Advantage: Faster than ReentrantReadWriteLock, supports optimistic reads
 * Disadvantage: More complex to use correctly, cannot be reentrant
 */
public class StampedLockCounter {

    private long counter = 0;
    private final StampedLock stampedLock = new StampedLock();

    /**
     * Write operation: Exclusive lock (same as ReadWriteLock write lock)
     */
    public void increment() {
        long stamp = stampedLock.writeLock();
        try {
            counter++;
        } finally {
            stampedLock.unlockWrite(stamp);
        }
    }

    /**
     * Read operation with optimistic lock:
     * Tries to read without acquiring lock first
     * If data was modified during read, retries with read lock
     */
    public long getCounterOptimistic() {
        long stamp = stampedLock.tryOptimisticRead();
        long currentValue = counter;

        // Check if lock was valid during read
        if (!stampedLock.validate(stamp)) {
            // If not, retry with actual read lock
            stamp = stampedLock.readLock();
            try {
                currentValue = counter;
            } finally {
                stampedLock.unlockRead(stamp);
            }
        }
        return currentValue;
    }

    /**
     * Pessimistic read (traditional read lock)
     */
    public long getCounterPessimistic() {
        long stamp = stampedLock.readLock();
        try {
            return counter;
        } finally {
            stampedLock.unlockRead(stamp);
        }
    }

    /**
     * Main demonstrates StampedLock performance benefits
     */
    static void main() throws InterruptedException {
        StampedLockCounter counter = new StampedLockCounter();
        int numReaderThreads = 10;
        int numWriterThreads = 1;
        int operationsPerThread = 5000;

        Thread[] readerThreads = new Thread[numReaderThreads];
        Thread[] writerThreads = new Thread[numWriterThreads];

        // Create reader threads
        for (int i = 0; i < numReaderThreads; i++) {
            readerThreads[i] = new Thread(() -> {
                for (int j = 0; j < operationsPerThread; j++) {
                    counter.getCounterOptimistic();  // Uses optimistic read
                }
            });
        }

        // Create writer threads
        for (int i = 0; i < numWriterThreads; i++) {
            writerThreads[i] = new Thread(() -> {
                for (int j = 0; j < operationsPerThread; j++) {
                    counter.increment();
                }
            });
        }

        long startTime = System.currentTimeMillis();

        // Start all threads
        for (Thread t : readerThreads) t.start();
        for (Thread t : writerThreads) t.start();

        // Wait for completion
        for (Thread t : readerThreads) t.join();
        for (Thread t : writerThreads) t.join();

        long endTime = System.currentTimeMillis();

        System.out.println("StampedLock - Final counter: " + counter.getCounterPessimistic());
        System.out.println("Expected: " + (numWriterThreads * operationsPerThread));
        System.out.println("Match: " + (counter.getCounterPessimistic() == numWriterThreads * operationsPerThread));
        System.out.println("Time taken: " + (endTime - startTime) + "ms");
    }
}


