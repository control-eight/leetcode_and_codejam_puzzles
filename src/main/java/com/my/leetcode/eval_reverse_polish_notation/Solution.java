package com.my.leetcode.eval_reverse_polish_notation;

import java.util.Deque;
import java.util.LinkedList;

public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().evalRPN(new String[] {"2", "1", "+", "3", "*"}));
        //9
        System.out.println(new Solution().evalRPN(new String[] {"4", "13", "5", "/", "+"}));
        //6
        System.out.println(new Solution().evalRPN(new String[] {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"}));
        //22
    }

    public int evalRPN(String[] tokens) {
        Deque<String> evalStack = new LinkedList<>();
        for (int i = tokens.length - 1; i >= 0; i--) {
            evalStack.offer(tokens[i]);
            while (isLastTwoDigits(evalStack)) {
                evalStack(evalStack);
            }
        }
        return Integer.parseInt(evalStack.poll());
    }

    private boolean isLastTwoDigits(Deque<String> evalStack) {
        if (evalStack.size() == 1) return false;
        String s1 = null;
        try {
            s1 = evalStack.pollLast();
            Integer.parseInt(s1);
        } catch (NumberFormatException e) {
            evalStack.offer(s1);
            return false;
        }
        try {
            Integer.parseInt(evalStack.peekLast());
        } catch (NumberFormatException e) {
            return false;
        } finally {
            evalStack.offer(s1);
        }
        return true;
    }

    private void evalStack(Deque<String> evalStack) {
        int d1 = Integer.parseInt(evalStack.pollLast());
        int d2  = Integer.parseInt(evalStack.pollLast());
        String operator = evalStack.pollLast();
        switch (operator) {
            case "+": {
                evalStack.offer((d1 + d2) + "");
                break;
            }
            case "-": {
                evalStack.offer((d1 - d2) + "");
                break;
            }
            case "/": {
                evalStack.offer((d1 / d2) + "");
                break;
            }
            case "*": {
                evalStack.offer((d1 * d2) + "");
                break;
            }
            default:
                throw new RuntimeException(operator);
        }
    }
}
