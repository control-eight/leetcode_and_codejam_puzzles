package com.my.leetcode.game_of_life;

import java.util.Arrays;

public class Solution {

    public static void main(String[] args) {
        int[][] t = {
                {0, 1, 0},
                {0, 0, 1},
                {1, 1, 1},
                {0, 0, 0}
        };
        new Solution().gameOfLife(t);
        for (int[] ints : t) {
            System.out.println(Arrays.toString(ints));
        }
    }

    /*[
            [0,0,0],
            [1,0,1],
            [0,1,1],
            [0,1,0]
            ]*/

    public void gameOfLife(int[][] board) {
        if (board.length == 0) return;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {

                int countLive = 0;
                if (i > 0) {
                    countLive += getCountLive(board[i - 1][j]);
                    if (j > 0) {
                        countLive += getCountLive(board[i - 1][j - 1]);
                    }
                    if (j < board[0].length - 1) {
                        countLive += getCountLive(board[i - 1][j + 1]);
                    }
                }
                if (j > 0) {
                    countLive += getCountLive(board[i][j - 1]);
                }
                if (i < board.length - 1) {
                    countLive += getCountLive(board[i + 1][j]);
                    if (j > 0) {
                        countLive += getCountLive(board[i + 1][j - 1]);
                    }
                }
                if (j < board[0].length - 1) {
                    countLive += getCountLive(board[i][j + 1]);
                    if (i < board.length - 1) {
                        countLive += getCountLive(board[i + 1][j + 1]);
                    }
                }
                if (countLive < 2 || countLive > 3) {
                    if (board[i][j] == 1) {
                        board[i][j] = 2;
                    }
                }
                if (countLive == 3) {
                    if (board[i][j] == 0) {
                        board[i][j] = 3;
                    }
                }
            }
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = getCountLiveFinal(board[i][j]);
            }
        }
    }

    private int getCountLive(int value) {
        return (value == 0 || value == 3)? 0: 1;
    }

    private int getCountLiveFinal(int value) {
        return (value == 0 || value == 2)? 0: 1;
    }
}
