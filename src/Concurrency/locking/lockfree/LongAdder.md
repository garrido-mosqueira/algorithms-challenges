# LongAdder & High-Contention Operations

## Overview
`LongAdder` is a highly scalable, lock-free counter introduced in Java 8 as part of the `java.util.concurrent.atomic` package. It is designed to be a better alternative to `AtomicLong` in scenarios where multiple threads are frequently updating a common sum.

## The Problem with AtomicLong
While `AtomicLong` is lock-free and uses Compare-And-Swap (CAS) for updates, it can suffer from performance degradation under **high contention**.
- When many threads try to update the exact same variable simultaneously, most of the CAS operations will fail because another thread just changed the value.
- Failed threads enter a retry loop (spinlock), wasting CPU cycles continuously failing and retrying until they finally succeed.

## How LongAdder Solves This
Instead of a single memory location, `LongAdder` maintains a base value and an **array of variables (called "cells")**.
1. **Low Contention:** If there's no contention, updates are applied directly to the base value (behaving similarly to `AtomicLong`).
2. **High Contention:** If a thread fails to update the base value via CAS (indicating contention), `LongAdder` hashes the thread to a specific cell in the array and performs the CAS operation on that cell instead.
3. This approach effectively distributes the updates across multiple memory locations, drastically reducing contention and minimizing wasted CPU spin time.

## Calculating the Total Sum
To get the current total sum (via the `sum()` or `longValue()` methods), `LongAdder` simply adds the base value and the values of all the individual cells together.

**Important Note:** The `sum()` method is *eventually consistent*. Since there are no locks, if threads are actively updating the cells while `sum()` is executing, the returned value might not reflect the absolute exact state at that split millisecond. Therefore, it's best suited for collecting statistics, metrics, and histograms, rather than for strict synchronization logic where the exact value is required for correctness.

## Pros & Cons

### Advantages
1. **Exceptional Throughput:** Significantly faster than `AtomicLong` under heavy thread contention.
2. **Reduced CPU Usage:** Eliminates the CPU cycles wasted on failed CAS retries.

### Disadvantages
1. **Higher Memory Footprint:** Allocates an array of cells, which takes slightly more memory than a single `AtomicLong`.
2. **Eventual Consistency on Reads:** The `sum()` operation is not strictly atomic if concurrent updates are happening.
3. **Limited Operations:** It primarily supports addition/subtraction. It doesn't support operations like `compareAndSet`, which `AtomicLong` does.
