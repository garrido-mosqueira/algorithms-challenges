package coder_pro;

import java.util.Stack;

/**
 * Session 12
 * https://www.techseries.dev/products/coderpro/categories/1831147/posts/6178061
 */
public class MaximumInAStack {

    static class Solution<T extends Comparable<T>>{
        private final Stack<T> principal;
        private final Stack<T> maximum;

        Solution() {
            this.principal = new Stack<>();
            this.maximum = new Stack<>();
        }

        public void push(T data) {
            if (principal.isEmpty()) {
                maximum.push(data);
            } else {
                if (maximum.peek().compareTo(data) < 0) {
                    maximum.push(data);
                } else {
                    maximum.push(maximum.peek());
                }
            }
            principal.push(data);
        }

        public T peek() {
            return principal.peek();
        }

        public T pop() {
            maximum.pop();
            return principal.pop();
        }

        public T maximum() {
            if (maximum.isEmpty()) {
                return null;
            }
            return maximum.peek();
        }

    }

    public static void main(String[] args) {
        Solution<Integer> solution = new Solution<>();

        solution.push(10);
        solution.push(8);
        solution.push(7);

        System.out.println(solution.peek());//7
        System.out.println(solution.maximum());//10

        solution.push(11);
        System.out.println(solution.maximum());//11

        solution.pop();
        System.out.println(solution.maximum());//10

    }


}
