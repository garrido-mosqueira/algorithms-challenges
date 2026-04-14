# Mutex (Mutual Exclusion)

## Overview
A **Mutex** (Mutual Exclusion) is a fundamental concurrency control mechanism used to prevent race conditions. It ensures that only one thread can execute a specific section of code (called a "critical section") at any given time.

If a thread holds a mutex lock, any other thread attempting to acquire that same lock will block (wait) until the first thread releases it.

## The Problem It Solves
When multiple threads read and write shared data concurrently, their operations can interleave in unpredictable ways, leading to data corruption (race conditions). A mutex serializes access to the shared state, making the operations appear atomic.

## Types of Mutexes in Java

### 1. `synchronized` Methods (Intrinsic Locks)
Every object in Java has a built-in, hidden lock called an "intrinsic lock" or "monitor lock". When you declare an entire instance method as `synchronized`, the thread automatically acquires the lock on the `this` object before executing the method.

- **Example File:** `SynchronizedCounter.java`
- **Pros:** Simple, readable, automatically releases the lock even if an exception occurs.
- **Cons:** Very coarse-grained. The entire method is locked, potentially reducing concurrency more than necessary. It also limits you to locking the current object instance.

### 2. `synchronized` Blocks
Instead of locking the whole method, you can lock just a specific block of code using `synchronized (lockObject)`. This allows you to protect only the true critical section.

- **Example File:** `SynchronizedBlockCounter.java`
- **Pros:** More fine-grained control. You can use a dedicated lock object (e.g., `private final Object lock = new Object();`) to avoid exposing your lock to outside code.
- **Cons:** Slightly more verbose than synchronized methods.

### 3. Explicit Locks (`ReentrantLock`)
Java provides explicit lock classes in the `java.util.concurrent.locks` package. The most common is `ReentrantLock`. A lock is "reentrant" if a thread that already holds the lock can acquire it again without deadlocking itself (useful for recursive method calls).

- **Example File:** `ReentrantCounterLock.java`
- **Pros:** Highly flexible. Provides advanced features like:
  - `tryLock()`: Attempt to acquire the lock without blocking indefinitely.
  - `lockInterruptibly()`: Allows a waiting thread to be interrupted.
  - **Fairness:** Can be configured to grant the lock to the longest-waiting thread.
- **Cons:** Requires manual management. You **must** release the lock in a `finally` block, or you risk deadlocks if an exception is thrown before `unlock()` is called.

## Key Concepts
*   **Critical Section:** The block of code that accesses shared state and must be protected by the mutex.
*   **Deadlock:** Occurs when two or more threads are blocked forever, each waiting for a lock held by the other.
*   **Contention:** Happens when many threads try to acquire the same lock simultaneously. High contention can degrade performance, as threads spend more time waiting than executing.

## When to Use Which?
1. Start with the simplest approach: **`synchronized` blocks or methods**.
2. If you need fine-grained control over the exact lines of code protected, use a **`synchronized` block** with a dedicated private lock object.
3. If you need advanced features like timed lock attempts, interruptible waiting, or fairness, upgrade to a **`ReentrantLock`**.
