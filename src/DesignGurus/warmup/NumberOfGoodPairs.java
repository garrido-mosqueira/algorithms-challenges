package DesignGurus.warmup;

import java.util.HashMap;
import java.util.Map;

public class NumberOfGoodPairs {

    public int numIdenticalPairs(int[] nums) {
        // Guard against null input
        if (nums == null || nums.length < 2) return 0;

        // O(n) solution using a frequency map. As we iterate the array, for each
        // element x, we already have seen count[x] occurrences. Each of those
        // forms a "good pair" with the current x. We add count[x] to the answer
        // and then increment count[x].
        Map<Integer, Integer> freq = new HashMap<>();
        int pairs = 0;
        for (int x : nums) {
            int seen = freq.getOrDefault(x, 0);
            pairs += seen; // each prior identical element forms a pair with current
            freq.put(x, seen + 1);
        }
        return pairs;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3,1,1,3};
        NumberOfGoodPairs numberofGoodPairs = new NumberOfGoodPairs();
        System.out.println(numberofGoodPairs.numIdenticalPairs(nums));

        int[] nums1 = {1,1,1,1};
        System.out.println(numberofGoodPairs.numIdenticalPairs(nums1));
    }

}
