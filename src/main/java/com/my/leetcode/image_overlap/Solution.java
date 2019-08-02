package com.my.leetcode.image_overlap;

public class Solution {
    //N^4

    public static void main(String[] args) {
        System.out.println(new Solution().largestOverlap(new int[][] {
                {1,1,0},
                {1,1,0},
                {0,0,0}
        }, new int[][] {
                {1,0,1},
                {1,1,1},
                {0,1,1}
        }));

        System.out.println(new Solution().largestOverlap(new int[][] {
                {0,1},
                {1,1}
        }, new int[][] {
                {1,1},
                {1,0}
        }));
        //2
        System.out.println(new Solution().largestOverlap(new int[][] {
                {1,1,0},
                {0,1,0},
                {0,1,0}
        }, new int[][] {
                {0,0,0},
                {0,1,1},
                {0,0,1}
        }));
        System.out.println(new Solution().largestOverlap(new int[][] {
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
        int[][] count = new int[2*L][2*L];
        for (int i = 0; i < L; i++) {
            for (int j = 0; j < L; j++) {
                if (A[i][j] == 1) {
                    for (int k = 0; k < L; k++) {
                        for (int m = 0; m < L; m++) {
                            count[k - i + L][m - j + L] += B[k][m];
                            res = Math.max(res, count[k - i + L][m - j + L]);
                        }
                    }
                }
            }
        }
        return res;
    }
}
