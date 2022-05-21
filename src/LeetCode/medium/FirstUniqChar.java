package LeetCode.medium;

import java.util.HashMap;
import java.util.Map;

/*
    https://leetcode.com/problems/first-unique-character-in-a-string/

    Example 1:
        Input: s = "leetcode"
        Output: 0

    Example 2:
        Input: s = "loveleetcode"
        Output: 2

    Example 3:
        Input: s = "aabb"
        Output: -1

 */

public class FirstUniqChar {

    public static void main(String[] args) {

        System.out.println(0 == firstUniqChar("leetcode"));
        System.out.println(2 == firstUniqChar("loveleetcode"));
        System.out.println(-1 == firstUniqChar("aabb"));

    }

    public static int firstUniqChar(String s) {
        Map<Character, Integer> letters = new HashMap<>();

        for (char letter : s.toCharArray()) {
            letters.put(letter, letters.getOrDefault(letter, 0) + 1);
        }

        for (int i = 0; i < s.length(); i++) {
            if (letters.get(s.charAt(i)) == 1) {
                return i;
            }
        }

        return -1;
    }

}
