package com.my.code_jam.y2018.round1.round1b.rounding_error;

import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * Created by alex.bykovsky on 4/29/18.
 */
public class Solution {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		for (int i = 1; i <= T; i++) {
			int N = in.nextInt();
			int L = in.nextInt();

			int[] arr = new int[L];
			for (int j = 0; j < L; j++) {
				arr[j] = in.nextInt();
			}
			int percentage = solve(N, arr);
			System.out.println("Case #" + i + ": " + percentage);
		}
	}

	private static int solve(int N, int[] arr) {

		int index = 0;
		for (int i : arr) {
			index += i;
		}
		index = N - index;

		Integer round = number.round(1, N);
		if (round > (1.0 / N) * 100.0) {
			int result = 0;
			for (int i : arr) {
				result += number.round(i, N);
 			}
			result += index * round;
			return result;
		}

		PriorityQueue<number> heap = new PriorityQueue<>();
		int result = 0;
		for (int i : arr) {
			double decimals = ((double) i / N) * 100 - Math.floor(((double) i / N) * 100);
			if (decimals < 0.5) {
				heap.offer(new number(i, decimals));
			} else {
				result += number.round(i, N);
			}
		}

		while (index > 0) {
			if (heap.isEmpty()) {
				heap.offer(new number(1, 1.0 / N * 100 - Math.floor((1.0 / N) * 100)));
			} else {
				number aNumber = heap.poll();
				aNumber.value++;
				aNumber.decimals = ((double) aNumber.value / N) * 100 - Math.floor(((double) aNumber.value / N) * 100);
				if (aNumber.decimals < 0.5) {
					heap.offer(aNumber);
				} else {
					result += number.round(aNumber.value, N);
				}
			}
			index--;
		}

		for (number aNumber : heap) {
			result += number.round(aNumber.value, N);
		}
		return result;
	}

	private static class number implements Comparable<number> {

		private int value;

		private double decimals;

		public number(int value, double decimals) {
			this.value = value;
			this.decimals = decimals;
		}

		@Override
		public int compareTo(number o) {
			return Double.compare(o.decimals, decimals);
		}

		public static Integer round(int i, int N) {
			return (int) Math.round(((double) i / N) * 100);
		}
	}
}
