package com.my.leetcode.decode_ways;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by alex.bykovsky on 10/12/17.
 */
public class Solution {

	public static void main(String[] args) {
		assertEquals(2, new Solution().numDecodings("12"));
		assertEquals(0, new Solution().numDecodings(""));
		assertEquals(0, new Solution().numDecodings("0"));
		assertEquals(1, new Solution().numDecodings("10"));
		assertEquals(0, new Solution().numDecodings("1000"));
		assertEquals(1, new Solution().numDecodings("36"));
		assertEquals(0, new Solution().numDecodings("01"));
	}

	private static void assertEquals(int expected, int actual) {
		if(expected != actual) throw new RuntimeException(expected + " != " + actual);
	}

	public int numDecodings(String s) {
		if(s.isEmpty()) return 0;

		return numDecodingsInternal(s, new HashMap<>());
	}

	private int numDecodingsInternal(String s, Map<String, Integer> cache) {

		if(s.isEmpty()) return 1;

		if(cache.containsKey(s)) return cache.get(s);

		int result;

		if(s.charAt(0) == '0') {
			return 0;
		} else {
			result = numDecodingsInternal(s.substring(1), cache);

			if(s.length() > 1) {
				String two = s.substring(0, 2);
				if(Integer.parseInt(two) <= 26) {
					result += numDecodingsInternal(s.substring(2), cache);
				}
			}
		}

		cache.put(s, result);
		return result;
	}
}
