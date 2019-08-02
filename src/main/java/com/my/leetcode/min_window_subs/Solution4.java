package com.my.leetcode.min_window_subs;

import java.util.*;

/**
 * Created by alex.bykovsky on 5/12/18.
 */
public class Solution4 {

	public static void main(String[] args) {
		System.out.println(new Solution4().minWindow("abcdebdde", "bde"));
		//bcde
		System.out.println(new Solution4().minWindow("acbbaca", "aba"));
		//acbba
		System.out.println(new Solution4().minWindow("bba", "ab"));
		//
		System.out.println(new Solution4().minWindow("ADOBECODEBANC", "ABC"));
		//ADOBEC
		System.out.println(new Solution4().minWindow("a", "a"));
		//a
		System.out.println(new Solution4().minWindow("ab", "a"));
		//a
		System.out.println(new Solution4().minWindow("ab", "b"));
		//b
		System.out.println(new Solution4().minWindow("a", "b"));
		//
		System.out.println(new Solution4().minWindow("cnhczmccqouqadqtmjjzl", "mm"));
		//mccqouqadqtm
	}

	public String minWindow(String s, String t) {

		char[] chart = t.toCharArray();
		char[] chars = s.toCharArray();

		List<int[]> solutions = new ArrayList<>();
		for (int i = 0; i < chars.length; i++) {
			for (int[] ints : solutions) {
				if (ints[2] < chart.length && chars[i] == chart[ints[2]]) {
					ints[1] = i;
					ints[2]++;
				}
			}
			if (chars[i] == chart[0]) {
				solutions.add(new int[] {i, i, 1});
			}
		}

		int[] min = new int[] {0, Integer.MAX_VALUE};
		for (int[] ints : solutions) {
			if (ints[2] == chart.length && ints[1] - ints[0] < min[1] - min[0]) {
				min = ints;
			}
		}
		return min[1] == Integer.MAX_VALUE? "": s.substring(min[0], min[1] + 1);
	}
}
