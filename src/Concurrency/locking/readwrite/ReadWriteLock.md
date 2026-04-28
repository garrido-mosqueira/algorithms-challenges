# Read-Write Locks

## Navigation

- [Concurrency README](../../README.md)
- [Generic concurrency patterns](../../ConcurrencyPatterns.md)
- [Mutex guide](../mutex/Mutex.md)
- [Executors guide](../../executors/Executors.md)

## Why this matters

A normal mutex serializes all access, even reads.
Read-write locking improves throughput when reads dominate writes.

## Core model

- Multiple readers may proceed concurrently
- Writers require exclusive access
- Read-heavy workloads benefit the most

## 1) `ReentrantReadWriteLock`

**Repository file:** [ReentrantReadWriteLockCounter.java](ReentrantReadWriteLockCounter.java)

### Characteristics

- Shared read lock + exclusive write lock
- Reentrant behavior for both lock types
- Good default for most read-heavy services

### Trade-offs

- Higher overhead than plain mutex in write-heavy paths
- Writer starvation may occur under continuous read pressure

### Run

```bash
java -cp src Concurrency.locking.readwrite.ReentrantReadWriteLockCounter
```

## 2) `StampedLock`

**Repository file:** [StampedLockCounter.java](StampedLockCounter.java)

### Characteristics

- Supports write lock, read lock, and optimistic read mode
- Optimistic read does not acquire a real lock; result must be validated
- Can outperform `ReentrantReadWriteLock` when writes are rare

### Trade-offs

- Not reentrant
- More complex API and correctness burden
- Improper stamp handling causes subtle bugs

### Run

```bash
java -cp src Concurrency.locking.readwrite.StampedLockCounter
```

## Decision guidance

- Choose `ReentrantReadWriteLock` when maintainability and predictable behavior matter most
- Choose `StampedLock` for well-profiled read-heavy hotspots where optimistic reads materially help

## Study next

- [Mutex.md](../mutex/Mutex.md)
- [Executors.md](../../executors/Executors.md)
