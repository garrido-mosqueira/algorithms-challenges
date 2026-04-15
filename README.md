# Algorithms & Concurrency Challenges 🚀

A comprehensive collection of algorithm challenges and Java concurrency examples. This repository serves as a reference for solving coding problems from popular platforms and a deep-dive into Java's concurrent programming mechanisms.

---

## 📂 Project Structure

The project is organized into two main parts: **Algorithms** and **Concurrency**.

### 1. Algorithms & Coding Challenges
Challenge solutions are grouped by their original source:

*   **`src/AlgoExpert/`**: Solutions to [AlgoExpert](https://www.algoexpert.io/) problems (Easy, Medium, Hard).
*   **`src/CodeSignal/`**: Structured by topics (Arrays, Lists, Stacks, Queues, Hash Maps, Sorting, Trees, Graphs).
*   **`src/LeetCode/`**: Various [LeetCode](https://leetcode.com/problemset/all/) challenges categorized by difficulty.
*   **`src/CoderPro/`**: Solutions from [CoderPro](https://www.coderpro.com/) (e.g., Reverse Linked List, Max in Stack).
*   **`src/DesignGurus/`**: Warmup and pattern-based challenges from [DesignGurus](https://www.designgurus.io/).
*   **`src/RealChallenges/`**: Practical coding challenges encountered in real interviews or assessments (e.g., Location with Stock, Package Matcher).

### 2. Java Concurrency Deep-Dive
A dedicated section in **`src/Concurrency/`** focused on learning thread safety, synchronization, and coordination.

*   **`basics/`**: Thread creation, shared state, and race conditions.
*   **`locking/`**: 
    *   `mutex/`: `synchronized` blocks/methods and `ReentrantLock`.
    *   `lockfree/`: Atomic variables (`AtomicLong`, `LongAdder`).
    *   `readwrite/`: `ReentrantReadWriteLock` and `StampedLock` for read-heavy workloads.
*   **`coordination/`**: High-level synchronizers like `CountDownLatch`, `CyclicBarrier`, and `Semaphore`.
*   **`patterns/`**: Thread-safe implementation of common patterns like Singleton.

---

## 🚀 Getting Started

### Prerequisites
*   Java Development Kit (JDK) 8 or higher.
*   An IDE (IntelliJ IDEA is recommended as the project includes `.iml` files).

### Compilation
To compile all files in the project:
```bash
javac -d out $(find src -name "*.java")
```

---

## 📖 Key Topics Covered

### Algorithms
| Topic | Highlights |
| :--- | :--- |
| **Arrays & Strings** | Left Rotation, Sliding Window, Two Pointers. |
| **Linked Lists** | Cycle Detection, Reversing, Node Insertion. |
| **Stacks & Queues** | Balanced Brackets, Queue using Two Stacks. |
| **Trees & Graphs** | BST Insertion, BFS/DFS, Shortest Reach. |
| **Sorting** | Merge Sort, Quick Sort, Insertion Sort. |

### Concurrency Reference
| Mechanism | Purpose |
| :--- | :--- |
| **`synchronized`** | Basic mutual exclusion (mutex). |
| **`ReentrantLock`** | Explicit lock control with advanced features. |
| **`AtomicLong`** | Lock-free, high-performance counters. |
| **`StampedLock`** | Optimistic read locking for high performance. |
| **`CountDownLatch`** | Waiting for a set of events to complete. |
| **`CyclicBarrier`** | Synchronizing threads in phases/rounds. |

---

## 📝 Notes
*   Most algorithm challenges include a link to the original problem in the source code or in the legacy `README.adoc`.
*   For a detailed explanation of the concurrency section, see [src/Concurrency/README.md](src/Concurrency/README.md).
