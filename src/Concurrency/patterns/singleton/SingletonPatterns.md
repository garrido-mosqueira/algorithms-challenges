# Thread-Safe Singleton Patterns in Java

The Singleton pattern ensures that a class has only one instance and provides a global point of access to it. While creating a Singleton is simple in a single-threaded environment, doing it safely in a concurrent environment requires care to prevent multiple threads from creating multiple instances simultaneously.

Here are the three most robust ways to implement a thread-safe Singleton in Java.

---

## 1. Double-Checked Locking (The "Two Synchronized Nested and Null Checking" Approach)

**File:** `DoubleCheckedLockingSingleton.java`

This is the classic approach that combines lazy initialization with performance optimization.

### How it Works
1. **First Check (No Lock):** Check if the instance is null. If it isn't, return it immediately. This avoids the overhead of acquiring a lock on every call after the Singleton is created.
2. **Synchronized Block:** If the instance is null, enter a synchronized block on the class object.
3. **Second Check (With Lock):** Check if the instance is still null. Another thread might have created it while this thread was waiting for the lock.
4. **Create:** If still null, safely create the instance.

### The Critical `volatile` Keyword
The `instance` variable **must** be declared `volatile`. Without `volatile`, the JVM or CPU might reorder the instructions for creating the object. A thread could see a non-null reference to the object *before* its constructor has fully finished executing, leading to subtle and dangerous bugs. `volatile` prevents this instruction reordering (establishing a "happens-before" relationship).

**Pros:** Lazy loading, excellent performance after initialization.
**Cons:** Verbose, complex to implement correctly due to the necessity of `volatile`.

---

## 2. Initialization-on-Demand Holder Idiom (The "Static Inner Class" Approach)

**File:** `InitializationOnDemandSingleton.java`

Also known as the Bill Pugh Singleton, this is widely considered the smartest, lock-free way to achieve lazy initialization.

### How it Works
It leverages the Java ClassLoader's guarantee that a class is initialized only once and that the static initialization phase is inherently thread-safe.

1. The main `InitializationOnDemandSingleton` class is loaded, but its inner static class (`SingletonHolder`) is *not* loaded yet.
2. When a thread first calls `getInstance()`, it references `SingletonHolder.INSTANCE`.
3. This triggers the ClassLoader to load and initialize `SingletonHolder`. The JVM handles the synchronization during this phase, creating the single instance safely without explicit `synchronized` blocks.

**Pros:** 100% thread-safe without explicit synchronization overhead, clean, simple, lazy-loaded.
**Cons:** None, really. It is the preferred approach for lazy initialization.

---

## 3. The Enum Singleton

**File:** `EnumSingleton.java`

Recommended by Joshua Bloch in *Effective Java* as the absolute best way to implement a Singleton.

### How it Works
In Java, `enum` types are strictly guaranteed by the JVM to be instantiated exactly once per JVM.

By declaring your Singleton as an `enum` with a single value (`INSTANCE`), you delegate all the complex creation, synchronization, and serialization logic directly to the JVM.

**Pros:**
*   **Inherent Thread Safety:** The JVM guarantees it.
*   **Serialization Safe:** Standard Singletons can be accidentally duplicated if serialized and deserialized. Enums handle this automatically.
*   **Reflection Safe:** Standard Singletons can be bypassed using Java Reflection to call the private constructor. Enums prevent reflection from instantiating them.
*   **Concise:** The most compact code.

**Cons:**
*   **Not Lazy-Loaded:** It is created eagerly when the Enum class is loaded, not when `INSTANCE` is first accessed (though in practice, classes are often loaded right when they are first used anyway).
*   **Can't Extend Classes:** Enums implicitly extend `java.lang.Enum`, so your Singleton cannot extend another class (though it can implement interfaces).

### Important Note on Enums and State
While the *creation* of the Enum is thread-safe, if your Enum Singleton holds **mutable state** (like the `counter` in the example), you still must synchronize access to that state (e.g., using `synchronized`, `AtomicInteger`, etc.) just like any other concurrent object!
