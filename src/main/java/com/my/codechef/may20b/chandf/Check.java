package com.my.codechef.may20b.chandf;

import java.util.Random;

public class Check {

    private static long bruteForce(long X, long Y, long L, long R) {
        long max = 0;
        long Z = L;
        for (long i = L; i <= R; i++) {
            long r = ((X & i) * (Y & i));
            if (r > max) {
                max = r;
                Z = i;
            }
        }
        return Z;
    }

    public static void main(String[] args) {
        //check(1176514368L, 25148064511L, 0L, 9735205363L);
        random();
    }

    private static void random() {
        long V_MAX = 50L;
        long B_MAX = 60L;
        Random random = new Random(5555);
        for (int i = 0; i < 30000; i++) {
            long X = (long) (random.nextDouble() * V_MAX);
            long Y = (long) (random.nextDouble() * V_MAX);
            if (X > Y) {
                long tmp = Y;
                Y = X;
                X = tmp;
            }
            long L = (long) (random.nextDouble() * B_MAX);
            long R = (long) (random.nextDouble() * B_MAX);
            if (L > R) {
                long tmp = R;
                R = L;
                L = tmp;
            }
            check(X, Y, L, R);
        }
    }

    private static void check(long x, long y, long l, long r) {
        long z1 = bruteForce(x, y, l, r);
        long z2 = (long) Codechef.solve(x, y, l, r);
        if (z1 != z2) {
            System.out.println("Correct: " + z1 + " != " + z2);
            System.out.println(x + " " + y + " " + l + " " + r);
            System.out.println(((x & z1) * (y & z1)) + " vs " + ((x & z2) * (y & z2)));
            //return;
        }
    }
}
