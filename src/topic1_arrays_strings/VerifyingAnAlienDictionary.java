package topic1_arrays_strings;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/verifying-an-alien-dictionary/
 */
public class VerifyingAnAlienDictionary {

    public static boolean isAlienSorted(String[] words, String order) {
        Map<Character, Integer> alphabet = new HashMap<>();
        char[] orderAt = order.toCharArray();

        for (int i = 0; i < orderAt.length; i++) {
            alphabet.put(orderAt[i], i);
        }

        for (int i = 0; i < words.length; i++) {
            for (int j = i + 1; j < words.length; j++) {
                char[] wordAti = words[i].toCharArray();
                char[] wordAtJ = words[j].toCharArray();
                int minLength = Math.min(wordAti.length, wordAtJ.length);
                int k = 0;
                for (k = 0; k < minLength; k++) {
                    if (alphabet.get(wordAti[k]) > alphabet.get(wordAtJ[k])) {
                        return false;
                    } else if (alphabet.get(wordAti[k]).equals(alphabet.get(wordAtJ[k]))) {
                        continue;
                    }
                    break;
                }
                if (k == minLength) {
                    if (wordAti.length > wordAtJ.length) {
                        return false;
                    }
                }
            }
        }
        return true;
    }


    public static void main(String[] args) {

        String[] words = {"hello", "leetcode"};
        String order = "hlabcdefgijkmnopqrstuvwxyz";
        System.out.println("Should be true: " + isAlienSorted(words, order));

        words = new String[]{"word", "world", "row"};
        order = "worldabcefghijkmnpqstuvxyz";
        System.out.println("Should be false: " + isAlienSorted(words, order));

        words = new String[]{"word", "record", "wordl"};
        order = "worldabcefghijkmnpqstuvxyz";
        System.out.println("Should be false: " + isAlienSorted(words, order));

        words = new String[]{"apple", "app"};
        order = "abcdefghijklmnopqrstuvwxyz";
        System.out.println("Should be false: " + isAlienSorted(words, order));

    }
}
