# Thread Coordination

## Navigation

- [Concurrency README](../README.md)
- [Generic concurrency patterns](../ConcurrencyPatterns.md)
- [Interview topics](../InterviewTopics.md)
- [Executors guide](../executors/Executors.md)

## Why this matters

Mutexes protect shared data. Coordination primitives control **when** threads proceed.

Use coordination when the real question is one of workflow:

- Should this thread wait for other tasks?
- How many threads may access a resource at once?
- Should all workers move through phases together?

## 1) `CountDownLatch` - wait for one-time completion

**Repository file:** [CountDownLatchExample.java](CountDownLatchExample.java)

### Core model

One or more threads wait until a fixed number of events have occurred.

### How it works

1. Create latch with count `N`
2. Waiting thread calls `await()`
3. Worker threads call `countDown()` as they finish
4. Waiting thread resumes when count reaches zero

### Strengths and trade-offs

- Very simple completion gate
- One-shot only (cannot reset)

### Common use cases

- Wait for startup tasks before serving traffic
- Aggregate parallel request results before final step

### Run

```bash
java -cp src Concurrency.coordination.CountDownLatchExample
```

## 2) `CyclicBarrier` - reusable phase sync point

**Repository file:** [PhasedBankTransfer.java](PhasedBankTransfer.java)

### Core model

A fixed number of threads must all reach the barrier before any continue.

### How it works

1. Create barrier with party count
2. Each worker completes phase work and calls `await()`
3. Threads block until all parties arrive
4. Barrier trips, all threads continue to next phase

### Strengths and trade-offs

- Reusable across phases
- Optional barrier action runs once per phase
- Progress depends on all participants arriving

### Common use cases

- Round-based parallel computation
- Phase-controlled simulations and pipelines

### Run

```bash
java -cp src Concurrency.coordination.PhasedBankTransfer
```

## 3) `Semaphore` - bounded concurrent access

**Repository file:** [SemaphoreCounter.java](SemaphoreCounter.java)

### Core model

A semaphore manages a finite number of permits and limits concurrent access to a resource.

### How it works

1. Initialize with `N` permits
2. Thread calls `acquire()` before using resource
3. Thread calls `release()` when done

### Strengths and trade-offs

- Models resource pool capacity directly
- Does not enforce ownership like a lock
- Binary semaphore (`N = 1`) behaves similarly to mutex access gating

### Common use cases

- Database connection pools
- Concurrency-limited external API usage

### Run

```bash
java -cp src Concurrency.coordination.SemaphoreCounter
```

## 4) `BlockingQueue` - producer-consumer with backpressure

**Repository file:** [BlockingQueueProducerConsumer.java](BlockingQueueProducerConsumer.java)

### Core model

Producers and consumers exchange work through a queue that blocks naturally under empty/full conditions.

### How it works

1. Producer uses `put()` to enqueue work
2. Consumer uses `take()` to dequeue work
3. Producer blocks when queue is full
4. Consumer blocks when queue is empty

### Strengths and trade-offs

- Natural backpressure without manual `wait`/`notify`
- Decouples producer and consumer throughput
- Queue capacity and type (`ArrayBlockingQueue` vs `LinkedBlockingQueue`) must match workload

### Common use cases

- Worker queue architectures
- Buffering between uneven subsystems

### Run

```bash
java -cp src Concurrency.coordination.BlockingQueueProducerConsumer
```

## Summary comparison

| Synchronizer | Main purpose | Reusable? | Typical fit |
|---|---|---|---|
| `CountDownLatch` | Wait for N one-time completions | No | Startup/shutdown gates |
| `CyclicBarrier` | Wait for all threads at a phase point | Yes | Iterative phase workflows |
| `Semaphore` | Limit concurrent access count | Yes | Resource pools |
| `BlockingQueue` | Producer-consumer handoff and backpressure | Yes | Work queues/pipelines |

## Study next

- [Executors.md](../executors/Executors.md)
- [Mutex.md](../locking/mutex/Mutex.md)
