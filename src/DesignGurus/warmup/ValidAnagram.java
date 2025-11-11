package DesignGurus.warmup;

public class ValidAnagram {

    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;

        int[] ascii = new int[256];

        for (char c : s.toCharArray()) ascii[c]++;

        for (char c : t.toCharArray()) ascii[c]--;

        for (int i : ascii) if (i != 0) return false;

        return true;
    }

    static void main(String[] args) {
        ValidAnagram solution = new ValidAnagram();

        System.out.println(solution.isAnagram("anagram", "nagaram"));
    }

}
