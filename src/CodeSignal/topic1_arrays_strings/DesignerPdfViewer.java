package CodeSignal.topic1_arrays_strings;

public class DesignerPdfViewer {

    static int designerPdfViewer(int[] h, String word) {
        char[] chars = word.toCharArray();
        int width = chars.length;

        int height = 0;
        for (int i = 0; i < width; i++) {
            int index = chars[i] - 97;
            if (height < h[index]) {
                height = h[index];
            }
        }

        return height * width;
    }

    public static void main(String[] args) {
        int[] array = {1, 3, 1, 3, 1, 4, 1, 3, 2, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 7};

        String word = "zaba";

        int i = designerPdfViewer(array, word);

        assert i == 28 : "Wrong";

    }
}
