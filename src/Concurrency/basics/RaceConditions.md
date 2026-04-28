# RaceConditions.java Explained

## Navigation

- [Concurrency README](../README.md)
- [Generic concurrency patterns](../ConcurrencyPatterns.md)
- [Interview topics](../InterviewTopics.md)
- [RaceConditions.java](RaceConditions.java)

## Why this example matters

It demonstrates a critical interview concept: **thread-safe collection operations are not the same as thread-safe business logic**.

## Core model

`ConcurrentHashMap` guarantees thread safety for each individual operation (`containsKey`, `put`, `remove`).
It does **not** make multi-step check-then-act logic atomic across multiple operations.

## The race condition in this demo

Without an outer lock, both threads can pass the check before either remove completes:

```text
Thread-1: containsKey("key") -> true
Thread-2: containsKey("key") -> true
Thread-1: remove("key")      -> "value"
Thread-2: remove("key")      -> null
```

Both reads were valid at the time, but one subsequent action became stale.

## How synchronization fixes it

Wrapping the full check + action sequence in one synchronized block turns it into a single critical section:

```
// Unsafe: check and action are separate steps
if (map.containsKey("key")) {
    map.remove("key");
}

// Safe: check + action execute atomically at app level
synchronized (map) {
    if (map.containsKey("key")) {
        map.remove("key");
    }
}
```

## Expected output pattern

- `withSync = false`: non-zero null removals (race is observable)
- `withSync = true`: zero null removals (compound operation protected)

## Common interview takeaway

When logic says "check condition, then act based on that condition", assume you need synchronization around the full sequence.

## Study next

- [VolatileStopSignal.java](VolatileStopSignal.java) for visibility guarantees
- [Mutex.md](../locking/mutex/Mutex.md) for lock strategy trade-offs
