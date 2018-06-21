package com.my.leetcode.min_window_subs;

/**
 * Created by alex.bykovsky on 5/12/18.
 */
public class Solution {

	public static void main(String[] args) {
		System.out.println(new Solution().minWindow("acbbaca", "aba"));
		System.out.println(new Solution().minWindow("bba", "ab"));
		System.out.println(new Solution().minWindow("ADOBECODEBANC", "ABC"));
		System.out.println(new Solution().minWindow("a", "a"));
		System.out.println(new Solution().minWindow("ab", "a"));
		System.out.println(new Solution().minWindow("ab", "b"));
		System.out.println(new Solution().minWindow("a", "b"));
	}

	public String minWindow(String s, String t) {

		int[] map = new int[128];
		for (char c : t.toCharArray()) {
			map[c]++;
		}

		int lo = 0; int minLo = 0; int minHi = Integer.MAX_VALUE; int found = 0;
		char[] charArr = s.toCharArray();

		for (int hi = 0; hi < charArr.length; hi++) {
			if (--map[charArr[hi]] < 0) {
				while (lo < Math.min(hi + 1, s.length()) && map[charArr[lo]] < 0) {
					map[charArr[lo++]]++;
				}
				if (found == t.length() && (hi - lo < minHi - minLo)) {
					minLo = lo; minHi = hi;
				}
			} else {
				if (++found == t.length()) {
					minLo = lo; minHi = hi;
				}
			}
		}
		return found < t.length()? "": s.substring(minLo, minHi + 1);
	}
}
