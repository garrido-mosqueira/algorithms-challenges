package Concurrency.coordination;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Demonstrates CyclicBarrier by running bank-account updates in phases.
 */
public class PhasedBankTransfer {

    static class Account {
        private final AtomicLong balance;

        Account(long initialBalance) {
            this.balance = new AtomicLong(initialBalance);
        }

        void applyDelta(long delta) {
            balance.addAndGet(delta);
        }

        long getBalance() {
            return balance.get();
        }
    }

    static void main() throws InterruptedException {
        int accountCount = 5;
        int phases = 3;

        Account[] accounts = new Account[accountCount];
        for (int i = 0; i < accountCount; i++) {
            accounts[i] = new Account(1_000);
        }

        CyclicBarrier barrier = new CyclicBarrier(accountCount,
                () -> System.out.println("All accounts completed current phase."));

        Thread[] workers = new Thread[accountCount];
        for (int i = 0; i < accountCount; i++) {
            final int accountId = i;
            workers[i] = new Thread(() ->
                    runPhases(accounts[accountId], accountId, phases, barrier));
            workers[i].start();
        }

        for (Thread worker : workers) {
            worker.join();
        }

        long total = 0;
        for (int i = 0; i < accountCount; i++) {
            long value = accounts[i].getBalance();
            total += value;
            System.out.println("Account-" + i + " final balance: " + value);
        }

        long expectedPerAccount = 1_000 + 100 + 200 + 300;
        System.out.println("Expected per account: " + expectedPerAccount);
        System.out.println("Total balance: " + total);
    }

    private static void runPhases(Account account, int accountId, int phases, CyclicBarrier barrier) {
        for (int phase = 1; phase <= phases; phase++) {
            long delta = phase * 100L;
            account.applyDelta(delta);
            System.out.println("Account-" + accountId + " applied delta " + delta + " in phase " + phase);
            awaitBarrier(barrier);
        }
    }

    private static void awaitBarrier(CyclicBarrier barrier) {
        try {
            barrier.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (BrokenBarrierException e) {
            throw new IllegalStateException("Barrier was broken", e);
        }
    }
}

