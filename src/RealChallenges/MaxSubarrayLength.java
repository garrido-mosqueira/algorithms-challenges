package RealChallenges;

import java.util.List;

/*
1. Code Question:

Amazon Web Services (AWS) offers learning opportunities for computer science students in a series of courses.
Upon completing a course, AWS awards the student with an electronic learning badge.
Before signing up for further courses, a student assigns each badge a value based on interest:

    * 1 means that the student is interested
    * -1 means that the student is not interested

To perform some analysis on the students' interest in the courses, given the array badges and their elements,
either 1 or -1, find a subarray of maximum length such that the product (multiplications) of all the elements in the subarray is 1.

A subarray is a contiguous group of elements in an array.

Example
There are 6 subjects:
1: Security
2: Networking
3: Machine Learning
4: IoT
5: DBMS
6: Analytics

badges = [1, -1, -1, 1, 1, -1]

The student is interested in Security, IoT, and DBMS as indicated in the badges array.

These are a few of the subarrays whose multiplication is equal to 1. Note that * represents the multiplication sign in the example below

    • beginning and ending indices (0, 0) i.e. 1, the length of the subarray is 1
    • indices (0, 4) i.e. 1 * -1 * -1 * 1 * 1 = 1, the length of the subarray is 5
    • indices (1, 4) i.e. -1 * -1 * 1 * 1 = 1, the length of the subarray is 4
    • indices (1, 2) i.e. -1 *-1 = 1, the length of the subarray is 2

The maximum subarray length whose product is equal to 1 is length 5. Return 5.

Function Description
Complete the function maxSubarrayLength in the editor below.

maxSubarrayLength has the following parameter:
int badges[n]: the student's interest in each of the subjects, either 1 or -1

Returns
int: the maximum length subarray with a product of 1

There will be at least one non-empty subarray that satisfies the given condition.

Sample Case 0
badges size, n = 6
badges = [1, -1, -1, -1, 1, 1]
output = 4

Explanation
These are a few of the subarrays whose product is equal to 1:
    • Subarray with indices from (0, 2), length of the subarray is 3.
    • Subarray with indices from (1, 2), length of the subarray is 2.
    • Subarray with indices from (2, 5), length of the subarray is 4.
    • Subarray with indices from (4, 5), length of the subarray is 2.
The maximum subarray length whose product is equal to 1 is length 4.

Sample Case 1
badges size, n = 4
badges = [-1, 1, -1, 1]
output = 4

Explanation
Here, the optimal solution is to choose the entire array as the subarray because its
product is 1.


 */
public class MaxSubarrayLength {

    public static void main(String[] args) {
        List<Integer> input0 = List.of(1, -1, -1, 1, 1, -1);
        List<Integer> input1 = List.of(1, -1, -1, -1, 1, 1);
        List<Integer> input2 = List.of(-1, 1, -1, 1);

        System.out.println("Output should be 5 : " + maxSubarrayLength(input0));
        System.out.println("Output should be 4 : " + maxSubarrayLength(input1));
        System.out.println("Output should be 4 : " + maxSubarrayLength(input2));

    }

//    public static int maxSubarrayLength(List<Integer> badges) {
//
//
//    }

    public static int maxSubarrayLength(List<Integer> badges) {
        int maxSubarrayLength = 0;
        int negativeCount = 0;
        int firstNegativeIndex = -1;

        for (int i = 0; i < badges.size(); i++) {
            if (badges.get(i) < 0) {
                negativeCount++;
                if (firstNegativeIndex == -1) {
                    firstNegativeIndex = i;
                } else {
                    if (negativeCount % 2 == 0) {
                        maxSubarrayLength = Math.max(maxSubarrayLength, i + 1);
                    }
                }
            } else {
                if (negativeCount % 2 == 0) {
                    maxSubarrayLength = Math.max(maxSubarrayLength, i + 1);
                } else {
                    maxSubarrayLength = Math.max(maxSubarrayLength, i - firstNegativeIndex);
                }
            }
        }
        return maxSubarrayLength;
    }

}
