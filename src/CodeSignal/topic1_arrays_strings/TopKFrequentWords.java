package CodeSignal.topic1_arrays_strings;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TopKFrequentWords {

    public static List<String> topKFrequent(String[] words, int k) {
        return null;
    }

    public static String[] topKFrequentlyMentionedKeywords(String[] keywords, String[] reviews, int k) {
        List<String> reviewWords = Arrays.stream(reviews)
            .map(String::toLowerCase)
            .map(str -> str.split(" "))
            .flatMap(Arrays::stream)
            .collect(Collectors.toList());

        return Arrays.stream(keywords)
            .map(key -> toKeyWord(key.toLowerCase(), reviewWords))
            .sorted(Comparator.comparing(KeyWord::getName))
            .sorted(Comparator.comparingLong(KeyWord::getCount).reversed())
            .map(KeyWord::getName)
            .limit(k)
            .toArray(String[]::new);
    }

    public static KeyWord toKeyWord(final String key, final List<String> reviewList) {
        long count = reviewList.stream()
            .filter(word -> word.contains(key))
            .count();
        return new KeyWord(key, count);
    }

    public static class KeyWord {
        String name;
        Long count;

        KeyWord(String name, Long count) {
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

    public static void main(String[] args) {
        /**
         * https://leetcode.com/discuss/interview-question/542597/
         * Given a list of reviews, a list of keywords and an integer k. Find the most popular k keywords in order of most to least frequently mentioned.
         *
         * The comparison of strings is case-insensitive. If keywords are mentioned an equal number of times in reviews, sort alphabetically.
         */
        //Example 1
        int k = 3;
        String[] keywords = {"cetracular", "anacell", "betacellular"};
        String[] reviews = {"Anacell provides the best services in the city",
            "betacellular has awesome services",
            "Best services provided by anacell, everyone should use anacell betacellular betacellular betacellular"};

        String[] expectedOutput = {"betacellular", "anacell", "cetracular"};
        String[] outPut1 = topKFrequentlyMentionedKeywords(keywords, reviews, k);
        System.out.println(String.join(", ", outPut1));


        //Example 2
        k = 2;
        keywords = new String[]{"anacell", "betacellular", "cetracular", "deltacellular", "eurocell"};
        reviews = new String[]{
            "I love anacell Best services; Best services provided by",
            "betacellular has great services",
            "deltacellular provides much better services than betacellular",
            "cetracular is worse than anacell",
            "Betacellular is better than deltacellular."};

        expectedOutput = new String[]{"betacellular", "anacell"};
        String[] output2 = topKFrequentlyMentionedKeywords(keywords, reviews, k);
        System.out.println(String.join(", ", output2));

    }

}
