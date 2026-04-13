package Concurrency.basics;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class RaceConditions {

    static void main() throws InterruptedException {
        System.out.println("--- Without synchronization ---");
        runDemo(false);

        System.out.println("\n--- With synchronization ---");
        runDemo(true);
    }

    private static void runDemo(boolean withSync) throws InterruptedException {
        Map<String, String> map = new ConcurrentHashMap<>();
        AtomicInteger nullRemovals = new AtomicInteger();

        Thread t1 = new Thread(() -> task(map, nullRemovals, withSync), "thread-1");
        Thread t2 = new Thread(() -> task(map, nullRemovals, withSync), "thread-2");
        t1.start(); t2.start();
        t1.join();  t2.join();

        System.out.println("Null removals: " + nullRemovals.get()
                + (nullRemovals.get() > 0 ? "  ← RACE CONDITION" : "  ✓ safe"));
    }

    private static void task(Map<String, String> map, AtomicInteger nullRemovals, boolean withSync) {
        for (int i = 0; i < 1_000_000; i++) {
            if (withSync) {
                synchronized (map) { step(map, nullRemovals, i); }
            } else {
                step(map, nullRemovals, i);
            }
        }
    }

    private static void step(Map<String, String> map, AtomicInteger nullRemovals, int i) {
        if (map.containsKey("key")) {
            String val = map.remove("key");
            if (val == null && nullRemovals.incrementAndGet() <= 3) {
                System.out.println("  [" + Thread.currentThread().getName()
                        + "] iter " + i + " → got null!  ← race");
            }
        } else {
            map.put("key", "value");
        }
    }
}
