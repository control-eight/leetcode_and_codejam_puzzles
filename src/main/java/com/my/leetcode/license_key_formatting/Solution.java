package com.my.leetcode.license_key_formatting;

public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().licenseKeyFormatting("2", 1));
        System.out.println(new Solution().licenseKeyFormatting("5F3Z-2e-9-w", 4));
        System.out.println(new Solution().licenseKeyFormatting("2-5g-3-J", 2));
    }

    public String licenseKeyFormatting(String S, int K) {
        String s1 = S.replaceAll("-", "").toUpperCase();
        Integer firstGroup  = s1.length() % K;
        StringBuilder sb = new StringBuilder();
        if (firstGroup > 0) {
            for (int i = 0; i < firstGroup; i++) {
                sb.append(s1.charAt(i));
            }
            if (firstGroup < s1.length() - 1) {
                sb.append("-");
            }
        }
        int j = 0;
        for (int i = firstGroup; i < s1.length(); i++) {
            if (j > 0 && j % K == 0) {
                sb.append("-");
            }
            sb.append(s1.charAt(i));
            j++;
        }
        return sb.toString();
    }
}
