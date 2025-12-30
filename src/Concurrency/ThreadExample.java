package Concurrency;

public class ThreadExample {

//    static void main() {
//        Runnable runnable1 = new MyRunnable();
//        Runnable runnable2 = new MyRunnable();
//
//        Thread thread1 = new Thread(runnable1, "thread 1");
//        Thread thread2 = new Thread(runnable1, "thread 2");
//
//        thread1.start();
//        thread2.start();
//    }


    static void main() {
        MyCounter counter = new MyCounter();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 1_000_000; i++) {
                counter.increment();
            }
            System.out.println(counter.getCounter());
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 1_000_000; i++) {
                counter.increment();
            }
            System.out.println(counter.getCounter());
        });

        thread1.start();
        thread2.start();
    }

}
