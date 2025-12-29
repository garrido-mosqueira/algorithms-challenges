package Concurrency;

public class ThreadExample {

    public static class MyThread extends Thread {
        public void run() {
            System.out.println("MyThread run");
        }
    }

    static void main() {
        Thread thread = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("This is a thread");
                    }
                }
        );
        thread.start();

        MyThread myThread = new MyThread();
        myThread.start();
    }

}
