package com.my.leetcode.num_corner_rect;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().countCornerRectangles(new int[][] {
                {1, 0, 0, 1, 0},
                {0, 0, 1, 0, 1},
                {0, 0, 0, 1, 0},
                {1, 0, 1, 0, 1}
        }));
        System.out.println(new Solution().countCornerRectangles(new int[][] {
                {1, 1, 1},
                {1, 1, 1},
                {1, 1, 1}
        }));
        System.out.println(new Solution().countCornerRectangles(new int[][] {
                {1, 1, 1, 1}
        }));
    }

    public int countCornerRectangles(int[][] grid) {
        List<List<Integer>> N = new ArrayList<>();
        int C = 0;
        for (int i = 0; i < grid.length; i++) {
            N.add(new ArrayList<>());
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    N.get(N.size() - 1).add(j);
                }
            }
            if (N.get(N.size() - 1).size() == 0) {
                N.remove(N.size() - 1);
            } else {
                C = Math.max(C, N.get(N.size() - 1).size());
            }

        }

        int result = 0;
        int[][] solutions = new int[grid[0].length][grid[0].length];
        for (int i = 0; i < N.size(); i++) {
            int j = -1;
            List<Integer> row = N.get(i);
            while (++j < row.size()) {
                int jj = j;
                while (++jj < row.size()) {
                    result += solutions[row.get(j)][row.get(jj)];
                    solutions[row.get(j)][row.get(jj)]++;
                }
            }
        }
        return result;
    }
}
