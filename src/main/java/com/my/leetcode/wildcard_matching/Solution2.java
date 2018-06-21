package com.my.leetcode.wildcard_matching;

/**
 * Created by alex.bykovsky on 5/10/18.
 */
public class Solution2 {

	public static void main(String[] args) {
		System.out.println(new Solution2().isMatch("adceb", "*a*b"));
		System.out.println(new Solution2().isMatch("aa", "a"));
		System.out.println(new Solution2().isMatch("aa", "*"));
	}


	public boolean isMatch(String s, String p) {
		return isMatch(s, p, 0, 0, new Result[s.length() + 1][p.length() + 1]);
	}

	private boolean isMatch(String s, String p, int i, int j, Result[][] solutions) {
		if (j == p.length()) {
			return i == s.length();
		} else {
			if (solutions[i][j] != null) {
				return solutions[i][j] == Result.TRUE;
			}
			boolean sEmpty = i == s.length();
			boolean result;
			if (p.charAt(j) == '*') {
				result = isMatch(s, p, i, j + 1, solutions) ||
						!sEmpty && (isMatch(s, p, i + 1, j, solutions) ||
								isMatch(s, p, i + 1, j + 1, solutions));
			} else {
				result = !sEmpty && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?')
						&& isMatch(s, p, i + 1, j + 1, solutions);
			}
			solutions[i][j] = result? Result.TRUE: Result.FALSE;
			return result;
		}
	}

	enum Result {
		TRUE,
		FALSE
	}
}
