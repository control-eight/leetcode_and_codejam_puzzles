package com.my.leetcode.the_maze_II;

import java.util.Arrays;

public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().shortestDistance(new int[][] {
                {0,0,1,0,0},{0,0,0,0,0},{0,0,0,1,0},{1,1,0,1,1},{0,0,0,0,0}
        }, new int[]{0,4}, new int[]{4,4}));
    }

    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        int[][] cache = new int[maze.length][maze[0].length];
        for (int[] ints : cache) {
            Arrays.fill(ints, Integer.MAX_VALUE);
        }
        solve(maze, start, destination, 0, cache);
        return cache[destination[0]][destination[1]] == Integer.MAX_VALUE? -1:
                cache[destination[0]][destination[1]];
    }

    public void solve(int[][] maze, int[] start, int[] destination, int length, int[][] cache) {
        if (length >= cache[start[0]][start[1]]) {
            return;
        }
        cache[start[0]][start[1]] = length;
        if (start[0] == destination[0] && start[1] == destination[1]) {
            return;
        }

        int[] up =    new int[] {start[0], start[1], 0};
        int[] down =  new int[] {start[0], start[1], 0};
        int[] left =  new int[] {start[0], start[1], 0};
        int[] right = new int[] {start[0], start[1], 0};

        while (up[0] > 0 && maze[up[0] - 1][up[1]] != 1) {
            up[0]--;
            up[2]++;
        }
        while (down[0] < maze.length - 1 && maze[down[0] + 1][down[1]] != 1) {
            down[0]++;
            down[2]++;
        }
        while (left[1] > 0 && maze[left[0]][left[1] - 1] != 1) {
            left[1]--;
            left[2]++;
        }
        while (right[1] < maze[0].length - 1 && maze[right[0]][right[1] + 1] != 1) {
            right[1]++;
            right[2]++;
        }

        if (up[0] != start[0]) {
            solve(maze, up, destination, length + up[2], cache);
        }
        if (down[0] != start[0]) {
            solve(maze, down, destination, length + down[2], cache);
        }
        if (left[1] != start[1]) {
            solve(maze, left, destination, length + left[2], cache);
        }
        if (right[1] != start[1]) {
            solve(maze, right, destination, length + right[2], cache);
        }
    }
}
