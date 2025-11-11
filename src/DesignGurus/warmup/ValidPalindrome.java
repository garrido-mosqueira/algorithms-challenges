package DesignGurus.warmup;

public class ValidPalindrome {

    /**
     * Checks if the given string is a palindrome considering only alphanumeric characters
     * and ignoring case. Runs in O(n) time and O(1) extra space.
     * <p>
     * Improvements over the original:
     * - Null-safety: returns false for null input instead of throwing NPE.
     * - Fewer repeated charAt calls by caching current left/right characters.
     * - Maintains two-pointer single pass with early exits.
     */
    public boolean isPalindrome(String s) {
        if (s == null) return false;

        int i = 0, j = s.length() - 1;

        while (i < j) {
            char left = s.charAt(i);
            char right = s.charAt(j);

            while (i < j && !Character.isLetterOrDigit(left)) i++;
            while (i < j && !Character.isLetterOrDigit(right)) j--;

            if (Character.toLowerCase(left) != Character.toLowerCase(right))
                return false;

            i++;
            j--;
        }

        return true;
    }

    static void main(String[] args) {
        String s = "A man, a plan, a canal: Panama";
        System.out.println(new ValidPalindrome().isPalindrome(s));

        String s2 = "Was it a car or a cat I saw?";
        System.out.println(new ValidPalindrome().isPalindrome(s2));

        String s3 = ""; // empty string is a palindrome
        System.out.println(new ValidPalindrome().isPalindrome(s3));

        String s4 = null; // null returns false safely
        System.out.println(new ValidPalindrome().isPalindrome(s4));
    }

}
