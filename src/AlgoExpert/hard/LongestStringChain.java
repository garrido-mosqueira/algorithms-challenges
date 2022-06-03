package AlgoExpert.hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
Longest String Chain:

Given a list of strings, write a function that returns the longest string chain that can be built from those strings.
A string chain is defined as follows: let string 'A' be a string in the initial array;
if removing any single character from string A yields a new string B that's contained in the initial array of strings, then strings A and B form a string chain of length 2.
Similarly, if removing any single character from string B yields a new string C that's contained in the initial array of strings, then strings A, B, and C form a string chain of length 3.
The function should return the string chain in descending order (i.e., from the longest string to the shortest one).

Note that string chains of length 1 don't exist; if the list of strings doesn't contain any string chain formed by two or more strings, the function should return an empty array.

You can assume that there will only be one longest string chain.

Sample Input
strings = ["abde", "abc", "abd", "abcde", "ade", "ae", "1abde", "abcdef"]

Sample Output
["abcdef", "abcde", "abde", "ade", "ae"]

 */
public class LongestStringChain {

    public static void main(String[] args) {
        String[] input = new String[]{"abde", "abc", "abd", "abcde", "ade", "ae", "1abde", "abcdef"};
        String[] output = new String[]{"abcdef", "abcde", "abde", "ade", "ae"};


    }

    public static List<String> longestStringChain(List<String> strings) {

        return new ArrayList<String>();
    }

}
