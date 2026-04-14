package Concurrency.patterns.singleton;

/**
 * Initialization-on-demand holder idiom (Bill Pugh Singleton)
 * 
 * Leverages the Java ClassLoader to provide a thread-safe, 
 * lazy-loaded Singleton without using synchronized blocks.
 */
public class InitializationOnDemandSingleton {

    private InitializationOnDemandSingleton() {
        // private constructor to prevent external instantiation
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
