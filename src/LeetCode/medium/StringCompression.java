package LeetCode.medium;

import common.Utils;

/*
Given an array of characters chars, compress it using the following algorithm:

Begin with an empty string s. For each group of consecutive repeating characters in chars:

If the group's length is 1, append the character to s.
Otherwise, append the character followed by the group's length.
The compressed string s should not be returned separately, but instead, be stored in the input character array chars. Note that group lengths that are 10 or longer will be split into multiple characters in chars.

After you are done modifying the input array, return the new length of the array.

You must write an algorithm that uses only constant extra space.

Example 1:

Input: chars = ["a","a","b","b","c","c","c"]
Output: Return 6, and the first 6 characters of the input array should be: ["a","2","b","2","c","3"]
Explanation: The groups are "aa", "bb", and "ccc". This compresses to "a2b2c3".
Example 2:

Input: chars = ["a"]
Output: Return 1, and the first character of the input array should be: ["a"]
Explanation: The only group is "a", which remains uncompressed since it's a single character.
Example 3:

Input: chars = ["a","b","b","b","b","b","b","b","b","b","b","b","b"]
Output: Return 4, and the first 4 characters of the input array should be: ["a","b","1","2"].
Explanation: The groups are "a" and "bbbbbbbbbbbb". This compresses to "ab12".


Constraints:

1 <= chars.length <= 2000
chars[i] is a lowercase English letter, uppercase English letter, digit, or symbol.

LeetCode: https://leetcode.com/problems/string-compression/
Explained solution: https://youtu.be/IhJgguNiYYk

 */
public class StringCompression {

    public static void main(String[] args) {
        char[] input1 = {'a', 'a', 'b', 'b', 'b', 'c', 'c', 'c' };
        int output1 = 6; //["a","2","b","2","c","3"]

        char[] input2 = {'a' };
        int output2 = 1; //["a"]'

        char[] input3 = {'a', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b' };
        int output3 = 4; //["a","b","1","2"]

        printSolution(compress(input1), input1);
        printSolution(compress(input2), input2);
        printSolution(compress(input3), input3);

    }

    public static void printSolution(int output, char[] solution) {
        System.out.println("output: " + output);
        Utils.printArrayRange(solution, 0, output-1);
    }

    public static int compress(char[] chars) {
        int index = 0;
        int i = 0;

        while (i < chars.length) {
            int j = i;
            while (j < chars.length && chars[i] == chars[j]) {
                j++;
            }

            String counter = (j - i) + "";
            chars[index++] = chars[i];
            if (j - i > 1) {
                for (char letter : counter.toCharArray()) {
                    chars[index++] = letter;
                }
            }
            i = j;
        }
        return index;
    }


}
