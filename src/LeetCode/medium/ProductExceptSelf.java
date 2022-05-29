package LeetCode.medium;

import java.util.Arrays;

public class ProductExceptSelf {

    public static void main(String[] args) {

        System.out.println("Output: [24,12,8,6] is? :" + Arrays.toString(productExceptSelf(new int[]{1, 2, 3, 4})));
        System.out.println("Output: [[0, 0, 9, 0, 0]] is? :" + Arrays.toString(productExceptSelf(new int[]{-1, 1, 0, -3, 3})));
        System.out.println("Output: [0,0] is? :" + Arrays.toString(productExceptSelf(new int[]{0, 0})));
        System.out.println("Output: [0,0,0] is? :" + Arrays.toString(productExceptSelf(new int[]{0,4,0})));

    }

    public static int[] productExceptSelf(int[] nums) {
        int totalProduct = 1;
        int absSum = 0;
        int numZeros = 0;
        for (int num : nums) {
            if (num != 0) {
                totalProduct = totalProduct * num;
            } else {
                numZeros++;
            }
            absSum += Math.abs(num);
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0 && numZeros > 0) {
                nums[i] = 0;
            } else {
                if (nums[i] == 0 && numZeros > 0) {
                    nums[i] = (absSum > 0 && numZeros==1 ) ? totalProduct : 0;
                } else {
                    nums[i] = totalProduct / nums[i];
                }
            }
        }

        return nums;
    }

    public static int[] productExceptSelf_notForEdgeCases(int[] nums) {
        int total = 1;
        for (int num : nums) {
            if (num != 0) {
                total = total * num;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[i] = total / nums[i];
            }
        }

        return nums;
    }

}
