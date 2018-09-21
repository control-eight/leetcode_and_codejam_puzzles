package com.my.leetcode.pacific_atlantic_water_flow;

import java.util.*;

public class Solution {

    public static void main(String[] args) {

    }

    public List<int[]> pacificAtlantic(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return Collections.emptyList();

        int h = matrix.length;
        int w = matrix[0].length;

        boolean[][][] reach = new boolean[h][w][2];
        boolean[][][] visited = new boolean[h][w][4];
        List<int[]> result = new ArrayList<>();
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                dfs(i, j, matrix, reach, visited);
                if (reach[i][j][0] && reach[i][j][1]) {
                    result.add(new int [] {i, j});
                }
            }
        }
        return result;
    }

    private boolean[] dfs(int i, int j, int[][] matrix, boolean[][][] reach, boolean[][][] visited) {
        if (reach[i][j][0] && reach[i][j][1]) return reach[i][j];

        reach[i][j][0] = i == 0 || j == 0;
        reach[i][j][1] = i == matrix.length - 1 || j == matrix[0].length - 1;

        if (reach[i][j][0] && reach[i][j][1]) return reach[i][j];

        if (i > 0 && matrix[i][j] >= matrix[i - 1][j]) {
            if (visited[i - 1][j][0]) {
                add(reach[i][j], reach[i - 1][j]);
            } else {
                visited[i - 1][j][0] = true;
                apply(i - 1, j, matrix, reach, visited, reach[i][j]);
            }
        }
        if (i < matrix.length - 1 && matrix[i][j] >= matrix[i + 1][j]) {
            if (visited[i + 1][j][1]) {
                add(reach[i][j], reach[i + 1][j]);
            } else {
                visited[i + 1][j][1] = true;
                apply(i + 1, j, matrix, reach, visited, reach[i][j]);
            }
        }
        if (j > 0 && matrix[i][j] >= matrix[i][j - 1]) {
            if (visited[i][j - 1][2]) {
                add(reach[i][j], reach[i][j - 1]);
            } else {
                visited[i][j - 1][2] = true;
                apply(i, j - 1, matrix, reach, visited, reach[i][j]);
            }
        }
        if (j < matrix[0].length - 1 && matrix[i][j] >= matrix[i][j + 1]) {
            if (visited[i][j + 1][3]) {
                add(reach[i][j], reach[i][j + 1]);
            } else {
                visited[i][j + 1][3] = true;
                apply(i, j + 1, matrix, reach, visited, reach[i][j]);
            }
        }
        return reach[i][j];
    }

    private void apply(int i, int j, int[][] matrix, boolean[][][] reach, boolean[][][] visited,
                       boolean[] result) {
        add(result, dfs(i, j, matrix, reach, visited));
    }

    private void add(boolean[] a1, boolean[] a2) {
        a1[0] |= a2[0];
        a1[1] |= a2[1];
    }
}
