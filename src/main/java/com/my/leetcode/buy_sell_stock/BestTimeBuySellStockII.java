package com.my.leetcode.buy_sell_stock;

/**
 * Created by alex.bykovsky on 9/5/17.
 */
public class BestTimeBuySellStockII {

	public static void main(String[] args) {
		System.out.println(new BestTimeBuySellStockII().maxProfit(new int[] {1,2}));
		System.out.println(new BestTimeBuySellStockII().maxProfit(new int[] {1,2,4}));
	}

	public int maxProfit(int[] prices) {

		int maxProfit = 0;
		int last = -1;

		for(int i = 0; i < prices.length - 1; i++) {

			if(last > -1) {
				maxProfit += prices[i] - last;
			}

			if(prices[i + 1] > prices[i]) {
				last = prices[i];
			} else {
				last = -1;
			}
		}

		if(last > -1) {
			maxProfit += prices[prices.length - 1] - last;
		}

		return maxProfit;
	}
}
