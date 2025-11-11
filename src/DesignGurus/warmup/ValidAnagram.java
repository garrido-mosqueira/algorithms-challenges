package DesignGurus.warmup;

public class ValidAnagram {

    /**
     * Returns true if strings s and t are anagrams under the constraints:
     * - 1 <= s.length, t.length <= 5e5
     * - s and t consist of lowercase English letters 'a'..'z'
     *
     * Implementation details:
     * - Time: O(n)
     * - Space: O(1) using a fixed-size frequency array of 26 entries
     * - Early exit: while decrementing counts for t, if any bucket goes negative,
     *   t contains a letter more times than s → not an anagram.
     * - Avoids creating intermediate char arrays to keep memory usage low for large inputs.
     */
    public boolean isAnagram(String s, String t) {
        if (s == null || t == null) return false; // defensive; constraints typically exclude null
        if (s.length() != t.length()) return false;

        int[] freq = new int[26];

        // Count letters from s
        for (int i = 0, n = s.length(); i < n; i++) {
            freq[s.charAt(i) - 'a']++;
        }

        // Subtract letters from t with early exit
        for (int i = 0, n = t.length(); i < n; i++) {
            int idx = t.charAt(i) - 'a';
            if (--freq[idx] < 0) return false;
        }

        // All buckets must be zero now
        return true;
    }

    static void main(String[] args) {
        ValidAnagram solution = new ValidAnagram();
        System.out.println(solution.isAnagram("anagram", "nagaram")); // true
        System.out.println(solution.isAnagram("rat", "car")); // false
        System.out.println(solution.isAnagram("a", "a")); // true
        System.out.println(solution.isAnagram("ab", "ba")); // true
        System.out.println(solution.isAnagram("abc", "abd")); // false
    }

}
