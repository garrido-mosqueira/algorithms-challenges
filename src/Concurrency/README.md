# Java Concurrency Deep-Dive Reference

This file is the canonical index for the concurrency material in `src/Concurrency`.

## 1) Why this folder matters

This package is designed to answer three core interview and production questions:

1. How do I protect shared mutable state correctly?
2. How do I coordinate progress between concurrent tasks?
3. How do I execute async work without manually managing raw threads?

The examples intentionally move from fundamentals to modern APIs:

- Shared state and race conditions
- Visibility and atomicity
- Lock-based and lock-free designs
- Coordination primitives
- Executor-based and pipeline-based async programming
- Liveness safety (deadlock prevention)

## 2) Package map

```text
Concurrency/
|- ConcurrencyPatterns.md
|- basics/
|  |- MyRunnable.java
|  |- RaceConditions.java
|  |- RaceConditions.md
|  |- ThreadExample.java
|  `- VolatileStopSignal.java
|- coordination/
|  |- BlockingQueueProducerConsumer.java
|  |- Coordination.md
|  |- CountDownLatchExample.java
|  |- PhasedBankTransfer.java
|  `- SemaphoreCounter.java
|- executors/
|  |- CompletableFuturePipelineExample.java
|  |- ExecutorServiceBatchExample.java
|  |- Executors.md
|  `- FutureTimeoutExample.java
|- locking/
|  |- lockfree/
|  |  |- AtomicCounter.java
|  |  |- AtomicCounter.md
|  |  |- LongAdder.md
|  |  `- LongAdderExample.java
|  |- mutex/
|  |  |- DeadlockAvoidanceLockOrdering.java
|  |  |- Mutex.md
|  |  |- ReentrantCounterLock.java
|  |  |- ReentrantCounterLockExample.java
|  |  |- SynchronizedBlockCounter.java
|  |  |- SynchronizedCounter.java
|  |  `- SynchronizedCounterExample.java
|  `- readwrite/
|     |- ReadWriteLock.md
|     |- ReentrantReadWriteLockCounter.java
|     `- StampedLockCounter.java
|- patterns/
|  `- singleton/
|     |- DoubleCheckedLockingSingleton.java
|     |- EnumSingleton.java
|     |- InitializationOnDemandSingleton.java
|     `- SingletonPatterns.md
|- InterviewTopics.md
`- README.md
```

## 2.5) Documentation hub

- [Generic concurrency patterns](ConcurrencyPatterns.md)
- [Interview topics](InterviewTopics.md)
- [Race conditions](basics/RaceConditions.md)
- [Mutex / mutual exclusion](locking/mutex/Mutex.md)
- [Lock-free counters](locking/lockfree/AtomicCounter.md)
- [High-contention counters](locking/lockfree/LongAdder.md)
- [Read-write locks](locking/readwrite/ReadWriteLock.md)
- [Coordination primitives](coordination/Coordination.md)
- [Executors and futures](executors/Executors.md)
- [Singleton patterns](patterns/singleton/SingletonPatterns.md)

## 3) Concept map (what each area teaches)

### Basics

- [`Concurrency.basics.ThreadExample`](basics/ThreadExample.java): thread lifecycle basics with shared runnable instances
- [`Concurrency.basics.RaceConditions`](basics/RaceConditions.java): check-then-act race in compound map logic
- [`Concurrency.basics.VolatileStopSignal`](basics/VolatileStopSignal.java): visibility guarantees with `volatile`

### Locking

- [`Concurrency.locking.mutex.SynchronizedCounter`](locking/mutex/SynchronizedCounter.java): method-level intrinsic lock
- [`Concurrency.locking.mutex.SynchronizedBlockCounter`](locking/mutex/SynchronizedBlockCounter.java): block-scoped locking
- [`Concurrency.locking.mutex.ReentrantCounterLock`](locking/mutex/ReentrantCounterLock.java): explicit lock lifecycle and lock API features
- [`Concurrency.locking.mutex.DeadlockAvoidanceLockOrdering`](locking/mutex/DeadlockAvoidanceLockOrdering.java): multi-lock deadlock prevention by global ordering
- [`Concurrency.locking.lockfree.AtomicCounter`](locking/lockfree/AtomicCounter.java): CAS-based lock-free updates on single variable state
- [`Concurrency.locking.lockfree.LongAdderExample`](locking/lockfree/LongAdderExample.java): contention-resistant counter strategy
- [`Concurrency.locking.readwrite.ReentrantReadWriteLockCounter`](locking/readwrite/ReentrantReadWriteLockCounter.java): concurrent reads + exclusive writes
- [`Concurrency.locking.readwrite.StampedLockCounter`](locking/readwrite/StampedLockCounter.java): optimistic read fast path for extreme read-heavy workloads

### Coordination

- [`Concurrency.coordination.CountDownLatchExample`](coordination/CountDownLatchExample.java): one-shot completion gate
- [`Concurrency.coordination.PhasedBankTransfer`](coordination/PhasedBankTransfer.java): repeated phase barrier
- [`Concurrency.coordination.SemaphoreCounter`](coordination/SemaphoreCounter.java): bounded concurrency with permits
- [`Concurrency.coordination.BlockingQueueProducerConsumer`](coordination/BlockingQueueProducerConsumer.java): producer-consumer handoff and backpressure

### Executors and async pipelines

- [`Concurrency.executors.ExecutorServiceBatchExample`](executors/ExecutorServiceBatchExample.java): task batching with fixed pool + `invokeAll`
- [`Concurrency.executors.FutureTimeoutExample`](executors/FutureTimeoutExample.java): timeout handling and cancellation
- [`Concurrency.executors.CompletableFuturePipelineExample`](executors/CompletableFuturePipelineExample.java): async composition, transform, fallback

### Singleton patterns

- [`Concurrency.patterns.singleton.DoubleCheckedLockingSingleton`](patterns/singleton/DoubleCheckedLockingSingleton.java): lazy singleton requiring `volatile`
- [`Concurrency.patterns.singleton.InitializationOnDemandSingleton`](patterns/singleton/InitializationOnDemandSingleton.java): class-loader-backed lazy initialization
- [`Concurrency.patterns.singleton.EnumSingleton`](patterns/singleton/EnumSingleton.java): JVM-enforced singleton safety

## 4) Mechanism comparison

| Mechanism | Primary use | Strength | Risk / trade-off |
|---|---|---|---|
| `synchronized` | Simple shared state protection | Strong correctness model, low API overhead | Coarse scope if overused |
| `ReentrantLock` | Advanced mutex behavior | Timed/interruptible acquisition, fairness option | Must unlock reliably in `finally` |
| `AtomicLong` | Simple lock-free counters | Fast CAS-based updates | Retry loops under heavy contention |
| `LongAdder` | High-contention increments | Better throughput at scale | Eventual consistency at read time (`sum()`) |
| `ReentrantReadWriteLock` | Read-heavy state | Multiple readers concurrently | Writer starvation in some workloads |
| `StampedLock` | Extreme read-heavy hotspots | Optimistic reads reduce lock overhead | Non-reentrant and more complex correctness |
| `Semaphore` | Resource pool limiting | Bounded concurrent access | Can be misapplied as a mutex substitute |
| `CountDownLatch` | Wait for one-time completion | Simple one-way coordination | Non-reusable once count hits zero |
| `CyclicBarrier` | Multi-phase progression | Reusable synchronization point | Progress depends on all parties arriving |
| `BlockingQueue` | Producer-consumer pipelines | Built-in blocking and backpressure | Capacity and queue choice require tuning |
| `ExecutorService` | Managed task execution | Thread lifecycle reuse and queueing | Wrong pool/rejection strategy can overload system |
| `CompletableFuture` | Async composition | Expressive non-blocking pipelines | Error propagation and blocking joins need discipline |
| Lock ordering | Multi-lock safety | Removes circular wait deadlock path | Must be globally consistent in all call sites |

## 5) Recommended learning order

For a language-agnostic view of the trade-offs, read [ConcurrencyPatterns.md](ConcurrencyPatterns.md) before diving into the Java-specific mechanisms below.

1. `Concurrency.basics.ThreadExample`
2. `Concurrency.basics.RaceConditions`
3. `Concurrency.basics.VolatileStopSignal`
4. `Concurrency.locking.mutex.SynchronizedCounter`
5. `Concurrency.locking.mutex.SynchronizedBlockCounter`
6. `Concurrency.locking.mutex.ReentrantCounterLock`
7. `Concurrency.locking.lockfree.AtomicCounter`
8. `Concurrency.locking.lockfree.LongAdderExample`
9. `Concurrency.locking.readwrite.ReentrantReadWriteLockCounter`
10. `Concurrency.locking.readwrite.StampedLockCounter`
11. `Concurrency.coordination.CountDownLatchExample`
12. `Concurrency.coordination.SemaphoreCounter`
13. `Concurrency.coordination.PhasedBankTransfer`
14. `Concurrency.coordination.BlockingQueueProducerConsumer`
15. `Concurrency.executors.ExecutorServiceBatchExample`
16. `Concurrency.executors.FutureTimeoutExample`
17. `Concurrency.executors.CompletableFuturePipelineExample`
18. `Concurrency.locking.mutex.DeadlockAvoidanceLockOrdering`
19. `Concurrency.patterns.singleton.DoubleCheckedLockingSingleton`
20. `Concurrency.patterns.singleton.InitializationOnDemandSingleton`
21. `Concurrency.patterns.singleton.EnumSingleton`

## 6) Compile and run

Compile all concurrency classes:

```bash
cd /Users/san_fran/Documents/repositories/algorithms-challenges
javac $(find src/Concurrency -name "*.java")
```

Run the newest additions:

```bash
java -cp src Concurrency.basics.VolatileStopSignal
java -cp src Concurrency.coordination.BlockingQueueProducerConsumer
java -cp src Concurrency.executors.ExecutorServiceBatchExample
java -cp src Concurrency.executors.FutureTimeoutExample
java -cp src Concurrency.executors.CompletableFuturePipelineExample
java -cp src Concurrency.locking.mutex.DeadlockAvoidanceLockOrdering
```

## 7) Related docs

- [ConcurrencyPatterns.md](ConcurrencyPatterns.md) for a generic, pattern-oriented view of locking, ownership, coordination, and liveness
- [InterviewTopics.md](InterviewTopics.md) for senior interview scope beyond implemented demos
- [Executors.md](executors/Executors.md) for the execution-management deep dive
- Topic docs under [`locking/`](locking/) and [`coordination/`](coordination/) for mechanism-specific details
