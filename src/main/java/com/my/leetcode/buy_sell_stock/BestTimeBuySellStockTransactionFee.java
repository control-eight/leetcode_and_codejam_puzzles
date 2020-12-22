package com.my.leetcode.buy_sell_stock;

/**
 * Created by alex.bykovsky on 10/24/17.
 */
public class BestTimeBuySellStockTransactionFee {

	public static void main(String[] args) {
		System.out.println(new BestTimeBuySellStockTransactionFee().maxProfit(new int[] {1, 3, 2, 8, 4, 9}, 2));
		System.out.println(new BestTimeBuySellStockTransactionFee().maxProfit(new int[] {1, 4, 2, 5}, 2));
		System.out.println(new BestTimeBuySellStockTransactionFee().maxProfit(new int[] {1, 4, 9, 10}, 2));
	}

	public int maxProfit(int[] prices, int fee) {

		if(prices.length <= 1) return 0;

		int buy = -prices[0];
		int sell = 0;

		for(int i = 1; i < prices.length; i++) {
			sell = Math.max(sell, buy + prices[i] - fee);
			buy = Math.max(buy, sell - prices[i]);
		}

		return sell;
	}
}
