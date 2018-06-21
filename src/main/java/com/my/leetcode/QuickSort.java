package com.my.leetcode;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by alex.bykovsky on 7/9/17.
 */
public class QuickSort {

	//5, 5, 5, 5, 5, 5, 5, 5, 5, 5
	//0, 1, 2, 3, 4, 5, 6, 7, 8, 9

	private static int steps;

	private static int lengths;

	public static void main(String[] args) {
		/*int[] arr = new int[]{15,50,2,25,8,0,1,14,49,12,5};
		new QuickSort().sort(arr, 0, arr.length);
		System.out.println(Arrays.toString(arr));*/

		int[] arr = new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27};
		//int[] arr = new int[]{27,26,25,24,23,22,21,20,19,18,17,16,15,14,13,12,11,10,9,8,7,6,5,4,3,2,1};
		//int[] arr = new int[]{15,50,2,25,8,0,1,14,49,12,5,81,31,5,39,21};
		//int[] arr = new int[]{5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5};
		//int[] arr = new int[]{81,50,2,25,8,0,1,14,49,12,5,15,31,5,39,21};
		new QuickSort().sort(arr, 0, arr.length);
		System.out.println(Arrays.toString(arr));
		System.out.println(steps + " vs " + (int)(Math.pow(2, Math.log(arr.length)/Math.log(2)) - 1));
		System.out.println(lengths + " vs " + (int)(Math.log(arr.length)/Math.log(2)) * arr.length + " vs " + arr.length * arr.length);

		test();
	}

	public void sort(int[] arr, int start, int end) {

		if(end - start <= 1) {
			return;
		}

		lengths += (end - start);
		steps++;

		int index = partition(arr, start, end);

		sort(arr, start, index);
		sort(arr, index, end);
	}

	private int partition(int[] arr, int start, int end) {

		int pivot = start;

		int lo = start + 1;
		int hi = end - 1;

		while(lo < hi) {
			if(arr[lo] >= arr[pivot]) {
				if(arr[hi] <= arr[pivot]) {
					swap(arr, lo, hi);
					lo++;
				}
				hi--;
			} else {
				lo++;
			}
		}
		swap(arr, pivot, arr[lo] > arr[pivot]? lo - 1: lo);

		return lo;
	}

	private void swap(int[] arr, int lo, int hi) {
		int tempValue = arr[lo];
		arr[lo] = arr[hi];
		arr[hi] = tempValue;
	}

	private static void test() {
		Random random = new Random(55);
		for(int i = 0; i < 1000; i++) {

			int length = (int) (random.nextDouble() * 2000);

			int[] origin = new int[length];
			int[] anArr1 = new int[length];
			int[] anArr2 = new int[length];
			for(int j = 0; j < length; j++) {
				anArr1[j] = (int) (random.nextDouble() * 20000);
				anArr2[j] = anArr1[j];
				origin[j] = anArr1[j];
			}
			new QuickSort().sort(anArr1, 0, anArr1.length);
			Arrays.sort(anArr2);

			if(!Arrays.equals(anArr1, anArr2)) {
				System.out.println(Arrays.toString(anArr1) + " from " + Arrays.toString(origin));
			}
		}
	}
}
