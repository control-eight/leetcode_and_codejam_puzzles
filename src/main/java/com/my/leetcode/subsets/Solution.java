package com.my.leetcode.subsets;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by alex.bykovsky on 9/28/17.
 */
public class Solution {

	public static void main(String[] args) {
		System.out.println(new Solution().subsets(new int[] {1,2,3}));
	}

	public List<List<Integer>> subsets(int[] nums) {
		if(nums.length == 0) return Collections.emptyList();
		long tracked = 0;
		List<List<Integer>> results = new ArrayList<>();
		generateSubsets(nums, 0, tracked, results);
		return results;
	}

	private void generateSubsets(int[] nums, int length, long tracked, List<List<Integer>> results) {

		if(length > nums.length) {
			return;
		}

		long check = 1;
		int count = 0;
		List<Integer> subResults = new ArrayList<>();
		while(count < nums.length) {
			if((check & tracked) > 0) {
				subResults.add(nums[count]);
			}
			check = check << 1;
			count++;
		}
		results.add(subResults);

		check = 1;
		for(int i: nums) {
			if((tracked & check) == 0) {
				tracked |= check;
				generateSubsets(nums, length + 1, tracked, results);
				tracked ^= check;
				check = check << 1;
			}
		}
	}
}
