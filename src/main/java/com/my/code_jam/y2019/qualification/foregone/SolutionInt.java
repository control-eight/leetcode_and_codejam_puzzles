package com.my.code_jam.y2019.qualification.foregone;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class SolutionInt {

    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt();
        for (int i = 1; i <= t; ++i) {
            int N = in.nextInt();
            solve(i, N);
        }
    }

    private static void solve(int testCase, int N) {
        int A = 1;
        int B = N - 1;
        while (true) {
            int last4Degree = calcLast4Degree(A);
            if (last4Degree > 0) {
                A += Math.pow(10, last4Degree - 1);
                B = N - A;
            }
            last4Degree = calcLast4Degree(B);
            if (last4Degree > 0) {
                B -= Math.pow(10, last4Degree - 1);
                A = N - B;
            }
            if (!containsDigit(A, 4) && !containsDigit(B, 4)) {
                System.out.print("Case #" + testCase + ": ");
                System.out.print(A);
                System.out.print(" ");
                System.out.println(B);
                return;
            }
            A++;
            B--;
        }
    }

    private static int calcLast4Degree(int n) {
        int degree = 1;
        int last4Degree = 0;
        while (n > 0) {
            if (n % 10 == 4) {
                last4Degree = degree;
            }
            n = n / 10;
            degree++;
        }
        return last4Degree;
    }

    private static boolean containsDigit(int number, int digit) {
        return (number + "").contains(digit+"");
    }
}
