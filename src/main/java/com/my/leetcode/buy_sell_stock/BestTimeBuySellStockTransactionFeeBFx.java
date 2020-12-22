package com.my.leetcode.buy_sell_stock;

/**
 * Created by alex.bykovsky on 10/24/17.
 */
public class BestTimeBuySellStockTransactionFeeBFx {

	public static void main(String[] args) {
		//System.out.println(new BestTimeBuySellStockTransactionFeeBFx().maxProfit(new int[] {1}, 2));
		//System.out.println(new BestTimeBuySellStockTransactionFeeBFx().maxProfit(new int[] {1, 4}, 2));
		System.out.println(new BestTimeBuySellStockTransactionFeeBFx().maxProfit(new int[] {1, 4, 9}, 2));
		/*System.out.println(new BestTimeBuySellStockTransactionFeeBFx().maxProfit(new int[] {1, 4, 9, 8}, 2));
		System.out.println(new BestTimeBuySellStockTransactionFeeBFx().maxProfit(new int[] {1, 3, 2, 8, 4}, 2));
		System.out.println(new BestTimeBuySellStockTransactionFeeBFx().maxProfit(new int[] {1, 3, 2, 8, 4, 9}, 2));
		System.out.println(new BestTimeBuySellStockTransactionFeeBFx().maxProfit(new int[] {1, 3, 2, 8, 4, 9, 10, 1}, 2));
		System.out.println(new BestTimeBuySellStockTransactionFeeBFx().maxProfit(new int[] {1, 3, 2, 8, 4, 9, 10, 1, 12, 14}, 2));
		System.out.println(new BestTimeBuySellStockTransactionFeeBFx().maxProfit(new int[] {1, 3, 2, 8, 4, 9, 10, 1, 12, 14, 1, 0}, 2));
		System.out.println(new BestTimeBuySellStockTransactionFeeBFx().maxProfit(new int[] {1, 3, 2, 8, 4, 9, 10, 1, 12, 14, 1, 0, 1, 12}, 2));
		System.out.println(new BestTimeBuySellStockTransactionFeeBFx().maxProfit(new int[] {1, 3, 2, 8, 4, 9, 10, 1, 12, 14, 1, 0, 1, 12, 1, 7}, 2));
		System.out.println(new BestTimeBuySellStockTransactionFeeBFx().maxProfit(new int[] {1, 3, 2, 8, 4, 9, 10, 1, 12, 14, 1, 0, 1, 12, 1, 7, 5, 6}, 2));*/
	}

	private int count = 0;

	public int maxProfit(int[] prices, int fee) {
		if(prices.length <= 1) return 0;
		return solve(prices, 0, fee);
	}

	private int solve(int[] prices, int lastSelldDay, int fee) {
		if (lastSelldDay >= prices.length - 1) return 0;
		int max = 0;
		for (int buyDay = lastSelldDay; buyDay < prices.length; buyDay++) {
			for (int sellDay = buyDay + 1; sellDay < prices.length; sellDay++) {
				int profit = prices[sellDay] - prices[buyDay] - fee;
				max = Math.max(profit + solve(prices, sellDay, fee), max);
			}
		}
		return max;
	}
}