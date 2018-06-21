package com.my.leetcode.buy_sell_stock;

/**
 * Created by alex.bykovsky on 9/5/17.
 */
public class BestTimeBuySellStockIIDP {

	public static void main(String[] args) {
		System.out.println(new BestTimeBuySellStockIIDP().maxProfit(new int[] {1,2}));
		//1
		System.out.println(new BestTimeBuySellStockIIDP().maxProfit(new int[] {1,2,4}));
		//3
		System.out.println(new BestTimeBuySellStockIIDP().maxProfit(new int[] {1,5,4,10}));
		//9
	}

	public int maxProfit(int[] prices) {

		if(prices.length <= 1) return 0;

		int buy = -prices[0];
		int sell = 0;

		for(int i = 0; i < prices.length; i++) {
			sell = Math.max(sell, buy + prices[i]);
			buy = Math.max(buy, sell - prices[i]);
		}

		return sell;
	}
}
