package topic5_sorting_algorithms;
/*
https://app.codesignal.com/interview-practice/task/6LMyHZbNoXwk4drYi
Note: Come up with a linear solution, since that is what you would be asked to do in an interview.
You have a sorted array of integers. Write a function that returns a sorted array containing the squares of those integers.

Example:

    For array = [-6, -4, 1, 2, 3, 5], the output should be
    sortedSquaredArray(array) = [1, 4, 9, 16, 25, 36].

    The array of squared integers from array is: [36, 16, 1, 4, 9, 25].
    When sorted, it becomes: [1, 4, 9, 16, 25, 36].

Input/Output:

    [execution time limit] 3 seconds (java)
    [input] array.integer array
            A sorted array of integers.
            Guaranteed constraints:
            1 ≤ array.length ≤ 104,
            -104 ≤ array[i] ≤ 104.

    [output] array.integer
            A sorted array of integers composed of the squared integers from the input array.
 */

import static common.Utils.printArray;

public class SortedSquaredArray {

    static class Solution {
        private final int[] array;

        Solution(int[] array) {
            this.array = array;
        }

        public int[] sortedSquaredQuickSort() {
            squareArray();
            quickSort(0, array.length - 1);
            return array;
        }

        private void squareArray() {
            for (int i = 0; i < array.length; i++) {
                array[i] = array[i] * array[i];
            }
        }

        private void quickSort(int low, int high) {
            if (low >= high) return;

            int pivot = findPivot(low, high);

            quickSort(low, pivot - 1);
            quickSort(pivot + 1, high);
        }

        private int findPivot(final int low, final int high) {
            int pivot = (high + low) / 2;
            int pivotValue = array[pivot];

            swap(pivot, high);

            int j = low;
            for (int i = low; i < high; i++) {
                if (array[i] <= pivotValue) {
                    swap(i, j);
                    j++;
                }
            }

            swap(j, high);
            return j;
        }

        public void swap(int i, int j) {
            int aux = array[i];
            array[i] = array[j];
            array[j] = aux;
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{-6, -4, 1, 2, 3, 5};
        Solution solution = new Solution(array);

        int[] sortSquared = solution.sortedSquaredQuickSort();

        printArray(sortSquared);
    }

}
