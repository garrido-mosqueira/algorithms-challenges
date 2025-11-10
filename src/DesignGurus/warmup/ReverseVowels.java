package DesignGurus.warmup;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ReverseVowels {

    public String reverseVowels(String s) {
        Set<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));
        int first = 0, last = s.length() - 1;
        char[] array = s.toCharArray();
        while (first <= last) {
            while (first < last && !vowels.contains(s.charAt(first))) {
                first++;
            }
            while (first < last && !vowels.contains(s.charAt(last))) {
                last--;
            }
            char temp = array[first];
            array[first] = array[last];
            array[last] = temp;
            first++;
            last--;
        }

        return new String(array);
    }

    static void main(String[] args) {
        String s = "hello";
        System.out.println(new ReverseVowels().reverseVowels(s));

        String s2 = "DesignGUrus";
        System.out.println(new ReverseVowels().reverseVowels(s2));
    }

}
