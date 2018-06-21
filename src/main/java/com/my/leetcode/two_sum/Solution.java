package com.my.leetcode.two_sum;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by alex.bykovsky on 7/6/17.
 */
public class Solution {

	public int[] twoSum(int[] nums, int target) {
		Map<Integer, Set<Integer>> helpMap = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			int num = nums[i];
			if(!helpMap.containsKey(num)) {
				Set<Integer> indexSet = new HashSet<>();
				indexSet.add(i);
				helpMap.put(num, indexSet);
			} else {
				helpMap.get(num).add(i);
			}
		}

		List<Integer> list = new LinkedList<>();
		for(int i = 0; i < nums.length; i++) {

			int num = nums[i];
			Set<Integer> indexSet = helpMap.get(target - num);
			if(indexSet != null) {
				for (Integer index : indexSet) {
					if(index != i) {
						list.add(index);
					}
				}
			}
		}

		int[] res = new int[list.size()];
		for (int i = 0; i < list.size(); i++) {
			res[i] = list.get(i);
		}
		return res;
	}
}
