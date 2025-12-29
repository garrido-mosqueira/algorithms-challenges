package Concurrency;

public class ThreadExample {

    static void main() {
        Runnable runnable = () -> {
            System.out.println("Lambda running");
            System.out.println("Lambda finished");
        };

        Thread thread = new Thread(runnable);
        thread.start();
    }

}
