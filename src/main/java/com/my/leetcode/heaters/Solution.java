package com.my.leetcode.heaters;

public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().findRadius(new int[] {1,2,3}, new int[] {2}));
        System.out.println(new Solution().findRadius(new int[] {1,2,3,4}, new int[] {1,4}));
        System.out.println(new Solution().findRadius(new int[] {1,2,3,4,5}, new int[] {3}));
        System.out.println(new Solution().findRadius(new int[] {1,5}, new int[] {2}));
        System.out.println(new Solution().findRadius(new int[] {1,2,3}, new int[] {1,2,3}));
    }

    public int findRadius(int[] houses, int[] heaters) {
        int res = 0;

        int prev = heaters[0];
        int next = heaters.length > 1? heaters[1]: -1;
        int j = 1;

        for (int i = 0; i < houses.length; i++) {
            int el = houses[i];
            if (j <= heaters.length - 1 && el > next) {
                prev = next;
                next = heaters[j + 1];
                j++;
            }
            if (el <= prev) {
                res = Math.max(Math.abs(el - prev), res);
            } else {
                res = next == -1? Math.max(Math.abs(el - prev), res): Math.min(Math.max(Math.abs(el - prev), res),
                        Math.max(Math.abs(el - next), res));
            }
        }

        return res;
    }
}
