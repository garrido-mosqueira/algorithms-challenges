package DesignGurus.warmup;

import java.util.HashSet;
import java.util.Set;

public class Pangram {

    public boolean checkIfPangram(String sentence) {
        int[] alphabet = new int[26];
        String lowerCaseSentence = sentence.toLowerCase();
        for (int i = 0; i < lowerCaseSentence.length(); i++) {
            if (lowerCaseSentence.charAt(i) >= 'a' && lowerCaseSentence.charAt(i) <= 'z') {
                alphabet[lowerCaseSentence.charAt(i) - 'a']++;
            }
        }

        for (int i = 0; i < alphabet.length; i++) {
            if (alphabet[i] == 0) {
                return false;
            }
        }

        return true;
    }

    // Function to check if given sentence is pangram
    public boolean checkIfPangramBetter(String sentence) {
        // Create a set to store unique characters
        Set<Character> seen = new HashSet<>();

        // Iterate over each character using a normal for loop
        for (int i = 0; i < sentence.length(); i++) {
            // Convert the current character to lowercase
            char currChar = Character.toLowerCase(sentence.charAt(i));

            if (Character.isLetter(currChar)) {
                // Add the character to the set
                seen.add(currChar);
            }
        }
        // Return true if the set size is 26 (total number of alphabets)
        return seen.size() == 26;
    }

    static void main(String[] args) {
        Pangram pangram = new Pangram();
        // Expected output: true
        System.out.println(pangram.checkIfPangram("The quick brown fox jumps over the lazy dog"));

        // Test case 1: "TheQuickBrownFoxJumpsOverTheLazyDog"
        // Expected output: true
        System.out.println(pangram.checkIfPangram("TheQuickBrownFoxJumpsOverTheLazyDog"));

        // Test case 2: "This is not a pangram"
        // Expected output: false
        System.out.println(pangram.checkIfPangram("This is not a pangram"));

        // Test case 3: "abcdef ghijkl mnopqr stuvwxyz"
        // Expected output: true
        System.out.println(pangram.checkIfPangram("abcdef ghijkl mnopqr stuvwxyz"));

        // Test case 4: ""
        // Expected output: false
        System.out.println(pangram.checkIfPangram(""));

        // Test case 5: "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
        // Expected output: true
        System.out.println(pangram.checkIfPangram("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"));
    }

}
