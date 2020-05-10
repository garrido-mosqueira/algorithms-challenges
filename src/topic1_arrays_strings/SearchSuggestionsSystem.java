package topic1_arrays_strings;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * https://leetcode.com/problems/search-suggestions-system/
 */

public class SearchSuggestionsSystem {

    public static List<List<String>> suggestedProducts(String[] products, String searchWord) {
        return IntStream.rangeClosed(1, searchWord.length())
            .mapToObj(index -> searchWord.substring(0, index))
            .map(subWord -> getWords(products, subWord))
            .collect(Collectors.toList());
    }

    private static List<String> getWords(final String[] products, final String subWord) {
        return Arrays.stream(products)
            .sorted()
            .filter(product -> product.startsWith(subWord))
            .limit(3)
            .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        String[] products = new String[]{"mobile", "mouse", "moneypot", "monitor", "mousepad"};
        String searchWord = "mouse";

        List<List<String>> lists = suggestedProducts(products, searchWord);

        System.out.println(lists.toString());
    }
}
