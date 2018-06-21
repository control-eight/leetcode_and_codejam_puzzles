package com.my.leetcode.trapping_rain_water;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by alex.bykovsky on 3/26/18.
 */
public class Solution {

	public static void main(String[] args) {
		System.out.println(new Solution().trap(new int[] {4,2,0,3,2,5}));
		//9
		System.out.println(new Solution().trap(new int[] {2,1,0,1,2}));
		//4
		System.out.println(new Solution().trap(new int[] {1,0,2}));
		//1
		System.out.println(new Solution().trap(new int[] {0,1,0,2}));
		//1
		System.out.println(new Solution().trap(new int[] {2,1,0,1,3}));
		//4
		System.out.println(new Solution().trap(new int[] {0,1,0,2,1,0,1,3,2,1,2,1}));
		//6
		System.out.println(new Solution().trap(new int[] {4,2,0,5}));
		//6
		System.out.println(new Solution().trap(new int[] {4,0,2,1,5}));
		//9
		System.out.println(new Solution().trap(new int[] {0,5,0,5,0,5}));
		//10
		System.out.println(new Solution().trap(new int[] {5,4,3,2,1,5}));
		//10
		System.out.println(new Solution().trap(new int[] {2,0,10}));
		//2
	}

	public int trap(int[] height) {
		Deque<Pair> stack = new LinkedList<>();
		int result = 0;
		for (int i = 0; i < height.length - 1; i++) {
			result = processCurrent(height, stack, result, i, new Pair(height[i], i));
		}
		return result;
	}

	private int processCurrent(int[] height, Deque<Pair> stack, int result, int i, Pair current) {
		Pair last = stack.peekLast();
		if (!stack.isEmpty() && last.height >= current.height && current.height <= height[i + 1]) {
			result += (Math.min(height[i + 1], last.height) - current.height) * (i - last.index);
			result = processCurrent(height, stack, result, i, stack.pollLast());
		} else {
			if (height[i] <= current.height) {
				stack.offerLast(current);
			}
		}
		return result;
	}

	private static class Pair {
		Integer height;
		Integer index;
		Pair(Integer height, Integer index) {
			this.height = height;
			this.index = index;
		}
	}
}
