package topic1_arrays_strings;

public class AVeryBigSum {

    static long aVeryBigSum(long[] ar) {
        long total = 0;
        for (int i = 0; i < ar.length; i++) {
            total += ar[i];
        }
        return total;
    }

    public static void main(String[] args) {

        long[] array = new long[]{1000000001L, 1000000002L, 1000000003L, 1000000004L, 1000000005L};

        long result = aVeryBigSum(array);

        assert result == 5000000015L : "Wrong";

    }
}
