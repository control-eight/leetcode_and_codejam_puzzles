package com.my.leetcode.strobo_number;

import java.util.HashMap;
import java.util.Map;

public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().isStrobogrammatic("88"));
        System.out.println(new Solution().isStrobogrammatic("44"));
        System.out.println(new Solution().isStrobogrammatic("11"));
        System.out.println(new Solution().isStrobogrammatic("69"));
        System.out.println(new Solution().isStrobogrammatic("6699"));
        System.out.println(new Solution().isStrobogrammatic("66199"));
        System.out.println(new Solution().isStrobogrammatic("66999"));
        System.out.println(new Solution().isStrobogrammatic("661999"));
    }

    public boolean isStrobogrammatic(String num) {
        Map<Character, Character> map = new HashMap<>();
        map.put('0', '0');
        map.put('1', '1');
        map.put('6', '9');
        map.put('9', '6');
        map.put('8', '8');
        int length = num.length() % 2 == 0? num.length() / 2 - 1: num.length() / 2;
        for (int i = 0; i <= length; i++) {
            Character m = map.get(num.charAt(num.length() - i - 1));
            if (m == null || num.charAt(i) != m) {
                return false;
            }
        }
        return true;
    }
}
