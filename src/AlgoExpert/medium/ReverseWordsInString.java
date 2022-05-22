package AlgoExpert.medium;

/*
Reverse Words In String:

Write a function that takes in a string of words separated by one or more whitespaces and returns a string that has these words in reverse order.
For example, given the string "tim is great", your function should return "great is tim"
For this problem, a word can contain special characters, punctuation, and numbers.
The words in the string will be separated by one or more whitespaces, and the reversed string must contain the same whitespaces as the original string.
For example, given the string "whitespaces   4" you would be expected to return "4   whitespaces"
Note that you're not allowed to use any built-in split or reverse methods/functions. However, you are allowed to use a built-in join
method/function.

Also note that the input string isn't guaranteed to always contain words.

Sample Input
string = "AlgoExpert is the best!"

Sample Output
"best! the is AlgoExpert"

 */

import java.util.Stack;

public class ReverseWordsInString {

    public static void main(String[] args) {

        String input = "AlgoExpert is the best!";
        String output = "best! the is AlgoExpert";

        String wordsInString = reverseWordsInString(input);

        System.out.println(wordsInString);
        System.out.println(wordsInString.equals(output));

    }

    public static String reverseWordsInString(String string) {
        Stack<String> reverse = new Stack<>();

        String word = "";
        int i = 0;
        while (i < string.length()) {
            char letter = string.charAt(i);
            if (letter != ' ') {
                word = findWord(i, string);
            } else {
                word = findSpaces(i, string);
            }
            reverse.push(word);
            i += word.length();
        }

        String solution = "";
        while (!reverse.isEmpty()) {
            solution = solution + reverse.pop();
        }

        return solution;
    }

    public static String findWord(int i, String string) {
        int right = i;
        while (right < string.length() && string.charAt(right) != ' ') {
            right++;
        }
        return string.substring(i, right);
    }

    public static String findSpaces(int i, String string) {
        int right = i;
        while (right < string.length() && string.charAt(right) == ' ') {
            right++;
        }
        return string.substring(i, right);
    }

}
