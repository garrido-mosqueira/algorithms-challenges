package common;

public final class Utils {

    public static void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
    }

    public static void printArrayRange(int[] array, int low, int high) {
        for (int i =low; i <= high; i++) {
            System.out.print(array[i] + " ");
        }
    }

    public static String getString(int[] array) {
        String aux = "";
        for (int i = 0; i < array.length; i++) {
           aux += array[i] + " ";
        }
        return aux.trim();
    }

}
