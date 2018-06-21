package com.my.code_jam.y2018.qualification.trouble_sort;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Created by alex.bykovsky on 4/7/18.
 */
public class Solution2 {

	public static void main(String[] args) {
		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		int t = in.nextInt();

		for (int i = 1; i <= t; ++i) {
			int N = in.nextInt();

			int[] arr = new int[N];
			for (int k = 0; k < N; k++) {
				arr[k] = in.nextInt();
			}
			solve(i, arr);
		}
	}

	private static void solve(int testCase, int[] arr) {
		boolean done = false;
		while (!done) {
			done = true;
			for (int i = 0; i < arr.length - 2; i++) {
				if (arr[i] > arr[i + 2]) {
					done = false;
					reverse(arr, i);
				}
			}
		}

		for (int i = 0; i < arr.length - 1; i++) {
			if (arr[i] > arr[i + 1]) {
				System.out.println("Case #" + testCase + ": " + i);
				return;
			}
		}
		System.out.println("Case #" + testCase + ": OK");
	}

	private static void reverse(int[] arr, int i) {
		int tmp = arr[i];
		arr[i] = arr[i + 2];
		arr[i + 2] = tmp;
	}
}


