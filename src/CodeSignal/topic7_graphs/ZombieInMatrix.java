package CodeSignal.topic7_graphs;

import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/discuss/interview-question/411357/
 * Given a 2D grid, each cell is either a zombie 1 or a human 0.
 * Zombies can turn adjacent (up/down/left/right) human beings into zombies every hour.
 * Find out how many hours does it take to infect all humans?
 */
public class ZombieInMatrix {

    /**
     *     Input:
     *         [[0, 1, 1, 0, 1],
     *         [0, 1, 0, 1, 0],
     *         [0, 0, 0, 0, 1],
     *         [0, 1, 0, 0, 0]]
     *
     *     Output: 2
     *
     *     Explanation:
     *     At the end of the 1st hour, the status of the grid:
     *         [[1, 1, 1, 1, 1],
     *         [1, 1, 1, 1, 1],
     *         [0, 1, 0, 1, 1],
     *         [1, 1, 1, 0, 1]]
     *
     *     At the end of the 2nd hour, the status of the grid:
     *         [[1, 1, 1, 1, 1],
     *         [1, 1, 1, 1, 1],
     *         [1, 1, 1, 1, 1],
     *         [1, 1, 1, 1, 1]]
     *
     */

    public static int minHours(int rows, int columns, List<List<Integer>> grid) {

        return 1;

    }

    public static void main(String[] args) {
        List<List<Integer>> matrix = Arrays.asList(
            Arrays.asList(0, 1, 1, 0, 1),
            Arrays.asList(0, 1, 0, 1, 0),
            Arrays.asList(0, 0, 0, 0, 1),
            Arrays.asList(0, 1, 0, 0, 0)
        );

        int result = minHours(4, 5, matrix);
        assert result == 2;

    }

}
