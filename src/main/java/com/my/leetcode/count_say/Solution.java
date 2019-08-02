package com.my.leetcode.count_say;

public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().countAndSay(1));
        //1
        System.out.println(new Solution().countAndSay(2));
        //11
        System.out.println(new Solution().countAndSay(3));
        //21
        System.out.println(new Solution().countAndSay(4));
        //1211
        System.out.println(new Solution().countAndSay(5));
        //111221
    }

    public String countAndSay(int n) {
        String s = "1";
        for (int i = 2; i <= n; i++) {
            StringBuilder next = new StringBuilder();
            int count = 1;
            char c = s.charAt(0);
            for (int j = 1; j < s.length(); j++) {
                if (s.charAt(j) != c) {
                    next.append(count).append(c);
                    count = 1;
                    c = s.charAt(j);
                } else {
                    count++;
                }
            }
            next.append(count).append(c);
            s = next.toString();
        }
        return s;
    }
}
