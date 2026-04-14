package Concurrency.patterns.singleton;

/**
 * Double-Checked Locking Singleton
 * 
 * Uses a volatile variable and a synchronized block to ensure only one instance is created.
 * The first check avoids synchronization overhead after the instance is created.
 */
public class DoubleCheckedLockingSingleton {
    // volatile is CRUCIAL here to prevent instruction reordering issues
    // where another thread might see a partially constructed object.
    private static volatile DoubleCheckedLockingSingleton instance;

    private DoubleCheckedLockingSingleton() {
        // Prevent instantiation via reflection
        if (instance != null) {
            throw new IllegalStateException("Instance already exists! Use getInstance() method.");
        }
    }

    public static DoubleCheckedLockingSingleton getInstance() {
        // First check (no locking) - makes it fast after initialization
        if (instance == null) {
            // Only lock if it's null
            synchronized (DoubleCheckedLockingSingleton.class) {
                // Second check (with locking) - prevents multiple threads that passed 
                // the first check simultaneously from creating multiple instances
                if (instance == null) {
                    instance = new DoubleCheckedLockingSingleton();
                }
            }
        }
        return instance;
    }
}
