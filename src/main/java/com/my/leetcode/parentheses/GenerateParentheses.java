package com.my.leetcode.parentheses;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex.bykovsky on 9/27/17.
 */
public class GenerateParentheses {

	public static void main(String[] args) {
		System.out.println(new GenerateParentheses().generateParenthesis(3));
	}

	public List<String> generateParenthesis(int n) {
		List<String> results = new ArrayList<>(n);
		generateParenthesis(2 * n - 1, n, 1, 0, "(", results);
		return results;
	}

	private void generateParenthesis(int n, int N, int openCount, int closedCount, String current, List<String> results) {

		if(n == 0) {
			results.add(current);
			return;
		}

		if(closedCount < openCount) {
			generateParenthesis(n - 1, N, openCount, closedCount + 1, current + ")", results);
		}

		if(openCount < N) {
			generateParenthesis(n - 1, N, openCount + 1, closedCount, current + "(", results);
		}
	}
}
