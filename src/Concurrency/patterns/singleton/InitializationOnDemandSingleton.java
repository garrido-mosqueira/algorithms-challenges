package Concurrency.patterns.singleton;

/**
 * Initialization-on-demand holder idiom (Bill Pugh Singleton)
 * 
 * Leverages the Java ClassLoader to provide a thread-safe, 
 * lazy-loaded Singleton without using synchronized blocks.
 */
public class InitializationOnDemandSingleton {

    private InitializationOnDemandSingleton() {
        // Prevent instantiation via reflection
        if (SingletonHolder.INSTANCE != null) {
            throw new IllegalStateException("Instance already exists! Use getInstance() method.");
        }
    }

    /**
     * Inner static class is not loaded until getInstance() is called.
     * The JVM guarantees that static initialization of a class is thread-safe.
     */
    private static class SingletonHolder {
        private static final InitializationOnDemandSingleton INSTANCE = new InitializationOnDemandSingleton();
    }

    public static InitializationOnDemandSingleton getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
