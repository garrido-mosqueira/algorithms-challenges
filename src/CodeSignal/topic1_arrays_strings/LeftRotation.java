package CodeSignal.topic1_arrays_strings;

import static common.Utils.getString;

public class LeftRotation {

    static int[] rotLeft(int[] a, int leftRotation) {
        int roulette = a.length;
        int[] aux;

        if (leftRotation < roulette) {
            int factor = roulette - leftRotation;
            aux = shiftArray(a, factor);
        } else {
            int spins = leftRotation / roulette;
            int shiftLeft = leftRotation - (spins * roulette);
            int factor = Math.abs(roulette - shiftLeft);
            aux = shiftArray(a, factor);
        }

        return aux;
    }

    private static int[] shiftArray(int[] a, int factor) {
        int roulette = a.length;
        int[] aux = new int[roulette];
        for (int i = 0; i < roulette; i++) {
            int newIndex = i + factor;
            if (newIndex >= roulette) {
                newIndex = newIndex - roulette;
            }
            aux[newIndex] = a[i];
        }
        return aux;
    }

    public static void main(String[] args) {
        int[] array = new int[]{1, 2, 3, 4, 5};

        int[] ints = rotLeft(array, 6);
        String result = getString(ints);
        assert result.equals("2 3 4 5 1") : "Wrong";

        ints = rotLeft(array, 61);
        result = getString(ints);
        assert result.equals("2 3 4 5 1") : "Wrong";
    }

}
