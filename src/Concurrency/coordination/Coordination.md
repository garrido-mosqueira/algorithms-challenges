# Thread Coordination 🚦

## Overview
While **Mutual Exclusion** (Locks, Mutexes) is about preventing threads from colliding when accessing shared data, **Thread Coordination** is about ordering and synchronizing the flow of execution between multiple threads. 

Instead of asking "Is it safe to touch this data?", coordination asks:
- "Should I wait for someone else to finish first?"
- "How many threads are allowed to do this at the same time?"
- "Can we all pause here and move to the next phase together?"

Java provides several high-level synchronizers in the `java.util.concurrent` package to solve these exact workflow problems.

---

## 1. `CountDownLatch` (The "Wait for Others" Pattern)

**File:** `CountDownLatchExample.java`

A `CountDownLatch` allows one or more threads to wait until a set of operations being performed in other threads completes. 

### How it Works
1. You initialize it with a count (e.g., `new CountDownLatch(3)`).
2. The waiting thread calls `latch.await()`. It will block here.
3. The worker threads do their job and call `latch.countDown()` when finished.
4. When the count reaches zero, the waiting thread is released and continues execution.

**Key Characteristic: One-Shot**
A `CountDownLatch` cannot be reset. Once the count reaches zero, it's done forever. If you need it again, you must create a new object.

**Common Use Cases:**
- A main thread waiting for 5 background services to initialize before starting the UI.
- Waiting for several parallel network requests to finish before aggregating the results.

---

## 2. `CyclicBarrier` (The "Meetup Point" Pattern)

**File:** `PhasedBankTransfer.java`

A `CyclicBarrier` allows a set of threads to all wait for each other to reach a common barrier point.

### How it Works
1. You initialize it with the number of parties (threads) (e.g., `new CyclicBarrier(4)`).
2. Each thread does its work for the current phase and calls `barrier.await()`.
3. The thread blocks until *all 4* threads have called `await()`.
4. Once everyone arrives, the barrier is tripped, and all threads are released simultaneously to begin the next phase.

**Key Characteristic: Reusable & Phased**
Unlike a latch, a `CyclicBarrier` automatically resets after the barrier is tripped. This makes it perfect for iterative, phased computations. You can also provide a `Runnable` action that executes exactly once when the barrier is tripped, before the threads are released.

**Common Use Cases:**
- Multiplayer games (waiting for all players to finish loading before starting the match).
- Parallel algorithms that process data in rounds/phases (e.g., matrix multiplication, physics simulations).

---

## 3. `Semaphore` (The "Bouncer / Permits" Pattern)

**File:** `SemaphoreCounter.java`

A `Semaphore` controls access to a shared resource by maintaining a set of permits. It does not enforce exclusive ownership like a lock; it enforces a *capacity limit*.

### How it Works
1. You initialize it with a number of permits (e.g., `new Semaphore(5)`).
2. Before accessing the resource, a thread calls `semaphore.acquire()`. If a permit is available, it takes one and proceeds. If not, it blocks until one is released.
3. When finished, the thread calls `semaphore.release()`, putting the permit back into the pool.

**Key Characteristic: Non-Ownership**
Unlike a `ReentrantLock`, a Semaphore does not track which thread owns a permit. Thread A can acquire a permit, and Thread B can technically release it! 
*(Note: A Semaphore initialized with `1` permit acts exactly like a Mutual Exclusion lock, known as a "Binary Semaphore").*

**Common Use Cases:**
- Connection pools (e.g., limiting database connections to exactly 10 at a time).
- Rate limiting or throttling incoming API requests.

---

## Summary Comparison

| Synchronizer | Main Purpose | Reusable? | Metaphor |
| :--- | :--- | :--- | :--- |
| **`CountDownLatch`** | Wait for a specific number of events to happen before proceeding. | ❌ No | A starting gun waiting for 3 green lights. |
| **`CyclicBarrier`** | Wait for a specific number of threads to reach the same point. | ✅ Yes | Friends hiking, waiting for everyone at every checkpoint. |
| **`Semaphore`** | Limit the number of threads accessing a resource concurrently. | ✅ Yes | A nightclub bouncer letting exactly 50 people in at a time. |
