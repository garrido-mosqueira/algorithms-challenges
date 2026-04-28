package Concurrency.basics;

/**
 * Demonstrates visibility guarantees provided by a volatile stop flag.
 */
public class VolatileStopSignal {

    private static volatile boolean keepRunning = true;

    static void main() throws InterruptedException {
        Thread worker = new Thread(() -> {
            long iterations = 0;
            while (keepRunning) {
                iterations++;
            }
            System.out.println("Worker observed stop signal after iterations=" + iterations);
        }, "volatile-worker");

        worker.start();
        Thread.sleep(300);

        keepRunning = false;
        System.out.println("Main published stop signal");

        worker.join();
        System.out.println("Worker stopped cleanly");
    }
}

