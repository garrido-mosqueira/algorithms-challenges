package AlgoExpert.medium;

/*
Max Subset Sum No Adjacent:

Write a function that takes in an array of positive integers and returns the maximum sum of non-adjacent elements in the array.
If the input array is empty, the function should return O.

Sample Input:
array = [75, 105, 120, 75, 90, 135]

Sample Output:
330 // 75 + 120 + 135

 */

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
        if(array.length == 0) return 0;
        if(array.length == 1) return array[0];

        int secondMax = array[0];
        int maxSum = Math.max(array[0], array[1]);
        for (int i = 2; i < array.length; i++) {
            int current = Math.max(maxSum, secondMax + array[i]);
            secondMax = maxSum;
            maxSum = current;
        }
        return maxSum;
    }

    public static int maxSubsetSumNoAdjacentNotOptimal(int[] array) {
        if(array.length == 0) return 0;
        if(array.length == 1) return array[0];

        int[] maxSumming = new int[array.length];
        maxSumming[0] = array[0];
        maxSumming[1] = Math.max(array[0], array[1]);
        for (int i = 2; i < array.length; i++) {
            maxSumming[i] = Math.max(maxSumming[i - 2] + array[i], maxSumming[i - 1]);
        }
        return maxSumming[maxSumming.length-1];
    }

    public static int maxSubsetSumNoAdjacentNotRight(int[] array) {
        int[] maxSumming = new int[array.length];
        maxSumming[0] = array[0];
        maxSumming[1] = array[1];
        for (int i = 2; i < array.length; i++) {
            maxSumming[i] = Math.max(maxSumming[i - 2] + array[i], maxSumming[i - 1]);
        }
        return maxSumming[array.length-1];
    }

}
