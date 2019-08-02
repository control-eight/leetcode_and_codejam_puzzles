package com.my.leetcode.bricks_falling_when_hit;

import java.util.Arrays;

public class Solution {

    public int[] hitBricks(int[][] grid, int[][] hits) {
        int[][] gridAfterHits = new int[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            gridAfterHits[i] = grid[i].clone();
        }
        for (int[] hit : hits) {
            gridAfterHits[hit[0]][hit[1]] = 0;
        }

        int root = gridAfterHits.length * gridAfterHits[0].length;
        DSU dsu = new DSU(root);
        int C = gridAfterHits[0].length;
        for (int j = 0; j < gridAfterHits[0].length; j++) {
            if (gridAfterHits[0][j] == 1) {
                dsu.union(translate(0, j, C), root);
            }
        }

        for (int i = 1; i < gridAfterHits.length; i++) {
            for (int j = 0; j < gridAfterHits[0].length; j++) {
                if (gridAfterHits[i][j] == 1) {
                    if (gridAfterHits[i - 1][j] == 1) {
                        dsu.union(translate(i, j, C), translate(i - 1, j, C));
                    }
                    if (j > 0 && gridAfterHits[i][j - 1] == 1) {
                        dsu.union(translate(i, j, C), translate(i, j - 1, C));
                    }
                }
            }
        }

        int[] results = new int[hits.length];
        int prevSize = dsu.size();

        for (int h = hits.length - 1; h >= 0; h--) {
            int i = hits[h][0];
            int j = hits[h][1];
            if (grid[i][j] == 1) {
                gridAfterHits[i][j] = 1;
                if (i == 0) {
                    dsu.union(translate(i, j, C), root);
                }
                if (i > 0 && gridAfterHits[i - 1][j] == 1) {
                    dsu.union(translate(i, j, C), translate(i - 1, j, C));
                }
                if (j > 0 && gridAfterHits[i][j - 1] == 1) {
                    dsu.union(translate(i, j - 1, C), translate(i, j, C));
                }
                if (j < C - 1 && gridAfterHits[i][j + 1] == 1) {
                    dsu.union(translate(i, j + 1, C), translate(i, j, C));
                }
                if (i < grid.length - 1 && gridAfterHits[i + 1][j] == 1) {
                    dsu.union(translate(i + 1, j, C), translate(i, j, C));
                }
            }
            results[h] = Math.max(0, (dsu.size() - prevSize) - 1);
            prevSize = dsu.size();
        }

        return results;
    }

    private int translate(int i, int j, int C) {
        return i * C + j;
    }


    private static class DSU {

        private int[] parents;
        private int[] sizes;

        public DSU(int size) {
            parents = new int[size + 1];
            for (int i = 0; i < size + 1; i++) {
                parents[i] = i;
            }
            sizes = new int[size + 1];
            Arrays.fill(sizes, 1);
        }

        public int find(int i) {
            if (parents[i] == i) return i;
            else {
                int parent = find(parents[i]);
                parents[i] = parent;
                return parent;
            }
        }

        public void union(int i1, int i2) {
            int p1 = find(i1);
            int p2 = find(i2);
            if (p1 != p2) {
                if (p1 == sizes.length - 1) {
                    parents[p2] = p1;
                    sizes[p1] += sizes[p2];
                } else {
                    parents[p1] = p2;
                    sizes[p2] += sizes[p1];
                }
            }
        }

        public int size() {
            return sizes[sizes.length - 1];
        }
    }
}
