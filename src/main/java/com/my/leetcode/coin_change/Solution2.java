package com.my.leetcode.coin_change;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by alex.bykovsky on 8/3/17.
 */
public class Solution2 {

	public static void main(String[] args) {
		System.out.println(new Solution2().change(5, new int[] {1, 2, 5}));
		//5=5
		//5=2+2+1
		//5=2+1+1+1
		//5=1+1+1+1+1
		//4
		System.out.println(new Solution2().change(11, new int[] {1, 2, 5}));
		//3
		System.out.println(new Solution2().change(3, new int[] {2}));
		//-1
		System.out.println(new Solution2().change(15, new int[] {1, 2, 5}));
		//
		long start1 = System.currentTimeMillis();
		System.out.println(new Solution2().change(500, new int[] {3,5,7,8,9,10,11}));
		System.out.println((System.currentTimeMillis() - start1) + "ms");
		//
		long start2 = System.currentTimeMillis();
		System.out.println(new Solution2().change(5000, new int[] {1}));
		System.out.println((System.currentTimeMillis() - start2) + "ms");
	}

	private Map<Key, Integer> cache = new HashMap<>();

	public int change(int amount, int[] coins) {

		List<Integer> list = new ArrayList<>();
		for (int coin : coins) {
			list.add(coin);
		}

		return coinChange(list, amount);
	}

	private int coinChange(List<Integer> coins, int amount) {

		Key key = new Key(coins, amount);
		if(cache.containsKey(key)) {
			return cache.get(key);
		}

		if(amount == 0) {
			return 1;
		} else if(amount < 0 || coins.isEmpty()) {
			return 0;
		}

		int result = coinChange(substruct(coins, 0), amount);
		result += coinChange(coins, amount - coins.get(0));

		if(!cache.containsKey(key)) {
			cache.put(key, result);
		}

		return result;
	}

	private List<Integer> substruct(List<Integer> coins, int index) {

		List<Integer> newList = new ArrayList<>();
		newList.addAll(coins);
		newList.remove(index);

		return newList;
	}

	private static class Key {

		private List<Integer> coins;

		private int amount;

		public Key(List<Integer> coins, int amount) {
			this.coins = coins;
			this.amount = amount;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;

			Key key = (Key) o;

			if (amount != key.amount) return false;
			return coins != null ? coins.equals(key.coins) : key.coins == null;
		}

		@Override
		public int hashCode() {
			int result = coins != null ? coins.hashCode() : 0;
			result = 31 * result + amount;
			return result;
		}
	}
}
