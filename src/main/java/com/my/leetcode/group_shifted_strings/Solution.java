package com.my.leetcode.group_shifted_strings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().groupStrings(new String[] {"abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"}));
        /*[
          ["abc","bcd","xyz"],
          ["az","ba"],
          ["acef"],
          ["a","z"]
        ]*/
    }

    public List<List<String>> groupStrings(String[] strings) {
        Map<List<Integer>, List<String>> map = new HashMap<>();
        for (String string : strings) {
            List<Integer> shift = shift(string);
            map.putIfAbsent(shift, new ArrayList<>());
            map.get(shift).add(string);
        }
        return new ArrayList<>(map.values());
    }

    private List<Integer> shift(String string) {
        List<Integer> result = new ArrayList<>(string.length());
        if (string.isEmpty()) return result;

        char first = string.charAt(0);
        result.add(0);

        for (int i = 1; i < string.length(); i++) {
            char c = string.charAt(i);
            if (c < first) {
                result.add(first - c);
            } else {
                result.add(('z' - c) + (first - 'a') + 1);
            }
        }
        return result;
    }
}
