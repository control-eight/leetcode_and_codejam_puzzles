package com.my.leetcode.buy_sell_stock;

/**
 * Created by alex.bykovsky on 9/5/17.
 */
public class BestTimeBuySellStockDP {

	public static void main(String[] args) {
		//5
		System.out.println(new BestTimeBuySellStockDP().maxProfit(new int[] {7, 1, 5, 3, 6, 4}));
		//0
		System.out.println(new BestTimeBuySellStockDP().maxProfit(new int[] {7, 6, 4, 3, 1}));
		//9
		System.out.println(new BestTimeBuySellStockDP().maxProfit(new int[] {1,5,4,10}));
		//8
		System.out.println(new BestTimeBuySellStockDP().maxProfit(new int[] {3,10,1,9}));
	}

	public int maxProfit(int[] prices) {

		if(prices.length <= 1) return 0;

		int buy = -prices[0];
		int sell = 0;

		for(int i = 0; i < prices.length; i++) {
			sell = Math.max(sell, buy + prices[i]);
			buy = Math.max(buy, -prices[i]);
		}

		return sell;
	}
}
