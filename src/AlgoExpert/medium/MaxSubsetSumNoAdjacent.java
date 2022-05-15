package AlgoExpert.medium;


public class MaxSubsetSumNoAdjacent {

    public static void main(String[] args) {
        int[] input1 = {75, 105, 120, 75, 90, 135};
        int output1 = 330; // 75 + 120 + 135

        int[] input2 = {7, 10, 12, 7, 9, 14};
        int output2 = 33; // 7 + 12 + 14

        System.out.println(maxSubsetSumNoAdjacent(input1));
        System.out.println(maxSubsetSumNoAdjacent(input2));

    }

    public static int maxSubsetSumNoAdjacent(int[] array) {
        int[] maxSumming = new int[array.length];
        maxSumming[0] = array[0];
        maxSumming[1] = array[1];
        for (int i = 2; i < array.length; i++) {
            maxSumming[i] = Math.max(maxSumming[i - 2] + array[i], maxSumming[i - 1]);
        }
        return maxSumming[array.length-1];
    }

}
