package com.my.leetcode.decode_string;

public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().decodeString("3[a]2[bc]"));
        //"aaabcbc"
        System.out.println(new Solution().decodeString("3[a2[c]]"));
        //"accaccacc"
        System.out.println(new Solution().decodeString("2[abc]3[cd]ef"));
        //"abcabccdcdcdef"
    }

    public String decodeString(String s) {

        if (s.indexOf('[') == -1) return s;
        StringBuilder sb = new StringBuilder();

        while (!s.isEmpty()) {
            int i = 0;
            while (i < s.length() && (s.charAt(i) < '0' || s.charAt(i) > '9')) {
                sb.append(s.charAt(i));
                i++;
            }
            s = s.substring(i);
            if (i < s.length()) {
                int times = Integer.parseInt(s.substring(0, s.indexOf('[')));
                int open = 1;
                for (i = s.indexOf('[') + 1; i < s.length(); i++) {
                    if (s.charAt(i) == ']') {
                        open--;
                    } else if (s.charAt(i) == '[') {
                        open++;
                    }
                    if (open == 0) {
                        open = i;
                        break;
                    }
                }
                copy(times, decodeString(s.substring(s.indexOf("[") + 1, open)), sb);
                s = s.substring(open + 1);
            }
        }
        return sb.toString();
    }

    private void copy(int times, String s, StringBuilder acc) {
        for (int i = 0; i < times; i++) {
            acc.append(s);
        }
    }
}
