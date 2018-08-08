package com.my.leetcode.k_empty_slots;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().kEmptySlots(new int[] {1,5,3,2,4}, 0));
        System.out.println(new Solution().kEmptySlots(new int[] {10,1,6,4,2,8,9,7,5,3}, 1));
        System.out.println(new Solution().kEmptySlots(new int[] {6,5,8,9,7,1,10,2,3,4}, 2));
        System.out.println(new Solution().kEmptySlots(new int[] {1,2,3,4,5,7,6}, 1));
        System.out.println(new Solution().kEmptySlots(new int[] {9,2,3,1,4,10,7,6,5,8}, 10));
        System.out.println(new Solution().kEmptySlots(new int[] {1,3,2}, 1));
        System.out.println(new Solution().kEmptySlots(new int[] {1,2,3}, 1));
    }

    public int kEmptySlots(int[] flowers, int k) {
        if (k > flowers.length - 2) return -1;

        flowers = invert(flowers);
        //System.out.println(Arrays.toString(flowers));

        Integer result1 = ifKisZero(flowers, k);
        if (result1 != null) return result1;

        Deque<Container> deque = new LinkedList<>();
        for (int i = 1; i < k + 1; i++) {
            deque.offerLast(new Container(i, flowers[i]));
        }

        Integer result = null;
        for (int i = 0; i < flowers.length - (k + 1); i++) {
            while (!deque.isEmpty()) {
                if (deque.peekFirst().time > flowers[i] && deque.peekFirst().time > flowers[i + k + 1]) {
                    deque.pollFirst();
                } else {
                    break;
                }
            }
            if (deque.isEmpty()) {
                if (result == null) {
                    result = Math.max(flowers[i], flowers[i + k + 1]);
                } else {
                    result = Math.min(result, Math.max(flowers[i], flowers[i + k + 1]));
                }
            }
            if (!deque.isEmpty() && deque.peekFirst().pos == i + 1) {
                deque.pollFirst();
            }
            deque.offerLast(new Container(i + k + 1, flowers[i + k + 1]));
        }

        return result == null? -1: result;
    }

    private Integer ifKisZero(int[] flowers, int k) {
        if (k == 0) {
            Integer result = null;
            for (int i = 1; i < flowers.length; i++) {
                if (result == null) {
                    result = Math.max(flowers[i], flowers[i - 1]);
                } else {
                    result = Math.min(result, Math.max(flowers[i], flowers[i - 1]));
                }
            }
            return result == null? -1:result;
        }
        return null;
    }

    private int[] invert(int[] flowers) {
        int[] flowersI = new int[flowers.length];
        for (int i = 0; i < flowers.length; i++) {
            flowersI[flowers[i] - 1] = i + 1;
        }
        return flowersI;
    }

    private static class Container {
        private int pos;
        private int time;
        public Container(int pos, int time) {
            this.pos = pos;
            this.time = time;
        }
    }
}
