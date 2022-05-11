package CodeSignal.topic5_sorting_algorithms;

import static common.Utils.getString;
import static common.Utils.printArray;

public class MergeSort {

    static class MergeSortAlgorithm {
        public int[] array;
        public int[] temp;

        public MergeSortAlgorithm(int[] array) {
            this.array = array;
            this.temp = new int[array.length];
        }

        public void sort() {
            mergeSort(0, array.length - 1);
        }

        public void mergeSort(int low, int high) {

            if (low >= high) return;

            int middle = (high + low) / 2;

            mergeSort(low, middle);
            mergeSort(middle + 1, high);
            merge(low, middle, high);

//            printArrayRange(array, low, high);
//            System.out.println();
        }

        private void merge(final int low, final int middle, final int high) {
            for (int i = low; i <= high; i++) {
                temp[i] = array[i];
            }

            int i = low;
            int j = middle + 1;
            int k = low;

            while (i <= middle && j <= high) {
                if (temp[i] <= temp[j]) {
                    array[k] = temp[i];
                    i++;
                } else {
                    array[k] = temp[j];
                    j++;
                }
                k++;
            }

            while (i <= middle) {
                array[k] = temp[i];
                i++;
                k++;
            }

            while (j <= middle) {
                array[k] = temp[j];
                i++;
                k++;
            }
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{1, 3, 2, 5, 4, 6, 8, 7, 9};

        MergeSortAlgorithm mergeSortAlgorithm = new MergeSortAlgorithm(array);
        mergeSortAlgorithm.sort();
        printArray(mergeSortAlgorithm.array);
        String sortedArray = getString(mergeSortAlgorithm.array);

        assert sortedArray.equals("1 2 3 4 5 6 7 8 9");

    }
}
