package com.my.leetcode.longest_incr_path_matrix;

import java.util.*;

public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().longestIncreasingPath(new int[][] {
                {9,9,4},{6,6,8},{2,1,1}
        }));
        //4
        System.out.println(new Solution().longestIncreasingPath(new int[][] {
                {3,4,5},{3,2,6},{2,2,1}
        }));
        //4
    }

    public int longestIncreasingPath(int[][] matrix) {
        if (matrix.length == 0) return 0;
        int result = 1;
        int[][] cache = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                result = Math.max(result, 1 + dfs(new int[]{i,j}, matrix, cache));
            }
        }
        return result;
    }

    private int dfs(int[] p, int[][] matrix, int[][] cache) {
        if (cache[p[0]][p[1]] > 0) {
            return cache[p[0]][p[1]];
        }

        int result = 0;
        int[] next = new int[] {p[0] - 1, p[1]};
        if (isSuitable(p, next, matrix)) {
            result = Math.max(result, 1 + dfs(next, matrix, cache));
        }
        next = new int[] {p[0] + 1, p[1]};
        if (isSuitable(p, next, matrix)) {
            result = Math.max(result, 1 + dfs(next, matrix, cache));
        }
        next = new int[] {p[0], p[1] - 1};
        if (isSuitable(p, next, matrix)) {
            result = Math.max(result, 1 + dfs(next, matrix, cache));
        }
        next = new int[] {p[0], p[1] + 1};
        if (isSuitable(p, next, matrix)) {
            result = Math.max(result, 1 + dfs(next, matrix, cache));
        }
        cache[p[0]][p[1]] = result;
        return result;
    }

    private boolean isSuitable(int[] prev, int[] next, int[][] matrix) {
        if (next[0] < 0 || next[0] >= matrix.length || next[1] < 0 || next[1] >= matrix[0].length) {
            return false;
        }
        return matrix[next[0]][next[1]] > matrix[prev[0]][prev[1]];
    }
}
