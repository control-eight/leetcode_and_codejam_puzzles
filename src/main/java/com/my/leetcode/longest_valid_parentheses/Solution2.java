package com.my.leetcode.longest_valid_parentheses;

/**
 * @author abykovsky
 * @since 6/9/18
 */
public class Solution2 {

    public static void main(String[] args) {
        System.out.println(new Solution2().longestValidParentheses("(())"));
        System.out.println(new Solution2().longestValidParentheses("((())()()()()()()"));
    }

    public int longestValidParentheses(String s) {
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            int open = 0;
            for (int j = i; j < s.length(); j++) {
                if (s.charAt(j) == '(') {
                    open++;
                } else {
                    open--;
                }
                if (open < 0) {
                    break;
                } else if (open == 0) {
                    result = Math.max(result, j - i + 1);
                }
            }
        }
        return result;
    }
}
