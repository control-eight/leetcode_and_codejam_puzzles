package com.my.leetcode.reverse_integer;

/**
 * Created by alex.bykovsky on 3/31/18.
 */
public class Solution2 {

	public static void main(String[] args) {
		System.out.println(new Solution2().reverse(12345));
		System.out.println(new Solution2().reverse(-123));
		System.out.println(new Solution2().reverse(1534236469));
	}

	public int reverse(int x) {
		if (x == 0) return 0;

		int pos = x < 0? -1: 1;
		x = Math.abs(x);

		long result = 0;
		while (x >= 0) {
			result = result * 10 + x % 10;
			x /= 10;
		}

		return result > Integer.MAX_VALUE? 0: pos * ((int) result);
	}
}
