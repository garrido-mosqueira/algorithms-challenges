package Concurrency;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RaceConditions {

    static void main() {
        Map<String, String> sharedMap = new ConcurrentHashMap<>();
        Thread thread1 = new Thread(getRunnable(sharedMap));
        Thread thread2 = new Thread(getRunnable(sharedMap));
        thread1.start();
        thread2.start();
    }

    private static Runnable getRunnable(Map<String, String> sharedMap) {
        return () -> {
            for (int i = 0; i < 1_000_000; i++) {
                synchronized (sharedMap) {
                    if (sharedMap.containsKey("key")) {
                        String val = sharedMap.remove("key");
                        if (val == null) {
                            System.out.println(
                                    "Iteration: " + i + ": Value for 'key' was null");
                        }
                    } else {
                        sharedMap.put("key", "value");
                    }
                }
            }
        };
    }

}
