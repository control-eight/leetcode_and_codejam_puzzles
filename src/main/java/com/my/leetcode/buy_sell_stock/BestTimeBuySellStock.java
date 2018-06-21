package com.my.leetcode.buy_sell_stock;

/**
 * Created by alex.bykovsky on 9/5/17.
 */
public class BestTimeBuySellStock {

	public static void main(String[] args) {
		//5
		System.out.println(new BestTimeBuySellStock().maxProfit(new int[] {7, 1, 5, 3, 6, 4}));
		//0
		System.out.println(new BestTimeBuySellStock().maxProfit(new int[] {7, 6, 4, 3, 1}));
	}

	public int maxProfit(int[] prices) {

		if(prices.length <= 1) return 0;

		int minSoFar = prices[0];
		int maxProfit = 0;

		for(int i = 1; i < prices.length; i++) {
			maxProfit = Math.max(maxProfit, prices[i] - minSoFar);
			minSoFar = Math.min(minSoFar, prices[i]);
		}

		return maxProfit;
	}
}
