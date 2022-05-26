import java.util.HashMap;
import java.util.Map;

/*
There are inputs  array of the format ["date, item"] .

eg: input[["10212021", popcorn], ["10222021", popcorn], ["10212021", soda],["10212021", popcorn],["10212021", soda]]

On the same day if there are popcorn and soda both purchased,
then it costs 9 dollars as a bundled price.
else soda is 2.5 dollars, popcorn is 8 dollars.

you need to calculate the total price based on these criteria and return that as output
 */
public class TotalPriceBundleCinema {

    public static void main(String[] args) {
        String[][] input = {{"10212021", "popcorn"}, {"10222021", "popcorn"}, {"10212021", "soda"}, {"10212021", "popcorn"}, {"10212021", "soda"}};

        Map<String, Map<String, Integer>> inventory = datesInventory(input);

        double output1 = (9 * 2);
        String date = "10212021";
        double total = totalPrice(inventory, date);
        System.out.println("total: " + total);
        System.out.println("expected: " + output1);
        System.out.println(total == output1);

        double total3 = totalPriceEasy(input, date);
        System.out.println("total: " + total3);
        System.out.println("expected: " + output1);
        System.out.println(total == output1);

        double output2 = 8;
        String date2 = "10222021";
        double total2 = totalPrice(inventory, date2);
        System.out.println("total: " + total2);
        System.out.println("expected: " + output2);
        System.out.println(total2 == output2);

        double total4 = totalPriceEasy(input, date2);
        System.out.println("total: " + total4);
        System.out.println("expected: " + output2);
        System.out.println(total2 == output2);

    }

    public static double totalPriceEasy(String[][] datesItem, String date) {
        int popcorn = 0;
        int soda = 0;
        for (String[] dateItem : datesItem) {
            if (dateItem[0].equals(date)) {
                String itemName = dateItem[1];
                if (itemName.equals("popcorn")) {
                    popcorn++;
                } else if (itemName.equals("soda")) {
                    soda++;
                }
            }
        }

        double bundle = 0;
        double unique = 0;

        if (popcorn > soda) {
            bundle = soda * 9;
            unique = (popcorn - soda) * 8;
        } else if (popcorn < soda) {
            bundle = popcorn * 9;
            unique = (soda - popcorn) * 2.5;
        } else {
            bundle = ((popcorn + soda) / 2) * 9;
        }

        return bundle + unique;
    }

    public static double totalPrice(Map<String, Map<String, Integer>> inventory, String date) {
        Map<String, Integer> itemsCount = inventory.get(date);
        double bundle = 0;
        double unique = 0;

        int popcorn = itemsCount.getOrDefault("popcorn", 0);
        int soda = itemsCount.getOrDefault("soda", 0);

        if (popcorn == soda) {
            bundle = ((popcorn + soda) / 2) * 9;
        } else if (popcorn > soda) {
            bundle = soda * 9;
            unique = (popcorn - soda) * 8;
        } else if (popcorn < soda) {
            bundle = popcorn * 9;
            unique = (soda - popcorn) * 2.5;
        }

        return bundle + unique;
    }

    public static Map<String, Map<String, Integer>> datesInventory(String[][] datesItem) {
        Map<String, Map<String, Integer>> dates = new HashMap<>();
        for (String[] strings : datesItem) {
            String date = strings[0];
            String item = strings[1];
            if (dates.containsKey(date)) {
                Map<String, Integer> itemsOfADateMap = dates.get(date);
                itemsOfADateMap.put(item, itemsOfADateMap.getOrDefault(item, 0) + 1);
            } else {
                Map<String, Integer> itemsOfADateMap = new HashMap<>();
                itemsOfADateMap.put(item, 1);
                dates.put(date, itemsOfADateMap);
            }
        }
        return dates;
    }
}
