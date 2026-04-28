# Thread-Safe Singleton Patterns in Java

## Navigation

- [Concurrency README](../../README.md)
- [Generic concurrency patterns](../../ConcurrencyPatterns.md)
- [Interview topics](../../InterviewTopics.md)
- [Mutex guide](../../locking/mutex/Mutex.md)

## Why this matters

Singleton correctness in concurrent code is about **safe publication** and **single initialization**, not just private constructors.

## 1) Double-checked locking

**Repository file:** [DoubleCheckedLockingSingleton.java](DoubleCheckedLockingSingleton.java)

### How it works

1. Fast-path null check without lock
2. Enter synchronized block only when instance is absent
3. Check again inside lock
4. Initialize once

### Critical rule

- `instance` must be `volatile`
- Without `volatile`, instruction reordering can expose a partially constructed object

### Pros and cons

- Pros: lazy initialization + fast post-init access
- Cons: easiest pattern to implement incorrectly; more verbose

## 2) Initialization-on-demand holder idiom

**Repository file:** [InitializationOnDemandSingleton.java](InitializationOnDemandSingleton.java)

### How it works

- Static inner holder class owns the singleton field
- JVM class initialization guarantees thread-safe one-time creation

### Pros and cons

- Pros: lazy, thread-safe, no explicit lock code
- Cons: behavior depends on understanding class-loader semantics

## 3) Enum singleton

**Repository file:** [EnumSingleton.java](EnumSingleton.java)

### How it works

- JVM guarantees one instance per enum constant

### Pros and cons

- Pros: built-in thread safety, serialization safety, reflection resistance
- Cons: less flexible for lazy initialization style and inheritance

## Important reminders

- Constructor safety does not make mutable singleton fields automatically thread-safe
- If singleton state changes over time, protect that state with proper synchronization or atomics

## Study next

- [VolatileStopSignal.java](../../basics/VolatileStopSignal.java)
- [InterviewTopics.md](../../InterviewTopics.md)
