package Concurrency.executors;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Demonstrates batching tasks with a fixed thread pool and Callable results.
 */
public class ExecutorServiceBatchExample {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        try {
            List<Callable<String>> tasks = new ArrayList<>();
            for (int i = 1; i <= 6; i++) {
                int number = i;
                tasks.add(() -> {
                    Thread.sleep(100L * number);
                    return Thread.currentThread().getName() + " processed " + number + " -> " + (number * number);
                });
            }

            List<Future<String>> futures = executor.invokeAll(tasks);
            for (Future<String> future : futures) {
                System.out.println(future.get());
            }
        } finally {
            executor.shutdown();
        }
    }
}

