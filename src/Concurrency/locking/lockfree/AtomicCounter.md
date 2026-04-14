# Atomic Counter & Lock-Free Operations

## Overview
An **Atomic Counter** provides a way to execute thread-safe, lock-free operations on a single variable. In Java, this is typically done using classes from the `java.util.concurrent.atomic` package (e.g., `AtomicInteger`, `AtomicLong`).

## How it works: Compare-And-Swap (CAS)
Instead of using traditional locks (like `synchronized` or `ReentrantLock`), atomic classes rely on **Compare-And-Swap (CAS)**, a low-level hardware operation.

CAS works by taking three parameters:
1. **Memory Location (V)**: Where the variable is stored.
2. **Expected Old Value (A)**: The value the thread believes is currently at the memory location.
3. **New Value (B)**: The new value to be written.

The operation checks: *Is the value at memory location V still equal to A?*
- **If Yes:** It updates V to the new value B.
- **If No:** It means another thread has modified V in the meantime. The operation fails, and the thread will typically read the new value and retry the CAS operation.

This retry loop is often called a **spinlock** or **busy-wait**, but because operations are usually fast, it can be much more efficient than putting the thread to sleep (which involves expensive OS context switching).

## Code Walkthrough
In `AtomicCounter.java`:
- `counter.incrementAndGet()`: Internally uses an infinite loop with a CAS operation to increment the counter until it succeeds.
- `counter.compareAndSet(expected, newValue)`: Exposes the raw CAS operation. It allows you to conditionally update the counter only if it hasn't changed since you last checked.

## Pros & Cons of Lock-Free Atomic Operations

### Advantages
1. **Performance**: Avoids the overhead of thread suspension and context switching caused by explicit locks.
2. **No Deadlocks**: Since threads don't acquire locks or block each other indefinitely, deadlocks are practically impossible.
3. **High Concurrency**: Multiple threads can attempt updates simultaneously; even if some fail and retry, overall system progress is guaranteed (wait-free/lock-free).

### Disadvantages
1. **Single Variable Only**: Operations are only atomic for a single variable. You cannot easily atomically update two separate atomic variables at the same time without additional locking mechanisms.
2. **High Contention Overhead**: Under extremely heavy contention (many threads constantly trying to update the exact same variable), the retry loop can consume a lot of CPU cycles. In such cases, alternatives like Java's `LongAdder` (which maintains an array of counters to reduce contention) might be preferred.
3. **ABA Problem**: If a value changes from A to B and back to A, a CAS operation might succeed even if the value was modified in between. This is typically not an issue for simple counters but can be problematic in complex lock-free data structures (often solved using `AtomicStampedReference`).
