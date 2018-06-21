package com.my.code_jam.y2017.round1.round1a;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by alex.bykovsky on 4/29/18.
 */
public class Ratatouille {

	public static void main(String[] args) {
		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		int t = in.nextInt();
		for (int i = 1; i <= t; ++i) {

			int N = in.nextInt();
			int P = in.nextInt();

			int[] recipe = new int[N];
			for (int j = 1; j <= N; j++) {
				recipe[j - 1] = in.nextInt();
			}

			int[][] ingredients = new int[N][P];
			for (int ii = 1; ii <= N; ii++) {
				for (int jj = 1; jj <= P; jj++) {
					ingredients[ii - 1][jj - 1] = in.nextInt();
				}
			}

			int kits = solve(recipe, ingredients);
			System.out.println("Case #" + i + ": " + kits);
		}
	}

	private static int solve(int[] recipe, int[][] ingredients) {

		int c = ingredients[0].length;
		Pair[][] amounts = new Pair[ingredients.length][c];
		for (int j = 0; j < c; j++) {
			for (int i = 0; i < ingredients.length; i++) {
				amounts[i][j] = new Pair((int) Math.ceil(ingredients[i][j] / (recipe[i] * 1.1)),
						(int) Math.floor(ingredients[i][j] / (recipe[i] * 0.9)));
				if (amounts[i][j].left > amounts[i][j].right) {
					amounts[i][j] = null;
					break;
				}
			}
		}

		int kits = 0;
		if (recipe.length == 1) {
			for (Pair[] amount : amounts) {
				for (Pair pair : amount) {
					kits += pair != null? 1: 0;
				}
			}
			return kits;
		}

		for (Pair[] amount : amounts) {
			System.out.println(Arrays.toString(amount));
		}

		return kits;
	}

	private static class Pair {
		private int left;
		private int right;
		public Pair(int left, int right) {
			this.left = left;
			this.right = right;
		}
		@Override
		public String toString() {
			return "Pair{" +
					"left=" + left +
					", right=" + right +
					'}';
		}
	}
}
