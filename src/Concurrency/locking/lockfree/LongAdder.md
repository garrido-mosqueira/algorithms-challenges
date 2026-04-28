# LongAdder and High-Contention Counters

## Navigation

- [Concurrency README](../../README.md)
- [Generic concurrency patterns](../../ConcurrencyPatterns.md)
- [Atomic counter guide](AtomicCounter.md)
- [Read-write locks](../readwrite/ReadWriteLock.md)

## Why this matters

`LongAdder` is built for counters that receive heavy concurrent increments where `AtomicLong` CAS retries become expensive.

## Core model

Instead of a single hot value, `LongAdder` uses:

- a base value for low contention
- striped internal cells for high contention

Threads update different cells, which lowers collision probability and improves write throughput.

## Why `AtomicLong` can degrade under contention

With many writers on one value:

1. Thread reads old value
2. Another thread wins CAS first
3. First thread CAS fails and retries
4. Repeated failures consume CPU

`LongAdder` reduces this by distributing writes.

## Trade-offs

- Better write scalability under heavy contention
- `sum()` is eventually consistent during concurrent updates
- Does not expose full CAS-style API like `compareAndSet`
- Uses more memory than a single `AtomicLong`

## Decision guidance

- Choose `LongAdder` for metrics, telemetry, hit counters, and frequent increments
- Choose `AtomicLong` when exact immediate reads or CAS semantics are required

## Repository mapping

- [LongAdderExample.java](LongAdderExample.java): practical high-contention counter demonstration

## Run

```bash
java -cp src Concurrency.locking.lockfree.LongAdderExample
```

## Study next

- [AtomicCounter.md](AtomicCounter.md)
- [ReadWriteLock.md](../readwrite/ReadWriteLock.md)

