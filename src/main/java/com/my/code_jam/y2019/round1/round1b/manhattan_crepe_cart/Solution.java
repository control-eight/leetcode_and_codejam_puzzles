package com.my.code_jam.y2019.round1.round1b.manhattan_crepe_cart;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt();
        for (int i = 1; i <= t; ++i) {
            int P = in.nextInt();
            int Q = in.nextInt();
            List<int[]> list = new ArrayList<>(P);
            for (int j = 1; j <= P; j++) {
                list.add(new int[] {in.nextInt(), in.nextInt(), direction(in.next())});
            }
            solve(i, Q, list);
        }
    }

    private static void solve(int i, int Q, List<int[]> list) {
        int[] xs = new int[Q+1];
        int[] ys = new int[Q+1];
        for (int[] ints : list) {
            if (ints[2] == 0) {
                for (int z = ints[1] + 1; z <= Q; z++) {
                    ys[z]++;
                }
            }
            if (ints[2] == 1) {
                for (int z = ints[0] + 1; z <= Q; z++) {
                    xs[z]++;
                }
            }
            if (ints[2] == 2) {
                for (int z = ints[1] - 1; z >= 0; z--) {
                    ys[z]++;
                }
            }
            if (ints[2] == 3) {
                for (int z = ints[0] - 1; z >= 0; z--) {
                    xs[z]++;
                }
            }
        }
        System.out.println("Case #" + i + ": " + findMax(xs) + " " + findMax(ys));
    }

    private static int findMax(int[] array) {
        int max = 0;
        int maxIndex = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    private static int direction(String next) {
        switch (next) {
            case "N": return 0;
            case "E": return 1;
            case "S": return 2;
            case "W": return 3;
        }
        throw new RuntimeException();
    }


}
