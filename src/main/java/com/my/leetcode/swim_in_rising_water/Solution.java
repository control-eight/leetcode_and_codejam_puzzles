package com.my.leetcode.swim_in_rising_water;

public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().swimInWater(new int[][] {{0,2},{1,3}}));
        //3
        System.out.println(new Solution().swimInWater(new int[][] {{3,2},{0,1}}));
        //3
        System.out.println(new Solution().swimInWater(new int[][] {{0,1,2,3,4},{24,23,22,21,5},{12,13,14,15,16},{11,17,18,19,20},{10,9,8,7,6}}));
        //16
        System.out.println(new Solution().swimInWater(new int[][] {{0,0,0,0,0,0,0},{0,0,0,0,0,0,0},{0,0,0,0,0,0,0},{0,0,0,0,0,0,0},{0,0,0,0,0,0,0},{1,1,1,1,1,1,0},{0,0,0,0,0,1,0}}));
        //4
    }

    public int swimInWater(int[][] grid) {
        int lo = 0;
        int hi = grid.length * grid.length - 1;
        while (lo < hi) {
            int mid = Math.max((hi - lo)/2, 1);
            if (!dfs(lo + mid, grid)) {
                lo = lo + mid;
            } else {
                hi = hi - mid;
            }
        }
        return dfs(lo, grid)? lo: hi + 1;
    }

    private boolean dfs(int solution, int[][] grid) {
        if (grid[0][0] > solution) return false;
        return dfs(0, 0, solution, grid, new boolean[grid.length][grid[0].length]);
    }

    private boolean dfs(int i, int j, int solution, int[][] grid, boolean[][] visited) {
        int[][] directions = new int[][] {{i - 1, j}, {i + 1, j}, {i, j - 1}, {i, j + 1}};

        for (int[] direction : directions) {
            if (direction[0] >= 0 && direction[0] < grid.length && direction[1] >= 0 && direction[1] < grid[0].length) {
                if (!visited[direction[0]][direction[1]]) {
                    visited[direction[0]][direction[1]] = true;
                    if (grid[direction[0]][direction[1]] <= solution) {
                        if (checkSolution(direction[0], direction[1], grid)) return true;
                        if (dfs(direction[0], direction[1], solution, grid, visited)) return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean checkSolution(int i, int j, int[][] grid) {
        return i == grid.length - 1 && j == grid[0].length - 1;
    }
}
