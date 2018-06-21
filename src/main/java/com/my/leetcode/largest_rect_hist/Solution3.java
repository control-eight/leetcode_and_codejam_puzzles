package com.my.leetcode.largest_rect_hist;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by alex.bykovsky on 12/5/17.
 */
public class Solution3 {

	public static void main(String[] args) {
		System.out.println(new Solution3().largestRectangleArea(new int[]{2,1,5,6,2,3}));
		System.out.println(new Solution3().largestRectangleArea(new int[]{2,2,5,6,2,3,1,1,1,1,1,1,1,1}));
		System.out.println(new Solution3().largestRectangleArea(new int[]{2,2,5,6,2,3}));
	}

	public int largestRectangleArea(int[] heights) {

		if(heights.length == 0) return 0;
		if(heights.length == 1) return heights[0];

		Deque<Container> stack = new LinkedList<>();
		stack.offer(new Container(heights[0], 1));

		int max = heights[0];

		int[] rects = new int[heights.length];
		rects[0] = heights[0];

		for(int i = 1; i < heights.length; i++) {
			Container newContainer = new Container(heights[i], 1);
			while(!stack.isEmpty()) {
				if(stack.peek().height >= newContainer.height) {
					newContainer.length += stack.poll().length;
				} else {
					break;
				}
			}
			rects[i] += newContainer.height * newContainer.length;
			max = Math.max(rects[i], max);
			stack.offerFirst(newContainer);
		}

		stack.clear();
		stack.offer(new Container(heights[heights.length - 1], 1));

		for(int i = heights.length - 2; i >= 0; i--) {
			Container newContainer = new Container(heights[i], 1);
			while(!stack.isEmpty()) {
				if(stack.peek().height >= newContainer.height) {
					newContainer.length += stack.poll().length;
				} else {
					break;
				}
			}
			rects[i] += newContainer.height * (newContainer.length - 1);
			max = Math.max(rects[i], max);
			stack.offerFirst(newContainer);
		}

		return max;
	}

	private class Container {

		private int height;
		private int length;

		public Container(int height, int length) {
			this.height = height;
			this.length = length;
		}
	}
}
