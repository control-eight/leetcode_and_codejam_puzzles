package com.my.code_jam.y2019.round1.round1b.fair_fight;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.BiFunction;

public class Solution1 {

    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt();
        for (int i = 1; i <= t; ++i) {
            int N = in.nextInt();
            int K = in.nextInt();
            List<Integer> C = new ArrayList<>(N);
            for (int z = 1; z <= N; z++) {
                C.add(in.nextInt());
            }
            List<Integer> D = new ArrayList<>(N);
            for (int z = 1; z <= N; z++) {
                D.add(in.nextInt());
            }
            solve(i, N, K, C, D);
        }
    }

    private static void solve(int i, int N, int K, List<Integer> C, List<Integer> D) {
        int result = 0;
        for (int ii = 0; ii < N; ii++) {
            int maxC = 0;
            int maxD = 0;
            for (int jj = ii; jj < N; jj++) {
                maxC = Math.max(C.get(jj), maxC);
                maxD = Math.max(D.get(jj), maxD);
                if (Math.abs(maxC - maxD) <= K) {
                    result++;
                }
            }
        }
        System.out.println("Case #" + i + ": " + result);
    }
}