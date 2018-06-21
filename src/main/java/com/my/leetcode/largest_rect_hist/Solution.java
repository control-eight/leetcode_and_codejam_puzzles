package com.my.leetcode.largest_rect_hist;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by alex.bykovsky on 5/15/17.
 */
public class Solution {

	public static void main(String[] args) {
		System.out.println(new Solution().largestRectangleArea(new int[] {5,5,6,6,4,4}));//24
		System.out.println(new Solution().largestRectangleArea(new int[] {3,6,5,7,4,8,1,0}));//20
		System.out.println(new Solution().largestRectangleArea(new int[] {2,1,2}));//3
		System.out.println(new Solution().largestRectangleArea(new int[] {2,1,1,2,2,2,1,4}));//8
		System.out.println(new Solution().largestRectangleArea(new int[] {2,2,1,2,2,4}));//6
		System.out.println(new Solution().largestRectangleArea(new int[] {0,0,0,0,0,0,0,0,2147483647}));//2147483647
		System.out.println(new Solution().largestRectangleArea(new int[] {5,4,1,2}));//8
		System.out.println(new Solution().largestRectangleArea(new int[] {2,2,1,10,2,4}));//10
		System.out.println(new Solution().largestRectangleArea(new int[] {2,1,5,6,2,3}));//10
	}

	public int largestRectangleArea(int[] heights) {

		int max = 0;

		Deque<Pair> stack = new LinkedList<>();

		for(int i = 0; i < heights.length; i++) {

			if(i == 0) {
				stack.offer(new Pair(heights[i], i));
			} else {

				if(heights[i] >= heights[i - 1]) {
					stack.offer(new Pair(heights[i], i));
				} else {
					max = iteratePrev(heights[i], max, stack, i);
				}
			}
		}

		for (Pair pair : stack) {
			max = Math.max(max, pair.value * (heights.length - pair.pos));
		}

		return max;
	}

	private int iteratePrev(int from, int max, Deque<Pair> stack, int i) {
		List<Pair> subList = new ArrayList<>();
		Pair element = stack.pollLast();
		while (element != null && element.value > from) {
			subList.add(element);
			element = stack.pollLast();
		}
		if(element != null) {
			stack.offer(element);
		}

		int tMin = Integer.MAX_VALUE;
		for (Pair pair : subList) {
			tMin = Math.min(tMin, pair.pos);

			int addOne = 0;
			if(from >= pair.value) {
				addOne++;
			}
			max = Math.max(max, pair.value * (i - pair.pos + addOne));

		}
		stack.offer(new Pair(from, i - (i - tMin)));

		return max;
	}

	private static class Pair {

		private int value;

		private int pos;

		Pair(int value, int pos) {
			this.value = value;
			this.pos = pos;
		}
	}
}
