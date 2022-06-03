package RealChallenges;

import java.util.*;

/*
As the owner of an online store, you need to fulfill orders every day.
To optimize the packing of each order, you decide to write an algorithm to match boxes and items based on their respective sizes.

You have access to the following two boxes:
- A medium box (identifier: M)
- A large box (identifier: L)

When possible, you should try to fit multiple items in the same box but boxes can only contain one type of product.

This is the list of items you sell along with associated boxes:

- Camera (identifier: Cam): one can fit in a medium box, and up to two can fit in a large box
- Gaming Console (identifier: Game): too big for a medium box, but up to two can fit in a large box
- max of 2 gaming consoles can fit in 1 box
- Bluetooth speaker (identifier: Blue): one can fit in a large box . max is 1 per large box

Your goal is to design a function that takes a list of items and returns the box & item matches (examples below).

Your solution should work for any number of each item greater than or equal to zero.

Input = [], Output = []

## Input/Output expectations

["Cam"]                                                             -> [M: ["Cam"]]
["Cam", "Game"]                                                     -> [M: ["Cam"], L: ["Game"]]
["Game", "Blue"]                                                    -> [L: ["Game"], L : ["Blue"]]
["Game", "Game", "Blue"]                                            -> [L: ["Game", "Game"], L : ["Blue"]]
["Cam", "Cam", "Game", "Game"]                                      -> [L: ["Cam", "Cam"], L: ["Game", "Game"]]
["Cam", "Cam", "Cam", "Game", "Game", "Game", "Cam", "Blue"]        -> [L: ["Cam", "Cam"], L: ["Cam", "Cam"], L: ["Game", "Game"], L: ["Game"], L: ["Blue"]]
["Cam", "Cam", "Cam", "Game", "Game", "Cam", "Cam", "Blue", "Blue"] -> [L: ["Cam", "Cam"] , L: ["Cam", "Cam"] , M: ["Cam"] , L: ["Game", "Game"] , L: ["Blue"] , L: ["Blue"]]
 */
public class PackageMatcher {

    static Map<String, Map<String, Integer>> itemsBoxes = Map.of(
            "Cam", Map.of("M", 1, "L", 2),
            "Game", Map.of("M", 0, "L", 2),
            "Blue", Map.of("M", 0, "L", 1)
    );

    public static void main(String[] args) {
        List<String> input1 = List.of("Cam");
        List<String> input2 = List.of("Cam", "Game");
        List<String> input3 = List.of("Game", "Blue");
        List<String> input4 = List.of("Game", "Game", "Blue");
        List<String> input5 = List.of("Cam", "Cam", "Game", "Game");
        List<String> input6 = List.of("Cam", "Cam", "Cam", "Game", "Game", "Game", "Cam", "Blue");
        List<String> input7 = List.of("Cam", "Cam", "Cam", "Game", "Game", "Cam", "Cam", "Blue", "Blue");

        testAndPrint(input1);
        testAndPrint(input2);
        testAndPrint(input3);
        testAndPrint(input4);
        testAndPrint(input5);
        testAndPrint(input6);
        testAndPrint(input7);

        System.out.println("end");
    }

    private static void testAndPrint(List<String> input) {
        List<Box> boxesMatches = findBoxesMatches(input);
        System.out.println(boxesMatches);
    }

    public static List<Box> findBoxesMatches(List<String> items) {
        Map<String, Integer> itemsCount = new HashMap<>();
        for (String item : items) {
            itemsCount.put(item, itemsCount.getOrDefault(item, 0) + 1);
        }

        List<Box> result = new ArrayList<>();
        for (String item : itemsCount.keySet()) {
            int quantity = itemsCount.get(item);
            if (item.equals("Cam")) {
                int boxesL = quantity / 2;
                int boxesM = quantity % 2;
                for (int i = 0; i < boxesL; i++) {
                    result.add(new Box("L", List.of(item, item)));
                }
                if (boxesM != 0 || boxesL == 0) {
                    result.add(new Box("M", List.of(item)));
                }
            } else {
                Map<String, Integer> boxSizes = itemsBoxes.get(item);
                for (String boxName : boxSizes.keySet()) {
                    int boxSize = boxSizes.get(boxName);
                    if (boxSize > 0) {
                        int numberBoxes = (quantity / boxSize) + (quantity % boxSize);
                        for (int i = 0; i < numberBoxes; i++) {
                            int inSideBox = 0;
                            List<String> itemsInBox = new ArrayList<>();
                            while (inSideBox < boxSize && inSideBox <= quantity) {
                                itemsInBox.add(item);
                                inSideBox++;
                                quantity--;
                            }
                            result.add(new Box(boxName, itemsInBox));
                        }
                    }
                }
            }
        }

        return result;
    }

    static class Box {
        public String name;
        public List<String> items;

        public Box(String name, List<String> items) {
            this.name = name;
            this.items = items;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "[" + name + ": " + "[" + items + "]]";
        }
    }

}
