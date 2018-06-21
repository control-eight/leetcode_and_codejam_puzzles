package com.my.leetcode.subsets;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by alex.bykovsky on 9/28/17.
 */
public class Solution2 {

	public static void main(String[] args) {
		System.out.println(new Solution2().subsets(new int[] {1,2,3}));
	}

	public List<List<Integer>> subsets(int[] nums) {
		if(nums.length == 0) return Collections.emptyList();

		List<List<Integer>> results = new ArrayList<>();

		long tracked = 0;
		long max = (long) Math.pow(2, nums.length);
		while(tracked < max) {
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
			tracked++;
		}

		return results;
	}
}
