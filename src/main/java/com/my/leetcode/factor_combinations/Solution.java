package com.my.leetcode.factor_combinations;

import java.util.*;

public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().getFactors(1));
        System.out.println(new Solution().getFactors(37));
        System.out.println(new Solution().getFactors(12));
        System.out.println(new Solution().getFactors(32));
    }

    public List<List<Integer>> getFactors(int n) {
        return getFactors(n, 2);
    }

    private List<List<Integer>> getFactors(int n, int startWith) {
        if (n == 1) return new LinkedList<>();

        List<List<Integer>> result = new LinkedList<>();

        int sqrt = (int) Math.sqrt(n);
        for (int i = startWith; i <= sqrt; i++) {
            if ((n / i) >= i && n % i == 0) {
                List<Integer> subResult2 = new LinkedList<>();
                subResult2.add(i);
                subResult2.add(n / i);
                result.add(subResult2);

                List<List<Integer>> subResults = getFactors(n / i, i);
                for (List<Integer> subResult : subResults) {
                    subResult.add(0, i);
                    result.add(subResult);
                }
            }
        }
        return result;
    }
}
