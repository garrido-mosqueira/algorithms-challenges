
/*
1. Code Question 1
A Store Shopping recently launched a new item whose daily customer ratings for n days is represented by the array ratings.
They monitor these ratings to identify products that are not performing well.
Find the number of groups that can be formed consisting of 1 or more consecutive days such that the rating continuously decreases over the days.

The rating is consecutively decreasing if it has the form: r, r- 1, r-2... and so on,
where r is the rating on the first day of the group being considered.
Two groups are considered different if they contain the ratings of different consecutive days.

Example
ratings = [4, 3, 5, 4, 3]

There are 9 periods in which the rating consecutively decreases.
5 one day periods: [4], [3], [5], [4], [3]
3 two day periods: [4, 3], [5, 4], [4, 3]
1 three day period: [5, 4, 3]

These can be visualized in the figure shown below.

                            |--------9--------|
             1       2       3       4       5
            [4]     [3]     [5]     [4]     [3]
            |----6----|     |---7---| |---8---|

Return 9.

Function Description
Complete the function countDecreasingRatings in the editor.

countDecreasingRatings contains one parameter:
    int ratings[n]: customer ratings for n days

Returns
    long: the number of valid groups of ratings

Constraints
1 <= n <= 10 exp 5
O <= ratings[i] <= 10 exp 9

-----------------------------------------
Sample Case 0

Sample Input For Custom Testing
STDIN           FUNCTION
3       ->      ratings[] size n = 3
2       ->      ratings = [2, 1, 3]
1
3

Sample Output: 4
Explanation:
There are 4 groups of continuously decreasing
ratings: [2], [1], [3], [2, 1]

-----------------------------------------
Sample Case 1

Sample Input For Custom Testing
STDIN           FUNCTION
4       ->      ratings[] size n = 4
4       ->      ratings = [4, 2, 3, 1]
2
3
1

Sample Output: 4
Explanation:
The groups [4], [2], [3], [1] are the only groups that qualify.

 */

public class CountDecreasingRatings {

    public static void main(String[] args) {

    }


    public static long countDecreasingRatings(int[] ratings) {

        return 1L;
    }

}
