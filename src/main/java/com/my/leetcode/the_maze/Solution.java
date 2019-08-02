package com.my.leetcode.the_maze;

public class Solution {

    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        return solve(maze, start, destination, new boolean[maze.length][maze[0].length],
                new boolean[maze.length][maze[0].length]);
    }

    public boolean solve(int[][] maze, int[] start, int[] destination, boolean[][] cache, boolean[][] visited) {
        if (start[0] == destination[0] && start[1] == destination[1]) {
            return true;
        }
        if (cache[start[0]][start[1]]) {
            return false;
        }
        visited[start[0]][start[1]] = true;

        int[] up =    new int[] {start[0], start[1]};
        int[] down =  new int[] {start[0], start[1]};
        int[] left =  new int[] {start[0], start[1]};
        int[] right = new int[] {start[0], start[1]};

        while (up[0] > 0 && maze[up[0] - 1][up[1]] != 1) {
            up[0]--;
        }
        while (down[0] < maze.length - 1 && maze[down[0] + 1][down[1]] != 1) {
            down[0]++;
        }
        while (left[1] > 0 && maze[left[0]][left[1] - 1] != 1) {
            left[1]--;
        }
        while (right[1] < maze[0].length - 1 && maze[right[0]][right[1] + 1] != 1) {
            right[1]++;
        }

        if (up[0] != start[0] && !visited[up[0]][up[1]]
                && solve(maze, up, destination, cache, visited)) {
            return true;
        }
        if (down[0] != start[0] && !visited[down[0]][down[1]]
                && solve(maze, down, destination, cache, visited)) {
            return true;
        }
        if (left[1] != start[1] && !visited[left[0]][left[1]]
                && solve(maze, left, destination, cache, visited)) {
            return true;
        }
        if (right[1] != start[1] && !visited[right[0]][right[1]]
                && solve(maze, right, destination, cache, visited)) {
            return true;
        }
        cache[start[0]][start[1]] = true;
        return false;
    }
}
