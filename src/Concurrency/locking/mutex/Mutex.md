# Mutex (Mutual Exclusion)

## Navigation

- [Concurrency README](../../README.md)
- [Generic concurrency patterns](../../ConcurrencyPatterns.md)
- [Interview topics](../../InterviewTopics.md)
- [Read-write locks](../readwrite/ReadWriteLock.md)

## Why this matters

A mutex guarantees that only one thread executes a critical section at a time.
Without it, read-modify-write logic interleaves unpredictably and corrupts shared state.

## Core concepts

- **Critical section:** code that touches shared mutable state
- **Contention:** many threads competing for the same lock
- **Deadlock:** threads wait forever due to circular lock dependency

## Mechanisms covered in this package

### 1) `synchronized` method

**Repository file:** [SynchronizedCounter.java](SynchronizedCounter.java)

- Locks on `this` for the full method body
- Most direct way to achieve correctness for simple state
- Trade-off: coarse lock scope can limit throughput

### 2) `synchronized` block

**Repository file:** [SynchronizedBlockCounter.java](SynchronizedBlockCounter.java)

- Locks only the critical region
- Often uses dedicated lock object to avoid exposing monitor ownership
- Better lock granularity than method-level synchronization

### 3) `ReentrantLock`

**Repository file:** [ReentrantCounterLock.java](ReentrantCounterLock.java)

- Explicit lock lifecycle: `lock()` and `unlock()`
- Supports timed attempts (`tryLock`), interruptible waits, and fairness policy
- Must always release in `finally` to avoid lock leaks

### 4) Lock ordering for deadlock prevention

**Repository file:** [DeadlockAvoidanceLockOrdering.java](DeadlockAvoidanceLockOrdering.java)

- For operations requiring multiple locks, always acquire in one global order
- This removes circular wait, one of the Coffman deadlock conditions
- Works best when all call paths follow the same ordering contract

## Choosing between mutex styles

- Start with `synchronized` for clarity and correctness
- Use block-level synchronization when only a subset of code needs locking
- Choose `ReentrantLock` when you need timed/interruptible semantics
- Enforce lock ordering whenever code may hold multiple locks concurrently

## Run references

```bash
java -cp src Concurrency.locking.mutex.SynchronizedCounterExample
java -cp src Concurrency.locking.mutex.SynchronizedBlockCounter
java -cp src Concurrency.locking.mutex.ReentrantCounterLockExample
java -cp src Concurrency.locking.mutex.DeadlockAvoidanceLockOrdering
```

## Study next

- [AtomicCounter.md](../lockfree/AtomicCounter.md)
- [ReadWriteLock.md](../readwrite/ReadWriteLock.md)
