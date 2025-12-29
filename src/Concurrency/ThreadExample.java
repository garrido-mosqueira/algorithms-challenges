package Concurrency;

public class ThreadExample {

    public static class MyThread extends Thread {
        public MyThread() {
            super();
        }
        public MyThread(Runnable newRunnable ) {
            super(newRunnable);
        }

        public void run() {
            System.out.println("MyThread run");
            System.out.println("MyThread finished");
        }
    }

    public static class MyRunnable implements Runnable {
        public void run() {
            System.out.println("MyRunnable run");
            System.out.println("MyRunnable finished");
        }
    }

    static void main() {
        Thread thread = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("Thread run");
                        System.out.println("Thread finished");
                    }
                }
        );
        thread.start();

        Thread threadLambda = new Thread(
                () -> {
                    System.out.println("ThreadLambda run");
                    System.out.println("ThreadLambda finished");
                }
        );
        threadLambda.start();

        MyThread myThread = new MyThread();
        myThread.start();

        Thread originalThread = new Thread(new MyRunnable());
        originalThread.start();

        MyThread myThreadLambda = new MyThread(new MyRunnable());
        myThreadLambda.start();
    }

}
