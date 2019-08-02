package com.my.leetcode.redundant_conn;

import java.util.*;

public class Solution {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().findRedundantConnection(new int[][] {{1,2},{1,3},{2,3}})));
        System.out.println(Arrays.toString(new Solution().findRedundantConnection(new int[][] {{1,2},{2,3},{3,4},{1,4},{1,5}})));
    }

    public int[] findRedundantConnection(int[][] edges) {
        UnionFind unionFind = new UnionFind(1000);
        for (int[] edge : edges) {
            if (!unionFind.union(edge[1], edge[0])) {
                return edge;
            }
        }
        return null;
    }

    private static class UnionFind {

        private int[] parents;

        public UnionFind(int n) {
            this.parents = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                parents[i] = i;
            }
        }

        public int find(int i) {
            if (parents[i] == i) return i;
            else {
                int res = find(parents[i]);
                parents[i] = res;
                return res;
            }
        }

        public boolean union(int i1, int i2) {
            int pi1 = find(i1);
            int pi2 = find(i2);
            parents[pi1] = parents[pi2];
            return pi1 != pi2;
        }
    }
}
