package AlgoExpert.medium;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/*
Group Anagrams:

Write a function that takes in an array of strings and groups anagrams together.
Anagrams are strings made up of exactly the same letters, where order doesn't matter. For example, "cinema" and
"iceman" are anagrams; similarly, 'foo" and "ofo" are anagrams.

Your function should return a list of anagram groups in no particular order.

Sample Input
words = ["yo", "act", "flop", "tac", "foo", "cat", "oy", "olfp"]

Sample Output
[["yo", "oy"], ["flop", "olfp"], ["act", "tac", "cat"], ["foo"]]

 */
public class GroupAnagrams {

    public static void main(String[] args) {
        List<String> input = Arrays.asList("yo", "act", "flop", "tac", "foo", "cat", "oy", "olfp");
        List<List<String>> output = List.of(List.of("act", "tac", "cat"), List.of("olfp", "flop"), List.of("yo", "oy"), List.of("foo"));
        List<List<String>> lists = groupAnagrams(input);
        System.out.println(lists);

        List<String> input1 = Arrays.asList("abc", "dabd", "bca", "cab", "ddba");
        List<List<String>> output1 = List.of(List.of("dabd", "ddba"), List.of("abc", "bca", "cab"));
        List<List<String>> lists1 = groupAnagrams(input1);
        System.out.println(lists1);

        List<String> input3 = Arrays.asList("zxc", "asd", "weq", "sda", "qwe", "xcz");
        List<List<String>> output3 = List.of(List.of("asd", "sda"), List.of("qwe", "weq"), List.of("xcz", "zxc"));
        List<List<String>> lists3 = groupAnagrams(input3);
        System.out.println(lists3);

        List<String> input4 = Arrays.asList("eat","tea","tan","ate","nat","bat");
        List<List<String>> output4 = List.of(List.of("bat"), List.of("nat","tan"), List.of("ate","eat","tea"));
        List<List<String>> lists4 = groupAnagrams(input4);
        System.out.println(lists4);

        List<String> input5 = Arrays.asList("","");
        List<List<String>> output5 = List.of(List.of("",""));
        List<List<String>> lists5 = groupAnagrams(input5);
        System.out.println(lists5);

    }

    public static List<List<String>> groupAnagrams(List<String> words) {
        Set<String> processed = new HashSet<>();
        List<List<String>> result = new ArrayList<>();
        for (String word : words) {
            if (!processed.contains(word)) {
                List<String> anagrams = new ArrayList<>();
                addAndRemoveFromOrigin(anagrams, processed, word);
                for (String wordCompare : words) {
                    if (!word.equals(wordCompare) && isAnagram(word, wordCompare)) {
                        addAndRemoveFromOrigin(anagrams, processed, wordCompare);
                    }
                }
                result.add(anagrams);
            }
        }
        return result;
    }

    private static void addAndRemoveFromOrigin(List<String> anagrams, Set<String> processed, String word) {
        processed.add(word);
        anagrams.add(word);
    }

    private static boolean isAnagram(String s, String s1) {
        if (s.length() != s1.length()) return false;
        int[] ascii = new int[256];

        for (int i = 0; i < s.length(); i++) {
            ascii[s.charAt(i)]++;
            ascii[s1.charAt(i)]--;
        }

        for (int i = 0; i < ascii.length; i++) {
            if (ascii[i] != 0) {
                return false;
            }
        }
        return true;
    }

    private static boolean isAnagram1(String s, String s1) {
        Map<Character, Integer> mapCount = new HashMap<>();

        for (char letter : s.toCharArray()) {
            mapCount.put(letter, mapCount.getOrDefault(letter, 0) + 1);
        }

        for (char letter : s1.toCharArray()) {
            mapCount.computeIfPresent(letter, (key, value) -> --value);
        }

        return mapCount.values().stream().allMatch(value -> value == 0);

    }

}
