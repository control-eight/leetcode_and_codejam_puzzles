package com.my.code_jam.y2019.qualification.you_can_go_your_own_way;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt();
        for (int i = 1; i <= t; ++i) {
            int N = in.nextInt();
            String S = in.next();
            solve(i, N, S);
        }
    }

    private static void solve(int testCase, int N, String S) {
        System.out.print("Case #" + testCase + ": ");
        for (int i = 0; i < S.length(); i++) {
            if (S.charAt(i) == 'E') {
                System.out.print('S');
            } else {
                System.out.print('E');
            }
            System.out.println();
        }
    }
}
