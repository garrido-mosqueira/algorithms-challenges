package challenge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * https://leetcode.com/problems/rotting-oranges/
 * In a given grid, each cell can have one of three values:
 * the value 0 representing an empty cell;
 * the value 1 representing a fresh orange;
 * the value 2 representing a rotten orange.
 * Every minute, any fresh orange that is adjacent (4-directionally) to a rotten orange becomes rotten.
 * Return the minimum number of minutes that must elapse until no cell has a fresh orange.
 * If this is impossible, return -1 instead.
 */
public class RottingOranges {

    public static void main(String[] args) {
        /**
         *  [2,1,1],
         *  [1,1,0],
         *  [0,1,1]
         *
         *  Output: 4 minutes
         */
        int[][] orange = new int[][]{{2, 1, 1}, {1, 1, 0}, {0, 1, 1}};
        int minutes = orangesRotting(orange);
        System.out.println(minutes);//4

        /**
         *  [2,1,1],
         *  [0,1,1],
         *  [1,0,1]
         *
         *  Output: 4 minutes
         */
        int[][] orangeNo = new int[][]{{2, 1, 1}, {0, 1, 1}, {1, 0, 1}};
        minutes = orangesRotting(orangeNo);
        System.out.println(minutes);//-1

        int[][] orange0 = new int[][]{{0}};
        minutes = orangesRotting(orange0);
        System.out.println(minutes);//0

        int[][] orange1 = new int[][]{{1}};
        minutes = orangesRotting(orange1);
        System.out.println(minutes);//-1

        int[][] orange3 = new int[][]{{0, 2}};
        minutes = orangesRotting(orange3);
        System.out.println(minutes);//0

        int[][] orange4 = new int[][]{{1, 2}};
        minutes = orangesRotting(orange4);
        System.out.println(minutes);//1

        int[][] orange5 = new int[][]{{0, 2, 2}};
        minutes = orangesRotting(orange5);
        System.out.println(minutes);//0

    }

    public static int orangesRotting(int[][] grid) {
        if (grid == null || grid[0].length < 1) {
            return -1;
        }
        int totalMinutes = 0;
        boolean immunities = false;
        List<Orange> oranges = transformToTree(grid);
        for (Orange orange : oranges) {
            if (!orange.isVisited && orange.value > 0) {
                int tested = checkOrange(orange);
                if (!immunities && tested == 0 && orange.value != 2) {
                    immunities = true;
                }
                totalMinutes += tested;
            }
        }
        return immunities && totalMinutes >= 0 ? -1 : !immunities && totalMinutes == 0 ? 0 : totalMinutes;
    }

    private static int checkOrange(Orange root) {
        Queue<Orange> queue = new LinkedList<>();
        queue.offer(root);
        int minutes = 0;
        boolean moreInfected = false;

        while (!queue.isEmpty()) {
            Orange current = queue.remove();
            boolean isInfected = root.value == 2;
            current.isVisited = true;
            if (moreInfected) {
                minutes++;
                moreInfected = false;
            }
            for (Orange neighbour : current.neighbour) {
                if (!neighbour.isVisited && neighbour.value != 0) {
                    neighbour.isVisited = true;
                    if (isInfected || neighbour.value == 2) {
                        moreInfected = true;
                        neighbour.value = 2;
                    }
                    queue.offer(neighbour);
                }
            }
        }
        return minutes;
    }

    static class Orange {
        int value;
        boolean isVisited;
        List<Orange> neighbour;

        Orange(int value) {
            this.value = value;
            this.isVisited = false;
            this.neighbour = new ArrayList<>();
        }

        void addNeighbour(Orange orange) {
            this.neighbour.add(orange);
        }
    }

    static private List<Orange> transformToTree(int[][] matrix) {
        Map<String, Orange> map = new HashMap<>();
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                if (matrix[row][col] != 0) {
                    map.put(Integer.toString(row).concat(Integer.toString(col)), createOrange(map, matrix, row, col));
                }
            }
        }
        return new ArrayList<>(map.values());
    }

    static private Orange createOrange(Map<String, Orange> map, int[][] matrix, int row, int col) {
        Orange toReturn;
        String key = Integer.toString(row).concat(Integer.toString(col));
        if (map.containsKey(key)) {
            toReturn = map.get(key);
        } else {
            toReturn = new Orange(matrix[row][col]);
        }
        if (col < matrix[0].length - 1) {
            assignedNeighbourIfExist(toReturn, map, matrix, col + 1, row);
        }
        if (col > 0) {
            assignedNeighbourIfExist(toReturn, map, matrix, col - 1, row);
        }
        if (row < matrix.length - 1) {
            assignedNeighbourIfExist(toReturn, map, matrix, col, row + 1);
        }
        if (row > 0) {
            assignedNeighbourIfExist(toReturn, map, matrix, col, row - 1);
        }

        return toReturn;
    }

    static private void assignedNeighbourIfExist(Orange toReturn, Map<String, Orange> map, int[][] matrix, int col, int row) {
        String key = Integer.toString(row).concat(Integer.toString(col));
        if (map.containsKey(key)) {
            toReturn.addNeighbour(map.get(key));
        } else {
            Orange orange = new Orange(matrix[row][col]);
            toReturn.addNeighbour(orange);
            map.put(key, orange);
        }
    }
}
