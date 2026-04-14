package Concurrency.locking.mutex;

public class SynchronizedCounter {

    private long counter = 0;

    public synchronized void increment() {
        counter++;
    }

    public synchronized long getCounter() {
        return counter;
    }

}

