package topic3_stacks_queues;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

public class KthLargestElement {

    static int kthLargestElement(int[] nums, int k) {
        //do heap sort
        Queue<Integer> minQueue = new PriorityQueue<Integer>();
        for (int i = 0; i < nums.length; i++) {
            minQueue.offer(nums[i]);
            if (minQueue.size() > k) {
                minQueue.poll();
            }
        }

        return minQueue.isEmpty() ? 0 : minQueue.peek();
    }

    static int kthSmallestElement(int[] nums, int k) {
        //do heap sort
        Queue<Integer> minQueue = new PriorityQueue<Integer>(Collections.reverseOrder());
        for (int i = 0; i < nums.length; i++) {
            minQueue.offer(nums[i]);
            if (minQueue.size() > k) {
                minQueue.poll();
            }
        }

        return minQueue.isEmpty() ? 0 : minQueue.peek();
    }

    public static void main(String[] args) {

        int k = 2;
        int[] nums = new int[]{3, 2, 1, 5, 6, 4};

        assert kthLargestElement(nums, k) == 5;

        assert kthSmallestElement(nums, k) == 2;
    }
}
