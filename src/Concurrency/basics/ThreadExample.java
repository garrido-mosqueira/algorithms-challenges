package Concurrency.basics;

public class ThreadExample {

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = new MyRunnable();

        Thread thread1 = new Thread(runnable, "thread 1");
        Thread thread2 = new Thread(runnable, "thread 2");

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
    }

}

