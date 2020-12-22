package com.my.codechef.may20b.nbots;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Check {

    public static void main(String[] args) throws IOException {
        int length = 100;
        int N = 4;
        int F = 10;
        Random random = new Random(5555);
        int[] Z = chooseZ(F, random);
        System.out.println("Z = " + Arrays.toString(Z));
        List<int[][]> list = generate(N, F, Z, length, random);
        for (int i = 0; i < list.size(); i++) {
            int[][] M = list.get(i);
            int K = defineK(M, F);
            //if (i != 1) continue;
            int r = Codechef.solve(M, F, K);
            if (r >= K) {
                System.out.println("i = " + i + " !ERROR: " + r + " vs K = " + K);
                for (int[] ints : M) {
                    for (int anInt : ints) {
                        System.out.print(anInt + " ");
                    }
                    System.out.println();
                }
                break;
            }
        }
    }

    private static int defineK(int[][] M, int F) {
        int lr = 0;
        for (int i = 0; i < M.length; i++) {
            int s = F;
            for (int j = 0; j < M.length; j++) {
                if (M[i][j] > s) {
                    s = F;
                    j--;
                    lr++;
                } else {
                    s -= M[i][j];
                }
            }
            lr += 1;
        }
        int ud = 0;
        for (int j = 0; j < M.length; j++) {
            int s = F;
            for (int i = 0; i < M.length; i++) {
                if (M[j][i] > s) {
                    s = F;
                    i--;
                    ud++;
                } else {
                    s -= M[j][i];
                }
            }
            ud += 1;
        }
        return Math.min(lr, ud);
    }

    private static List<int[][]> generate(int N, int F, int[] Z, int length, Random random) {
        List<int[][]> result = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            result.add(generateM(N, F, Z, random));
        }
        return result;
    }

    private static int[][] generateM(int N, int F, int[] Z, Random random) {
        int[][] result = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                result[i][j] = (int) (Z[0] + random.nextDouble() * (Z[1] - Z[0]));
            }
        }
        return result;
    }

    private static int[] chooseZ(int F, Random random) {
        int[] Z = new int[]{1, F/4, F/2, 3*F/4, F};

        int l = (int) (random.nextDouble()*3);
        int r = (int) (random.nextDouble()*4) + 1;
        while (r <= l) {
            r = (int) (random.nextDouble()*4) + 1;
        }
        return new int[] {Z[l], Z[r]};
    }
}
