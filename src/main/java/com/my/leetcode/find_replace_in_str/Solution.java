package com.my.leetcode.find_replace_in_str;

import java.util.Arrays;

public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().findReplaceString("abcd", new int[] {0,2}, new String[] {"a", "cd"},
                new String[] {"eee", "ffff"}));
        //eeebffff
        System.out.println(new Solution().findReplaceString("abcd", new int[] {0,2}, new String[] {"ab", "ec"},
                new String[] {"eee", "ffff"}));
        //eeecd
    }

    public String findReplaceString(String S, int[] indexes, String[] sources, String[] targets) {
        int[] ind = new int[1000];
        Arrays.fill(ind, -1);
        for (int i = 0; i < indexes.length; i++) {
            ind[indexes[i]] = i;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < S.length(); i++) {
            if (ind[i] > -1) {
                if (S.substring(i, i + sources[ind[i]].length()).equals(sources[ind[i]])) {
                    sb.append(targets[ind[i]]);
                    i += sources[ind[i]].length() - 1;
                } else {
                    sb.append(S.charAt(i));
                }
            } else {
                sb.append(S.charAt(i));
            }
        }
        return sb.toString();
    }
}
