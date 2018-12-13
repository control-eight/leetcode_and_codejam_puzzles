package com.my.leetcode.valid_parenthesis_string;

public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().checkValidString("*()("));
        System.out.println(new Solution().checkValidString("(())((())()()(*)(*()(())())())()()((()())((()))(*"));
        System.out.println(new Solution().checkValidString("()"));
        System.out.println(new Solution().checkValidString("(*)"));
        System.out.println(new Solution().checkValidString("(*))"));
        System.out.println(new Solution().checkValidString("(()*"));
        System.out.println(new Solution().checkValidString(")(*"));
        System.out.println(new Solution().checkValidString(")((**"));
    }

    public boolean checkValidString(String s) {

        int starsCount = 0;
        int openParenthes = 0;
        int closedParenthes = 0;
        char[] chars = s.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (c == '(') {
                openParenthes++;
            } else if (c == ')') {
                closedParenthes++;
            } else {
                starsCount++;
            }
            if (closedParenthes > openParenthes) {
                int diff = Math.min(starsCount, closedParenthes - openParenthes);
                closedParenthes -= diff;
                starsCount -= diff;
                if (closedParenthes > openParenthes) return false;
            }
        }
        boolean result = starsCount - Math.abs(openParenthes - closedParenthes) >= 0;
        if (!result) return false;

        starsCount = 0;
        openParenthes = 0;
        closedParenthes = 0;

        for (int i = chars.length - 1; i >= 0; i--) {
            char c = chars[i];
            if (c == '(') {
                openParenthes++;
            } else if (c == ')') {
                closedParenthes++;
            } else {
                starsCount++;
            }
            if (openParenthes > closedParenthes) {
                int diff = Math.min(starsCount, openParenthes - closedParenthes);
                openParenthes -= diff;
                starsCount -= diff;
                if (openParenthes > closedParenthes) return false;
            }
        }
        return starsCount - Math.abs(openParenthes - closedParenthes) >= 0;
    }
}
