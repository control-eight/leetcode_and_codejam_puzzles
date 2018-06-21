package com.my.code_jam.y2018.round1.round1b.transmutation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by alex.bykovsky on 5/3/18.
 */
public class Solution3 {

	private static int invokeCount;

	public static void main(String[] args) {
		int G = 100;
		for (int M = 3; M <= 20; M++) {
			int[] cost = new int[M];
			for (int i = 0; i < M; i++) {
				cost[i] = G;
			}
			Random random = new Random((long) 1e6);
			int maxInvokeCount = 0;

			for(long i = 0; i < 1e7; i++) {
				List<int[]> formulas = new ArrayList<>();
				for (int j = 0; j < M; j++) {
					formulas.add(new int[2]);
					formulas.get(formulas.size() - 1)[0] = (int) ((M - 1) * random.nextDouble()) + 2;
					formulas.get(formulas.size() - 1)[1] = (int) ((M - 1) * random.nextDouble()) + 2;
				}
				solve(formulas, cost);
				maxInvokeCount = Math.max(maxInvokeCount, invokeCount);
				invokeCount = 0;
			}
			/*for (int i = 1; i <= M; i++) {
				generateAndSolve(new ArrayList<>(), M, 0, 0,i, i,  cost);
			}*/
			System.out.println("M = " + M + ", " + maxInvokeCount);
		}
	}

	private static void generateAndSolve(List<int[]> formulas, int M, int index, int indexOneTwo, int valueOne, int valueTwo, int[] cost) {
		if (index == M) {
			solve(formulas, cost);
		} else {
			if (indexOneTwo == 0) {
				List<int[]> newFormulas = new ArrayList<>();
				newFormulas.addAll(formulas);
				newFormulas.add(new int[2]);
				newFormulas.get(index)[0] = valueOne;
				generateAndSolve(newFormulas, M, index, 1, valueOne + 1, valueTwo, cost);
			} else {
				List<int[]> newFormulas = new ArrayList<>();
				newFormulas.addAll(formulas);
				newFormulas.get(index)[1] = valueTwo;
				generateAndSolve(newFormulas, M, index + 1, 0, valueOne, valueTwo + 1, cost);
			}
		}
	}

	public static int solve(List<int[]> formulas, int[] cost) {
		boolean[] notForUse = new boolean[cost.length];
		int result = 0;
		while (solve(1, formulas, cost, notForUse)) {
			result++;
		}
		return result;
	}

	private static boolean solve(int metal, List<int[]> formulas, int[] cost, boolean[] notForUse) {

		invokeCount++;

		if (notForUse[metal - 1]) return false;

		if (cost[metal - 1] > 0) {
			cost[metal - 1]--;
			return true;
		}

		notForUse[metal - 1] = true;

		int r1 = formulas.get(metal - 1)[0];
		int r2 = formulas.get(metal - 1)[1];

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
