package com.my.leetcode.most_stones_rem_with_same_row_or_col;

import java.util.*;

public class SolutionUnionFind {

    public static void main(String[] args) {
        System.out.println(new SolutionUnionFind().removeStones(new int[][] {
                {0,0},{0,1},{1,0},{1,2},{2,1},{2,2}
        }));
        System.out.println(new SolutionUnionFind().removeStones(new int[][] {
                {0,0},{0,2},{1,1},{2,0},{2,2}
        }));
        System.out.println(new SolutionUnionFind().removeStones(new int[][] {
                {0,0}
        }));
    }

    public int removeStones(int[][] stones) {
        Map<List<Integer>, Integer> index = new HashMap<>();
        Map<Integer, Set<List<Integer>>> x = new HashMap<>();
        Map<Integer, Set<List<Integer>>> y = new HashMap<>();
        for (int i = 0; i < stones.length; i++) {
            List<Integer> key = Arrays.asList(stones[i][0], stones[i][1]);
            index.put(key, i);
            x.putIfAbsent(stones[i][0], new HashSet<>());
            x.get(stones[i][0]).add(key);

            y.putIfAbsent(stones[i][1], new HashSet<>());
            y.get(stones[i][1]).add(key);
        }

        UnionFind unionFind = new UnionFind(stones.length);
        for (int i = 0; i < stones.length; i++) {
            List<Integer> key = Arrays.asList(stones[i][0], stones[i][1]);
            processPoints(index, unionFind, key, x.get(stones[i][0]));
            processPoints(index, unionFind, key, y.get(stones[i][1]));
        }
        return unionFind.count();
    }

    private void processPoints(Map<List<Integer>, Integer> index, UnionFind unionFind, List<Integer> key,
                               Set<List<Integer>> points) {
        if (points != null && points.size() > 1) {
            for (List<Integer> point : points) {
                if (!key.equals(point)) {
                    unionFind.union(index.get(key), index.get(point));
                    break;
                }
            }
        }
    }

    private static class UnionFind {

        private int[] parents;

        public UnionFind(int size) {
            parents = new int[size];
            for (int i = 0; i < size; i++) {
                parents[i] = i;
            }
        }

        public int find(int index) {
            if (parents[index] == index) {
                return index;
            } else {
                int res = find(parents[index]);
                parents[index] = res;
                return res;
            }
        }

        public void union(int index1, int index2) {
            parents[find(index1)] = parents[find(index2)];
        }

        public int count() {
            Map<Integer, Integer> count = new HashMap<>();
            for (int i = 0; i < parents.length; i++) {
                int parent = find(parents[i]);
                count.putIfAbsent(parent, 0);
                count.put(parent, count.get(parent) + 1);
            }
            int res = 0;
            for (Integer value : count.values()) {
                res += value - 1;
            }
            return res;
        }
    }
}
