package com.my.leetcode.walls_and_gates;

public class Solution {

    public void wallsAndGates(int[][] rooms) {
        if (rooms.length == 0 || rooms[0].length == 0) return;

        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[0].length; j++) {
                if (rooms[i][j] == 0) {
                    bfs(i, j, rooms, 0);
                }
            }
        }
    }

    private void bfs(int i, int j, int[][] rooms, int distance) {
        if (rooms[i][j] == -1) return;
        if (distance > rooms[i][j]) return;

        rooms[i][j] = distance;

        if (i > 0) {
            bfs(i - 1, j, rooms, distance + 1);
        }
        if (i < rooms.length - 1) {
            bfs(i + 1, j, rooms, distance + 1);
        }
        if (j > 0) {
            bfs(i, j - 1, rooms, distance + 1);
        }
        if (i < rooms[0].length - 1) {
            bfs(i, j + 1, rooms, distance + 1);
        }
    }
}
