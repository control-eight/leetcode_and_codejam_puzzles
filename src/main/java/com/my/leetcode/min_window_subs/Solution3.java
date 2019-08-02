package com.my.leetcode.min_window_subs;

import java.util.*;

/**
 * Created by alex.bykovsky on 5/12/18.
 */
public class Solution3 {

	public static void main(String[] args) {
		System.out.println(new Solution3().minWindow("abcdebdde", "bde"));
		//bcde
		System.out.println(new Solution3().minWindow("acbbaca", "aba"));
		//acbba
		System.out.println(new Solution3().minWindow("bba", "ab"));
		//
		System.out.println(new Solution3().minWindow("ADOBECODEBANC", "ABC"));
		//ADOBEC
		System.out.println(new Solution3().minWindow("a", "a"));
		//a
		System.out.println(new Solution3().minWindow("ab", "a"));
		//a
		System.out.println(new Solution3().minWindow("ab", "b"));
		//b
		System.out.println(new Solution3().minWindow("a", "b"));
		//
		System.out.println(new Solution3().minWindow("cnhczmccqouqadqtmjjzl", "mm"));
		//mccqouqadqtm
	}

	public String minWindow(String s, String t) {

		Queue<int[]> solution = new LinkedList<>();
		char[] chart = t.toCharArray();
		char[] chars = s.toCharArray();

		Map<Character, List<Integer>> nextChar = new HashMap<>();
		for (int j = 0; j < chars.length; j++) {
			if (chars[j] == chart[0]) {
				solution.offer(new int[]{j, j});
			}
			nextChar.putIfAbsent(chars[j], new ArrayList<>());
			nextChar.get(chars[j]).add(j);
		}

		for (int i = 1; i < chart.length; i++) {
			Queue<int[]> newSolutions = new LinkedList<>();
			while (!solution.isEmpty()) {
				int[] ss = solution.poll();
				int j = findCharNextAfter(chart[i], ss[1], nextChar);
				if (j != -1) {
					newSolutions.offer(new int[]{ss[0], j});
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

	private int findCharNextAfter(char c, int after, Map<Character, List<Integer>> nextChar) {
		List<Integer> integers = nextChar.get(c);
		return binarySearch(integers, after, 0, integers.size());
	}

	private int binarySearch(List<Integer> integers, int after, int lo, int hi) {
		int mid;
		while (lo < hi - 1) {
			mid = lo + (hi - lo)/2;
			if (integers.get(mid) <= after) {
				lo = mid;
			} else {
				hi = mid;
			}
		}
		mid = lo + (hi - lo)/2;
		return integers.get(mid) <= after? (mid == integers.size() - 1? -1: integers.get(mid + 1)): integers.get(mid);
	}
}
