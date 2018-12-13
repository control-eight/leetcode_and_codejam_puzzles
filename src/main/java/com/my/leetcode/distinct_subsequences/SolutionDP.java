package com.my.leetcode.distinct_subsequences;

public class SolutionDP {

    public static void main(String[] args) {
        System.out.println(new SolutionDP().numDistinct("rabbbit", "rabbit"));
        System.out.println(new SolutionDP().numDistinct("babgbag", "bag"));
        System.out.println(new SolutionDP().numDistinct("dbaaadcddccdddcadacbadbadbabbbcad", "dadcccbaab"));
    }

    public int numDistinct(String s, String t) {
        if (s.length() < t.length()) return 0;

        int[] prevSolutions = new int[s.length()];

        char[] chars = s.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == t.charAt(0)) {
                prevSolutions[i] = 1;
            }
        }

        for (int i = 2; i <= t.length(); i++) {
            int[] nextSolutions = new int[s.length()];
            for (int j = 0; j < prevSolutions.length; j++) {
                if (prevSolutions[j] > 0) {
                    for (int k = j + 1; k < chars.length; k++) {
                        if (chars[k] == t.charAt(i - 1)) {
                            nextSolutions[k] += prevSolutions[j];
                        }
                    }
                }
            }
            prevSolutions = nextSolutions;
        }

        int count = 0;
        for (int solution : prevSolutions) {
            count += solution;
        }

        return count;
    }
}
