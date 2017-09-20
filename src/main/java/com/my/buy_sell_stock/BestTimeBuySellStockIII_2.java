package com.my.buy_sell_stock;

/**
 * Created by alex.bykovsky on 9/5/17.
 */
public class BestTimeBuySellStockIII_2 {

	//0 1 15 6 50

	public static void main(String[] args) {
		System.out.println(new BestTimeBuySellStockIII_2().maxProfit(new int[] {6,5,4,8,6,8,7,8,9,4,5}));
		//7
		System.out.println(new BestTimeBuySellStockIII_2().maxProfit(new int[] {4, 2, 1}));
		//0
		System.out.println(new BestTimeBuySellStockIII_2().maxProfit(new int[] {3, 2, 6, 5, 0, 3}));
		//7
		System.out.println(new BestTimeBuySellStockIII_2().maxProfit(new int[] {4,8,6,8,7,8,9,20}));
		//18
		System.out.println(new BestTimeBuySellStockIII_2().maxProfit(new int[] {1, 10, 0, 20}));
		//29
		System.out.println(new BestTimeBuySellStockIII_2().maxProfit(new int[] {0, 100, 200, 1000}));
		//1000
		System.out.println(new BestTimeBuySellStockIII_2().maxProfit(new int[] {0, 50, 0, 50}));
		//100
		System.out.println(new BestTimeBuySellStockIII_2().maxProfit(new int[] {0, 5, 2, 5}));
		//8
		System.out.println(new BestTimeBuySellStockIII_2().maxProfit(new int[] {1, 2}));
		//1
		System.out.println(new BestTimeBuySellStockIII_2().maxProfit(new int[] {6, 1, 3, 2, 4, 7}));
		//7
		System.out.println(new BestTimeBuySellStockIII_2().maxProfit(new int[] {2, 1, 4, 5, 2, 9, 7}));
		//11
		System.out.println(new BestTimeBuySellStockIII_2().maxProfit(new int[] {1, 2, 4, 2, 5, 7, 2, 4, 9, 0}));
		//13 = 1, 2, 4, 2, 5, 7 + 2, 4, 9
		System.out.println(new BestTimeBuySellStockIII_2().maxProfit(new int[] {3, 3, 5, 0, 0, 3, 1, 4}));
		//6
		System.out.println(new BestTimeBuySellStockIII_2().maxProfit(new int[] {4, 7, 2, 1, 11}));
		//13
		System.out.println(new BestTimeBuySellStockIII_2().maxProfit(new int[] {1, 4, 1, 4, 3, 1}));
		//6
		System.out.println(new BestTimeBuySellStockIII_2().maxProfit(new int[] {1, 7, 4, 2}));
		//6
		System.out.println(new BestTimeBuySellStockIII_2().maxProfit(new int[] {7, 4, 2, 1}));
		//0
		System.out.println(new BestTimeBuySellStockIII_2().maxProfit(new int[] {8,6,4,3,3,2,3,5,8,3,8,2,6}));
		//11
		System.out.println(new BestTimeBuySellStockIII_2().maxProfit(new int[] {1,3,5,4,3,7,6,9,2,4}));
		//10
		System.out.println(new BestTimeBuySellStockIII_2().maxProfit(new int[] {5,7,2,7,3,3,5,3,0}));
		//7
		System.out.println(new BestTimeBuySellStockIII_2().maxProfit(new int[] {5,5,4,9,3,8,5,5,1,6,8,3,4}));
		//12
		System.out.println(new BestTimeBuySellStockIII_2().maxProfit(new int[] {8,3,6,2,8,8,8,4,2,0,7,2,9,4,9}));
		//15
		System.out.println(new BestTimeBuySellStockIII_2().maxProfit(new int[] {1,4,2,9,4,3}));
		//10
		System.out.println(new BestTimeBuySellStockIII_2().maxProfit(new int[] {1,2,4,2,5,7,2,4,9,0,9}));
		//17
		System.out.println(new BestTimeBuySellStockIII_2().maxProfit(new int[] {2,6,8,7,8,7,9,4,1,2,4,5,8}));
		//14
		System.out.println(new BestTimeBuySellStockIII_2().maxProfit(new int[] {5,2,3,2,6,6,2,9,1,0,7,4,5,0}));
		//14
	}

	public int maxProfit(int[] prices) {

		if(prices.length <= 1) return 0;

		int[] one = new int[] {0, 0};
		int[] two = new int[] {0, 0};

		int[] next = new int[] {0, 0};

		for(int i = 1; i < prices.length; i++) {

			int diff = prices[i] - prices[i - 1];

			if(diff > 0) {
				next[1] = i;
			} else if(diff < 0) {

				//possible join
				boolean operation = false;
				if(next[1] > next[0]) {

					int sum = prices[next[1]] - prices[one[0]] + prices[two[1]] - prices[two[0]];
					if(sum >
							Math.max(prices[one[1]] - prices[one[0]], prices[two[1]] - prices[two[0]]) + prices[next[1]] - prices[next[0]]
							&& sum > prices[one[1]] - prices[one[0]] + prices[two[1]] - prices[two[0]]) {
						//join
						one[1] = next[1];
						operation = true;
					} else if((prices[next[1]] - prices[next[0]]) > Math.min(prices[one[1]] - prices[one[0]], prices[two[1]] - prices[two[0]])) {
						//switch
						if(prices[one[1]] - prices[one[0]] > prices[two[1]] - prices[two[0]]) {

							if(prices[two[0]] < prices[one[0]]) {
								one[0] = two[0];
							}

							two[0] = next[0];
							two[1] = next[1];

							int[] temp = one;
							one = two;
							two = temp;
						} else {

							if(prices[one[1]] > prices[two[1]]) {
								two[1] = one[1];
							}

							one[0] = next[0];
							one[1] = next[1];
						}
						operation = true;
					}
				}

				if(operation || prices[i] < prices[next[0]]) {
					next[0] = i;
				}
			}
		}

		//last step
		//possible join
		if(next[1] > next[0]) {
			if (prices[next[1]] - prices[one[0]] + prices[two[1]] - prices[two[0]] >
					Math.max(prices[one[1]] - prices[one[0]], prices[two[1]] - prices[two[0]]) + prices[next[1]] - prices[next[0]]
					&& prices[next[1]] - prices[one[0]] + prices[two[1]] - prices[two[0]] >
					prices[one[1]] - prices[one[0]] + prices[two[1]] - prices[two[0]]) {
				//join
				one[1] = next[1];
			} else if((prices[next[1]] - prices[next[0]]) > Math.min(prices[one[1]] - prices[one[0]], prices[two[1]] - prices[two[0]])) {
				//switch
				if (prices[one[1]] - prices[one[0]] > prices[two[1]] - prices[two[0]]) {

					if(prices[two[0]] < prices[one[0]]) {
						one[0] = two[0];
					}

					two[0] = next[0];
					two[1] = next[1];

					int[] temp = one;
					one = two;
					two = temp;
				} else {

					if(prices[one[1]] > prices[two[1]]) {
						two[1] = one[1];
					}

					one[0] = next[0];
					one[1] = next[1];
				}
			}
		}


		return (one[0] != -1? prices[one[1]] - prices[one[0]]: 0) + (two[0] != -1? prices[two[1]] - prices[two[0]]: 0);
	}
























}
