package com.my.leetcode;

/**
 * Created by alex.bykovsky on 4/18/17.
 */
public class Test {

	public static void main(String[] args) {
		print(55);
		print(66);
		print(76);
		print(101);
		print(551);
	}

	private static void print(long x) {
		System.out.println(x * 100);
		System.out.println((x << 6) + (x << 5) + (x << 2));
	}
}
