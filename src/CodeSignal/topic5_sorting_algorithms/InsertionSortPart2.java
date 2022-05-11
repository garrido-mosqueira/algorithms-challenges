package CodeSignal.topic5_sorting_algorithms;

public class InsertionSortPart2 {

    static void insertionSort2(int n, int[] arr) {
        for(int i= 1; i < arr.length; i++) {
            int j = i;
            while(j > 0 && arr[j-1] > arr[j] ) {
                swap(arr, j);
                j--;
            }
            System.out.println(printArray(arr));
        }
    }

    static String printArray(int[] arr){
        String buff = "";
        for(int i=0; i<arr.length; i++){
            buff = buff + arr[i] + " ";
        }
        return buff;
    }

    static void swap(int arr[], int j) {
        int aux = arr[j];
        arr[j] = arr[j-1];
        arr[j-1] = aux;
    }

    public static void main(String[] args) {
        int[] array = new int[]{2, 3, 5, 1, 4, 6, 8, 7, 9};
        insertionSort2(array.length, array);
    }

}
