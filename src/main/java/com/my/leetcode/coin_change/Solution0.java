package com.my.leetcode.coin_change;

import java.util.HashMap;
import java.util.Map;

public class Solution0 {

    public int coinChange(int[] coins, int amount) {
        int result = solve(coins, amount);
        return result >= 1000000? -1: result;
    }

    private Map<Integer, Integer> cache = new HashMap<>();

    int solve(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        if (amount < 0) {
            return 1000000;
        }
        if (cache.get(amount) != null) {
            return cache.get(amount);
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < coins.length; i++) {
            min = Math.min(min, solve(coins, amount - coins[i]) + 1);
        }
        cache.put(amount, min);
        return min;
    }
}
