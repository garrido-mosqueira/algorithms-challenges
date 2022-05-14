package AlgoExpert.hard;

/*
Longest Increasing Subsequence:

Given a non-empty array of integers, write a function that returns the longest strictly-increasing subsequence in the array.
A subsequence of an array is a set of numbers that aren't necessarily adjacent in the array but that are in the same order as they appear in the array.
For instance,the numbers [1, 3, 4] form a subsequence of the array [1, 2, 3, 4] , and so do the numbers [2, 4] .
Note that a single number in an array and the array itself are both valid subsequences of the array.
You can assume that there will only be one longest increasing subsequence.

Sample Input
array = [5, 7, -24, 12, 10, 2, 3, 12, 5, 6, 35]

Sample Output
[-24, 2, 3, 5, 6, 35]
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;


public class LongestIncreasingSubsequence {

    public static void main(String[] args) {
        int[] arrayInput1 = new int[]{5, 7, -24, 12, 10, 2, 3, 12, 5, 6, 35};
        List<Integer> output1 = List.of(-24, 2, 3, 5, 6, 35);

        int[] arrayInput2 = new int[]{10, 22, 9, 33, 21, 61, 41, 60, 80};
        List<Integer> output2 = List.of(10, 22, 33, 41, 60, 80);

        testing(arrayInput1, output1);
        testing(arrayInput2, output2);

    }

    private static void testing(int[] arrayInput, List<Integer> output) {
        List<Integer> integers = longestIncreasingSubsequence(arrayInput);
        System.out.println(integers);
        boolean containsAll = new HashSet<>(integers).containsAll(output);
        System.out.println(containsAll);
    }

    public static List<Integer> longestIncreasingSubsequence(int[] array) {
        List<Integer> sequence = new ArrayList<>();
        sequence.add(0, array[0]);
        for (int i = 1; i < array.length; i++) {
            int index = binaryMinimumSearch(sequence, array[i]);
            if (index != Integer.MIN_VALUE) {
                sequence.set(index, array[i]);
            } else {
                sequence.add(array[i]);
            }
        }
        return sequence.stream().sorted().collect(Collectors.toList());
    }

    private static int binaryMinimumSearch(List<Integer> sequence, int num) {
        int left = 0;
        int right = sequence.size()-1;
        int indexMin = Integer.MAX_VALUE;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (sequence.get(mid) >= num) {
                indexMin = Math.min(indexMin, mid);
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return indexMin != Integer.MAX_VALUE ? indexMin : Integer.MIN_VALUE;
    }

    public static List<Integer> longestIncreasingSubsequenceOrderN2(int[] array) {
        int[] length = new int[array.length];
        int[] sequence = new int[array.length];

        Arrays.fill(length, 1);
        Arrays.fill(sequence, Integer.MIN_VALUE);
        int maxIndex = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < i; j++) {
                if (array[i] > array[j] && length[j] + 1 > length[i]) {
                    sequence[i] = j;
                    length[i] = 1 + length[j];
                }
                maxIndex = length[i] > length[maxIndex] ? i : maxIndex;
            }
        }

        List<Integer> result = new ArrayList<>();
        while (maxIndex != Integer.MIN_VALUE) {
            result.add(array[maxIndex]);
            maxIndex = sequence[maxIndex];
        }

        return result.stream().sorted().collect(Collectors.toList());
    }

}
