package challenge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class NumberOfIslands {

    /**
     * SOLUTION 1
     */
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
     * SOLUTION 2 - without extra matrix
     */
    public int numIslands2(char[][] grid) {
        int count = 0, i, j;
        for (i = 0; i < grid.length; i++) {
            for (j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    count += 1;
                    findIsland(grid, i, j);
                }
            }
        }
        return count;
    }

    private void findIsland(char[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[i].length || grid[i][j] == '0') {
            return;
        }
        grid[i][j] = '0';
        findIsland(grid, i + 1, j);
        findIsland(grid, i - 1, j);
        findIsland(grid, i, j - 1);
        findIsland(grid, i, j + 1);
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
                {'1', '1', '0', '1', '1'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
            };

        int search = numIslands(matrix);
        System.out.println("total " + search);

        int[][] grid =
            new int[][]{
                {1, 1, 0, 0, 0},
                {1, 1, 0, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 0, 1, 1}
            };

        int numIslandsUsingStack = numIslandsUsingStack(grid);
        System.out.println("total " + numIslandsUsingStack);

    }

    /**
     * SOLUTION USING STACK
     */
    static class Vertex {
        int value;
        boolean isVisited;
        List<Vertex> neighbours;

        Vertex(int value) {
            this.value = value;
            this.isVisited = false;
            this.neighbours = new ArrayList<>();
        }

        void addNeighbours(Vertex vertex) {
            this.neighbours.add(vertex);
        }
    }

    public static int numIslandsUsingStack(int[][] grid) {
        int numberOfIsland = 0;
        List<Vertex> graphList;
//      graphList = transformToList(grid);

        Map<Integer, Vertex> map = new HashMap<>();
        for (int i = 1; i < 21; i++) {
            map.put(i, new Vertex(i));
        }
        Vertex v1 = map.get(1);
        Vertex v2 = map.get(2);
        Vertex v4 = map.get(4);
        Vertex v5 = map.get(5);
        Vertex v6 = map.get(6);
        Vertex v7 = map.get(7);
        Vertex v13 = map.get(13);
        Vertex v19 = map.get(19);
        Vertex v20 = map.get(20);

        v1.neighbours.add(v2);
        v1.neighbours.add(v6);

        v4.neighbours.add(v5);

        v2.neighbours.add(v7);

        v6.neighbours.add(v7);

        v19.neighbours.add(v20);

        graphList = Arrays.asList(v1, v2, v4, v5, v6, v7, v13, v19, v20);

        for (Vertex node : graphList) {
            if (!node.isVisited) {
                node.isVisited = true;
                plotIsland(node);
                numberOfIsland++;
            }
        }
        return numberOfIsland;
    }

    private static List<Vertex> transformToList(final int[][] grid) {
        Map<Integer, Vertex> map = new HashMap<>();
        for (int row = 0; row < grid.length; row++) {
            Vertex vertex = getVertexIfExist(map, row);
            for (int col = 1; col < grid[0].length; col++) {
                if (grid[row][col] == 1) {
                    Vertex neighbour = getVertexIfExist(map, col);
                    vertex.addNeighbours(neighbour);
                }
            }
        }
        return new ArrayList<>(map.values());
    }

    private static Vertex getVertexIfExist(Map<Integer, Vertex> map, int row) {
        if (map.containsKey(row)) {
            return map.get(row);
        }
        Vertex vertex = new Vertex(row);
        map.put(row, vertex);
        return vertex;
    }

    public static void plotIsland(Vertex node) {
        Stack<Vertex> stack = new Stack<>();
        stack.push(node);
        node.isVisited = true;
        while (!stack.isEmpty()) {
            Vertex actual = stack.pop();
            for (Vertex nei : actual.neighbours) {
                if (!nei.isVisited) {
                    nei.isVisited = true;
                    stack.push(nei);
                }
            }
        }
    }
}
