package com.my.code_jam.y2018.round1.round1b.transmutation;

import java.util.Scanner;

/**
 * Created by alex.bykovsky on 5/3/18.
 */
public class Solution2 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();

		for (int i = 1; i <= T; i++) {
			int M = in.nextInt();
			int[][] formulas = new int[M][2];

			for (int j = 0; j < M; j++) {
				formulas[j][0] = in.nextInt();
				formulas[j][1] = in.nextInt();
			}

			int[] cost = new int[M];
			for (int j = 0; j < M; j++) {
				cost[j] = in.nextInt();
			}
			System.out.println("Case #" + i + ": " + solve(formulas, cost));
		}
	}

	public static int solve(int[][] formulas, int[] cost) {
		boolean[] notForUse = new boolean[cost.length];
		int result = 0;
		while (solve(1, formulas, cost, notForUse)) {
			result++;
		}
		return result;
	}

	private static boolean solve(int metal, int[][] formulas, int[] cost, boolean[] notForUse) {

		if (notForUse[metal - 1]) return false;

		if (cost[metal - 1] > 0) {
			cost[metal - 1]--;
			return true;
		}

		notForUse[metal - 1] = true;

		int r1 = formulas[metal - 1][0];
		int r2 = formulas[metal - 1][1];

		if (solve(r1, formulas, cost, notForUse)) {
			if (solve(r2, formulas, cost, notForUse)) {
				notForUse[metal - 1] = false;
				return true;
			}
		}
		notForUse[metal - 1] = false;

		return false;
	}
}
