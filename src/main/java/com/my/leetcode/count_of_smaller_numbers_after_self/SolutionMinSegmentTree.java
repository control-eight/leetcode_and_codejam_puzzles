package com.my.leetcode.count_of_smaller_numbers_after_self;

import java.util.Collections;
import java.util.List;

public class SolutionMinSegmentTree {

    public static void main(String[] args) {
        System.out.println(new SolutionMinSegmentTree().countSmaller(new int[] {-1, -1}));
        System.out.println(new SolutionMinSegmentTree().countSmaller(new int[] {}));
        System.out.println(new SolutionMinSegmentTree().countSmaller(new int[] {5,2,6,1}));
        System.out.println(new SolutionMinSegmentTree().countSmaller(new int[] {3,2,1}));
        System.out.println(new SolutionMinSegmentTree().countSmaller(new int[] {3,2,2,1}));
    }

    public List<Integer> countSmaller(int[] nums) {
        if (nums.length == 0) return Collections.emptyList();

        return null;
    }
}
