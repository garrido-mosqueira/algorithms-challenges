package Concurrency.locking.readwrite;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Demonstrates ReadWriteLock - Allows multiple concurrent readers, exclusive writer
 *
 * Use case: When you have many read operations and few write operations
 * Advantage: Multiple threads can read simultaneously (better performance)
 * Disadvantage: Overhead for lock acquisition, slower for write-heavy scenarios
 */
public class ReentrantReadWriteLockCounter {

    private long counter = 0;
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    /**
     * Write lock: Exclusive access, prevents other reads and writes
     */
    public void increment() {
        try {
            readWriteLock.writeLock().lock();
            counter++;
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    /**
     * Read lock: Shared access, allows concurrent reads but blocks writers
     */
    public long getCounter() {
        try {
            readWriteLock.readLock().lock();
            return counter;
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    /**
     * Main demonstrates concurrent read-heavy scenario
     */
    public static void main(String[] args) throws InterruptedException {
        ReentrantReadWriteLockCounter counter = new ReentrantReadWriteLockCounter();
        int numReaderThreads = 8;
        int numWriterThreads = 2;
        int operationsPerThread = 5000;

        Thread[] readerThreads = new Thread[numReaderThreads];
        Thread[] writerThreads = new Thread[numWriterThreads];

        // Create reader threads (many concurrent readers)
        for (int i = 0; i < numReaderThreads; i++) {
            readerThreads[i] = new Thread(() -> {
                for (int j = 0; j < operationsPerThread; j++) {
                    long value = counter.getCounter();
                    // Simulate read operation
                }
            });
        }

        // Create writer threads (few writers)
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

        System.out.println("ReadWriteLock - Final counter: " + counter.getCounter());
        System.out.println("Expected: " + (numWriterThreads * operationsPerThread));
        System.out.println("Match: " + (counter.getCounter() == numWriterThreads * operationsPerThread));
        System.out.println("Time taken: " + (endTime - startTime) + "ms");
    }
}


