package LeetCode.medium;

import java.util.HashSet;
import java.util.Set;

/*
            ['.','.','.',       '.','5','.',        '.','1','.'],
            ['.','4','.',       '3','.','.',        '.','.','.'],
            ['.','.','.',       '.','.','3',        '.','.','1'],
            ['8','.','.',       '.','.','.',        '.','2','.'],
            ['.','.','2',       '.','7','.',        '.','.','.'],
            ['.','1','5',       '.','.','.',        '.','.','.'],
            ['.','.','.',       '.','.','2',        '.','.','.'],
            ['.','2','.',       '9','.','.',        '.','.','.'],
            ['.','.','4',       '.','.','.',        '.','.','.']
 */

public class ValidSudoku {

    public static void main(String[] args) {
        char[][] board = new char[][]{
                {'.','.','.',       '.','5','.',        '.','1','.'},
                {'.','4','.',       '3','.','.',        '.','.','.'},
                {'.','.','.',       '.','.','3',        '.','.','1'},
                {'8','.','.',       '.','.','.',        '.','2','.'},
                {'.','.','2',       '.','7','.',        '.','.','.'},
                {'.','1','5',       '.','.','.',        '.','.','.'},
                {'.','.','.',       '.','.','2',        '.','.','.'},
                {'.','2','.',       '9','.','.',        '.','.','.'},
                {'.','.','4',       '.','.','.',        '.','.','.'}
        };

        System.out.println(isValidSudoku(board));

    }


    public static boolean isValidSudoku(char[][] board) {

        for (int i = 0; i < 9; i++) {
            Set<Character> nums = new HashSet<>();
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.' && !nums.add(board[i][j])) {
                    System.out.println("checking rows " + i);
                    return false;
                }
            }
        }

        for (int i = 0; i < 9; i++) {
            Set<Character> nums = new HashSet<>();
            for (int j = 0; j < 9; j++) {
                if (board[j][i] != '.' && !nums.add(board[j][i])) {
                    System.out.println("checking columns " + j);
                    return false;
                }
            }
        }

        int[][] coords = new int[][]{{0, 0}, {0, 3}, {0, 6}, {3, 0}, {3, 3}, {3, 6}, {6, 0}, {6, 3}, {6, 6}};
        for (int[] coord : coords) {
            if (!checkSquare(board, coord[0], coord[1])) {
                return false;
            }
        }

        return true;
    }

    public static boolean checkSquare(char[][] board, int row, int colum) {
        Set<Character> nums = new HashSet<>();
        for (int i = row; i < row + 3; i++) {
            for (int j = colum; j < colum + 3; j++) {
                if (board[i][j] != '.' && !nums.add(board[i][j])) {
                    System.out.println("checking square");
                    return false;
                }
            }
        }
        return true;
    }
}
