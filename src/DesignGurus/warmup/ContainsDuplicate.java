package DesignGurus.warmup;

import java.util.HashSet;
import java.util.Set;

public class ContainsDuplicate {

    public boolean containsDuplicate(int[] nums) {
        Set<Integer> numbers = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            if (numbers.contains(nums[i])) {
                return true;
            } else {
                numbers.add(nums[i]);
            }
        }
        return false;
    }

    public boolean containsDuplicateBetter(int[] nums) {
        Set<Integer> unique_set = new HashSet<>(); // Use HashSet to store unique elements

        for (int x : nums) {
            if (!unique_set.add(x)) // If the set already contains the current element, return true
                return true;
        }

        return false; // Return false if no duplicates found
    }

    static void main(String[] args) {
        ContainsDuplicate solution = new ContainsDuplicate();

        int[] nums1 = {1, 2, 3, 4};
        System.out.println(solution.containsDuplicate(nums1)); // Expected output: false

        int[] nums2 = {1, 2, 3, 1};
        System.out.println(solution.containsDuplicate(nums2)); // Expected output: true

        int[] nums3 = {};
        System.out.println(solution.containsDuplicate(nums3)); // Expected output: false

        int[] nums4 = {3, 2, 6, -1, 2, 1};
        System.out.println(solution.containsDuplicate(nums4)); // Expected output: true
    }

}
