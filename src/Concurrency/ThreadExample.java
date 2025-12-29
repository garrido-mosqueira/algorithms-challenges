package Concurrency;

public class ThreadExample {

    static void main() {
        Runnable runnable = () -> {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " Running");
        };

        Thread thread = new Thread(runnable, "thread name 1");
        thread.start();

        Thread thread2 = new Thread(runnable, "thread name 2");
        thread2.start();
    }

}
