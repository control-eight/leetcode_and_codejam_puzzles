package com.my.leetcode.bomb_enemy;

public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().maxKilledEnemies(new char[][]{
                {'0','E','0','0'},
                {'E','0','W','E'},
                {'0','E','0','0'}
        }));
        System.out.println(new Solution().maxKilledEnemies(new char[][]{
                {'W','E','E','E','E','0','E','E','E','E','E','W'},
        }));
    }

    public int maxKilledEnemies(char[][] grid) {
        if (grid.length == 0) return 0;
        int[][][] solution = initSolutions(grid);
        int result = 0;
        int[] ints = {-1, -1};
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '0') {
                    if (solution[i + 1][j][0] == -1) {
                        int enemies = 0;
                        for (int jj = j + 1; jj < grid[0].length && grid[i][jj] != 'W'; jj++) {
                            if (grid[i][jj] == 'E') enemies++;
                        }
                        for (int jj = j - 1; jj >= 0 && grid[i][jj] != 'W'; jj--) {
                            if (grid[i][jj] == 'E') enemies++;
                        }
                        solution[i + 1][j + 1][0] = enemies;
                    } else {
                        solution[i + 1][j + 1][0] = solution[i + 1][j][0];
                    }
                    if (solution[i][j + 1][1] == -1) {
                        int enemies = 0;
                        for (int ii = i + 1; ii < grid.length && grid[ii][j] != 'W'; ii++) {
                            if (grid[ii][j] == 'E') enemies++;
                        }
                        for (int ii = i - 1; ii >= 0 && grid[ii][j] != 'W'; ii--) {
                            if (grid[ii][j] == 'E') enemies++;
                        }
                        solution[i + 1][j + 1][1] = enemies;
                    } else {
                        solution[i + 1][j + 1][1] = solution[i][j + 1][1];
                    }
                    result = Math.max(result, solution[i + 1][j + 1][0] + solution[i + 1][j + 1][1]);
                } else if (grid[i][j] == 'E') {
                    solution[i + 1][j + 1][0] = solution[i + 1][j][0];
                    solution[i + 1][j + 1][1] = solution[i][j + 1][1];
                } else {
                    solution[i + 1][j + 1] = ints;
                }
            }
        }
        return result;
    }

    private int[][][] initSolutions(char[][] grid) {
        int[] ints = {-1, -1};
        int[][][] solution = new int[grid.length + 1][grid[0].length + 1][2];
        for (int i = 0; i < grid.length + 1; i++) {
            solution[i][0] = ints;
        }
        for (int j = 0; j < grid[0].length + 1; j++) {
            solution[0][j] = ints;
        }
        return solution;
    }
}
