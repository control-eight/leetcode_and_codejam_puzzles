package com.my.coin_change_2;

import java.util.Arrays;

/**
 * Created by alex.bykovsky on 8/4/17.
 */
public class Solution1 {

	public static void main(String[] args) {
		System.out.println(new Solution1().change(5, new int[] {1, 2, 5}));
		//4
		System.out.println(new Solution1().change(5, new int[] {1, 2, 3}));
		//5
		System.out.println(new Solution1().change(3, new int[] {2}));
		//0
		System.out.println(new Solution1().change(10, new int[] {10}));
		//1
	}

	public int change(int amount, int[] coins) {

		if(coins.length == 0) return amount == 0? 1: 0;

		Arrays.sort(coins);

		int[][] help = new int[amount + 1][coins.length + 1];
		Arrays.fill(help[0], 1);

		for(int i = 1; i <= amount; i++) {
			for(int j = 1; j <= coins.length; j++) {
				int prev = i - coins[j - 1];
				if(prev >= 0) {
					help[i][j] += help[prev][j];
				}
				help[i][j] += help[i][j - 1];
			}
		}

		return help[amount][coins.length];
	}
}
