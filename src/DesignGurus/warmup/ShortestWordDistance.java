package DesignGurus.warmup;

public class ShortestWordDistance {

    public int shortestDistance(String[] words, String word1, String word2) {




        return 1;
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
