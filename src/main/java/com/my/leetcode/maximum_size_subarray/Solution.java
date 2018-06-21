package com.my.leetcode.maximum_size_subarray;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by alex.bykovsky on 10/12/17.
 */
public class Solution {

	public static void main(String[] args) {
		assertEquals(4, new Solution().maxSubArrayLen(new int[] {1, -1, 5, -2, 3}, 3));
		assertEquals(5, new Solution().maxSubArrayLen(new int[] {10, 0, 1, -1, 5, -2, 3}, 3));
		assertEquals(2, new Solution().maxSubArrayLen(new int[] {-2, -1, 2, 1}, 1));
	}

	private static void assertEquals(int expected, int actual) {
		if(expected != actual) throw new RuntimeException(expected + " != " + actual);
	}

	//1 -1 5 -2 3
	//1  0 5  3 6

	//10, 0, 1, -1, 5, -2, 3
	//10  0  11 10 15  13  16

	//-2 -1 2 1
	//-2 -3 -1 0

	public int maxSubArrayLen(int[] nums, int k) {

		if(nums.length == 0) return 0;

		Map<Integer, Integer> sumIndexMap = new HashMap<>();

		int[] partialSums = new int[nums.length];
		partialSums[0] = nums[0];
		sumIndexMap.put(partialSums[0], 0);
		for(int i = 1; i < nums.length; i++) {
			partialSums[i] = partialSums[i - 1] + nums[i];
			if(!sumIndexMap.containsKey(partialSums[i])) {
				sumIndexMap.put(partialSums[i], i);
			}
		}

		int max = 0;
		for(int i = 0; i < partialSums.length; i++) {
			Integer prevSumIndex = sumIndexMap.get(partialSums[i] - k);
			if(prevSumIndex != null && prevSumIndex < i) {
				max = Math.max(max, i - prevSumIndex);
			}
			if(partialSums[i] == k) {
				max = Math.max(max, i + 1);
			}
		}

		return max;
	}
}
