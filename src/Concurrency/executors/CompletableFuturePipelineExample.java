package Concurrency.executors;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Demonstrates async composition with thenApply/thenCompose and error handling.
 */
public class CompletableFuturePipelineExample {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        try {
            CompletableFuture<String> pipeline = CompletableFuture
                    .supplyAsync(() -> "user-42", executor)
                    .thenCompose(userId -> fetchScoreAsync(userId, executor))
                    .thenApply(score -> "Computed loyalty tier: " + (score >= 100 ? "GOLD" : "SILVER"))
                    .exceptionally(error -> "Pipeline failed: " + error.getMessage());

            System.out.println(pipeline.get());
        } finally {
            executor.shutdown();
        }
    }

    private static CompletableFuture<Integer> fetchScoreAsync(String userId, ExecutorService executor) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new IllegalStateException("Interrupted while fetching score for " + userId, e);
            }
            return 120;
        }, executor);
    }
}

