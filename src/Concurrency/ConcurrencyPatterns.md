# Generic Concurrency Patterns and Locking Techniques

This guide explains concurrency from a **design and pattern** perspective rather than from a single language or runtime API.

## Navigation

- [Concurrency README](README.md)
- [Interview topics](InterviewTopics.md)
- [Race conditions example](basics/RaceConditions.md)
- [Mutex guide](locking/mutex/Mutex.md)
- [Coordination guide](coordination/Coordination.md)
- [Executors guide](executors/Executors.md)

Use it to answer questions like:

- Should I protect shared state with a lock, or avoid sharing altogether?
- How should independent tasks coordinate progress?
- Which designs fail under contention, retries, or partial stalls?

## 1) Start from the problem, not from the primitive

Before choosing a lock, identify which kind of problem you actually have:

1. **Shared-state safety** - multiple workers can touch the same data
2. **Progress coordination** - one task must wait for another event or phase
3. **Capacity control** - only a limited number of workers should use a resource
4. **Throughput under contention** - correctness is not enough; the system must still move fast
5. **Failure isolation** - one slow or blocked participant should not freeze everything else

The best concurrency design is often the one that **reduces the amount of coordination required**.

## 2) First question: do you need shared mutable state at all?

Many locking problems disappear when ownership is clearer.

### Prefer these designs before adding locks

- **Immutability**: create new values instead of mutating existing ones
- **Single-writer ownership**: only one thread/task owns a piece of state at a time
- **Partitioning / sharding**: split state so different workers touch different partitions
- **Message passing**: exchange work and results through queues/mailboxes instead of shared objects
- **Thread/task confinement**: keep data local to one execution context

These patterns reduce contention and make correctness easier to reason about.

## 3) Locking patterns

Locking is about protecting an **invariant**, not just a line of code.

If two fields must always change together, the critical section should cover the whole invariant.

### A) Mutex / exclusive lock

Use when only one worker may enter a critical section at a time.

**Good fit:**

- updating shared counters with extra business logic
- maintaining consistency across multiple fields
- protecting non-thread-safe resources

**Trade-offs:**

- simple mental model
- can become a bottleneck under high contention
- long critical sections increase latency for everyone waiting

### B) Read-write lock

Use when reads are frequent, writes are rare, and readers can safely proceed together.

**Good fit:**

- mostly-read caches
- reference data refreshed occasionally
- configuration snapshots with rare updates

**Trade-offs:**

- better read concurrency than a plain mutex
- more complex than exclusive locking
- can suffer writer starvation or degraded performance if the read/write balance changes

### C) Optimistic locking

Read without holding a heavy exclusive lock, then verify the state did not change before committing.

Typical forms:

- version numbers
- compare-and-swap (CAS)
- validate-after-read strategies

**Good fit:**

- read-heavy workloads
- low-conflict updates
- systems where retries are acceptable

**Trade-offs:**

- excellent when conflicts are rare
- expensive when contention is high because retries multiply work

### D) Lock striping / segmented locking

Instead of one global lock, use multiple independent locks for independent partitions.

**Good fit:**

- maps, buckets, shards, or account partitions
- workloads with naturally separable keys

**Trade-offs:**

- reduces hotspot contention
- introduces complexity when operations span multiple partitions

### E) Hierarchical or ordered locking

When an operation must take more than one lock, always acquire them in the same global order.

This is one of the most practical deadlock prevention patterns.

**Rule:** consistent order beats ad-hoc local reasoning.

## 4) Patterns that reduce or avoid locking

### A) Immutability

Publish values that never change after creation.

**Why it helps:** readers need no lock if the object graph cannot be mutated.

### B) Single-writer principle

Assign one owner to each piece of mutable state.

Other workers send requests to the owner instead of mutating the data directly.

This is common in event loops, actor systems, and partitioned services.

### C) Actor / mailbox pattern

Each actor owns its state and processes one message at a time.

**Strengths:**

- avoids most shared-memory races
- scales well when state is partitionable
- failure boundaries are easier to define

**Trade-offs:**

- cross-actor workflows become distributed coordination problems
- message ordering and backpressure still matter

### D) Lock-free / CAS-based updates

Workers retry atomic updates until one succeeds.

**Good fit:**

- simple shared values
- counters, sequences, state machines with compact transitions

**Trade-offs:**

- avoids blocking lock ownership
- can burn CPU under heavy contention due to retry loops
- correctness becomes harder as state transitions become more complex

### E) Queue-based handoff

Instead of many writers modifying shared state, send work through a queue to one or more consumers.

This naturally combines **coordination**, **ownership**, and often **backpressure**.

## 5) Coordination patterns

Sometimes the problem is not protection of state, but control of **when** work proceeds.

### Producer-consumer

Producers create work, consumers process it.

Key concerns:

- queue capacity
- backpressure
- ordering guarantees
- shutdown signaling

### Fan-out / fan-in

Split one job into parallel sub-tasks, then merge their results.

Key concerns:

- slowest task determines completion time
- timeouts and cancellation
- partial failure handling

### Barrier / phased execution

Multiple workers must all finish one stage before any begin the next.

Good for simulations, batch phases, or stepwise workflows.

### Bounded concurrency

Limit the number of in-flight tasks accessing a scarce dependency.

Typical goals:

- protect a database or downstream API
- control memory usage
- smooth spikes during bursty traffic

### Backpressure

Backpressure means a fast producer cannot indefinitely overwhelm a slow consumer.

Ways to express it:

- bounded queues
- permits/tokens
- dropping or shedding work
- adaptive rate limiting

## 6) Common liveness failures

Correctness is only half the story. A design can be logically correct and still stop making useful progress.

### Deadlock

Two or more workers wait forever on each other.

Common causes:

- inconsistent lock ordering
- holding one lock while waiting for another resource
- synchronous call cycles across components

### Livelock

Workers keep reacting and retrying, but useful work never completes.

Example shape: both sides repeatedly back off and retry at the same time.

### Starvation

One worker is perpetually denied access to CPU time, a lock, or a scheduling slot.

### Priority inversion

A high-priority task waits behind a low-priority holder while medium-priority work runs in between.

### Lock convoying

Many workers repeatedly queue behind the same hot lock, causing throughput collapse.

## 7) How to choose a pattern

| Situation | Usually start with | Why |
|---|---|---|
| Small amount of shared mutable state | Mutex | Clearest correctness model |
| Heavy reads, rare writes | Read-write or optimistic strategy | Improves read concurrency |
| State can be partitioned by key | Sharding + lock striping or single-owner partitions | Reduces contention |
| Shared updates are simple and atomic | Lock-free / CAS | Avoids lock blocking |
| Work can be passed instead of shared | Queue or actor/mailbox | Safer ownership boundaries |
| Downstream system is fragile or rate-limited | Bounded concurrency / semaphore-like control | Protects dependency |
| Pipeline stages run at different speeds | Bounded queues + backpressure | Prevents overload |
| Multi-step workflow with phase boundaries | Barrier / completion coordination | Makes stage progression explicit |

## 8) Practical locking heuristics

- Keep critical sections **small but complete**
- Protect **invariants**, not individual variables in isolation
- Never hold a lock while doing slow I/O if it can be avoided
- Be suspicious of nested locks; define a strict acquisition order
- Prefer timeouts, cancellation, and failure paths over infinite waiting
- Measure contention before introducing clever lock-free complexity
- Document ownership rules clearly: who can mutate what, and when
- If a queue is unbounded, assume overload will eventually show up as memory pressure instead of immediate rejection

## 9) A simple decision flow

1. Can I avoid shared mutable state?
2. If not, can I reduce sharing by partitioning ownership?
3. If shared state remains, is a simple mutex already good enough?
4. If contention is real, is the workload read-heavy, partitionable, or retry-friendly?
5. Do I actually need coordination, capacity control, or backpressure rather than a lock?
6. What is the failure mode under overload: blocking, retry storms, queue growth, or starvation?

## 10) Connect this back to the repository

This document is intentionally language-agnostic. For concrete examples in this repository, see:

- [Mutex.md](locking/mutex/Mutex.md)
- [ReadWriteLock.md](locking/readwrite/ReadWriteLock.md)
- [AtomicCounter.md](locking/lockfree/AtomicCounter.md)
- [LongAdder.md](locking/lockfree/LongAdder.md)
- [Coordination.md](coordination/Coordination.md)
- [Executors.md](executors/Executors.md)
- [InterviewTopics.md](InterviewTopics.md)

Use this file as the **conceptual map**, then use the language-specific examples as implementations of those ideas.
