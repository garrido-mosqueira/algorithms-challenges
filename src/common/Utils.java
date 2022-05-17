package common;

public final class Utils {

    public static void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    public static void printArrayRange(int[] array, int low, int high) {
        System.out.print("[ ");
        for (int i =low; i <= high; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.print(" ]");
        System.out.println();
    }

    public static void printArrayRange(char[] array, int low, int high) {
        System.out.print("[ ");
        for (int i =low; i <= high; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.print("]");
        System.out.println();
    }

    public static String getString(int[] array) {
        String aux = "";
        for (int i = 0; i < array.length; i++) {
           aux += array[i] + " ";
        }
        return aux.trim();
    }

}
