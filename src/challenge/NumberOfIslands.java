package challenge;

public class NumberOfIslands {

    public static int numIslands(char[][] grid) {
        int total = 0;
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                total += depthFirstSearch(grid, row, col, visited);
            }
        }
        return total;
    }

    public static int depthFirstSearch(char[][] grid, int row, int col, boolean[][] visited) {
        if (row < 0 || col < 0 || row >= grid.length || col >= grid[0].length || visited[row][col]) {
            return 0;
        }
        visited[row][col] = true;
        if (Character.getNumericValue(grid[row][col]) == 0) return 0;
        depthFirstSearch(grid, row + 1, col, visited);
        depthFirstSearch(grid, row - 1, col, visited);
        depthFirstSearch(grid, row, col + 1, visited);
        depthFirstSearch(grid, row, col - 1, visited);
        return 1;
    }

    /**
     * https://leetcode.com/problems/number-of-islands/
     * 1 1 0 0 0
     * 1 1 0 0 0
     * 0 0 1 0 0
     * 0 0 0 1 1
     */
    public static void main(String[] args) {
        char[][] matrix =
            new char[][]{
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
            };

        int search = numIslands(matrix);

        System.out.println("total " + search);

    }
}
