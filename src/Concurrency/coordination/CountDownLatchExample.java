package Concurrency.coordination;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Demonstrates CountDownLatch by waiting for workers to finish before reading result.
 */
public class CountDownLatchExample {

    static class DataProcessor extends Thread {
        private final String workerName;
        private final CountDownLatch latch;
        private final AtomicLong total;

        DataProcessor(String workerName, CountDownLatch latch, AtomicLong total) {
            this.workerName = workerName;
            this.latch = latch;
            this.total = total;
        }

        @Override
        public void run() {
            try {
                long produced = workerName.length() * 10L;
                total.addAndGet(produced);
                System.out.println(workerName + " produced " + produced);
            } finally {
                latch.countDown();
                System.out.println(workerName + " completed. Remaining: " + latch.getCount());
            }
        }
    }

    static void main() throws InterruptedException {
        int workers = 4;
        CountDownLatch latch = new CountDownLatch(workers);
        AtomicLong total = new AtomicLong(0);

        for (int i = 1; i <= workers; i++) {
            new DataProcessor("Worker-" + i, latch, total).start();
        }

        System.out.println("Main waiting for workers...");
        latch.await();

        System.out.println("All workers done.");
        System.out.println("Final aggregated value: " + total.get());
    }
}


