package CodeSignal.topic5_sorting_algorithms;

public class QuickSortWeirdVersion {

    static class QuickSortFirstPivot {
        private final int[] array;

        public QuickSortFirstPivot(int[] array) {
            this.array = array;
        }

        public void sort() {
            quickSort(0, array.length - 1);
        }

        public void print(int low, int high) {
            for (int i = low; i <= high; i++) {
                System.out.print(array[i] + " ");
            }
            System.out.println();
        }

        public void quickSort(int low, int high) {
            //Solution for HackerRank: Quicksort 2 - Sorting
            if (low >= high) return;

            //int pivot = findPivot(low, high);
            int pivot = quickSortPivot(low, high);

            quickSort(low, pivot - 1);
            quickSort(pivot + 1, high);
            print(low, high);
        }

        private int quickSortPivot(int low, int high) {
            //The right Solution for HackerRank: Quicksort 1 - Partition.. weird, it's a MergeSort wanna be, but works
            int[] leftArray = new int[high - low];
            int[] rightArray = new int[high - low];
            int left = 0;
            int right = 0;
            int pivot = low;
            int pivotValue = array[pivot];

            if (leftArray.length < 1) {
                return low;
            } else if (leftArray.length < 2) {
                if (array[low] > array[high]) {
                    swap(low, high);
                }
                return low;
            }

            for (int i = low + 1; i <= high; i++) {
                if (array[i] < pivotValue) {
                    leftArray[left++] = array[i];
                    pivot++;
                } else {
                    rightArray[right++] = array[i];
                }
            }

            for (int i = 0; i < left; i++) {
                array[low++] = leftArray[i];
            }

            array[pivot] = pivotValue;

            for (int i = 0; i < right; i++) {
                array[++low] = rightArray[i];
            }

            return pivot;
        }

        private void swap(int i, int j) {
            int aux = array[i];
            array[i] = array[j];
            array[j] = aux;
        }

        private int findPivot(int low, int high) {
            //Right way to find the pivot
            int j = low;
            for (int i = low; i < high; i++) {
                if (array[i] <= array[low]) {
                    swap(i, j);
                    j++;
                }
            }
            swap(low, j - 1);
            return j - 1;
        }
    }

    public static void main(String[] args) {
        int[] array = {5, 8, 1, 3, 7, 9, 2};

        QuickSortFirstPivot quickSort = new QuickSortFirstPivot(array);
        quickSort.sort();
    }
}
