package LeetCode.medium;

import java.util.*;

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
        testing(new String[]{"yo", "act", "flop", "tac", "foo", "cat", "oy", "olfp"}, "[[act, tac, cat], [olfp, flop] , [yo, oy], [foo]]");
        testing(new String[]{"abc", "dabd", "bca", "cab", "ddba"}, "[[dabd, ddba], [abc, bca, cab]]");
        testing(new String[]{"zxc", "asd", "weq", "sda", "qwe", "xcz"}, "[[asd, sda], [qwe, weq], [xcz, zxc]]");
        testing(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}, "[[bat], [nat, tan], [ate, eat, tea]]");
        testing(new String[]{"", ""}, "[[, ]]");
        testing(new String[]{"", "b"}, "[[], [b]]");
    }

    private static void testing(String[] input, String expected) {
        List<List<String>> lists = groupAnagrams(input);
        System.out.println("Group Anagrams: " + lists);
        System.out.println("Expected: " + expected);
        System.out.println("-");
    }

    public static List<List<String>> groupAnagrams(String[] words) {
        Map<String, List<String>> groupAnagrams = new HashMap<>();

        for (String word : words) {
            char[] character = word.toCharArray();
            Arrays.sort(character);
            String sorted = new String(character);

            if (groupAnagrams.containsKey(sorted)) {
                groupAnagrams.get(sorted).add(word);
            } else {
                groupAnagrams.put(sorted, new ArrayList<>(Arrays.asList(word)));
            }
        }
        return new ArrayList<>(groupAnagrams.values());
    }

}
