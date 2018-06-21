package com.my.leetcode.coin_change;

/**
 * Created by alex.bykovsky on 8/3/17.
 */
public class Solution4 {

	public static void main(String[] args) {
		System.out.println(new Solution4().coinChange(new int[] {1, 2, 5}, 11));
		//3
		System.out.println(new Solution4().coinChange(new int[] {2}, 3));
		//-1
		System.out.println(new Solution4().coinChange(new int[] {1, 2, 5}, 15));
		//3
		System.out.println(new Solution4().coinChange(new int[] {1}, 0));
		//
		System.out.println(new Solution4().coinChange(new int[] {2}, 1));
		//0
		System.out.println(new Solution4().coinChange(new int[] {3,7,405,436}, 8839));
		//25
		System.out.println(new Solution4().coinChange(new int[] {389,46,222,352,4,250}, 5343));
		//16
		System.out.println(new Solution4().coinChange(new int[] {470,35,120,81,121}, 9825));
		//30
		System.out.println(new Solution4().coinChange(new int[] {438,86,218,138,358,152,129}, 7656));
		//19
	}

	public int coinChange(int[] coins, int amount) {

		if(amount == 0) return 0;

		int[] help = new int[amount + 1];
		help[0] = 1;

		for(int i = 1; i <= amount; i++) {
			for (int coin : coins) {
				int diff = i - coin;
				if(diff >= 0 && help[diff] != 0) {
					help[i] = help[i] == 0? help[diff] + 1: Math.min(help[i], help[diff] + 1);
				}
			}
		}

		return help[amount] == 0? -1: help[amount] - 1;
	}


}
