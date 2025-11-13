package DesignGurus.warmup;

public class ShortestWordDistance {

    public int shortestDistance(String[] words, String word1, String word2) {
        int shortestDistance = words.length;
        int position1 = -1, position2 = -1;

        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word1)) {
                position1 = i;
            } else if (words[i].equals(word2)) {
                position2 = i;
            }
            if (position1 != -1 && position2 != -1) {
                shortestDistance = Math.min(shortestDistance, Math.abs(position1 - position2));
            }
        }
        return shortestDistance;
    }

    static void main(String[] args) {
        String[] words = {"practice", "makes", "perfect", "coding", "makes"};
        String word1 = "coding";
        String word2 = "practice";

        ShortestWordDistance shortestWordDistance = new ShortestWordDistance();
        int result = shortestWordDistance.shortestDistance(words, word1, word2);
        System.out.println("Shortest distance between " + word1 + " and " + word2 + " is " + result);

        /*
         * Input: words = ["the", "quick", "brown", "fox", "jumps", "over", "the", "lazy", "dog"], word1 = "fox", word2 = "dog"
         * Output: 5
         * Explanation: The distance between "fox" and "dog" is 5 words.
         */
        words = new String[]{"the", "quick", "brown", "fox", "jumps", "over", "the", "lazy", "dog"};
        word1 = "fox";
        word2 = "dog";
        result = shortestWordDistance.shortestDistance(words, word1, word2);
        System.out.println("Shortest distance between " + word1 + " and " + word2 + " is " + result);
    }

}
