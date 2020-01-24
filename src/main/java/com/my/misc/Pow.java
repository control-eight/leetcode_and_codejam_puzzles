package com.my.misc;

import java.util.Random;
import java.util.function.BiFunction;

public class Pow {

    public static void main(String[] args) {
        test(Pow::solve2);
        test(Pow::solve3);
        test(Pow::solve4);
        test(Pow::solve5);
        perfTest(Pow::solve2, "solve2");
        perfTest(Pow::solve3, "solve3");
        perfTest(Pow::solve4, "solve4");
        perfTest(Pow::solve5, "solve5");
        perfTest2(Pow::solve3, "solve3");
        perfTest2(Pow::solve4, "solve4");
        perfTest2(Pow::solve5, "solve5");
    }

    private static void perfTest(BiFunction<Long, Long, Long> pow, String name) {
        long ts = System.nanoTime();
        int count = 0;
        for (long j = 2; j < 1e7; j++) {
            pow.apply(3L, j);
            count++;
        }
        System.out.println("Overall time for " + name + " = " + (System.nanoTime() - ts) + ", avg: " +
                ((double) (System.nanoTime() - ts))/count + ", calls = " + count);
    }

    private static void perfTest2(BiFunction<Long, Long, Long> pow, String name) {
        long ts = System.nanoTime();
        int count = 0;
        Random random = new Random(55);
        double pow1 = Math.pow(2, 61);
        for (long i = 0; i < 1e7; i++) {
            long u = random.nextLong();
            if (u >= pow1 && u * 2 > 0) {
                pow.apply(3L, u);
                count++;
            }
        }
        System.out.println("Overall time2 for " + name + " = " + (System.nanoTime() - ts) + ", avg: " +
                ((double) (System.nanoTime() - ts))/count + ", calls = " + count);
    }

    private static void test(BiFunction<Long, Long, Long> pow) {
        for (int i = 2; i < 50; i++) {
            for (int j = 2; j < 50; j++) {
                if (solve1(i, j) != pow.apply((long) i, (long) j)) {
                    System.out.println(solve1(i, j) + " " + pow.apply((long) i, (long) j));
                    System.out.println(i + " " + j);
                    throw new RuntimeException();
                }
            }
        }
    }

    private static long solve1(long x, long y) {
        if (y == 0) return 1;
        if (y == 1) return x;
        return solve1(x, y/2)*solve1(x, y - y/2);
    }

    private static long solve2(long x, long y) {
        if (y == 0) return 1;
        long tmp = x;
        long degree = 1;
        while (degree * 2 < y) {
            tmp *= tmp;
            degree *= 2;
        }
        return tmp * solve2(x, y - degree);
    }

    private static long solve3(long x, long y) {
        long retValue = 1;
        while (y > 0) {
            if ((y & 1) >= 1) {
                retValue *= x;
            }
            x *= x;
            y >>= 1;
        }
        return retValue;
    }

    private static long solve4(long x, long y) {
        long res = 1;
        while (y > 0) {
            long tmp = x;
            long degree = 1;
            while (degree * 2 < y) {
                tmp *= tmp;
                degree *= 2;
            }
            res *= tmp;
            y -= degree;
        }
        return res;
    }

    private static long solve5(long x, long y) {
        long res = 1;
        long mask = 1;
        while (mask <= y) {
            if ((mask & y) >= 1) {
                res *= x;
            }
            mask <<= 1;
            x *= x;
        }
        return res;
    }
}
