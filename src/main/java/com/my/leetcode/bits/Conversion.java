package com.my.leetcode.bits;

/**
 * Created by alex.bykovsky on 10/8/17.
 */
public class Conversion {

	public static void main(String[] args) {
		System.out.println(new Conversion().checkDifference(29, 15));
	}

	private int checkDifference(int a, int b) {
		int count = 0;
		for(int c = a ^ b; c > 0; c = c & (c - 1)) {
			count++;
		}
		return count;
	}
}
