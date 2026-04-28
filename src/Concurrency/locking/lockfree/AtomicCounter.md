# Atomic Counter and Lock-Free Operations

## Navigation

- [Concurrency README](../../README.md)
- [Generic concurrency patterns](../../ConcurrencyPatterns.md)
- [Mutex guide](../mutex/Mutex.md)
- [Read-write locks](../readwrite/ReadWriteLock.md)

## Why this matters

`AtomicLong` gives lock-free, thread-safe updates for simple shared counters without explicit mutexes.

## Core model: CAS (compare-and-swap)

CAS checks whether a memory location still has an expected value.
If yes, it writes the new value atomically. If no, it fails and the caller retries.

In abstract form:

1. Read current value
2. Compute proposed new value
3. CAS(expected, proposed)
4. Retry if CAS fails

## Why this helps

- No explicit `lock()` / `unlock()` lifecycle
- No lock ownership deadlock for this single-variable update pattern
- Usually higher throughput than lock-based counters at moderate contention

## Trade-offs and limits

- Atomicity is per variable, not across multiple fields
- High contention can cause many failed CAS retries (CPU burn)
- For heavy concurrent increments, `LongAdder` often scales better
- The ABA problem can matter in some lock-free structures (less relevant for pure counters)

## Repository mapping

- [AtomicCounter.java](AtomicCounter.java): `incrementAndGet()` and `compareAndSet(...)` usage
- [LongAdderExample.java](LongAdderExample.java): contention-oriented alternative

## Run

```bash
java -cp src Concurrency.locking.lockfree.AtomicCounter
```

## Study next

- [LongAdder.md](LongAdder.md)
- [VolatileStopSignal.java](../../basics/VolatileStopSignal.java) (visibility vs atomicity)
