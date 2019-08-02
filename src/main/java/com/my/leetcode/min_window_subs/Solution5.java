package com.my.leetcode.min_window_subs;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex.bykovsky on 5/12/18.
 */
public class Solution5 {

    public static void main(String[] args) {
        System.out.println(new Solution5().minWindow("acbbaca", "aba"));
        //acbba
        System.out.println(new Solution5().minWindow("abcdebdde", "bde"));
        //bcde
        System.out.println(new Solution5().minWindow("bba", "ab"));
        //
        System.out.println(new Solution5().minWindow("ADOBECODEBANC", "ABC"));
        //ADOBEC
        System.out.println(new Solution5().minWindow("a", "a"));
        //a
        System.out.println(new Solution5().minWindow("ab", "a"));
        //a
        System.out.println(new Solution5().minWindow("ab", "b"));
        //b
        System.out.println(new Solution5().minWindow("a", "b"));
        //
        System.out.println(new Solution5().minWindow("cnhczmccqouqadqtmjjzl", "mm"));
        //mccqouqadqtm
        System.out.println(new Solution5().minWindow("fweekpamjwqobhxiesgzivminqqjjkgnhkdxpfjvvgfcdlgwvwtdwizpjcuwnwpioxcshyjglqjnkluedopzyhozjzqnjentspwffoawwbgyhrrapncwetqulmaupkkwugkpfztgejujlakrnkvefbvncfzhhmciztzjzrzrzlcfkejmlkhlogtraexhgrvxitcnaacegjrkwbseomwvdawsymemhsvtqcpbfvinhngdvhnrswwgoff", "qkkwtlzbbn"));
        //qulmaupkkwugkpfztgejujlakrnkvefbvncfzhhmciztzjzrzrzlcfkejmlkhlogtraexhgrvxitcnaacegjrkwbseomwvdawsymemhsvtqcpbfvin
    }

public String minWindow(String s, String t) {
    int[] empty = {0, 0, 0};
    int[][] solutions = new int[t.length()][3];
    for (int i = 0; i < s.length(); i++) {
        for (int ss = solutions.length - 1; ss >= 0; ss--) {
            int[] ints = solutions[ss];
            if (ints[2] != 0 && ints[2] < t.length() && s.charAt(i) == t.charAt(ints[2])) {
                ints[1] = i;
                ints[2]++;
                int[] current = solutions[ints[2] - 1];
                if (ints[2] == t.length()) {
                    if (current[2] == 0 ||
                            current[1] - current[0] > ints[1] - ints[0]) {
                        solutions[ints[2] - 1] = ints;
                    }
                } else if (current[2] == 0 || current[0] < ints[0]) {
                    solutions[ints[2] - 1] = ints;
                }
                solutions[ints[2] - 2] = empty;
            }
        }
        if (s.charAt(i) == t.charAt(0)) {
            solutions[0] = new int[] {i, i, 1};
        }
    }
    int[] lastSolution = solutions[t.length() - 1];
    return lastSolution[2] != 0? s.substring(lastSolution[0], lastSolution[1] + 1): "";
}
}
