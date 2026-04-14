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

### Preventing Reflection Attacks
Even with a private constructor, an attacker can use Java Reflection (`Constructor.setAccessible(true)`) to invoke the constructor and create a second instance. To prevent this, the private constructor explicitly checks if the instance already exists and throws an `IllegalStateException` if it does.

### The Critical `volatile` Keyword and Object Creation States
To understand why `volatile` is absolutely crucial in this pattern, you must understand what happens under the hood when Java executes `instance = new DoubleCheckedLockingSingleton();`.

Creating an object is **not** a single atomic step. It generally consists of three distinct states/instructions at the machine level:
1. **Allocate memory:** Allocate the blank memory space for the object.
2. **Initialize object:** Call the constructor to set up the object's fields.
3. **Assign reference:** Point the `instance` variable to the newly allocated memory space.

**The Problem: Instruction Reordering**
For performance reasons, modern compilers and CPUs are allowed to reorder instructions as long as it doesn't break the logic for a *single thread*. 
Because Steps 2 and 3 don't directly depend on each other, the JVM is allowed to execute them in this order:
1. Allocate memory.
2. **Assign reference** (point `instance` to the memory).
3. **Initialize object** (run the constructor).

**The Concurrency Disaster**
Imagine Thread A and Thread B:
1. Thread A enters the synchronized block and reaches the creation step.
2. The JVM reorders the instructions. It allocates memory and **assigns the reference** to `instance`.
3. *Context switch!* Thread A is paused before it can run the constructor.
4. Thread B enters `getInstance()`. It checks `if (instance == null)`.
5. Because Thread A already assigned the reference in Step 2, `instance` is **not null**.
6. Thread B returns the `instance` and starts trying to use it.
7. **CRASH/BUGS!** Thread B is now using a "partially constructed object" whose constructor hasn't run yet. Its fields are all empty or in an invalid state.

**The Solution:**
Declaring the variable as `private static volatile DoubleCheckedLockingSingleton instance;` solves this. The `volatile` keyword establishes a **happens-before** guarantee. It strictly forbids the JVM from reordering the assignment of the reference (Step 3) before the object is fully initialized (Step 2). It guarantees that any thread reading `instance` will only ever see a fully constructed object.

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

### Preventing Reflection Attacks
Just like the Double-Checked Locking approach, we add a check inside the private constructor to throw an `IllegalStateException` if `SingletonHolder.INSTANCE` is not null, actively blocking Reflection API abuse.

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
*   **Reflection Safe:** Standard Singletons can be bypassed using Java Reflection to call the private constructor. Enums completely prevent reflection from instantiating them by default at the JVM level (no custom constructor logic needed!).
*   **Concise:** The most compact code.

**Cons:**
*   **Not Lazy-Loaded:** It is created eagerly when the Enum class is loaded, not when `INSTANCE` is first accessed (though in practice, classes are often loaded right when they are first used anyway).
*   **Can't Extend Classes:** Enums implicitly extend `java.lang.Enum`, so your Singleton cannot extend another class (though it can implement interfaces).

### Important Note on Enums and State
While the *creation* of the Enum is thread-safe, if your Enum Singleton holds **mutable state** (like the `counter` in the example), you still must synchronize access to that state (e.g., using `synchronized`, `AtomicInteger`, etc.) just like any other concurrent object!
