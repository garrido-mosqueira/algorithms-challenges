# Executors, Future, and CompletableFuture

## Navigation

- [Concurrency README](../README.md)
- [Generic concurrency patterns](../ConcurrencyPatterns.md)
- [Interview topics](../InterviewTopics.md)
- [Coordination guide](../coordination/Coordination.md)

## Why this matters

Modern Java concurrency is usually task-oriented, not thread-oriented.
You submit work to an executor, represent outcomes as futures, and compose stages declaratively.

## 1) `ExecutorService` and thread pools

### Core model

`ExecutorService` manages worker threads, queueing, and task lifecycle.

### Pool styles to know

- `newFixedThreadPool(n)`: bounded worker count
- `newCachedThreadPool()`: expands/shrinks aggressively
- `newSingleThreadExecutor()`: serialized task execution

### Repository example

- [ExecutorServiceBatchExample.java](ExecutorServiceBatchExample.java) demonstrates batch submission with `invokeAll(...)`

### Run

```bash
java -cp src Concurrency.executors.ExecutorServiceBatchExample
```

## 2) `Callable` and `Future`

### Core model

- `Callable<T>` returns a value and may throw checked exceptions
- `Runnable` has no return value and cannot throw checked exceptions directly

`Future<T>` provides:

- `get()`: wait for completion and retrieve result
- `get(timeout, unit)`: bounded wait
- `cancel(true)`: interruption-based cancellation request

### Repository example

- [FutureTimeoutExample.java](FutureTimeoutExample.java) demonstrates timeout and cancellation flow

### Run

```bash
java -cp src Concurrency.executors.FutureTimeoutExample
```

## 3) `CompletableFuture` pipelines

### Core model

`CompletableFuture` enables non-blocking stage composition without callback nesting.

### Operators to know

- `thenApply`: synchronous transform of completed result
- `thenCompose`: compose into another async stage
- `thenAccept`: terminal consumer
- `allOf` / `anyOf`: combine multiple futures
- `exceptionally` / `handle`: fallback and error transformation

### Repository example

- [CompletableFuturePipelineExample.java](CompletableFuturePipelineExample.java) demonstrates stage composition plus fallback handling

### Run

```bash
java -cp src Concurrency.executors.CompletableFuturePipelineExample
```

## Pitfalls and production cautions

- Always close executors (`shutdown()` / `shutdownNow()`)
- Avoid unbounded blocking on `Future.get()`
- Use timeout budgets and explicit fallback behavior
- Understand queue capacity, saturation, and rejection policy
- Be deliberate about where blocking joins happen in async pipelines

## Study next

- [Coordination.md](../coordination/Coordination.md)
- [InterviewTopics.md](../InterviewTopics.md)

