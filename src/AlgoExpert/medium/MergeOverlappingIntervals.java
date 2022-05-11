package AlgoExpert.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/*
Merge Overlapping Intervals:

Write a function that takes in a non-empty array of arbitrary intervals, merges any overlapping intervals, and returns the new intervals in no particular order.
Each interval [interval] is an array of two integers, with interval[0] as the start of the interval and interval[1] as the end of the interval.
Note that back-to-back intervals aren't considered to be overlapping, For example, [1, 5] and [6, 7] aren't overlapping;
however, [1, 6] and [6, 7] are indeed overlapping.
Also note that the start of any particular interval will always be less than or equal to the end of that interval.

Sample Input
intervals = [[1, 2], [3, 5], [4, 7], [6, 8], [9, 10]]

Sample Output
[[1, 2], [3, 81, [9, 10]]

// Merge the intervals (3, 5], [4, 71, and [6, 8].
// The intervals could be ordered differently.

 */

public class MergeOverlappingIntervals {

    public static void main(String[] args) {

		int[][] matrix =new int[][]{{1, 2},{3, 5},{4, 7},{6, 8},{9, 10}};   //worked
//        int[][] matrix = new int[][]{{100, 105}, {1, 104}};               //worked
//        int[][] matrix = new int[][]{{2, 3}, {4, 5}, {6, 7}, {8, 9}, {1, 10}};

        int[][] ints = mergeOverlappingIntervals(matrix);

        System.out.println(Arrays.deepToString(ints));

    }

    public static int[][] mergeOverlappingIntervals(int[][] intervals) {
        List<int[]> result = new ArrayList<>();
        int size = intervals.length;
        int i = 0, j = 0;
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        while (i < size) {
            int low = intervals[i][0];
            int high = intervals[i][1];
            j = i+1;
            while (j < size) {
                int currentLow = intervals[j][0];
                if (currentLow <= high) {
                    if (high < intervals[j][1]) {
                        high = intervals[j][1];
                    }
                    j++;
                } else {
                    result.add(new int[]{low, high});
                    break;
                }
            }
            i = j;
            if (i == size) {
                result.add(new int[]{low, high});
                return result.toArray(new int[0][0]);
            }
        }
        return result.toArray(new int[0][0]);
    }

    public static int[][] mergeOverlappingIntervalsNotOptimal(int[][] intervals) {
        List<int[]> result = new ArrayList<>();
        int size = intervals.length;
        int i = 0;
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        
        while (i < size) {
            int low = intervals[i][0];
            int high = intervals[i][1];
            if (i == size - 1) {
                result.add(new int[]{low, high});
                return result.toArray(new int[0][0]);
            }
            for (int j = i + 1; j <= size; j++) {
                int currentLow = intervals[j][0];
                if (currentLow > high) {
                    result.add(new int[]{low, high});
                    i = j;
                    break;
                } else {
                    if (high < intervals[j][1]) {
                        high = intervals[j][1];
                    }
                    if (j == size - 1) {
                        result.add(new int[]{low, high});
                        return result.toArray(new int[0][0]);
                    }
                }
            }
        }
        return result.toArray(new int[0][0]);
    }

}
