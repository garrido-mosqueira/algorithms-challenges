package Concurrency.locking.mutex;

/**
 * Demonstrates using CounterLock safely from multiple threads.
 */
public class CounterLockExample {

    static void main() throws InterruptedException {
        CounterLock counter = new CounterLock();
        int numThreads = 4;
        int incrementsPerThread = 250_000;

        Thread[] threads = new Thread[numThreads];
        for (int i = 0; i < numThreads; i++) {
            int workerIndex = i + 1;
            threads[i] = new Thread(() -> incrementCounter(counter, incrementsPerThread),
                    "counter-lock-worker-" + workerIndex);
            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        long expected = (long) numThreads * incrementsPerThread;
        long actual = counter.getCount();

        System.out.println("CounterLock - Final counter: " + actual);
        System.out.println("Expected: " + expected);
        System.out.println("Match: " + (actual == expected));
    }

    private static void incrementCounter(CounterLock counter, int incrementsPerThread) {
        for (int i = 0; i < incrementsPerThread; i++) {
            counter.inc();
        }
    }
}

