import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
Items in Containers
Amazon would like to know how much inventory exists in their closed inventory compartments.
Given a strings consisting of items as "*' and closed compartments as an open and close '|',
an array of starting indices startIndices and an array of ending indices endIndices,
determine the number of items in closed compartments within the substring between the two indices, inclusive.

An item is represented as an asterisk (* = ascii decimal 42)

A compartment is represented as a pair of pipes that may or may not have items between them ('|'= ascii decimal 124).
Example s = '|**|*|*'
startIndices = [1,1]
endIndices = [5,6]

The string has a total of 2 closed compartments, one with 2 items and one with 1 item.
For the first pair of indices, (1,5), the substring is '|**|*'. There are 2 items in a compartment.
For the second pair of indices, (1,6), the substring is '|**|*|' and there are 2 + 1 = 3 items in compartments.

Both of the answers are returned in an array. (2, 3).

Function Description
Write a function that returns an integer array that contains the results for each of the startIndices(i) and endIndices(i) pairs.

The function must have three parameters:
S               : A string to evaluate
startIndices    : An integer array, the starting indices.
endIndices      : An integer array, the ending indices.

Constraints
1 < m. n ≤ 105
1 < startIndices[i] ≤ endIndices i ≤ n
Each character of S is either * or |

 */
public class ItemContainers {

    public static void main(String[] args) {
        String input = "|**|*|*";

        List<Integer> integers = numberOfItems(input, List.of(1, 1), List.of(5, 6));

        System.out.println(integers);
    }

    public static List<Integer> numberOfItems(String s, List<Integer> startIndices, List<Integer> endIndices) {
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < startIndices.size(); i++) {
            int start = startIndices.get(i);
            int end = endIndices.get(i);

            String substring = s.substring(start - 1, end);
            boolean wasFirstPipeFound = false;
            int countItem = 0;
            int countItemTotal = 0;
            for (char c : substring.toCharArray()) {
                if (c == '|') {
                    if (wasFirstPipeFound) {
                        countItemTotal += countItem;
                        countItem = 0;
                    } else {
                        wasFirstPipeFound = true;
                    }
                } else if (c == '*') {
                    countItem++;
                }

            }
            result.add(countItemTotal);
        }
        return result;
    }

    public static List<Integer> numberOfItemsStacks(String s, List<Integer> startIndices, List<Integer> endIndices) {
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < startIndices.size(); i++) {
            int start = startIndices.get(i);
            int end = endIndices.get(i);

            String substring = s.substring(start - 1, end);
            Stack<Integer> pipe = new Stack<>();
            Stack<Integer> item = new Stack<>();
            int count = 0;
            for (char letter : substring.toCharArray()) {
                if (letter == '|') {
                    pipe.push(124);
                    if (pipe.size() == 2) {
                        count += item.size();
                        pipe.clear();
                        item.clear();
                        pipe.push(124);
                    }
                }
                if (letter == '*' && pipe.size() > 0) {
                    item.push(42);
                }
            }
            result.add(count);
        }
        return result;
    }

}
