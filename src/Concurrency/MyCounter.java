package Concurrency;

public class MyCounter {

    private long counter = 0;

    public void increment(){
        counter++;
    }
    public long getCounter(){
        return counter;
    }

}
