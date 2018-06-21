package com.my.leetcode.coin_change;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by alex.bykovsky on 8/3/17.
 */
public class Solution {

	public static void main(String[] args) {
		System.out.println(new Solution().coinChange(new int[] {1, 2, 5}, 11));
		//3
		System.out.println(new Solution().coinChange(new int[] {2}, 3));
		//-1
		System.out.println(new Solution().coinChange(new int[] {1, 2, 5}, 15));
		//3
		System.out.println(new Solution().coinChange(new int[] {1}, 0));
		//0
		System.out.println(new Solution().coinChange(new int[] {3,7,405,436}, 8839));
		//
		System.out.println(new Solution().coinChange(new int[] {389,46,222,352,4,250}, 5343));
		//
	}

	private Map<Key, Integer> cached = new HashMap<>();

	private Integer bestSoFar = Integer.MAX_VALUE;

	public int coinChange(int[] coins, int amount) {

		if(amount == 0) return 0;

		List<Integer> list = new ArrayList<>();
		for (int coin : coins) {
			list.add(coin);
		}

		int result = coinChange(list, amount, 0);
		return result == 0? -1: result;
	}

	private int coinChange(List<Integer> coins, int amount, int coinsAmount) {

		if(coinsAmount >= bestSoFar) return -1;

		if(amount == 0) {
			return coinsAmount;
		} else if(amount < 0 || coins.isEmpty()) {
			return -1;
		}

		Key key = new Key(coins, amount, coinsAmount);

		if(cached.containsKey(key)) {
			return cached.get(key);
		}

		int result1 = coinChange(substruct(coins, 0), amount, coinsAmount);
		int result2 = coinChange(coins, amount - coins.get(0), coinsAmount + 1);

		int res = result1 == -1? result2: result2 == -1? result1: Math.min(result1, result2);

		if(res != -1) {
			bestSoFar = Math.min(bestSoFar, res);
		}

		if(!cached.containsKey(key) || cached.get(key) > res) {
			cached.put(key, res);
		}

		return res;
	}

	private List<Integer> substruct(List<Integer> coins, int index) {

		List<Integer> newList = new ArrayList<>();
		newList.addAll(coins);
		newList.remove(index);

		return newList;
	}

	private static class Key {

		private List<Integer> coins;

		private Integer amount;

		private Integer coinsAmount;

		public Key(List<Integer> coins, Integer amount, Integer coinsAmount) {
			this.coins = coins;
			this.amount = amount;
			this.coinsAmount = coinsAmount;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;

			Key key = (Key) o;

			if (coins != null ? !coins.equals(key.coins) : key.coins != null) return false;
			if (amount != null ? !amount.equals(key.amount) : key.amount != null) return false;
			return coinsAmount != null ? coinsAmount.equals(key.coinsAmount) : key.coinsAmount == null;
		}

		@Override
		public int hashCode() {
			int result = coins != null ? coins.hashCode() : 0;
			result = 31 * result + (amount != null ? amount.hashCode() : 0);
			result = 31 * result + (coinsAmount != null ? coinsAmount.hashCode() : 0);
			return result;
		}
	}
}
