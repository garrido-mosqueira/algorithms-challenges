package Concurrency.executors;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Demonstrates timeout and cancellation behavior when waiting on Future.get().
 */
public class FutureTimeoutExample {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        try {
            Future<String> future = executor.submit(() -> {
                Thread.sleep(2000);
                return "slow-result";
            });

            try {
                String result = future.get(500, TimeUnit.MILLISECONDS);
                System.out.println("Result: " + result);
            } catch (TimeoutException timeoutException) {
                System.out.println("Timed out waiting for result; cancelling task.");
                boolean cancelled = future.cancel(true);
                System.out.println("Cancelled: " + cancelled);
            }
        } finally {
            executor.shutdownNow();
        }
    }
}

