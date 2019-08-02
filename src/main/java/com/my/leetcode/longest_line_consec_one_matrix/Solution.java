package com.my.leetcode.longest_line_consec_one_matrix;

public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().longestLine(new int[][] {
                {0,1,1,0},
                {0,1,1,0},
                {0,0,0,1}
        }));
        System.out.println(new Solution().longestLine(new int[][] {}));
        System.out.println(new Solution().longestLine(new int[][] {
                {0},{0},{0},{0},{0}
        }));
    }

    public int longestLine(int[][] M) {

        if (M.length == 0) return 0;

        int result = 0;

        //horizontal
        for (int i = 0; i < M.length; i++) {
            int count = 0;
            for (int j = 0; j < M[0].length; j++) {
                if (M[i][j] == 1) {
                    count++;
                } else {
                    result = Math.max(result, count);
                    count = 0;
                }
            }
            result = Math.max(result, count);
        }

        //vertical
        for (int j = 0; j < M[0].length; j++) {
            int count = 0;
            for (int i = 0; i < M.length; i++) {
                if (M[i][j] == 1) {
                    count++;
                } else {
                    result = Math.max(result, count);
                    count = 0;
                }
            }
            result = Math.max(result, count);
        }

        //diagonal
        for (int i = 0; i < M.length; i++) {
            int j = 0;
            int count = 0;
            int ii = i;
            while (ii < M.length && j < M[0].length) {
                if (M[ii][j] == 1) {
                    count++;
                } else {
                    result = Math.max(result, count);
                    count = 0;
                }
                ii++;j++;
            }
            result = Math.max(result, count);
        }

        for (int j = 0; j < M[0].length; j++) {
            int i = 0;
            int count = 0;
            int jj = j;
            while (i < M.length && jj < M[0].length) {
                if (M[i][jj] == 1) {
                    count++;
                } else {
                    result = Math.max(result, count);
                    count = 0;
                }
                i++;jj++;
            }
            result = Math.max(result, count);
        }

        //anti-diagonal
        for (int i = 0; i < M.length; i++) {
            int j = M[0].length - 1;
            int count = 0;
            int ii = i;
            while (ii < M.length && j >= 0) {
                if (M[ii][j] == 1) {
                    count++;
                } else {
                    result = Math.max(result, count);
                    count = 0;
                }
                ii++;j--;
            }
            result = Math.max(result, count);
        }

        for (int j = 0; j < M[0].length; j++) {
            int i = 0;
            int count = 0;
            int jj = j;
            while (i < M.length && jj >= 0) {
                if (M[i][jj] == 1) {
                    count++;
                } else {
                    result = Math.max(result, count);
                    count = 0;
                }
                i++;jj--;
            }
            result = Math.max(result, count);
        }

        return result;
    }
}
