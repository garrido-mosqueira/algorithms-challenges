package AlgoExpert.hard;

/*
Longest Palindromic Substring:

Write a function that, given a string, returns its longest palindromic substring.
A palindrome is defined as a string that's written the same forward and backward.
Note that single-character strings are palindromes.
You can assume that there will only be one longest palindromic substring.

Sample Input
string = "abaxyzzyxf"

Sample Output
"xyzzyx"

 */
public class LongestPalindromicSubstring {

    public static void main(String[] args) {
        String input1 = "abaxyzzyxf";
        String output1 = "xyzzyx";

        String input2 = "a";
        String output2 = "a";

        System.out.println(longestPalindromicSubstring(input1).equals(output1));
        System.out.println(longestPalindromicSubstring(input2).equals(output2));

    }

    public static String longestPalindromicSubstring(String str) {
        String palindrome = "";
        for (int i = 0; i < str.length(); i++) {
            int right = str.length();
            boolean isPalindrome = false;
            while (i < right && !isPalindrome) {
                String substring = str.substring(i, right);
                if (palindrome.length() <= substring.length() && isPalindrome(substring)) {
                    palindrome = str.substring(i, right);
                }
                right--;
            }
        }

        return palindrome;
    }

    public static boolean isPalindrome(String str) {
        int left = 0;
        int right = str.length() - 1;
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

}
