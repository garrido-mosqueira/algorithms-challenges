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
        private final int[] temp;
        private int[] quickArray;
        private int[] mergeArray;

        Solution(int[] array) {
            this.array = array;
            this.temp = new int[array.length];
        }

        public void printNotSorted() {
            printArray(array);
        }

        public int[] sortedSquaredQuickSort() {
            quickArray = this.array.clone();
            squareArray(quickArray);
            quickSort(0, quickArray.length - 1);
            return quickArray;
        }

        public int[] sortedSquaredMergeSort() {
            mergeArray = this.array.clone();
            squareArray(mergeArray);
            mergeSort(0, mergeArray.length - 1);
            return mergeArray;
        }

        private void squareArray(int[] arr) {
            for (int i = 0; i < arr.length; i++) {
                arr[i] = arr[i] * arr[i];
            }
        }

        private void quickSort(int low, int high) {
            if (low >= high) return;

            int pivot = findPivot(low, high);

            quickSort(low, pivot - 1);
            quickSort(pivot + 1, high);
        }

        private void mergeSort(final int low, final int high) {
            if (low >= high) return;

            int middle = (high + low) / 2;
            mergeSort(low, middle);
            mergeSort(middle + 1, high);
            merge(low, middle, high);
        }

        private void merge(final int low, final int middle, final int high) {
            for (int i = low; i <= high; i++) {
                temp[i] = mergeArray[i];
            }

            int i = low;
            int j = middle + 1;
            int k = low;

            while (i <= middle && j <= high) {
                if (temp[i] <= temp[j]) {
                    mergeArray[k++] = temp[i++];
                } else {
                    mergeArray[k++] = temp[j++];
                }
            }

            while (i <= middle) {
                mergeArray[k++] = temp[i++];
            }
            while (j <= high) {
                mergeArray[k++] = temp[j++];
            }
        }

        private int findPivot(final int low, final int high) {
            int pivot = (high + low) / 2;
            int pivotValue = quickArray[pivot];

            swap(pivot, high);

            int j = low;
            for (int i = low; i < high; i++) {
                if (quickArray[i] <= pivotValue) {
                    swap(i, j);
                    j++;
                }
            }

            swap(j, high);
            return j;
        }

        public void swap(int i, int j) {
            int aux = quickArray[i];
            quickArray[i] = quickArray[j];
            quickArray[j] = aux;
        }
    }

    public static int[] sortedSquaredArray(int[] array) {
        //This will be the linear solution since neither Merge or Quick sorting algorithms are O(N) - best O(LogN)
        int[] aux = new int[array.length];
        int i = 0;
        int j = array.length - 1;
        int k = j;

        while (i <= j) {
            int squaredLow = array[i] * array[i];
            int squaredHigh = array[j] * array[j];

            if (squaredHigh > squaredLow) {
                aux[k--] = squaredHigh;
                j--;
            } else {
                aux[k--] = squaredLow;
                i++;
            }
        }
        return aux;
    }

    public static void main(String[] args) {
        int[] array = new int[]{-6, -4, 1, 2, 3, 5};

        int[] result = sortedSquaredArray(array);
        printArray(result);

        Solution solution = new Solution(array);

        int[] sortedSquaredQuickSort = solution.sortedSquaredQuickSort();
        printArray(sortedSquaredQuickSort);
        solution.printNotSorted();

        int[] sortedSquaredMergeSort = solution.sortedSquaredMergeSort();
        printArray(sortedSquaredMergeSort);
        solution.printNotSorted();
    }

}
