# Read-Write Locks

## Overview
Traditional mutexes (like `synchronized` or `ReentrantLock`) are exclusive: only one thread can hold the lock at a time, regardless of whether it's reading or writing. This can become a performance bottleneck in applications where data is read much more frequently than it is written.

**Read-Write Locks** address this by allowing:
*   **Multiple readers concurrently:** If no thread is writing, any number of threads can acquire a read lock simultaneously.
*   **A single writer exclusively:** If a thread holds the write lock, no other thread (reader or writer) can acquire any lock until the writer releases it.

This pattern significantly improves concurrency and throughput for **read-heavy workloads**.

## Types of Read-Write Locks in Java

### 1. `ReentrantReadWriteLock`

**File:** `ReentrantReadWriteLockCounter.java`

This is the standard implementation of a read-write lock in Java, found in the `java.util.concurrent.locks` package. It provides separate locks for reading and writing.

#### How it Works
*   **Read Lock:** Acquired using `readLock().lock()`. Multiple threads can hold the read lock as long as no writer holds the write lock.
*   **Write Lock:** Acquired using `writeLock().lock()`. Only one thread can hold the write lock, and it blocks all other readers and writers.
*   **Reentrancy:** Both the read and write locks are reentrant, meaning a thread already holding a lock can acquire it again.
*   **Downgrading:** A writer can acquire a read lock and then release its write lock (downgrading from write to read lock). This is useful if a thread needs to read the data it just modified.
*   **Upgrading:** A reader cannot directly upgrade to a write lock without releasing its read lock first, which can lead to deadlocks if not handled carefully.

**Pros:**
*   Improved concurrency for read-heavy scenarios.
*   Clear separation of read and write concerns.
*   Reentrant.

**Cons:**
*   Can suffer from writer starvation: if there's a continuous stream of readers, a writer might never get a chance to acquire the write lock.
*   Performance can still be an issue under very high contention or if writes are frequent.

---

### 2. `StampedLock` (Java 8+)

**File:** `StampedLockCounter.java`

`StampedLock` is a more advanced and performant alternative to `ReentrantReadWriteLock`, especially for extremely read-heavy scenarios. It introduces a concept of "stamps" and offers three modes of locking.

#### How it Works
`StampedLock` provides three modes:
1.  **Write Lock:** Exclusive, similar to `ReentrantReadWriteLock`'s write lock. Acquired with `writeLock()`, returns a `stamp`.
2.  **Read Lock:** Shared, similar to `ReentrantReadWriteLock`'s read lock. Acquired with `readLock()`, returns a `stamp`.
3.  **Optimistic Read Lock:** This is the key innovation. Acquired with `tryOptimisticRead()`, it returns a `stamp` but **does not acquire any actual lock**. It's a very lightweight check.
    *   A thread performs its read operations.
    *   It then calls `validate(stamp)` to check if any write occurred while it was reading.
    *   If `validate()` returns `true`, the read was successful and consistent.
    *   If `validate()` returns `false`, a write occurred, and the thread must fall back to acquiring a full `readLock()` and re-reading the data.

**Pros:**
*   **Highest Concurrency for Reads:** Optimistic reads are extremely fast and don't block writers at all. They are ideal when writes are rare and reads are frequent.
*   Can offer better throughput than `ReentrantReadWriteLock` in highly read-heavy scenarios.

**Cons:**
*   **Not Reentrant:** A thread cannot acquire the same lock (read or write) multiple times. This makes it more prone to deadlocks if not used carefully.
*   **No Conditional Variables:** Unlike `ReentrantLock` and `ReentrantReadWriteLock`, `StampedLock` does not support `Condition` objects, making complex coordination harder.
*   **More Complex API:** The API is more intricate due to the stamp-based approach and the need to handle optimistic read validation.
*   **Writer Starvation:** Can also suffer from writer starvation, similar to `ReentrantReadWriteLock`.

## When to Choose Which?

*   **`ReentrantReadWriteLock`:**
    *   When you have a clear read-heavy workload, but not *extremely* read-heavy.
    *   When you need reentrancy.
    *   When you need `Condition` objects for more complex thread coordination.
    *   When simplicity and robustness are preferred over maximum possible throughput.

*   **`StampedLock`:**
    *   When you have an **extremely** read-heavy workload where writes are very rare.
    *   When you need the absolute highest read concurrency and are willing to accept the increased complexity and lack of reentrancy.
    *   When you can tolerate the possibility of re-reading data after an optimistic read fails validation.
    *   When you are confident in handling its non-reentrant nature and do not require `Condition` objects.

In most common read-heavy scenarios, `ReentrantReadWriteLock` is a good, safe choice. `StampedLock` is a powerful optimization for expert users in very specific, performance-critical contexts.
