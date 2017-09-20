package com.my.coin_change;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by alex.bykovsky on 8/3/17.
 */
public class Solution1 {

	public static void main(String[] args) {
		System.out.println(new Solution1().change(5, new int[] {1, 2, 5}));
		//5=5
		//5=2+2+1
		//5=2+1+1+1
		//5=1+1+1+1+1
		//4
		System.out.println(new Solution1().change(11, new int[] {1, 2, 5}));
		//3
		System.out.println(new Solution1().change(3, new int[] {2}));
		//-1
		System.out.println(new Solution1().change(15, new int[] {1, 2, 5}));
		//
		System.out.println(new Solution1().change(500, new int[] {3,5,7,8,9,10,11}));
	}

	public int change(int amount, int[] coins) {

		List<Integer> list = new ArrayList<>();
		for (int coin : coins) {
			list.add(coin);
		}

		return coinChange(list, amount);
	}

	private int coinChange(List<Integer> coins, int amount) {

		if(amount == 0) {
			return 1;
		} else if(amount < 0 || coins.isEmpty()) {
			return 0;
		}

		int result = coinChange(substruct(coins, 0), amount);
		result += coinChange(coins, amount - coins.get(0));

		return result;
	}

	private List<Integer> substruct(List<Integer> coins, int index) {

		List<Integer> newList = new ArrayList<>();
		newList.addAll(coins);
		newList.remove(index);

		return newList;
	}
}
