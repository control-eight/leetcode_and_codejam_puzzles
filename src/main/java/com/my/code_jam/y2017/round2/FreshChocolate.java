package com.my.code_jam.y2017.round2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Created by alex.bykovsky on 5/8/18.
 */
public class FreshChocolate {

	public static void main(String[] args) {
		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		int T = in.nextInt();
		for (int t = 1; t <= T; ++t) {

			int N = in.nextInt();
			int P = in.nextInt();

			int[] arr = new int[N];
			for (int i = 0; i < N; i++) {
				arr[i] = in.nextInt();
			}
			System.out.println("Case #" + t + ": " + solve(arr, P));
		}
	}

	private static int solve(int[] arr, int P) {
		int[] modArr = new int[P];
		for (int i = 0; i < arr.length; i++) {
			modArr[arr[i] % P]++;
		}
		if (P == 2) {
			return (int) (modArr[0] + Math.ceil(modArr[1] / 2.0));
		} else if (P == 3) {
			int result = modArr[0] + Math.min(modArr[1], modArr[2]);
			if (modArr[1] > modArr[2]) {
				result += Math.ceil((modArr[1] - modArr[2]) / 3.0);
			} else {
				result += Math.ceil((modArr[2] - modArr[1]) / 3.0);
			}
			return result;
		//P == 4
		} else {
			int result = modArr[0];
			result += modArr[2] / 2;
			result += Math.min(modArr[1], modArr[3]);
			result += Math.ceil((2 * (modArr[2] % 2) + Math.abs(modArr[3] - modArr[1]))/4.0);
			return result;
		}
	}
}
