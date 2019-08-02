package com.my.leetcode.range_sum_query_2d_immutable;

public class NumMatrix {

    private int[][] sum;

    public NumMatrix(int[][] matrix) {
        if (matrix.length == 0) return;
        sum = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                sum[i][j] = matrix[i][j];
                if (i > 0) {
                    sum[i][j] += sum[i - 1][j];
                }
                if (j > 0) {
                    sum[i][j] += sum[i][j - 1];
                }
                if (i > 0 && j > 0) {
                    sum[i][j] -= sum[i - 1][j - 1];
                }
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return sum[row2][col2] - sum[row2][col1] - sum[row1][col2] + sum[row1][col1];
    }
}
