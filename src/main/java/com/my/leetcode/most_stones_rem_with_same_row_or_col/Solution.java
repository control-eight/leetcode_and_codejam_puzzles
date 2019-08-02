package com.my.leetcode.most_stones_rem_with_same_row_or_col;

import java.util.*;

public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().removeStones(new int[][]{
                {0, 0}, {0, 1}, {1, 0}, {1, 2}, {2, 1}, {2, 2}
        }));
        System.out.println(new Solution().removeStones(new int[][]{
                {0, 0}, {0, 2}, {1, 1}, {2, 0}, {2, 2}
        }));
        System.out.println(new Solution().removeStones(new int[][]{
                {0, 0}
        }));
    }

    public int removeStones(int[][] stones) {
        Queue[] x = new Queue[10000];
        Queue[] y = new Queue[10000];
        for (int i = 0; i < stones.length; i++) {
            if (x[stones[i][0]] == null) {
                x[stones[i][0]] = new LinkedList();
            }
            x[stones[i][0]].add(stones[i]);
            if (y[stones[i][1]] == null) {
                y[stones[i][1]] = new LinkedList();
            }
            y[stones[i][1]].add(stones[i]);
        }
        Set<int[]> visited = new HashSet<>();
        int result = 0;
        for (int[] stone : stones) {
            if (visited.add(stone)) {
                result += dfs(stone, visited, x, y) - 1;
            }
        }
        return result;
    }

    private int dfs(int[] stone, Set<int[]> visited, Queue[] x, Queue[] y) {
        int res = 1;
        res += traverse(visited, x, y, x[stone[0]]);
        res += traverse(visited, x, y, y[stone[1]]);
        return res;
    }

    private int traverse(Set<int[]> visited, Queue[] x, Queue[] y, Queue<int[]> queue) {
        int res = 0;
        while (queue != null && !queue.isEmpty()) {
            int[] ints = queue.poll();
            if (visited.add(ints)) {
                res += dfs(ints, visited, x, y);
            }
        }
        return res;
    }
}