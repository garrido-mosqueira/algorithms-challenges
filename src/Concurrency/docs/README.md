# Java Concurrency Deep-Dive Reference

This is the **main reference** for the concurrency examples in `src/Concurrency`.

If you want one place to learn from this folder, read this file.
The other markdown files in `docs/` now simply point here.

---

## 1. What this folder is trying to teach

This concurrency section is organized around a simple idea:

1. **Start with threads and shared state**
2. **Understand why race conditions happen**
3. **Learn different ways to protect shared data**
4. **Learn when thread coordination matters more than locking**
5. **Compare trade-offs instead of memorizing APIs**

This is important because Java concurrency is not just about syntax like `synchronized` or `lock()`.
It is mostly about understanding these questions:

- What data is shared?
- Can two threads modify it at the same time?
- Do I need **mutual exclusion**, **visibility guarantees**, or **coordination**?
- Is my workload **read-heavy**, **write-heavy**, or **resource-limited**?
- Am I solving a **data safety** problem or a **workflow ordering** problem?

---

## 2. Package map

```text
Concurrency/
├── basics/
│   ├── MyRunnable.java
│   ├── RaceConditions.java
│   └── ThreadExample.java
├── locking/
│   ├── lockfree/
│   │   └── AtomicCounter.java
│   ├── mutex/
│   │   ├── CounterLock.java
│   │   ├── MyCounter.java
│   │   ├── MyCounterExample.java
│   │   └── SynchronizedBlockCounter.java
│   └── readwrite/
│       ├── ReentrantReadWriteLockCounter.java
│       └── StampedLockCounter.java
├── coordination/
│   ├── CountDownLatchExample.java
│   ├── PhasedBankTransfer.java
│   └── SemaphoreCounter.java
├── runner/
│   └── ConcurrencyMechanismsRunner.java
└── docs/
    └── README.md
```

---

## 3. Big-picture mental model

### Concurrency vs parallelism

These words are related, but not identical.

- **Concurrency** = multiple tasks are in progress during overlapping time
- **Parallelism** = multiple tasks are literally executing at the same time on multiple cores

Java concurrency tools are useful in both cases, but the core learning in this folder is:

- how to safely share data
- how to prevent race conditions
- how to coordinate multiple threads

### The main problems in concurrent code

#### A. Race conditions
A race condition happens when the result depends on timing between threads.

Example:

- Thread A reads a value
- Thread B changes it before A finishes
- A writes a result based on stale data

#### B. Atomicity
Some operations look simple but are actually multiple steps.

For example, `counter++` is not one magical indivisible action.
It is conceptually:

1. read current value
2. add one
3. write result back

If two threads do that without protection, one update can be lost.

#### C. Visibility
One thread may update a value, but another thread may not immediately observe the change unless Java’s memory rules guarantee visibility.

Locks, atomics, and some synchronization tools help here.

#### D. Coordination
Sometimes your problem is not “protect data” but “wait until the others reach this point.”
That is where barriers, latches, and semaphores become more useful than plain mutexes.

---

## 4. How the packages are organized

The code is grouped by **concept**, not just by who uses whom.

### `Concurrency.basics`
Use this package to learn:

- how threads are created
- how shared runnable instances behave
- how race-condition thinking starts

### `Concurrency.locking.lockfree`
Use this package to learn:

- lock-free counters
- CAS-style atomic updates
- why atomics are often the fastest option for simple state

### `Concurrency.locking.mutex`
Use this package to learn:

- `synchronized` methods
- `synchronized` blocks
- explicit lock objects like `ReentrantLock`
- classic mutual exclusion patterns

### `Concurrency.locking.readwrite`
Use this package to learn:

- the difference between read-heavy and write-heavy workloads
- how to allow many readers but only one writer
- why `StampedLock` can outperform a classic read-write lock in the right workload

### `Concurrency.coordination`
Use this package to learn:

- limiting concurrency with permits
- waiting for phases to complete
- making multiple threads move through a workflow together

### `Concurrency.runner`
Use this package when you want to run several demos in one command.

---

## 5. Class-by-class reference

## `Concurrency.basics`

### `ThreadExample`
**Purpose:** demonstrate basic thread creation with a shared `Runnable` instance.

What it teaches:

- two threads can execute the same runnable object
- shared mutable state must be protected
- even a basic example quickly becomes a concurrency example once state is shared

When to study it:

- first, before deeper lock mechanisms

Run:

```bash
java -cp src Concurrency.basics.ThreadExample
```

---

### `MyRunnable`
**Purpose:** support class used by `ThreadExample`.

What it teaches:

- a single runnable instance can be shared by multiple threads
- `synchronized (this)` protects a critical section inside the runnable

Key idea:

If two threads share the same runnable object, then `this` is also shared.
That makes `synchronized (this)` meaningful.

---

### `RaceConditions`
**Purpose:** show the kind of interleaving problems that happen when multiple threads touch the same structure.

What it teaches:

- shared state problems are often subtle
- even when using concurrent collections, your *multi-step logic* can still need synchronization
- “thread-safe collection” does not automatically make a compound operation thread-safe

Run:

```bash
java -cp src Concurrency.basics.RaceConditions
```

---

## `Concurrency.locking.mutex`

### `MyCounter`
**Purpose:** simplest synchronized counter.

What it teaches:

- `synchronized` on methods
- mutual exclusion around the whole method
- visibility + atomicity for the protected method body

Strengths:

- easy to understand
- low syntax overhead
- great first mutex example

Weaknesses:

- lock scope is the full method
- less flexible than explicit locks

---

### `MyCounterExample`
**Purpose:** runnable demo for `MyCounter`.

What it teaches:

- multiple threads increment the same synchronized counter safely
- correct final total is preserved under concurrency

Run:

```bash
java -cp src Concurrency.locking.mutex.MyCounterExample
```

---

### `SynchronizedBlockCounter`
**Purpose:** mutex via a synchronized block instead of synchronized methods.

What it teaches:

- narrower lock scope than method-level synchronization
- protecting only the true critical section
- using a dedicated lock object can be cleaner than synchronizing on `this`

Use when:

- only part of the method needs protection
- you want tighter control over lock scope

Avoid when:

- simplicity matters more than lock granularity

Run:

```bash
java -cp src Concurrency.locking.mutex.SynchronizedBlockCounter
```

---

### `CounterLock`
**Purpose:** explicit mutual exclusion with `ReentrantLock`.

What it teaches:

- manual `lock()` / `unlock()` lifecycle
- the importance of `try/finally`
- explicit locks give more control than `synchronized`

Why it matters:

`ReentrantLock` is useful when you need features such as:

- interruptible lock acquisition
- timed lock attempts
- fairness configuration
- more explicit lock management

Trade-off:

- more flexible
- easier to misuse than `synchronized`

---

## `Concurrency.locking.lockfree`

### `AtomicCounter`
**Purpose:** lock-free counter based on `AtomicLong`.

What it teaches:

- compare-and-swap style updates
- simple counters do not always need locks
- atomics are often the best choice for single-variable state

Use when:

- the shared state is simple
- operations are atomic-friendly
- you want high throughput and low lock contention

Avoid when:

- multiple values must change together atomically
- you need richer invariants across several fields

Run:

```bash
java -cp src Concurrency.locking.lockfree.AtomicCounter
```

---

## `Concurrency.locking.readwrite`

### `ReentrantReadWriteLockCounter`
**Purpose:** allow many readers but only one writer.

What it teaches:

- read locks can be shared
- write lock is exclusive
- this helps most when reads dominate writes

Use when:

- lots of threads read frequently
- writes are less common

Avoid when:

- writes are frequent
- state is tiny and a simpler lock is enough

Run:

```bash
java -cp src Concurrency.locking.readwrite.ReentrantReadWriteLockCounter
```

---

### `StampedLockCounter`
**Purpose:** more advanced read-heavy locking with optimistic reads.

What it teaches:

- optimistic read first
- validate afterward
- fall back to a real read lock if needed

Use when:

- you have read-heavy workloads
- you want to explore performance-oriented designs

Be careful because:

- `StampedLock` is more advanced than `synchronized`
- it is not reentrant like `ReentrantLock`
- complexity is higher, so correctness discipline matters more

Run:

```bash
java -cp src Concurrency.locking.readwrite.StampedLockCounter
```

---

## `Concurrency.coordination`

### `SemaphoreCounter`
**Purpose:** limit how many threads can access a section at the same time.

What it teaches:

- permits instead of one-owner mutex locking
- resource limiting
- real-world connection-pool style control

Use when:

- you want at most N threads inside an operation
- access is limited by a resource count

Important distinction:

A semaphore is not just “another mutex.”
A mutex usually means **one thread at a time**.
A semaphore means **up to N threads at a time**.

Run:

```bash
java -cp src Concurrency.coordination.SemaphoreCounter
```

---

### `PhasedBankTransfer`
**Purpose:** demonstrate phased coordination using `CyclicBarrier`.

What it teaches:

- threads progress in phases
- all workers must reach the barrier before the phase completes
- barriers are about workflow synchronization, not just data protection

Use when:

- a computation naturally has rounds/phases
- all participants must finish phase N before phase N+1 begins

Run:

```bash
java -cp src Concurrency.coordination.PhasedBankTransfer
```

---

### `CountDownLatchExample`
**Purpose:** wait until a set of worker threads finishes.

What it teaches:

- one thread can block until several others report completion
- latches are one-shot coordination tools

Use when:

- main thread must wait for worker completion
- startup, shutdown, and “wait for all tasks” flows

Run:

```bash
java -cp src Concurrency.coordination.CountDownLatchExample
```

---

## `Concurrency.runner`

### `ConcurrencyMechanismsRunner`
**Purpose:** run the main concurrency demos in one sequence.

Use when:

- you want a quick overview
- you want to compare outputs from several mechanisms quickly

Run:

```bash
java -cp src Concurrency.runner.ConcurrencyMechanismsRunner
```

---

## 6. Mechanism comparison

| Mechanism | Main idea | Best workload | Strength | Weakness |
|---|---|---|---|---|
| `synchronized` method | whole-method mutex | simple shared state | easiest to reason about | coarse lock scope |
| `synchronized` block | critical-section mutex | simple shared state with narrower lock scope | more control | slightly more design overhead |
| `ReentrantLock` | explicit mutex | advanced mutual exclusion | flexible API | easier to misuse |
| `AtomicLong` / atomics | lock-free single-variable updates | very simple shared state | high throughput | not enough for rich multi-field invariants |
| `ReadWriteLock` | many readers, one writer | read-heavy workloads | reader concurrency | overhead if writes are common |
| `StampedLock` | optimistic + read/write lock modes | very read-heavy workloads | can outperform classic read-write lock | more complex |
| `Semaphore` | bounded concurrent access | resource pools | controls N-way access | not a drop-in mutex substitute |
| `CountDownLatch` | wait for completion | one-shot coordination | simple workflow sync | cannot be reused |
| `CyclicBarrier` | phase synchronization | iterative/round-based work | reusable coordination point | all parties must arrive |

---

## 7. Which one should you choose?

### Choose `AtomicCounter` when

- you only need a counter or simple atomic variable
- state is small and independent
- performance matters

### Choose `MyCounter` or `SynchronizedBlockCounter` when

- you are learning the basics of mutual exclusion
- your shared state is simple
- clarity matters more than advanced flexibility

### Choose `CounterLock` when

- you need more control than `synchronized`
- you want to learn explicit lock management

### Choose `ReentrantReadWriteLockCounter` when

- reads are much more frequent than writes
- multiple readers should not block each other unnecessarily

### Choose `StampedLockCounter` when

- you understand the read-heavy pattern already
- you want to study optimistic reads and more advanced trade-offs

### Choose `SemaphoreCounter` when

- you want to limit concurrency rather than serialize everything
- you model a pool of identical resources

### Choose `CountDownLatchExample` when

- one thread must wait until several workers finish
- you have a one-time completion milestone

### Choose `PhasedBankTransfer` when

- threads must advance together through repeated phases

---

## 8. Recommended learning order

### Stage 1 — thread basics

1. `Concurrency.basics.ThreadExample`
2. `Concurrency.basics.MyRunnable`
3. `Concurrency.basics.RaceConditions`

Goal:

- understand shared runnable instances
- understand why race conditions are a real issue

### Stage 2 — classic mutual exclusion

4. `Concurrency.locking.mutex.MyCounter`
5. `Concurrency.locking.mutex.MyCounterExample`
6. `Concurrency.locking.mutex.SynchronizedBlockCounter`
7. `Concurrency.locking.mutex.CounterLock`

Goal:

- understand mutex protection
- compare method-level, block-level, and explicit-lock approaches

### Stage 3 — lock-free and performance-oriented thinking

8. `Concurrency.locking.lockfree.AtomicCounter`
9. `Concurrency.locking.readwrite.ReentrantReadWriteLockCounter`
10. `Concurrency.locking.readwrite.StampedLockCounter`

Goal:

- learn when lock-free wins
- learn why workload shape matters

### Stage 4 — coordination patterns

11. `Concurrency.coordination.CountDownLatchExample`
12. `Concurrency.coordination.SemaphoreCounter`
13. `Concurrency.coordination.PhasedBankTransfer`

Goal:

- shift from “protecting data” to “coordinating progress”

---

## 9. Compile and run

### Compile everything

```bash
cd /Users/san_fran/Documents/repositories/algorithms-challenges
javac $(find src/Concurrency -name "*.java")
```

### Run all main demos

```bash
java -cp src Concurrency.runner.ConcurrencyMechanismsRunner
```

### Run in recommended study order

```bash
java -cp src Concurrency.basics.ThreadExample
java -cp src Concurrency.basics.RaceConditions
java -cp src Concurrency.locking.mutex.MyCounterExample
java -cp src Concurrency.locking.mutex.SynchronizedBlockCounter
java -cp src Concurrency.locking.lockfree.AtomicCounter
java -cp src Concurrency.locking.readwrite.ReentrantReadWriteLockCounter
java -cp src Concurrency.locking.readwrite.StampedLockCounter
java -cp src Concurrency.coordination.CountDownLatchExample
java -cp src Concurrency.coordination.SemaphoreCounter
java -cp src Concurrency.coordination.PhasedBankTransfer
```

---

## 10. Deep-dive questions to ask yourself while reading the code

### For mutex examples

- What is the shared mutable state?
- What exact code region is protected?
- Am I locking too much or too little?

### For atomic examples

- Is the operation truly representable as a single atomic update?
- Would a second shared field break the design?

### For read/write locking

- Is the workload actually read-heavy enough to justify this?
- What happens when write frequency increases?

### For coordination examples

- Am I controlling access, or am I controlling timing/order?
- Is this reusable (`CyclicBarrier`) or one-shot (`CountDownLatch`)?

---

## 11. Important limitation of this folder

This folder gives you a strong practical introduction, but it does **not** yet cover all of Java concurrency.

For a fuller deep dive later, the next topics to study would be:

- `volatile`
- `ExecutorService`
- thread pools
- `Callable` and `Future`
- `CompletableFuture`
- producer/consumer patterns
- concurrent queues
- immutability and safe publication
- deadlocks, livelocks, starvation
- Java Memory Model concepts in more depth

So think of this folder as a **solid foundation**, especially around:

- race conditions
- mutexes
- atomics
- read-heavy locking
- coordination primitives

---

## 12. Final advice

Do not try to memorize every API first.

Instead, for each example ask:

1. What problem is this class solving?
2. Is it solving a **data safety** problem or a **coordination** problem?
3. Why is this mechanism better than the simpler alternatives?
4. What would break if synchronization was removed?

If you keep asking those questions, Java concurrency will start feeling much more logical.

---

## 13. Canonical docs note

- This file is the canonical reference for the concurrency folder.
- The other markdown files in `docs/` are now lightweight pointers here so you only need one reading path.
