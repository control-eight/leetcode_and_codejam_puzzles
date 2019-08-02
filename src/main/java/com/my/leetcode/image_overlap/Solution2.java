package com.my.leetcode.image_overlap;

public class Solution2 {
    //N^8



    public static void main(String[] args) {
        System.out.println(new Solution2().largestOverlap(new int[][] {
                {1,1,0},
                {1,1,0},
                {0,0,0}
        }, new int[][] {
                {1,0,1},
                {1,1,1},
                {0,1,1}
        }));
        System.out.println(new Solution2().largestOverlap(new int[][] {
                {1,1,0},
                {0,1,0},
                {0,1,0}
        }, new int[][] {
                {0,0,0},
                {0,1,1},
                {0,0,1}
        }));
        System.out.println(new Solution2().largestOverlap(new int[][] {
                {0,0,0,0,0},
                {0,1,0,0,0},
                {0,0,1,0,0},
                {0,0,0,0,1},
                {0,1,0,0,1}
        }, new int[][] {
                {1,0,1,1,1},
                {1,1,1,1,1},
                {1,1,1,1,1},
                {0,1,1,1,1},
                {1,0,1,1,1}
        }));
    }

    public int largestOverlap(int[][] A, int[][] B) {
        int res = 0;
        int L = A.length;
        for (int i = 0; i < L; i++) {
            for (int j = 0; j < L; j++) {
                for (int k = i; k < L; k++) {
                    for (int m = j; m < L; m++) {
                        for (int x = 0; x < L; x++) {
                            for (int y = 0; y < L; y++) {
                                if (x + k - i < L && y + m - j < L) {
                                    res = Math.max(res, compare(A, i, j, k, m, B, x, y, x + k - i, y + m - j));
                                }
                            }
                        }
                    }
                }
            }
        }
        return res;
    }

    private int compare(int[][] a, int i1, int j1, int i2, int j2, int[][] b, int x1, int y1, int x2, int y2) {
        int res = 0;
        for (int i = i1, x = x1; i <= i2 && x <= x2; i++, x++) {
            for (int j = j1, y = y1; j <= j2 && y <= y2; j++, y++) {
                if (a[i][j] == 1 && b[x][y] == 1) {
                    res++;
                }
            }
        }
        return res;
    }
}
