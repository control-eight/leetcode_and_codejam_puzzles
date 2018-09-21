package com.my.leetcode.factor_combinations;

import java.util.*;

public class Solution2 {

    public static void main(String[] args) {
        System.out.println(new Solution2().getFactors(1));
        System.out.println(new Solution2().getFactors(37));
        System.out.println(new Solution2().getFactors(12));
        System.out.println(new Solution2().getFactors(32));
    }

    private Map<Integer, List<List<Integer>>> cache = new HashMap<>();

    public List<List<Integer>> getFactors(int n) {
        if (n == 1) return new ArrayList<>();
        if (cache.containsKey(n)) return cache.get(n);

        Set<List<Integer>> result = new HashSet<>();

        int sqrt = (int) Math.sqrt(n);
        for (int i = 2; i <= sqrt; i++) {
            if (n % i == 0) {
                List<Integer> subResult2 = new ArrayList<>();
                subResult2.add(i);
                subResult2.add(n / i);
                subResult2.sort(Integer::compare);
                result.add(subResult2);

                if (n / i > 2) {
                    List<List<Integer>> subResults = getFactors(n / i);
                    for (List<Integer> subResult : subResults) {
                        List<Integer> subResult5 = new ArrayList<>(subResult);
                        subResult5.add(0, i);
                        subResult5.sort(Integer::compare);
                        result.add(subResult5);
                    }
                }
            }
        }
        ArrayList<List<Integer>> f = new ArrayList<>(result);
        cache.put(n, f);
        return f;
    }
}
