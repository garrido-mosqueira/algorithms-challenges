package topic4_hash_maps;

import java.util.HashMap;
import java.util.Map;

import static common.Utils.printArray;

public class IceCreamParlor {

    static int[] iceCreamParlor(int money, int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            int dif = money - arr[i];
            if (map.containsKey(dif)) {
                return new int[]{map.get(dif) + 1, i + 1};
            } else {
                map.put(arr[i], i);
            }
        }
        return new int[]{};
    }

    public static void main(String[] args) {
        int[] result;

        int[] prices = new int[]{1, 4, 5 ,3, 2};
        result = iceCreamParlor(4, prices);
        printArray(result);

        prices = new int[]{2, 2, 4, 3};
        result = iceCreamParlor(4, prices);
        printArray(result);
    }

}
