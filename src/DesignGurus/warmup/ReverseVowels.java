package DesignGurus.warmup;

public class ReverseVowels {

    public String reverseVowels(String s) {
        if (s == null || s.length() < 2) return s;

        int first = 0, last = s.length() - 1;
        char[] array = s.toCharArray();

        while (first < last) {
            while (first < last && !isVowel(array[first])) first++;
            while (first < last && !isVowel(array[last])) last--;

            if (first < last) {
                char temp = array[first];
                array[first] = array[last];
                array[last] = temp;
                first++;
                last--;
            }
        }

        return new String(array);
    }

    private static boolean isVowel(char c) {
        return switch (c) {
            case 'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U' -> true;
            default -> false;
        };
    }

    static void main(String[] args) {
        String s = "hello";
        System.out.println(new ReverseVowels().reverseVowels(s)); // holle

        String s2 = "DesignGUrus";
        System.out.println(new ReverseVowels().reverseVowels(s2)); // DusUgnGires
    }
}
