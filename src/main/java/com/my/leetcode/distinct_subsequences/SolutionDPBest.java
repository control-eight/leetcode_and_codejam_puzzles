package com.my.leetcode.distinct_subsequences;

import java.util.HashMap;
import java.util.Map;

public class SolutionDPBest {

    public static void main(String[] args) {
        System.out.println(new SolutionDPBest().numDistinct("rabbbit", "rabbit"));
        System.out.println(new SolutionDPBest().numDistinct("babgbag", "bag"));
        System.out.println(new SolutionDPBest().numDistinct("dbaaadcddccdddcadacbadbadbabbbcad", "dadcccbaab"));
    }

    public int numDistinct(String s, String t) {
        if (s.length() < t.length()) return 0;

        int[] prevSolutions = new int[s.length()];
        char[] chars = s.toCharArray();
        char[] tChars = t.toCharArray();

        int sum = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == tChars[0]) {
                sum++;
            }
            prevSolutions[i] = sum;
        }

        for (int i = 2; i <= tChars.length; i++) {
            int[] nextSolutions = new int[chars.length];
            sum = 0;
            for (int k = i - 1; k < chars.length; k++) {
                if (chars[k] == tChars[i - 1]) {
                    sum += prevSolutions[k - 1];
                }
                nextSolutions[k] = sum;
            }
            prevSolutions = nextSolutions;
        }

        return prevSolutions[prevSolutions.length - 1];
    }
}
