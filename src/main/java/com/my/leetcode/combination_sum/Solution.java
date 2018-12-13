package com.my.leetcode.combination_sum;

import java.util.*;

public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().combinationSum(new int[] {2,3,6,7}, 7));
        /*[
            [7],
            [2,2,3]
        ]*/
        System.out.println(new Solution().combinationSum(new int[] {2,3,5}, 8));
        /*[
            [2,2,2,2],
            [2,3,3],
            [3,5]
        ]*/
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {

        Map<Integer, List<List<Integer>>> result = new HashMap<>();
        result.put(0, new ArrayList<>());
        for (int i = 1; i <= target; i++) {
            result.putIfAbsent(i, new ArrayList<>());
            for (int integer : candidates) {
                if (i == integer) {
                    List<Integer> list = new ArrayList<>();
                    list.add(i);
                    result.get(i).add(list);
                } else if (i - integer > 0) {
                    for (List<Integer> integers : result.get(i - integer)) {
                        if (integers.get(integers.size() - 1) <= integer) {
                            ArrayList<Integer> e = new ArrayList<>(integers);
                            e.add(integer);
                            result.get(i).add(e);
                        }
                    }
                }
            }
        }

        return result.get(target);
    }
}
