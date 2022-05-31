package LeetCode.medium;

import java.util.*;

public class ThreeSum {

    public static void main(String[] args) {
        int[] nums = new int[]{-1, 0, 1, 2, -1, -4};

        System.out.println((threeSum(nums)) + " should be [[-1,-1,2],[-1,0,1]]");
        System.out.println((threeSum1(nums)) + " should be [[-1,-1,2],[-1,0,1]]");
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> sol = new ArrayList<>();

        for (int i = 0; i < nums.length - 2; i++) {
            if (i == 0 || nums[i] != nums[i - 1]) {
                int target = -nums[i];
                int left = i + 1;
                int right = nums.length - 1;
                while (left < right) {
                    if (nums[left] + nums[right] == target) {
                        sol.add(List.of(nums[i], nums[left], nums[right]));
                        while (left < right && nums[left] == nums[left + 1]) {
                            left++;
                        }
                        while (left < right && nums[right] == nums[right - 1]) {
                            right--;
                        }
                        left++;
                        right--;
                    } else if (nums[left] + nums[right] > target) {
                        right--;
                    } else {
                        left++;
                    }
                }
            }
        }
        return sol;
    }

    public static List<List<Integer>> threeSum1(int[] nums) {
        Set<List<Integer>> result = new HashSet<>();
        List<Integer> sorted = Arrays.stream(nums).boxed().sorted().toList();

        for (int i = 0; i < sorted.size(); i++) {
            int target = sorted.get(i);
            int left = i + 1;
            int right = sorted.size() - 1;

            while (left < right ) {
                int sum = sorted.get(left) + sorted.get(right) + target;
                if (sum > 0) {
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    List<Integer> triple = List.of(sorted.get(i), sorted.get(left), sorted.get(right));
                    result.add(triple);
                    left++;
                    right--;
                }
            }
        }
        return result.stream().toList();
    }
}
