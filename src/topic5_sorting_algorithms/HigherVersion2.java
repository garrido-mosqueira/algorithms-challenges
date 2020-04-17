package topic5_sorting_algorithms;

/**
 * https://app.codesignal.com/interview-practice/task/EEYbE9SHJx5xKC4tz
 */
public class HigherVersion2 {

    static int higherVersion2(String ver1, String ver2) {
        String[] numbers1 = ver1.split("\\*");
        String[] numbers2 = ver2.split("\\*");
        for (int i = 0; i < numbers1.length; i++) {
            int num1 = Integer.parseInt(numbers1[i]);
            int num2 = Integer.parseInt(numbers2[i]);
            if (num1 > num2) {
                return 1;
            } else if (num1 < num2) {
                return -1;
            }
        }
        return 0;
    }


    public static void main(String[] args) {

        System.out.println("Should be 1  : " + higherVersion2("1*2*2", "1*2*0"));
        System.out.println("Should be -1 : " + higherVersion2("1*0*5", "1*1*0"));
        System.out.println("Should be  0 : " + higherVersion2("1*0*5", "1*00*05"));

    }
}
