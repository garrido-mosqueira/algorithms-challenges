package LeetCode.medium;

import java.util.*;
import java.util.stream.Collectors;

/*
Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.

Example 1:

Input: nums = [1,1,1,2,2,3], k = 2
Output: [1,2]
Example 2:

Input: nums = [1], k = 1
Output: [1]
 */
public class TopKFrequent {

    public static void main(String[] args) {

        int[] nums1 = {1,1,1,2,2,3};
        int[] nums2 = {1};

        System.out.println("[1,2] is? " + Arrays.toString(topKFrequent(nums1, 2)));
        System.out.println("[1] is? " + Arrays.toString(topKFrequent(nums2, 1)));

    }

    public static int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> count = new HashMap<>();

        for (Integer num : nums) {
            count.put(num, count.getOrDefault(num, 1) + 1);
        }

        LinkedHashMap<Integer, Integer> sortedMap =
                count.entrySet().stream()
                        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                        .collect(Collectors.toMap(Map.Entry::getKey,
                                Map.Entry::getValue,
                                (e1, e2) -> e1, LinkedHashMap::new));

        return sortedMap.keySet().stream()
                .mapToInt(i -> i)
                .limit(k)
                .toArray();

    }

}
