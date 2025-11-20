package DesignGurus.warmup;

public class Sqrt {

    public int mySqrt(int x) {
        if (x == 0) return 0;
        int start = 1, end = x;

        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (mid == x / mid) return mid;
            else if (mid < x / mid) start = mid + 1;
            else end = mid - 1;
        }

        return end;
    }

    static void main(String[] args) {
        Sqrt solution = new Sqrt();
        System.out.println(solution.mySqrt(16));
        System.out.println(solution.mySqrt(8));
    }

}
