package com.my.misc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Minesweeper {

    public static void main(String[] args) {
        new Minesweeper().shuffleBoard();
    }

    void shuffleBoard() {
        int nRows = 5;
        int nColumns = 5;
        List<int[]> cells = new ArrayList<>();
        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < nColumns; j++) {
                cells.add(new int[]{i, j});
            }
        }
        Random random = new Random();
        List<int[]> bombs = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            bombs.add(cells.remove((int)(random.nextDouble() * cells.size())));
        }









        StringBuilder sb = new StringBuilder();
        for (int[] bomb : bombs) {
            sb.append(Arrays.toString(bomb)).append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        System.out.println(sb);
    }
}
