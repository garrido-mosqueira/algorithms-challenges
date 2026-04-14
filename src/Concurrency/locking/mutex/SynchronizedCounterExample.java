package Concurrency.locking.mutex;

/**
 * Demonstrates using a synchronized counter from multiple threads.
 */
public class SynchronizedCounterExample {

    static void main() throws InterruptedException {
        SynchronizedCounter counter = new SynchronizedCounter();
        int incrementsPerThread = 1_000_000;

        Thread thread1 = new Thread(() -> incrementCounter(counter, incrementsPerThread), "counter-thread-1");
        Thread thread2 = new Thread(() -> incrementCounter(counter, incrementsPerThread), "counter-thread-2");

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        long expected = 2L * incrementsPerThread;
        System.out.println("MyCounter - Final counter: " + counter.getCounter());
        System.out.println("Expected: " + expected);
        System.out.println("Match: " + (counter.getCounter() == expected));
    }

    private static void incrementCounter(SynchronizedCounter counter, int incrementsPerThread) {
        for (int i = 0; i < incrementsPerThread; i++) {
            counter.increment();
        }
    }
}

