package com.my.leetcode.longest_line_consec_one_matrix;

public class Solution2 {

    public static void main(String[] args) {
        System.out.println(new Solution2().longestLine(new int[][] {
                {0,1,1,0},
                {0,1,1,0},
                {0,0,0,1}
        }));
        System.out.println(new Solution2().longestLine(new int[][] {}));
        System.out.println(new Solution2().longestLine(new int[][] {
                {0},{0},{0},{0},{0}
        }));
        System.out.println(new Solution2().longestLine(new int[][] {
                {0,1,0,1,1},
                {1,1,0,0,1},
                {0,0,0,1,0},
                {1,0,1,1,1},
                {1,0,0,0,1}
        }));
    }

    public int longestLine(int[][] M) {
        if (M.length == 0) return 0;
        int result = 0;
        int[][] prevSolutions = new int[M[0].length + 2][4];
        for (int i = 0; i < M.length; i++) {
            int[][] nextSolutions = new int[M[0].length + 2][4];
            for (int j = 0; j < M[0].length; j++) {
                if (M[i][j] == 1) {
                    nextSolutions[j + 1][0] = nextSolutions[j][0] + 1;
                    nextSolutions[j + 1][1] = prevSolutions[j + 1][1] + 1;
                    nextSolutions[j + 1][2] = prevSolutions[j][2] + 1;
                    nextSolutions[j + 1][3] = prevSolutions[j + 2][3] + 1;
                }
                result = Math.max(result, nextSolutions[j + 1][0]);
                result = Math.max(result, nextSolutions[j + 1][1]);
                result = Math.max(result, nextSolutions[j + 1][2]);
                result = Math.max(result, nextSolutions[j + 1][3]);
            }
            prevSolutions = nextSolutions;
        }
        return result;
    }
}
