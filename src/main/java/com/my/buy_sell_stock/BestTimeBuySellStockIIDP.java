package com.my.buy_sell_stock;

/**
 * Created by alex.bykovsky on 9/5/17.
 */
public class BestTimeBuySellStockIIDP {

	public static void main(String[] args) {
		System.out.println(new BestTimeBuySellStockIIDP().maxProfit(new int[] {1,2}));
		//1
		System.out.println(new BestTimeBuySellStockIIDP().maxProfit(new int[] {1,2,4}));
		//3
	}

	public int maxProfit(int[] prices) {

		if(prices.length <= 1) return 0;

		int[] buy = new int[prices.length];
		int[] sell = new int[prices.length];

		buy[0] = -prices[0];
		sell[0] = 0;

		for(int i = 1; i < prices.length; i++) {
			sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i]);
			buy[i] = Math.max(buy[i - 1], sell[i] - prices[i]);
		}

		return sell[prices.length - 1];
	}
}
