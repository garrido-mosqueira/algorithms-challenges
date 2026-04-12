# Concurrency Demos

This folder contains runnable examples organized by category:

- `Concurrency.locking.lockfree.AtomicCounter` (lock-free CAS)
- `Concurrency.locking.mutex.SynchronizedBlockCounter` (`synchronized` block)
- `Concurrency.locking.readwrite.ReentrantReadWriteLockCounter` (many readers, one writer)
- `Concurrency.locking.readwrite.StampedLockCounter` (optimistic reads)
- `Concurrency.coordination.SemaphoreCounter` (limited permits)
- `Concurrency.coordination.PhasedBankTransfer` (`CyclicBarrier` phases)
- `Concurrency.coordination.CountDownLatchExample` (`CountDownLatch` coordination)
- `Concurrency.runner.ConcurrencyMechanismsRunner` (runs all demos)

## Run one class

```bash
javac $(find src/Concurrency -name "*.java")
java -cp src Concurrency.locking.lockfree.AtomicCounter
```

## Run all demos

```bash
javac $(find src/Concurrency -name "*.java")
java -cp src Concurrency.runner.ConcurrencyMechanismsRunner
```

