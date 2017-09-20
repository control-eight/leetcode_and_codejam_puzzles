package com.my.buy_sell_stock;

import java.util.Arrays;

/**
 * Created by alex.bykovsky on 9/5/17.
 */
public class BestTimeBuySellStockIII {

	public static void main(String[] args) {
		System.out.println(new BestTimeBuySellStockIII().maxProfit(new int[] {1, 10, 0, 20}));
		//29
		System.out.println(new BestTimeBuySellStockIII().maxProfit(new int[] {0, 100, 200, 1000}));
		//1000
		System.out.println(new BestTimeBuySellStockIII().maxProfit(new int[] {0, 50, 0, 50}));
		//100
		System.out.println(new BestTimeBuySellStockIII().maxProfit(new int[] {1, 2}));
		//1
		System.out.println(new BestTimeBuySellStockIII().maxProfit(new int[] {3, 2, 6, 5, 0, 3}));
		//7
	}

	public int maxProfit(int[] prices) {

		if(prices.length <= 1) return 0;

		int[] mins = new int[prices.length];
		int[] maxs = new int[prices.length];

		mins[0] = prices[0];
		maxs[prices.length - 1] = prices[prices.length - 1];

		for(int i = 1; i < prices.length; i++) {
			if(prices[i] < mins[i - 1]) {
				mins[i] = prices[i];
			} else {
				mins[i] = mins[i - 1];
			}
		}

		for(int i = prices.length - 2; i >= 0; i--) {
			if(prices[i] > maxs[i + 1]) {
				maxs[i] = prices[i];
			} else {
				maxs[i] = maxs[i + 1];
			}
		}

		int max = 0;
		int maxLeft = 0;

		for(int i = 1; i < prices.length - 2; i++) {
			maxLeft = Math.max(prices[i] - mins[i - 1], maxLeft);
			int right = maxs[i + 2] - prices[i + 1];
			max = Math.max(max, maxLeft + right);
		}

		return Math.max(max, maxProfitOne(prices));
	}

	private int maxProfitOne(int[] prices) {

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
