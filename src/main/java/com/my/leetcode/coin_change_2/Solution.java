package com.my.leetcode.coin_change_2;

import java.util.Arrays;

/**
 * Created by alex.bykovsky on 8/4/17.
 */
public class Solution {

	public static void main(String[] args) {
		System.out.println(new Solution().change(5, new int[] {1, 2, 3}));
		//5
		System.out.println(new Solution().change(5, new int[] {1, 2, 5}));
		//4
		System.out.println(new Solution().change(3, new int[] {2}));
		//0
		System.out.println(new Solution().change(10, new int[] {10}));
		//1
	}

	public int change(int amount, int[] coins) {

		if(coins.length == 0) return amount == 0? 1: 0;

		Arrays.sort(coins);

		int[][] help = new int[amount + 1][coins.length];
		help[0][0] = 1;

		for(int i = 1; i <= amount; i++) {
			for(int j = 0; j < coins.length; j++) {
				if(i - coins[j] >= 0) {
					for(int k = 0; k <= j; k++) {
						help[i][j] += help[i - coins[j]][k];
					}
				}
			}
		}

		int res = 0;
		for(int k = 0; k < coins.length; k++) {
			res += help[amount][k];
		}

		return res;
	}
}
