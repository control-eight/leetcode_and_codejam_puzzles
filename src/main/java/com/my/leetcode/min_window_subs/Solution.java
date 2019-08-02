package com.my.leetcode.min_window_subs;

/**
 * Created by alex.bykovsky on 5/12/18.
 */
public class Solution {

	public static void main(String[] args) {
		System.out.println(new Solution().minWindow("abcdebdde", "bde"));
		//bcde
		System.out.println(new Solution().minWindow("acbbaca", "aba"));
		//acbba
		System.out.println(new Solution().minWindow("bba", "ab"));
		//
		System.out.println(new Solution().minWindow("ADOBECODEBANC", "ABC"));
		//ADOBEC
		System.out.println(new Solution().minWindow("a", "a"));
		//a
		System.out.println(new Solution().minWindow("ab", "a"));
		//a
		System.out.println(new Solution().minWindow("ab", "b"));
		//b
		System.out.println(new Solution().minWindow("a", "b"));
		//
	}

	public String minWindow(String s, String t) {
		int minI = 0; int minJ = Integer.MAX_VALUE;
		for (int i = 0; i < s.length(); i++) {
			int matchPtr = 0;
			int j = i;
			for (; j < s.length() && matchPtr < t.length(); j++) {
				if (t.charAt(matchPtr) == s.charAt(j)) {
					matchPtr++;
				}
			}
			if (matchPtr == t.length() && (j - i) < (minJ - minI)) {
				minI = i;
				minJ = j;
			}
		}
		return minJ == Integer.MAX_VALUE? "": s.substring(minI, minJ);
	}
}
