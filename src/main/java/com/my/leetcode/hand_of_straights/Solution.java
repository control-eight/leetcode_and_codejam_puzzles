package com.my.leetcode.hand_of_straights;

import java.util.*;

public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().isNStraightHand(new int[] {1,2,3,6,2,3,4,7,8}, 3));
        System.out.println(new Solution().isNStraightHand(new int[] {1,2,3,4,5}, 4));
        System.out.println(new Solution().isNStraightHand(new int[] {2,1}, 2));
        System.out.println(new Solution().isNStraightHand(new int[] {5,1}, 1));
        System.out.println(new Solution().isNStraightHand(new int[] {616,717}, 2));
    }

    public boolean isNStraightHand(int[] hand, int W) {
        if (hand.length % W != 0) return false;

        Arrays.sort(hand);
        LinkedHashMap<Integer, Integer> map = new LinkedHashMap<>();
        for (int i : hand) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        int w = 0;
        while (w < hand.length/W) {
            int i = 0;
            int prev = -1;
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                if (entry.getValue() > 0) {
                    if (prev != -1 && entry.getKey() - prev != 1) {
                        return false;
                    }
                    map.put(entry.getKey(), map.get(entry.getKey()) - 1);
                    prev = entry.getKey();
                    i++;
                    if (i == W) {
                        break;
                    }
                }
            }
            if (i < W) return false;
            w++;
        }
        return true;
    }
}
