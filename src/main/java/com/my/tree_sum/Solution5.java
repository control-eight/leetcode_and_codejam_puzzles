package com.my.tree_sum;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by alex.bykovsky on 7/6/17.
 */
public class Solution5 {

	public static void main(String[] args) {
		/*System.out.println(new Solution().threeSum(new int[]{-1,0,1,2,-1,-4}));
		System.out.println(new Solution().threeSum(new int[]{0,0,0}));
		System.out.println(new Solution().threeSum(new int[]{3,0,-2,-1,1,2}));
		System.out.println(new Solution().threeSum(new int[]{-4,-2,-2,-2,0,1,2,2,2,3,3,4,4,6,6}));
		System.out.println(new Solution().threeSum(new int[]{-4,-2,1,-5,-4,-4,4,-2,0,4,0,-2,3,1,-5,0}));
		System.out.println(new Solution().threeSum(new int[]{-1,-2,-3,4,1,3,0,3,-2,1,-2,2,-1,1,-5,4,-3}));
		System.out.println(new Solution().threeSum(new int[]{-4,-2,-2,-2,0,1,2,2,2,3,3,4,4,6,6}));
		//[[-4,-2,6],[-4,0,4],[-4,1,3],[-4,2,2],[-2,-2,4],[-2,0,2]]

		*//*Input: [-4,-2,1,-5,-4,-4,4,-2,0,4,0,-2,3,1,-5,0]
		Output: [[-2,-2,4],[-4,-4,4],[-5,-5,4],[-5,1,4],[-4,1,3],[-2,1,1],[0,0,0],[-4,0,4]]
		Expected: [[-5,1,4],[-4,0,4],[-4,1,3],[-2,-2,4],[-2,1,1],[0,0,0]]*//*

		System.out.println(new Solution().threeSum(new int[]{0,0}));
		System.out.println(new Solution().threeSum(new int[]{-6,3}));
		System.out.println(new Solution().threeSum(new int[]{-4,-2,-1}));
		System.out.println(new Solution().threeSum(new int[]{-4,1,3,0,0,0}));
		System.out.println(new Solution().threeSum(new int[]{-4,-2,1,-5,-4,-4,4,-2,0,4,0,-2,3,1,-5,0}));
		//[[-5,1,4],[-4,0,4],[-4,1,3],[-2,-2,4],[-2,1,1],[0,0,0]]
		System.out.println(new Solution().threeSum(new int[]{0,0}));*/
		System.out.println(new Solution5().threeSum(new int[]{-3,-3,1,2,-3,0,0,4,-1,3,-4,-2}));
		//[[-4,0,4],[-4,1,3],[-3,-1,4],[-3,0,3],[-3,1,2],[-2,-1,3],[-2,0,2],[-1,0,1]]


		/*
		[[-1, 0, 1], [-1, -1, 2]]
		[[0, 0, 0]]
		[[-2, 0, 2], [-1, 0, 1], [-2, -1, 3]]
		[[-4, 0, 4], [-4, 1, 3], [-4, 2, 2], [-2, 0, 2], [-2, -2, 4], [-4, -2, 6]]
		*/
	}

	public List<List<Integer>> threeSum(int[] nums) {

		Arrays.sort(nums);
		System.out.println(Arrays.toString(nums));

		Map<Integer, Set<Integer>> helpMap = new HashMap<>();
		int minPositive = Integer.MAX_VALUE;
		int minPositiveIndex = -1;
		int zeroCount = 0;
		for (int index = 0; index < nums.length; index++) {
			int num = nums[index];
			if(!helpMap.containsKey(num)) {
				helpMap.put(num, new HashSet<>());
			}
			helpMap.get(num).add(index);

			if(num >= 0 && num < minPositive) {
				minPositive = num;
				minPositiveIndex = index;
			}
			if(num == 0) {
				zeroCount++;
			}
		}

		Set<List<Integer>> res = new HashSet<>();

		List<List<Integer>> ress = new LinkedList<>();

		if(zeroCount >= 3) {
			res.add(Arrays.asList(0,0,0));
		}

		if(minPositiveIndex <= 0) {
			ress.addAll(res);
			return ress;
		}

		int goLeft = minPositiveIndex - 1;
		int goRight = minPositiveIndex;
		while(goLeft >= 0 && goRight <= nums.length - 1) {

			checkAndAndSolutions(nums, helpMap, res, goLeft, goRight);

			if(Math.abs(nums[goLeft]) < Math.abs(nums[goRight])) {
				if(goLeft > 0) {
					goLeft--;
					checkAndAndSolutions(nums, helpMap, res, goLeft, goLeft+1);
				} else {
					goRight++;
					checkAndAndSolutions(nums, helpMap, res, goRight-1, goRight);
				}
			} else {
				if(goRight < nums.length - 1) {
					goRight++;
					checkAndAndSolutions(nums, helpMap, res, goRight-1, goRight);
				} else {
					goLeft--;
					checkAndAndSolutions(nums, helpMap, res, goLeft, goLeft+1);
				}
			}
		}

		ress.addAll(res);

		return ress;

	}

	private void checkAndAndSolutions(int[] nums, Map<Integer, Set<Integer>> helpMap, Set<List<Integer>> res, int goLeft, int goRight) {
		if(goLeft < 0 || goRight >= nums.length) return;

		int sum = nums[goLeft] + nums[goRight];
		Set<Integer> indexes = helpMap.get(-sum);
		if(indexes != null) {
			for (Integer index : indexes) {
				if (index != goLeft && index != goRight) {
					List<Integer> e = Arrays.asList(nums[goLeft], nums[goRight], nums[index]);
					Collections.sort(e);
					res.add(e);
				}
			}
		}
	}
}
