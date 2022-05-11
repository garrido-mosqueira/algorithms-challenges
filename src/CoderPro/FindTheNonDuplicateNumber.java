package CoderPro;

import java.util.HashSet;
import java.util.Set;

/**
 * Session 10
 * https://www.techseries.dev/products/coderpro/categories/1831147/posts/6178060
 */
public class FindTheNonDuplicateNumber {

    static class Solution {

        private final int[] array;

        public Solution(final int[] array) {
            this.array = array;
        }

        public int find() {
            Set<Integer> oneResult = new HashSet<>();
            for (int num : array) {
                if (!oneResult.add(num)) {
                    oneResult.remove(num);
                }
            }
            return oneResult.stream().findFirst().orElse(-1);
        }

        public int findXOR() {
            int result = 0;
            for (int num : array) {
                result ^= num;
            }
            return result;
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{4, 3, 2, 4, 1, 3, 2};
        Solution solution = new Solution(array);

        int usingSet = solution.find();
        int usingXOR = solution.findXOR();

        System.out.println("Using Set " + usingSet);
        System.out.println("Using XOR " + usingXOR);

    }

}
