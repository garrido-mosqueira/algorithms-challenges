# Java Concurrency Learning Guide

## Overview
This Concurrency package contains **practical examples** of different locking mechanisms to handle concurrent access in Java. Each class demonstrates a specific synchronization strategy with real-world use cases.

---

## 📚 Available Classes

### 1. **SynchronizedBlockCounter.java**
**Lock-based: Object Monitor (Synchronized Block)**

- **Use Case**: Protect specific code sections
- **Key Feature**: Only critical section is locked, better than method synchronization
- **Performance**: Good (less lock contention than method sync)
- **Thread Safety**: ✅ Yes
- **Reentrant**: ✅ Yes

```java
// Synchronize only critical section
synchronized (lock) {
    counter++;
}
```

**When to Use**: When you only need to protect part of a method, not the entire method.

---

### 2. **ReentrantReadWriteLockCounter.java**
**Lock-based: Read-Write Lock**

- **Use Case**: Many readers, few writers (read-heavy workloads)
- **Key Feature**: Multiple threads can read simultaneously, but writes are exclusive
- **Performance**: ⭐⭐⭐⭐⭐ (Excellent for read-heavy, poor for write-heavy)
- **Thread Safety**: ✅ Yes
- **Reentrant**: ✅ Yes

```java
// Multiple readers can acquire lock simultaneously
readLock.lock();    // Can have 100+ concurrent readers

// Writer has exclusive access
writeLock.lock();   // Only 1 writer at a time
```

**When to Use**: Cache layers, configuration files, read-mostly data structures.

---

### 3. **StampedLockCounter.java**
**Lock-based: Stamped Lock (Java 8+, Optimized)**

- **Use Case**: High-concurrency read-heavy scenarios
- **Key Feature**: Optimistic reads (try read without lock first), falls back to read lock if needed
- **Performance**: ⭐⭐⭐⭐⭐⭐ (Best for read-heavy scenarios)
- **Thread Safety**: ✅ Yes
- **Reentrant**: ❌ No

```java
// Optimistic read - try without lock
long stamp = stampedLock.tryOptimisticRead();
long value = counter;
if (!stampedLock.validate(stamp)) {
    // If data changed, retry with actual lock
    stamp = stampedLock.readLock();
    value = counter;
    stampedLock.unlockRead(stamp);
}
```

**When to Use**: High-frequency reads with occasional writes (sensor data, metrics).

---

### 4. **AtomicCounter.java**
**Lock-free: Atomic Variables using Compare-And-Swap (CAS)**

- **Use Case**: Simple atomic operations on single variables
- **Key Feature**: No locks, uses CPU's Compare-And-Swap instruction
- **Performance**: ⭐⭐⭐⭐⭐⭐⭐ (Fastest, no blocking)
- **Thread Safety**: ✅ Yes
- **Reentrant**: N/A

```java
// Atomic increment - completely lock-free!
counter.incrementAndGet();
counter.addAndGet(5);
counter.compareAndSet(expected, newValue);
```

**When to Use**: Counters, simple flags, compare-and-set operations.

---

### 5. **SemaphoreCounter.java**
**Lock-based: Semaphore (Resource Access Control)**

- **Use Case**: Limit concurrent access to limited resources (thread pools, connection pools)
- **Key Feature**: Manages number of permits, threads wait for available permit
- **Performance**: Good (enforces concurrency limits)
- **Thread Safety**: ✅ Yes
- **Reentrant**: ❌ No

```java
// Create semaphore with max 3 concurrent accesses
Semaphore sem = new Semaphore(3);

// Thread must acquire permit before proceeding
sem.acquire();      // Wait for permit if needed
try {
    // Critical section (only 3 threads can be here)
    criticalSection();
} finally {
    sem.release();  // Release permit for next thread
}
```

**When to Use**: Database connection pools (max 10 connections), rate limiting, resource management.

---

### 6. **PhasedBankTransfer.java**
**Synchronization: Cyclic Barrier**

- **Use Case**: Coordinate multiple threads reaching a synchronization point
- **Key Feature**: All threads wait at barrier, then proceed together; **reusable**
- **Performance**: Good (for phased operations)
- **Thread Safety**: ✅ Yes
- **Reentrant**: ❌ No

```java
// Create barrier that waits for 5 threads
CyclicBarrier barrier = new CyclicBarrier(5, () -> {
    System.out.println("All threads reached barrier!");
});

// Each thread waits here until all 5 reach
barrier.await();  // All proceed together
```

**When to Use**: Multi-phase computations (initialization, processing, finalization), parallel matrix multiplication.

---

### 7. **CountDownLatchExample.java**
**Synchronization: Count Down Latch**

- **Use Case**: Wait for multiple tasks to complete before proceeding
- **Key Feature**: One-time countdown counter (**cannot be reset**)
- **Performance**: Good (for wait operations)
- **Thread Safety**: ✅ Yes
- **Reentrant**: ❌ No

```java
// Create latch that waits for 3 tasks
CountDownLatch latch = new CountDownLatch(3);

// Worker threads
for (int i = 0; i < 3; i++) {
    new Thread(() -> {
        doWork();
        latch.countDown();  // Decrease counter
    }).start();
}

// Main thread waits here for counter to reach 0
latch.await();
System.out.println("All workers completed!");
```

**When to Use**: Application startup (wait for all services to initialize), test frameworks, before-after operations.

---

## 🚀 Quick Start - Running Examples

### Compile all classes:
```bash
cd /Users/san_fran/Documents/repositories/algorithms-challenges
javac $(find src/Concurrency -name "*.java")
```

### Run individual examples:
```bash
# Atomic (fastest, lock-free)
java -cp src Concurrency.locking.lockfree.AtomicCounter

# Synchronized block (simple synchronization)
java -cp src Concurrency.locking.mutex.SynchronizedBlockCounter

# Read-Write Lock (many readers scenario)
java -cp src Concurrency.locking.readwrite.ReentrantReadWriteLockCounter

# Stamped Lock (optimized read-heavy)
java -cp src Concurrency.locking.readwrite.StampedLockCounter

# Semaphore (resource limiting)
java -cp src Concurrency.coordination.SemaphoreCounter

# Cyclic Barrier (phased operations)
java -cp src Concurrency.coordination.PhasedBankTransfer

# Count Down Latch (wait for completion)
java -cp src Concurrency.coordination.CountDownLatchExample

# Run all demos
java -cp src Concurrency.runner.ConcurrencyMechanismsRunner
```

---

## 📊 Comparison Matrix

| Mechanism | Type | Granularity | Reentrant | Performance | Best For |
|-----------|------|------------|-----------|-------------|----------|
| **AtomicCounter** | Lock-Free | N/A | N/A | ⭐⭐⭐⭐⭐⭐⭐ | Simple counters |
| **StampedLock** | Lock | High | No | ⭐⭐⭐⭐⭐⭐ | Read-heavy |
| **ReadWriteLock** | Lock | High | Yes | ⭐⭐⭐⭐⭐ | Read-heavy |
| **Synchronized Block** | Lock | Medium | Yes | ⭐⭐⭐⭐ | General sync |
| **ReentrantLock** | Lock | High | Yes | ⭐⭐⭐⭐ | General sync |
| **Semaphore** | Sync | Medium | No | ⭐⭐⭐ | Resource limits |
| **CyclicBarrier** | Sync | N/A | No | ⭐⭐⭐ | Phase sync |
| **CountDownLatch** | Sync | N/A | No | ⭐⭐⭐ | Task completion |

---

## 🎯 Decision Guide

### I need to count something quickly
```
→ Use AtomicCounter (lock-free, fastest)
```

### I have many readers and few writers
```
→ Use StampedLock (best) or ReentrantReadWriteLock (easier)
```

### I need simple thread safety
```
→ Use Synchronized Block (simple) or ReentrantLock (flexible)
```

### I need to limit concurrent access to N resources
```
→ Use Semaphore (perfect for connection/thread pools)
```

### I need threads to wait at a synchronization point
```
→ Use CyclicBarrier (reusable) or CountDownLatch (one-time)
```

---

## 💡 Key Takeaways

1. **AtomicCounter** = Fastest (use for simple counters)
2. **StampedLock** = Best for read-heavy (Java 8+)
3. **ReadWriteLock** = Good alternative to StampedLock
4. **Synchronized Block** = Simplest for mutual exclusion
5. **ReentrantLock** = More control than synchronized
6. **Semaphore** = Perfect for resource limiting
7. **Barriers** = Great for coordinating threads

---

## 📖 Learning Path

1. Start with **AtomicCounter** - understand lock-free operations
2. Move to **SynchronizedBlock** - basic locking
3. Learn **ReentrantLock** - more control
4. Study **ReadWriteLock** - read/write patterns
5. Explore **StampedLock** - optimized reads
6. Practice **Semaphore** - resource management
7. Master **CyclicBarrier** and **CountDownLatch** - thread coordination

---

Happy learning! 🎓

