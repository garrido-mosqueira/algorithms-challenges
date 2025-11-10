package DesignGurus.warmup;

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


}
