package com.my.test;

import org.apache.commons.lang.RandomStringUtils;

public class StringIndexOf {

    public static void main(String[] args) {
        int[] check = new int[] {1, 10, 100, 500, 1000, 3000, 5000, 10000, 20000, 50000, 100000, 200000};
        for (int i : check) {
            System.out.println(i + " " + calc((int) 5e5, i));
        }
        for (int i : check) {
            System.out.println(i + " " + calc2((int) 5e5, i));
        }
    }

    private static long calc(int max, int check) {
        StringBuilder sb = new StringBuilder();
        String p = "";
        for (int i = 0; i < max; i++) {
            sb.append('a');
            if (i < check) {
                p = sb.toString() + 'z';
            }
        }
        String s = sb.append('c').toString();

        long start = System.currentTimeMillis();
        s.indexOf(p);
        return (System.currentTimeMillis() - start);
    }

    private static long calc2(int max, int check) {
        String s = RandomStringUtils.randomAlphabetic(max);
        String p = RandomStringUtils.randomAlphabetic(check);
        long start = System.currentTimeMillis();
        s.indexOf(p);
        return (System.currentTimeMillis() - start);
    }
}
