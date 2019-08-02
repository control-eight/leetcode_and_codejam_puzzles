package com.my.kickstart.y2019.practice_round.II_mural;

import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for (int i = 1; i <= T; ++i) {
            int N = in.nextInt();
            System.out.print("Case #" + i + ": ");
            solve(in.next());
        }
    }

    private static void solve(String next) {
        int half = (int) Math.ceil((double) next.length() / 2);

        int maxScore = 0;
        for (int i = 0; i < half; i++) {
            maxScore += next.charAt(i) - '0';
        }

        int rightBound = half;
        half = next.length() % 2 == 0 ? half + 1 : half;
        int nextScore = maxScore;
        for (int i = 1; i < half; i++) {
            nextScore = nextScore - (next.charAt(i - 1) - '0') + (next.charAt(rightBound) - '0');
            maxScore = Math.max(maxScore, nextScore);
            rightBound++;
        }
        System.out.println(maxScore);
    }
}