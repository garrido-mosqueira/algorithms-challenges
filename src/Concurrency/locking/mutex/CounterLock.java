package Concurrency.locking.mutex;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CounterLock {

    private long count = 0;
    private final Lock lock = new ReentrantLock();

    public void inc() {
        lock.lock();
        try {
            this.count++;
        } finally {
            lock.unlock();
        }
    }

    public long getCount() {
        lock.lock();
        try {
            return this.count;
        } finally {
            lock.unlock();
        }
    }

}

