package Concurrency.runner;

import Concurrency.coordination.CountDownLatchExample;
import Concurrency.coordination.PhasedBankTransfer;
import Concurrency.coordination.SemaphoreCounter;
import Concurrency.locking.lockfree.AtomicCounter;
import Concurrency.locking.mutex.SynchronizedBlockCounter;
import Concurrency.locking.readwrite.ReentrantReadWriteLockCounter;
import Concurrency.locking.readwrite.StampedLockCounter;

/**
 * Runs the concurrency mechanism demos one by one.
 */
public class ConcurrencyMechanismsRunner {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== AtomicCounter ===");
        AtomicCounter.main(new String[0]);

        System.out.println("\n=== SynchronizedBlockCounter ===");
        SynchronizedBlockCounter.main(new String[0]);

        System.out.println("\n=== ReentrantReadWriteLockCounter ===");
        ReentrantReadWriteLockCounter.main(new String[0]);

        System.out.println("\n=== StampedLockCounter ===");
        StampedLockCounter.main(new String[0]);

        System.out.println("\n=== SemaphoreCounter ===");
        SemaphoreCounter.main(new String[0]);

        System.out.println("\n=== PhasedBankTransfer ===");
        PhasedBankTransfer.main(new String[0]);

        System.out.println("\n=== CountDownLatchExample ===");
        CountDownLatchExample.main(new String[0]);
    }
}


