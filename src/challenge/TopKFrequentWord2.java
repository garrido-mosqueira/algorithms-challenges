package challenge;

import javax.swing.text.html.parser.Entity;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TopKFrequentWord2 {

    /**
     * https://leetcode.com/problems/top-k-frequent-words/
     */
    static class Solution {
        public List<String> topKFrequent(String[] words, int k) {
            Map<String, Long> map = Arrays.stream(words)
                .collect(Collectors.groupingBy(String::trim, Collectors.counting()));

            return Arrays.stream(words)
                .distinct()
                .map(word -> new Key(word, map.get(word)))
                .sorted(Comparator.comparing(Key::getName))
                .sorted(Comparator.comparingLong(Key::getCount).reversed())
                .map(Key::getName)
                .limit(k)
                .collect(Collectors.toList());

        }

        public class Key {
            String name;
            Long count;

            Key(String name, Long count) {
                this.name = name;
                this.count = count;
            }

            String getName() {
                return this.name;
            }

            Long getCount() {
                return this.count;
            }
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        //Example 1:
        int k = 2;
        String[] Input = {"i", "love", "leetcode", "i", "love", "coding"};
        String[] Output = {"i", "love"};
        //Explanation: "i" and "love" are the two most frequent words.
        //Note that "i" comes before "love" due to a lower alphabetical order.

        assert solution.topKFrequent(Input, k).toString().equals(Arrays.toString(Output));

        //Example 2:
        k = 4;
        Input = new String[]{"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"};
        Output = new String[]{"the", "is", "sunny", "day"};
        //Explanation: "the", "is", "sunny" and "day" are the four most frequent words,
        //with the number of occurrence being 4, 3, 2 and 1 respectively.

        assert solution.topKFrequent(Input, k).toString().equals(Arrays.toString(Output));

        int[] nums = {1, 1, 1, 2, 2, 3};
        k = 2;
        Integer[] output = {1, 2};

        List<Integer> integers = topKFrequent(nums, k);

        assert integers.toString().equals(Arrays.toString(output));

    }

    public static List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Long> map = Arrays.stream(nums)
            .mapToObj(Integer::toString)
            .collect(Collectors.groupingBy(Integer::parseInt, Collectors.counting()));

        return map.entrySet().stream()
            .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
            .map(Map.Entry::getKey)
            .limit(k)
            .collect(Collectors.toList());
    }

}
