package com.my.code_jam.y2018.round1.round1a.edgy_baking;

import java.util.Scanner;

/**
 * Created by alex.bykovsky on 5/4/18.
 */
public class Solution {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		for (int t = 1; t <= T; t++) {
			int N = in.nextInt();
			int P = in.nextInt();

			int[][] arr = new int[N][2];
			for (int i = 0; i < N; i++) {
				arr[i][0] = in.nextInt();
				arr[i][1] = in.nextInt();
			}
			System.out.println("Case #"+t+": "+solve(arr, P));
		}
	}

	private static double solve(int[][] arr, double P) {

		double w = arr[0][0];
		double h = arr[0][1];
		double p = 2 * w + 2 * h;
		double L = p + 2 * Math.min(w, h);
		double R = p + 2 * Math.sqrt(w*w + h*h);

		for (int k = 1; k <= arr.length; k++) {
			if ((k * L + (arr.length - k) * p) <= P && (k * R + (arr.length - k) * p) >= P) {
				return P;
			} else if ((k * L + (arr.length - k) * p) > P) {
				return (k - 1) * R + (arr.length - (k - 1)) * p;
			}
		}

		return arr.length * R < P? arr.length * R: arr.length * p;
	}
}
