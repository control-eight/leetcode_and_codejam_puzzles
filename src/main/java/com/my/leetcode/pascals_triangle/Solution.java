package com.my.leetcode.pascals_triangle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex.bykovsky on 4/1/18.
 */
public class Solution {

	public static void main(String[] args) {
		System.out.println(new Solution().generate(5));
	}

	public List<List<Integer>> generate(int numRows) {

		List<List<Integer>> result = new ArrayList<>();

		if (numRows == 0) return result;

		result.add(new ArrayList<>());
		result.get(0).add(1);

		if (numRows == 1) return result;

		result.add(new ArrayList<>());
		result.get(1).add(1);
		result.get(1).add(1);

		for(int i = 2; i < numRows; i++) {
			result.add(new ArrayList<>());
			result.get(i).add(1);
			for(int j = 0; j < i - 1; j++) {
				result.get(i).add(result.get(i - 1).get(j) + result.get(i - 1).get(j + 1));
			}
			result.get(i).add(1);
		}

		return result;
	}
}
