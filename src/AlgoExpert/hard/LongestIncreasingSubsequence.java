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
//        int[] arrayInput = new int[]{5, 7, -24, 12, 10, 2, 3, 12, 5, 6, 35};
//        List<Integer> output = List.of(-24, 2, 3, 5, 6, 35);

        int[] arrayInput = new int[]{10, 22, 9, 33, 21, 61, 41, 60, 80};
        List<Integer> output = List.of(10, 22, 33, 41, 60, 80);

        List<Integer> integers = longestIncreasingSubsequence(arrayInput);

        System.out.println(integers);

        boolean containsAll = new HashSet<>(integers).containsAll(output);

        System.out.println(containsAll);

    }

    public static List<Integer> longestIncreasingSubsequence(int[] array) {
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
