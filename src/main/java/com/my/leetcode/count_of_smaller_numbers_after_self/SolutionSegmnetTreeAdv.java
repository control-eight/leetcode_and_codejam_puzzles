package com.my.leetcode.count_of_smaller_numbers_after_self;

import java.util.*;

public class SolutionSegmnetTreeAdv {

    public static void main(String[] args) {
        System.out.println(new SolutionSegmnetTreeAdv().countSmaller(new int[] {1,2,7,8,5}));
        System.out.println(new SolutionSegmnetTreeAdv().countSmaller(new int[] {3,2,2,1}));
        System.out.println(new SolutionSegmnetTreeAdv().countSmaller(new int[] {5,2,6,1}));
        System.out.println(new SolutionSegmnetTreeAdv().countSmaller(new int[] {-1, -1}));
        System.out.println(new SolutionSegmnetTreeAdv().countSmaller(new int[] {}));
        System.out.println(new SolutionSegmnetTreeAdv().countSmaller(new int[] {3,2,1}));
    }

    public List<Integer> countSmaller(int[] nums) {
        if (nums.length == 0) return Collections.emptyList();

        int[] nnums = new int[nums.length];
        System.arraycopy(nums, 0, nnums, 0, nnums.length);
        Arrays.sort(nnums);

        Map<Integer, Integer> map = new HashMap<>();
        int id = 0;
        for (int num : nnums) {
            if (!map.containsKey(num)) {
                map.put(num, id);
                id++;
            }
        }

        SegmentTreeSumMonoid segmentTree = new SegmentTreeSumMonoid(nums.length);

        List<Integer> result = new ArrayList<>(nums.length);
        for (int i = nums.length - 1; i >= 0; i--) {
            int index = map.get(nums[i]);
            segmentTree.updateDiff(index, 1);
            result.add(segmentTree.rangeQuery(index));
        }
        Collections.reverse(result);
        return result;
    }
}
