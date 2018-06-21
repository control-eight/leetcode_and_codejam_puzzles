package com.my.leetcode.jump_game;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by alex.bykovsky on 7/10/17.
 */
public class Solution {

	public static void main(String[] args) {
		System.out.println(new Solution().canJump(new int[] {2,3,1,1,4}));
		System.out.println(new Solution().canJump(new int[] {3,2,1,0,4}));
		System.out.println(new Solution().canJump(new int[] {4,0,1,0,2,0}));
		System.out.println(new Solution().canJump(new int[] {3,0,1,0,2,0}));
		System.out.println(new Solution().canJump(new int[] {2,1,0,0}));
	}

	public boolean canJump(int[] nums) {

		Queue<Integer> intervals = new LinkedList<>();

		for(int i = 0; i < nums.length; i++) {
			if(nums[i] == 0 && i < nums.length - 1) {
				int j = i + 1;
				while (j < nums.length && nums[j] == 0) {
					j++;
				}
				if(j < nums.length) {
					intervals.add(j);
				} else {
					intervals.add(j - 1);
				}
				i = j;
			}
		}

		for (int i = 0; i < nums.length; i++) {
			if(intervals.isEmpty()) {
				return true;
			}

			if(i == intervals.peek()) {
				return false;
			}

			while (!intervals.isEmpty() && i + nums[i] >= intervals.peek()) {
				intervals.remove();
			}
		}

		return intervals.isEmpty();
	}
}
