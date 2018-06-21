package com.my.hackerrank.chief_hopper;

/**
 * Created by alex.bykovsky on 5/12/18.
 */
public class Solution2 {

	// Complete the chiefHopper function below.
	static int chiefHopper(int[] arr) {
		double pow = 0.5;
		double sum = 0;
		for (int i = 0; i < Math.min(18, arr.length); i++) {
			sum += arr[i] * pow;
			pow *= 0.5;
		}
		int check = (int) Math.ceil(sum);
		int prev = -1;
		for (int i : arr) {
			prev = check;
			check = check + (check - i);
		}
		return check < 0 && prev < check? (int) Math.ceil(sum) + 1: (int) Math.ceil(sum);
	}

	static long chiefHopper2(int[] arr) {
		long remainingRequiredEnergy = 0;
		for (int i = arr.length - 1; i >= 0; --i) {
			remainingRequiredEnergy = (remainingRequiredEnergy + arr[i]) / 2 + (remainingRequiredEnergy + arr[i]) % 2;
		}
		return remainingRequiredEnergy;
	}

	public static void main(String[] args) {

		System.out.println((Integer.MAX_VALUE) * 4);

		int[] arr = new int[(int) 1e5];
		int s = (int) 1e5;
		for (int i = 0; i < arr.length; i++) {
			arr[i] = s--;
		}

		long start = System.currentTimeMillis();
		for (int i = 0; i < 100000; i++) {
			Solution2.chiefHopper(arr);
		}
		System.out.println(System.currentTimeMillis() - start + "ms");

		start = System.currentTimeMillis();
		for (int i = 0; i < 100000; i++) {
			Solution2.chiefHopper2(arr);
		}
		System.out.println(System.currentTimeMillis() - start + "ms");

		start = System.currentTimeMillis();
		for (int i = 0; i < 100000; i++) {
			Solution2.chiefHopper(arr);
		}
		System.out.println(System.currentTimeMillis() - start + "ms");

		start = System.currentTimeMillis();
		for (int i = 0; i < 100000; i++) {
			Solution2.chiefHopper2(arr);
		}
		System.out.println(System.currentTimeMillis() - start + "ms");
	}
}
