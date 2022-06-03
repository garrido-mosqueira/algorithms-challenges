package RealChallenges;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Write a function that takes a list of integers and outputs a list of pairs representing the consecutive ranges contained in the inputlist. Note: the input list is sorted.

arr1 = [1,3,4]
Output = [(1,1),(3,4)]

arr2 = [1,2,3,4,5]
Output = [(1,5)]

arr3 = [1,1,1,3,4,5,6,7,9]
Output = [(1,1), (3,7), (9,9)]

 */
public class ConsecutiveRanges {

    public static void main(String[] args) {
        int[] input1 = {1, 3, 4};
        int[][] output1 = {{1,1},{3,4}};

        int[] input2 = {1,2,3,4,5};
        int[][] output2 = {{1,5}};

        int[] input3 = {1,1,1,3,4,5,6,7,9};
        int[][] output3 = {{1,1}, {3,7}, {9,9}};

        int[][] out1 = consecutiveRange(input1);
        int[][] out2 = consecutiveRange(input2);
        int[][] out3 = consecutiveRange(input3);

        System.out.println(Arrays.deepToString(output1) + " is = ? " + Arrays.deepToString(out1));
        System.out.println(Arrays.deepToString(output2) + " is = ? " + Arrays.deepToString(out2));
        System.out.println(Arrays.deepToString(output3) + " is = ? " + Arrays.deepToString(out3));

    }

    public static int[][] consecutiveRange(int[] array) {
        int left = 0;
        int index = left;
        int right = left + 1;

        List<int[]> result = new ArrayList<>();
        while (right <= array.length) {
            while (right < array.length && (array[right] - array[index] == 1 || array[right] == array[index])) {
                index++;
                right++;
            }
            result.add(new int[]{array[left], array[index]});
            left = right;
            index = right;
            right++;
        }
        return result.toArray(new int[0][0]);
    }
}
