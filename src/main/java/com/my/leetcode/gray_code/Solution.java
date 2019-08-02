package com.my.leetcode.gray_code;

import java.util.*;

public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().grayCode(0));
        System.out.println(new Solution().grayCode(1));
        System.out.println(new Solution().grayCode(2));
        System.out.println(new Solution().grayCode(3));
    }

    public List<Integer> grayCode(int n) {
        int max = (int) Math.pow(2, n) - 1;
        List<Integer> result = new ArrayList<>(max + 1);
        boolean[] visited = new boolean[max + 1];
        generate(0, max, n, result, visited);
        return result;
    }

    private void generate(int number, int max, int n, List<Integer> result, boolean[] visited) {
        result.add(number);
        visited[number] = true;
        int flipMask = 1;
        for (int i = 0; i < n; i++) {
            int next = number ^ flipMask;
            if (!visited[next] && next <= max) {
                generate(next, max, n, result, visited);
                break;
            }
            flipMask = flipMask << 1;
        }
    }
}