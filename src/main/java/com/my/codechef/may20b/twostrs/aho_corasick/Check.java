package com.my.codechef.may20b.twostrs.aho_corasick;

import com.my.codechef.may20b.twostrs.Codechef;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Check {

    public static void main(String[] args) {
        big();
    }

    private static void big() {
        int lc = 26;
        double rr = 0.99;
        int l = 1000;
        int pr = 26;
        int c = 20000;
        int b = 100000;
        int count = 10;
        Random random = new Random(555);
        for (int t = 0; t < count; t++) {
            String A = randomString(l, lc, random);
            String B = randomString(l, lc, random);
            Map<String, Integer> map = generateMap(A + B, pr, lc, c, b, rr, random);
            SolutionOpt.VocabularyEntry[] convert = convert(map);
            long start = System.currentTimeMillis();
            long r1 = (long) SolutionOpt.solve(A, B, convert);
            System.out.println((System.currentTimeMillis() - start) + "ms");
            long r2 = (long) Codechef.solve(A, B, map);
            System.out.println(r1 + " " + r2);
            /*int sum = 0;
            for (String s : map.keySet()) {
                sum += s.length();
            }
            System.out.println(sum);*/
        }
    }

    private static SolutionOpt.VocabularyEntry[] convert(Map<String, Integer> map) {
        SolutionOpt.VocabularyEntry[] ss = new SolutionOpt.VocabularyEntry[map.size()];
        int i = 0;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            ss[i] = new SolutionOpt.VocabularyEntry(entry.getKey(), entry.getValue());
            i++;
        }
        return ss;
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
}