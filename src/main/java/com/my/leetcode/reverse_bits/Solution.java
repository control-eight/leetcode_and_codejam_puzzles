package com.my.leetcode.reverse_bits;

/**
 * Created by alex.bykovsky on 4/1/18.
 */
public class Solution {

	public static void main(String[] args) {
		System.out.println(new Solution().reverseBits(  -1));
		//-1
		System.out.println(new Solution().reverseBits(43261596));
		//964176192
		System.out.println(new Solution().reverseBits(1));
		//-2147483648
		System.out.println(new Solution().reverseBits(-2147483648));
		//1
	}

	// you need treat n as an unsigned value
	public int reverseBits(int n) {
		long res = 0;
		long checkOne = 1;
		long checkTwo = ((long)1 << (long)31);

		while (n < 0? checkOne >= n: checkOne <= n) {
			if ((n & checkOne) >= 1) {
				res |= checkTwo;
			}
			checkOne <<= 1;
			checkTwo >>= 1;
		}

		return (int) res;
	}
}
