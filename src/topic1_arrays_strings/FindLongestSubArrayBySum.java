package topic1_arrays_strings;

import java.util.Arrays;

/**
 * https://app.codesignal.com/interview-practice/task/izLStwkDr5sMS9CEm
 * <p>
 * You have an unsorted array arr of non-negative integers and a number s.
 * Find a longest contiguous subarray in arr that has a sum equal to s.
 * Return two integers that represent its inclusive bounds.
 * If there are several possible answers, return the one with the smallest left bound. If there are no answers, return [-1].
 * <p>
 * Your answer should be 1-based, meaning that the first position of the array is 1 instead of 0.
 * <p>
 * Example:
 * <p>
 * For s = 12 and arr = [1, 2, 3, 7, 5], the output should be
 * findLongestSubarrayBySum(s, arr) = [2, 4].
 * The sum of elements from the 2nd position to the 4th position (1-based) is equal to 12: 2 + 3 + 7.
 * <p>
 * For s = 15 and arr = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10], the output should be
 * findLongestSubarrayBySum(s, arr) = [1, 5].
 * The sum of elements from the 1st position to the 5th position (1-based) is equal to 15: 1 + 2 + 3 + 4 + 5.
 * <p>
 * For s = 15 and arr = [1, 2, 3, 4, 5, 0, 0, 0, 6, 7, 8, 9, 10], the output should be
 * findLongestSubarrayBySum(s, arr) = [1, 8].
 * The sum of elements from the 1st position to the 8th position (1-based) is equal to 15: 1 + 2 + 3 + 4 + 5 + 0 + 0 + 0.
 */
public class FindLongestSubArrayBySum {

    static int[] findLongestSubarrayBySum(int s, int[] arr) {

        for (int i = 0; i < arr.length; i++) {
            int sum = arr[i];
            int j = i + 1;
            while (j < arr.length && (sum < s || arr[j] == 0)) {
                sum += arr[j];
                j++;
            }
            if (sum == s) return new int[]{i + 1, j};
        }

        return new int[]{-1};
    }

    static int[] findLongestSubarrayBySum2(int s, int[] arr) {
        int sum = 0;
        int j = 0;
        int left = -1;
        int right = -1;
        for (int i = 0; i < arr.length; i++) {
            while (j < arr.length && (sum < s || arr[j] == 0)) {
                sum += arr[j];
                j++;
            }
            if (sum == s) {
                if (left < i) {
                    left = i + 1;
                    right = j;
                }
            }
            sum -= arr[i];
        }
        return left == -1 ? new int[]{-1} : new int[]{left, right};
    }

    public static void main(String[] args) {
        int sum = 12;
        int[] arr = new int[]{1, 2, 3, 7, 5};
//        System.out.println(Arrays.toString(findLongestSubarrayBySum(sum, arr)));//[2, 4]
//        System.out.println(Arrays.toString(findLongestSubarrayBySum2(sum, arr)));
//
//        sum = 15;
//        arr = new int[]{1, 2, 3, 4, 5, 0, 0, 0, 6, 7, 8, 9, 10};
//        System.out.println(Arrays.toString(findLongestSubarrayBySum(sum, arr)));//[1, 8]
//        System.out.println(Arrays.toString(findLongestSubarrayBySum2(sum, arr)));
//
//        sum = 20;
//        arr = new int[]{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 9, 10};
//        System.out.println(Arrays.toString(findLongestSubarrayBySum(sum, arr)));//[2, 13]
//        System.out.println(Arrays.toString(findLongestSubarrayBySum2(sum, arr)));
//
//        sum = 20;
//        arr = new int[]{5, 5, 5, 6, 5, 5, 5, 6, 6, 5, 1, 9, 10};
//        System.out.println(Arrays.toString(findLongestSubarrayBySum(sum, arr)));//[11, 13]
//        System.out.println(Arrays.toString(findLongestSubarrayBySum2(sum, arr)));

        sum = 1150; //[42, 56]
        arr = new int[]{131, 45, 45, 50, 119, 66, 164, 153, 174, 71, 132, 148, 112, 70, 199, 99, 104, 153, 80, 54, 44, 123, 89, 164, 35, 51, 23, 41, 112, 93, 52, 181, 78, 17, 77, 179, 21, 16, 149, 199, 180, 36, 58, 84, 63, 103, 63, 21, 171, 23, 74, 42, 87, 89, 118, 118, 93, 99, 68, 150, 66, 190, 133, 20, 82, 4, 131, 29, 32, 190, 153, 31, 173, 65, 8};
//        System.out.println(Arrays.toString(findLongestSubarrayBySum(sum, arr)));
        System.out.println(Arrays.toString(findLongestSubarrayBySum2(sum, arr)));

    }

}
