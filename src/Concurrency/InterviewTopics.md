# Java Concurrency Interview Topics

This file extends the practical examples in [the concurrency docs hub](README.md) into the broader theory and APIs expected in senior interviews.

## Navigation

- [Concurrency README](README.md)
- [Generic concurrency patterns](ConcurrencyPatterns.md)
- [Executors guide](executors/Executors.md)
- [Coordination guide](coordination/Coordination.md)
- [Mutex guide](locking/mutex/Mutex.md)

## Repository practice anchors

Study these classes while reading this topic guide:

- [VolatileStopSignal.java](basics/VolatileStopSignal.java)
- [BlockingQueueProducerConsumer.java](coordination/BlockingQueueProducerConsumer.java)
- [ExecutorServiceBatchExample.java](executors/ExecutorServiceBatchExample.java)
- [FutureTimeoutExample.java](executors/FutureTimeoutExample.java)
- [CompletableFuturePipelineExample.java](executors/CompletableFuturePipelineExample.java)
- [DeadlockAvoidanceLockOrdering.java](locking/mutex/DeadlockAvoidanceLockOrdering.java)

## 1) High-level execution management

### Why this matters

Interviewers expect you to avoid raw `new Thread(...)` in production systems and explain task execution policy.

### Topics to master

- `ExecutorService` and thread pools
  - Why pools exist: avoid repeated thread creation/destruction overhead
  - Pool types: fixed, cached, single-thread
  - Queue saturation and rejection policy behavior
- `Callable` vs `Runnable`
  - `Callable<T>` returns a value and can throw checked exceptions
- `Future`
  - `Future.get()` semantics and blocking behavior
  - `get(timeout, unit)` and timeout strategy
  - Cancellation via interruption (`cancel(true)`)

## 2) Advanced async programming (`CompletableFuture`)

### Why this matters

Modern Java services are often async and pipeline-oriented.

### Topics to master

- Chaining:
  - `thenApply` for synchronous transform
  - `thenCompose` for async flatten/composition
  - `thenAccept` for side-effect stage
- Combining:
  - `allOf`, `anyOf`
- Error behavior:
  - `exceptionally`, `handle`
  - Failure propagation across dependent stages

## 3) Concurrent collections

### Why this matters

You need to distinguish thread-safe single operations from thread-safe compound logic.

### Topics to master

- `ConcurrentHashMap`
  - Internal strategy for concurrency without full-map lock
  - Limitation: check-then-act compound sequences still need external synchronization
- `CopyOnWriteArrayList`
  - Best for read-dominant workloads with infrequent writes
- `BlockingQueue`
  - Producer-consumer pipelines and backpressure
  - `ArrayBlockingQueue` (bounded) vs `LinkedBlockingQueue` (optionally bounded)

## 4) Java Memory Model (JMM)

### Why this matters

Interviews test whether you understand why concurrency bugs occur, not just how to patch them.

### Topics to master

- Visibility vs atomicity
  - Visibility: one thread observes another thread's write
  - Atomicity: operation is indivisible
- `volatile`
  - Visibility and ordering guarantee
  - Not enough for non-atomic updates like `counter++`
- Happens-before relationship
  - Formal ordering guarantee for publication safety and visibility correctness

## 5) Liveness problems

### Why this matters

Correct data is not enough if threads stop making progress.

### Topics to master

- Deadlock
  - Coffman conditions
  - Prevention via global lock ordering (see [DeadlockAvoidanceLockOrdering.java](locking/mutex/DeadlockAvoidanceLockOrdering.java))
- Livelock
  - Threads react continuously but do not progress
- Starvation
  - A thread is perpetually denied CPU/lock access

## 6) Advanced thread tools

### Topics to master

- `ThreadLocal`
  - Per-thread request context/state
  - Mandatory cleanup (`remove()`) in long-lived thread pools
- Virtual threads (Java 21+)
  - Lightweight blocking model for massive I/O concurrency
  - Positioning versus classic pool-backed platform threads

## Suggested study order (repo + theory)

1. [README.md](README.md)
2. [RaceConditions.md](basics/RaceConditions.md)
3. [Mutex.md](locking/mutex/Mutex.md)
4. [Coordination.md](coordination/Coordination.md)
5. [Executors.md](executors/Executors.md)
6. [ReadWriteLock.md](locking/readwrite/ReadWriteLock.md)
7. Revisit this file and answer each section with concrete examples from code
