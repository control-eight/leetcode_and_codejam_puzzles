package com.my.code_jam.y2019.qualification.foregone;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt();
        for (int i = 1; i <= t; ++i) {
            BigInteger N = new BigInteger(in.next());
            solve(i, N);
        }
    }

    private static void solve(int testCase, BigInteger N) {
        BigInteger A = BigInteger.ONE;
        BigInteger B = N.subtract(BigInteger.ONE);
        while (true) {
            int last4Degree = calcLast4Degree(A);
            if (last4Degree > 0) {
                A = A.add(BigInteger.TEN.pow(last4Degree - 1));
                B = N.subtract(A);
            }
            last4Degree = calcLast4Degree(B);
            if (last4Degree > 0) {
                B = B.subtract(BigInteger.TEN.pow(last4Degree - 1));
                A = N.subtract(B);
            }
            if (!containsDigit(A, 4) && !containsDigit(B, 4)) {
                System.out.print("Case #" + testCase + ": ");
                System.out.print(A);
                System.out.print(" ");
                System.out.println(B);
                return;
            }
            A = A.add(BigInteger.ONE);
            B = B.subtract(BigInteger.ONE);
        }
    }

    private static int calcLast4Degree(BigInteger n) {
        int degree = 1;
        int last4Degree = 0;
        while (n.compareTo(BigInteger.ZERO) > 0) {
            if (n.mod(BigInteger.valueOf(10)).intValue() == 4) {
                last4Degree = degree;
            }
            n = n.divide(BigInteger.TEN);
            degree++;
        }
        return last4Degree;
    }

    private static boolean containsDigit(BigInteger number, int digit) {
        return (number + "").contains(digit+"");
    }
}
