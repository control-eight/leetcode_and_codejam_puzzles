package com.my.leetcode.interleaving_string;

import java.util.*;

public class Solution {

    public static final int INT = 1000;

    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.isEmpty()) return s2.equals(s3);
        if (s2.isEmpty()) return s1.equals(s3);
        if ((s1 + s2).equals(s3)) return true;
        if ((s2 + s1).equals(s3)) return true;

        char[] s1c = s1.toCharArray();
        char[] s2c = s2.toCharArray();
        char[] s3c = s3.toCharArray();
        int s1cl = s1c.length;
        int s2cl = s2c.length;
        int s3cl = s3c.length;

        Collection<Integer> lastIndexes = new HashSet<>();
        lastIndexes.add(0);
        for (int i = 0; i < s3cl; i++) {
            Collection<Integer> newLastIndexes = new HashSet<>();
            for (Integer lastIndex : lastIndexes) {
                int is1 = (lastIndex % INT) - 1;
                int is2 = (lastIndex / INT) - 1;
                char cs3 = s3c[is1 + 2 + is2];
                if (is1 + 1 < s1cl && s1c[is1 + 1] == cs3) {
                    newLastIndexes.add((is1 + 2) + (INT * (is2 + 1)));
                }
                if (is2 + 1 < s2cl && s2c[is2 + 1] == cs3) {
                    newLastIndexes.add((is1 + 1) + (INT * (is2 + 2)));
                }
            }
            lastIndexes = newLastIndexes;
        }
        for (Integer lastIndex : lastIndexes) {
            int is1 = (lastIndex % INT) - 1;
            int is2 = (lastIndex / INT) - 1;
            if (is1 == s1cl - 1 && is2 == s2cl - 1 && is1 + is2 + 2 == s3cl) {
                return true;
            }
        }
        return false;
    }
}