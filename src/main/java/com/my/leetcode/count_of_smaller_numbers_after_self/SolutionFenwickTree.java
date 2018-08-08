package com.my.leetcode.count_of_smaller_numbers_after_self;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SolutionFenwickTree {

    public static void main(String[] args) {
        System.out.println(new SolutionFenwickTree().countSmaller(new int[] {-1, -1}));
        System.out.println(new SolutionFenwickTree().countSmaller(new int[] {}));
        System.out.println(new SolutionFenwickTree().countSmaller(new int[] {5,2,6,1}));
    }

    public List<Integer> countSmaller(int[] nums) {
        if (nums.length == 0) return Collections.emptyList();

        int min = Integer.MAX_VALUE;
        int max = 0;
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }

        List<Integer> result = new ArrayList<>();
        FenwickTree fenwickTree = new FenwickTree(max - min + 1);
        for (int i = nums.length - 1; i >= 0; i--) {
            int value = nums[i] - min;
            result.add(fenwickTree.rangeQuery(value - 1));
            fenwickTree.update(value, 1);
        }
        Collections.reverse(result);
        return result;
    }
}
