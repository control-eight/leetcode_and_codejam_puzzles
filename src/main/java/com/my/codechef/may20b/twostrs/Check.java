package com.my.codechef.may20b.twostrs;

import java.util.*;

public class Check {

    private static void getZarr(String str, int[] Z) {
        int n = str.length();
        int L = 0, R = 0;
        for (int i = 1; i < n; ++i) {
            if (i > R) {
                L = R = i;
                while (R < n && str.charAt(R - L) == str.charAt(R))
                    R++;
                Z[i] = R - L;
                R--;
            } else {
                int k = i - L;
                if (Z[k] < R - i + 1)
                    Z[i] = Z[k];
                else {
                    L = i;
                    while (R < n && str.charAt(R - L) == str.charAt(R))
                        R++;
                    Z[i] = R - L;
                    R--;
                }
            }
        }
    }

    private static long calc(String S, Map<String, Integer> map) {
        long sum = 0;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String c = entry.getKey() + "$" + S;
            int[] Z = new int[c.length()];
            getZarr(c, Z);
            for (int i = 0; i < c.length(); ++i) {
                if (Z[i] == entry.getKey().length()) {
                    sum += entry.getValue();
                }
            }
        }
        return sum;
    }

    private static Object solve(String A, String B, Map<String, Integer> map) {
        long max = 0;
        for (int i = 0; i <= A.length(); i++) {
            for (int j = 0; j <= B.length(); j++) {
                max = Math.max(max, calc(A.substring(0, i) + B.substring(j), map));
            }
        }
        return max;
    }

    public static void main(String[] args) {
        //test1();
        //random();
        big();
    }

    private static void random() {
        int lc = 6;
        double rr = 0.25;
        int l = 20;
        int pr = 4;
        int c = 8;
        int b = 10000;
        int count = 1000;
        Random random = new Random(555);
        for (int t = 0; t < count; t++) {
            String A = randomString(l, lc, random);
            String B = randomString(l, lc, random);
            Map<String, Integer> map = generateMap(A + B, pr, lc, c, b, rr, random);
            //System.out.println(A + " " + B + " " + map);
            long r1 = (long) solve(A, B, map);
            long r2 = (long) Codechef.solve(A, B, map);
            if (r1 != r2) {
                System.out.println("Correct: " + r1 + " vs " + r2);
                System.out.println(A + " " + B);
                System.out.println(map);
                //break;
            }
        }
    }

    private static void big() {
        int lc = 3;
        double rr = 0.0;
        int l = 2000;
        int pr = 26;
        int c = 20000;
        int b = 100000;
        int count = 5;
        Random random = new Random(555);
        for (int t = 0; t < count; t++) {
            String A = randomString(l, lc, random);
            String B = randomString(l, lc, random);
            Map<String, Integer> map = generateMap(A + B, pr, lc, c, b, rr, random);
            long start = System.currentTimeMillis();
            long r = (long) Codechef.solve(A, B, map);
            System.out.println((System.currentTimeMillis() - start) + "ms");
            System.out.println("Ops: " + Codechef.ops);
            Codechef.ops = 0;
        }
    }

    private static Map<String, Integer> generateMap(String S, int pr, int lc, int count, int B, double rr, Random random) {
        Map<String, Integer> result = new HashMap<>();
        for (int i = 0; i < count; i++) {
            String s = getSubstr(S, pr, random);
            if (random.nextDouble() < rr) {
                s = randomString(pr, lc, random);
            }
            int b = (int) (random.nextDouble() * B) + 1;
            result.put(s, b);
        }
        return result;
    }

    private static String getSubstr(String S, int pr, Random random) {
        int lo = (int) (random.nextDouble() * S.length());
        int hi = (int) (random.nextDouble() * pr) + 1;
        return S.substring(lo, Math.min(S.length(), lo + hi));
    }

    private static String randomString(int l, int lc, Random random) {
        char[] c = new char[l];
        for (int i = 0; i < l; i++) {
            c[i] = (char) ((int) (random.nextDouble() * lc) + 'a');
        }
        return String.valueOf(c);
    }

    private static void test1() {
        System.out.println(solve("cccccb", "cbbbcc", map0()));
        System.out.println(solve("hitech", "codechef", map1()));
        System.out.println(solve("abc", "dcc", map2()));
        System.out.println(solve("aaaaa", "bcbbb", map3()));
    }

    private static Map<String, Integer> map0() {
        Map<String, Integer> result = new HashMap<>();
        result.put("bb", 9);
        result.put("c", 10);
        result.put("bbb", 2);
        return result;
    }

    private static Map<String, Integer> map1() {
        Map<String, Integer> result = new HashMap<>();
        result.put("chef", 3);
        result.put("code", 1);
        result.put("eche", 5);
        return result;
    }

    private static Map<String, Integer> map2() {
        Map<String, Integer> result = new HashMap<>();
        result.put("ab", 1);
        result.put("cd", 3);
        result.put("cc", 5);
        return result;
    }

    private static Map<String, Integer> map3() {
        Map<String, Integer> result = new HashMap<>();
        result.put("aaa", 1);
        result.put("bb", 2);
        result.put("a", 3);
        result.put("bbb", 4);
        result.put("bc", 13);
        result.put("abc", 500);
        result.put("aabb", 1000);
        return result;
    }
}