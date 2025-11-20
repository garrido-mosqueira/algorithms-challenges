package DesignGurus.warmup;

public class NumberofGoodPairs {

    public int numIdenticalPairs(int[] nums) {
        int numberOfGoodPairs = 0;

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    numberOfGoodPairs++;
                }
            }
        }

        return numberOfGoodPairs;
    }

    static void main(String[] args) {
        int[] nums = {1,2,3,1,1,3};
        NumberofGoodPairs numberofGoodPairs = new NumberofGoodPairs();
        System.out.println(numberofGoodPairs.numIdenticalPairs(nums));

        int[] nums1 = {1,1,1,1};
        System.out.println(numberofGoodPairs.numIdenticalPairs(nums1));
    }

}
