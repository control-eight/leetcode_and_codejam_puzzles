package com.my.leetcode.minimum_unique_word_abbreviation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().minAbbreviation("apple", new String[] {"blade"}));
        System.out.println(new Solution().minAbbreviation("apple", new String[] {"plain", "amber", "blade"}));
        System.out.println(new Solution().minAbbreviation("aabadaa", new String[] {"aabaxaa", "aaxadaa"}));
    }

    public String minAbbreviation(String target, String[] dictionary) {
        if (target.isEmpty()) return target;

        List<String> ld = new ArrayList<>();
        for (String s : dictionary) {
            if (s.length() == target.length()) {
                ld.add(s);
            }
        }

        if (ld.isEmpty()) return Integer.toString(target.length());

        String res = target;
        for (int size = 1; size <= target.length(); size++) {
            for (int position = 0; position < target.length() - size + 1; position++) {
                boolean conflict = false;
                String abb = applyAbbreviation(target, size, position);
                String tmp = null;
                for (int i = 0; i < ld.size(); i++) {
                    String s = ld.get(i);
                    if (!applyAbbreviation(s, size, position).equals(abb)) {
                        tmp = abb;
                    } else {
                        conflict = true;
                        System.out.println(abb + " " + conflict + " " + applyAbbreviation(s, size, position));
                        break;
                    }
                }
                if (!conflict) {
                    res = tmp;
                    break;
                }
            }
        }
        return res;
    }

    private String applyAbbreviation(String word, int size, int position) {
        return word.substring(0, position) + Integer.toString(size) + word.substring(position + size);
    }
}
