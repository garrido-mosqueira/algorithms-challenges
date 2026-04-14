package Concurrency.patterns.singleton;

/**
 * Enum Singleton Pattern
 * 
 * Recommended by Joshua Bloch (Effective Java) as the best way
to implement a Singleton in Java.
 * 
 * Provides inherent thread safety, prevents multiple instantiations 
 * via serialization or reflection, and is concise.
 */
public enum EnumSingleton {
    // The single instance of the enum, guaranteed by the JVM to be created once.
    INSTANCE;

    // You can add normal instance fields
    private int counter = 0;

    // And normal instance methods
    public void increment() {
        // Even though Enum creation is thread-safe, 
        // you still need to protect shared mutable state!
        synchronized (this) {
            counter++;
        }
    }

    public int getCounter() {
        return counter;
    }
}
