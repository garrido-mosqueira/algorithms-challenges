package topic7_graphs;

import java.util.List;
import java.util.Stack;

import static java.util.Arrays.asList;

public class GraphUsingList {

    public static void main(String[] args) {
        /**
         * 1 1 0 0 0
         * 1 1 0 0 0
         * 0 0 1 0 0
         * 0 0 0 1 1
         */
        List<List<Integer>> grid = asList(
            asList(1, 1, 0, 0, 0),
            asList(1, 1, 0, 0, 0),
            asList(0, 0, 1, 0, 0),
            asList(0, 0, 0, 1, 1)
        );

        assert countBuildings(grid, 4, 5) == 3;

    }

    private static int countBuildings(List<List<Integer>> grid, int rows, int columns) {
        int count = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (grid.get(i).get(j) == 1) {
                    count++;
                    plotBlock(grid, i, j);
                }
            }
        }
        return count;
    }

    private static void plotBlock(List<List<Integer>> grid, int i, int j) {
        Stack<Cord> stack = new Stack<>();
        List<Cord> neighbours = asList(new Cord(0, 1), new Cord(0, -1), new Cord(-1, 0), new Cord(1, 0));
        stack.push(new Cord(i, j));
        grid.get(i).set(j, 0);

        while (!stack.isEmpty()) {
            Cord node = stack.pop();
            for (Cord neighbour : neighbours) {
                int row = node.row + neighbour.row;
                int col = node.col + neighbour.col;

                if (!(row < 0 || col < 0 || row >= grid.size() || col >= grid.get(0).size())) {
                    int value = grid.get(row).get(col);
                    if (value == 1) {
                        grid.get(row).set(col, 0);
                        stack.push(new Cord(row, col));
                    }
                }
            }
        }
    }

    static class Cord {
        int row;
        int col;
        Cord(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
