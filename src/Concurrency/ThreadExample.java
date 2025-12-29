package Concurrency;

public class ThreadExample {

    static void main() {
        Runnable runnable = () -> {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " Running");
        };

        Thread thread = new Thread(runnable, "thread name");
        thread.start();
    }

}
