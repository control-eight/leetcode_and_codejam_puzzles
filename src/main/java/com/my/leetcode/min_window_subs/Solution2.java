package com.my.leetcode.min_window_subs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * Created by alex.bykovsky on 5/12/18.
 */
public class Solution2 {

	public static void main(String[] args) {
		System.out.println(new Solution2().minWindow("abcdebdde", "bde"));
		//bcde
		System.out.println(new Solution2().minWindow("acbbaca", "aba"));
		//acbba
		System.out.println(new Solution2().minWindow("bba", "ab"));
		//
		System.out.println(new Solution2().minWindow("ADOBECODEBANC", "ABC"));
		//ADOBEC
		System.out.println(new Solution2().minWindow("a", "a"));
		//a
		System.out.println(new Solution2().minWindow("ab", "a"));
		//a
		System.out.println(new Solution2().minWindow("ab", "b"));
		//b
		System.out.println(new Solution2().minWindow("a", "b"));
		//
		System.out.println(new Solution2().minWindow("cnhczmccqouqadqtmjjzl", "mm"));
		//
	}

	public String minWindow(String s, String t) {

		Queue<int[]> solution = new LinkedList<>();
		char[] chart = t.toCharArray();
		char[] chars = s.toCharArray();

		for (int j = 0; j < chars.length; j++) {
			if (chars[j] == chart[0]) {
				solution.offer(new int[]{j, j});
			}
		}

		for (int i = 1; i < chart.length; i++) {
			Queue<int[]> newSolutions = new LinkedList<>();
			while (!solution.isEmpty()) {
				int[] ss = solution.poll();
				for (int j = ss[1] + 1; j < s.length(); j++) {
					if (chars[j] == chart[i]) {
						newSolutions.offer(new int[]{ss[0], j});
						break;
					}
				}
			}
			solution = newSolutions;
		}

		if (solution.isEmpty()) return "";
		int[] min = new int[] {0, Integer.MAX_VALUE};
		for (int[] ints : solution) {
			if (ints[1] - ints[0] < min[1] - min[0]) {
				min = ints;
			}
		}
		return s.substring(min[0], min[1] + 1);
	}
}
