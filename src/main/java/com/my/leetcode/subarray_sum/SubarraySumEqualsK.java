package com.my.leetcode.subarray_sum;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by alex.bykovsky on 9/7/17.
 */
public class SubarraySumEqualsK {

	public static void main(String[] args) {
		System.out.println(new SubarraySumEqualsK().subarraySum(new int[]{1,1,1}, 2));
		System.out.println(new SubarraySumEqualsK().subarraySum(new int[]{10,20,30,40,10}, 50));
		System.out.println(new SubarraySumEqualsK().subarraySum(new int[]{1}, 0));
		System.out.println(new SubarraySumEqualsK().subarraySum(new int[]{-1,-1,1}, 0));
		System.out.println(new SubarraySumEqualsK().subarraySum(new int[]{0,0,0}, 0));
		System.out.println(new SubarraySumEqualsK().subarraySum(new int[]{0,0,0,0,0,0,0,0,0,0}, 0));
		System.out.println(new SubarraySumEqualsK().subarraySum(new int[]{1,2,3}, 3));
		//55
	}

	public int subarraySum(int[] nums, int k) {

		if(nums.length == 0) return 0;

		Map<Integer, List<Integer>> sumsIndex = new HashMap<>();

		int[] partialSums = new int[nums.length];
		partialSums[0] = nums[0];

		sumsIndex.put(0, new LinkedList<>());
		sumsIndex.get(0).add(-1);
		sumsIndex.put(partialSums[0], new LinkedList<>());
		sumsIndex.get(partialSums[0]).add(0);

		int count = 0;

		if(nums[0] == 0 && k == 0) count++;

		for(int i = 1; i < nums.length; i++) {
			partialSums[i] = partialSums[i - 1] + nums[i];
			sumsIndex.putIfAbsent(partialSums[i], new LinkedList<>());
			sumsIndex.get(partialSums[i]).add(i);

			if(nums[i] == 0 && k == 0) count++;
		}

		for(int i = partialSums.length - 1; i >= 0; i--) {
			List<Integer> indexList = sumsIndex.get(partialSums[i] - k);
			if(indexList != null) {
				int j = 0;
				while(j < indexList.size() && indexList.get(j) < i) {
					count++;
					j++;
				}
			}
		}

		return count;
	}
}
