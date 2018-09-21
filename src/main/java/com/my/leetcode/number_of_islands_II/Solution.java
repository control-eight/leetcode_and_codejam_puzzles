package com.my.leetcode.number_of_islands_II;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().numIslands2(3, 3, new int[][] {{0,0}, {0,1}, {1,2}, {2,1}}));
        //[1,1,2,3]
    }

    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        int[][] field = new int[m][n];
        List<Integer> result = new ArrayList<>();
        UnionFind unionFind = new UnionFind(m * n);
        int count = 0;
        for (int[] xy : positions) {
            int x = xy[0]; int y = xy[1];
            field[x][y] = 1;
            int mergedCount = 0;
            if (x > 0 && field[x - 1][y] == 1) {
                mergedCount += unionFind.union(findIndex(x, y, n), findIndex(x - 1, y, n));
            }
            if (y > 0 && field[x][y - 1] == 1) {
                mergedCount += unionFind.union(findIndex(x, y, n), findIndex(x, y - 1, n));
            }
            if (x < m - 1 && field[x + 1][y] == 1) {
                mergedCount += unionFind.union(findIndex(x, y, n), findIndex(x + 1, y, n));
            }
            if (y < n - 1 && field[x][y + 1] == 1) {
                mergedCount += unionFind.union(findIndex(x, y, n), findIndex(x, y + 1, n));
            }
            if (mergedCount == 0) {
                count++;
            } else {
                count -= mergedCount - 1;
            }
            result.add(count);
        }
        return result;
    }

    private int findIndex(int x, int y, int length) {
        return x * length + y;
    }

    private static class UnionFind {

        private int[] parents;

        public UnionFind(int length) {
            this.parents = new int[length];
            for (int i = 0; i < length; i++) {
                this.parents[i] = i;
            }
        }

        public int find(int index) {
            if (parents[index] == index) return index;
            else return find(parents[index]);
        }

        public int union(int index1, int index2) {
            int parent1 = find(index1);
            int parent2 = find(index2);
            this.parents[parent1] = parent2;
            return parent1 != parent2? 1: 0;
        }
    }
}
