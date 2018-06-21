package com.my.leetcode.wildcard_matching;

/**
 * Created by alex.bykovsky on 5/10/18.
 */
public class Solution {

	public static void main(String[] args) {
		System.out.println(new Solution().isMatch("adceb", "*a*b"));
		System.out.println(new Solution().isMatch("aa", "a"));
		System.out.println(new Solution().isMatch("aa", "*"));
	}

	public boolean isMatch(String s, String p) {
		boolean[] prev = new boolean[p.length() + 1];
		boolean[] current = new boolean[p.length() + 1];
		current[p.length()] = true;

		for (int i = s.length(); i >= 0; i--) {
			for (int j = p.length() - 1; j >= 0; j--) {
				if (p.charAt(j) == '*') {
					current[j] = current[j + 1] || i < s.length() && prev[j + 1] || i < s.length() && prev[j];
				} else {
					boolean match = i < s.length() && ('?' == p.charAt(j) || s.charAt(i) == p.charAt(j));
					current[j] = match && prev[j + 1];
				}
			}
			prev = current;
			current = new boolean[p.length() + 1];
		}
		return prev[0];
	}
}
