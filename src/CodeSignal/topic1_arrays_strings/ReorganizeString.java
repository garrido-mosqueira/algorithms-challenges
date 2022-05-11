package CodeSignal.topic1_arrays_strings;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/reorganize-string/
 */
public class ReorganizeString {

    public static String reorganizeString(String S) {
        Map<Character, Integer> mapCount = new HashMap<>();
        for (char c : S.toCharArray()) {
            mapCount.put(c, mapCount.getOrDefault(c, 0) + 1);
        }

        PriorityQueue<Character> maxHeap = new PriorityQueue<>(Comparator.comparingInt(mapCount::get).reversed());
        maxHeap.addAll(mapCount.keySet());

        StringBuilder aux = new StringBuilder();
        while (maxHeap.size() > 1) {
            char current = maxHeap.remove();
            char next = maxHeap.remove();
            aux.append(current);
            aux.append(next);

            mapCount.put(current, mapCount.get(current) - 1);
            mapCount.put(next, mapCount.get(next) - 1);

            if (mapCount.get(current) > 0) {
                maxHeap.add(current);
            }
            if (mapCount.get(next) > 0) {
                maxHeap.add(next);
            }
        }

        if (!maxHeap.isEmpty()) {
            char lastOne = maxHeap.remove();
            if (mapCount.get(lastOne) > 1) {
                return "";
            }
            aux.append(lastOne);
        }

        return aux.toString();
    }

    public static void main(String[] args) {

        System.out.println(reorganizeString("aab"));
        System.out.println(reorganizeString("aaab"));

    }


}
