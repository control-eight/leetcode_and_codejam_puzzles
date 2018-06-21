package com.my.leetcode.reverse_integer;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by alex.bykovsky on 3/31/18.
 */
public class Solution {

	public static void main(String[] args) {
		System.out.println(new Solution().reverse(12345));
		System.out.println(new Solution().reverse(-123));
	}

	public int reverse(int x) {
		if (x == 0) return 0;

		Deque<Integer> digits = new LinkedList<>();

		int pos = x < 0? -1: 1;
		x = Math.abs(x);

		while (x > 0) {
			digits.offer(x % 10);
			x /= 10;
		}

		long result = 0;
		long mult = 1;
		int size = digits.size();
		for (int i = 0; i < size; i++) {
			result += digits.pollLast() * mult;
			mult *= 10;
		}
		return result > Integer.MAX_VALUE? 0: pos * ((int) result);
	}
}
