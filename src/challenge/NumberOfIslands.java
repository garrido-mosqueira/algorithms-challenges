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

    /**
     * SOLUTION 2
     */
    public int numIslands2(char[][] grid) {

        int count = 0, i, j;
        for(i=0;i<grid.length;i++){
            for(j=0;j<grid[i].length;j++){
                if(grid[i][j] == '1'){
                    count += 1;
                    findIsland(grid, i, j);
                }
            }
        }
        return count;
    }

    private void findIsland(char[][]grid, int i, int j){
        if(i<0 || i>=grid.length || j<0 || j>=grid[i].length || grid[i][j] == '0'){
            return;
        }
        grid[i][j] = '0';
        findIsland(grid, i+1, j);
        findIsland(grid, i-1, j);
        findIsland(grid, i, j-1);
        findIsland(grid, i, j+1);
    }
}
