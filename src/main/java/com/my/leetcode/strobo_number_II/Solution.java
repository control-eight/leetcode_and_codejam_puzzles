package com.my.leetcode.strobo_number_II;

import java.util.*;

public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().findStrobogrammatic(1));
        System.out.println(new Solution().findStrobogrammatic(2));
        System.out.println(new Solution().findStrobogrammatic(3));
        System.out.println(new Solution().findStrobogrammatic(4));
    }

    public List<String> findStrobogrammatic(int n) {
        Map<Character, Character> map = initMap();
        return n % 2 == 0? generate("", n, map):
                addAll(generate("0", n, map), generate("1", n, map), generate("8", n, map));
    }

    private List<String> generate(String s, int n, Map<Character, Character> map) {
        if (s.length() == n && (s.length() == 1 || s.charAt(0) != '0')) {
            return Collections.singletonList(s);
        } else if (s.length() > n) {
            return Collections.emptyList();
        }
        List<String> result = new ArrayList<>();
        for (Map.Entry<Character, Character> entry : map.entrySet()) {
            result.addAll(generate(entry.getKey() + s + entry.getValue(), n, map));
        }
        return result;
    }

    private Map<Character, Character> initMap() {
        Map<Character, Character> map = new HashMap<>();
        map.put('0', '0');
        map.put('1', '1');
        map.put('6', '9');
        map.put('8', '8');
        map.put('9', '6');
        return map;
    }

    private List<String> addAll(List<String> generate, List<String> generate1, List<String> generate2) {
        List<String> result = new ArrayList<>();
        result.addAll(generate);
        result.addAll(generate1);
        result.addAll(generate2);
        return result;
    }
}
