# RaceConditions.java — Explained

## The core idea

> **A thread-safe collection does not make a multi-step operation thread-safe.**

The demo uses a `ConcurrentHashMap` that toggles a single key back and forth.
Each thread ping-pongs the key: one iteration puts it in, the next removes it.
This makes the race easy to observe and count.

---

## What each thread does every iteration

```
if key exists  → remove it
if key missing → put it back
```

With 2 threads alternating, the map should stay in sync.
But there is a hidden problem when there is no outer lock.

---

## Why the race happens (without `synchronized`)

`containsKey` and `remove` are two separate calls.
`ConcurrentHashMap` makes each one individually safe, but **nothing prevents another thread from slipping in between them**:

```
Thread-1: containsKey("key") → true    ✓ key is there
Thread-2: containsKey("key") → true    ✓ key still there
Thread-1: remove("key")      → "value"
Thread-2: remove("key")      → null    ← key already gone!
```

The check and the act are two separate steps, not one atomic action.
That gap is where the race lives.

---

## What `synchronized(map)` fixes

Wrapping both calls in `synchronized(map)` forces the entire check-then-act
block to run as **one uninterruptible unit**.
No other thread can enter the block while one thread is inside it.

```java
// UNSAFE — two separate steps, gap between them
if (map.containsKey("key")) {
    map.remove("key");          // ← another thread can strike here
}

// SAFE — one atomic compound operation
synchronized (map) {
    if (map.containsKey("key")) {
        map.remove("key");
    }
}
```

---

## Demo output

| Run | What happens | Output |
|---|---|---|
| `withSync = false` | Threads interleave freely | `Null removals: 28176  ← RACE CONDITION` |
| `withSync = true`  | Each compound op is atomic | `Null removals: 0  ✓ safe` |

---

## Code structure

| Method | Role |
|---|---|
| `main()` | Runs both demos back to back |
| `runDemo(withSync)` | Creates the map, starts both threads, prints the summary |
| `task(...)` | Per-thread loop; routes to `synchronized` or bare `step()` based on the flag |
| `step(...)` | The single compound operation shared by both variants |

---

## Key takeaway

`ConcurrentHashMap` protects individual calls (`put`, `remove`, `containsKey`).
It does **not** protect a sequence of calls that must be treated as one unit.
Whenever your logic says *"check, then act based on that check"*, you need an outer lock.

