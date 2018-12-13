package com.my.leetcode.sliding_window_max;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class Solution {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().maxSlidingWindow(new int[] {1,3,-1,-3,5,3,6,7}, 3)));
        //3 3 5 5 6 7
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0 || k == 0) return new int[]{};

        Deque<Integer> deque = new LinkedList<>();

        int[] result = new int[nums.length - k + 1];
        int index = 0;

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < k; i++) {
            deque.offerLast(nums[i]);
            max = Math.max(max, nums[i]);
        }
        while (deque.peekFirst() != max) {
            deque.pollFirst();
        }
        result[index] = max;

        while (index < (nums.length - k)) {
            int next = nums[index + k];

            if (deque.size() == k) {
                deque.pollFirst();
                deque.offerLast(next);
                max = Integer.MIN_VALUE;
                for (Integer integer : deque) {
                    max = Math.max(max, integer);
                }
                while (deque.peekFirst() != max) {
                    deque.pollFirst();
                }
            } else if (next > max) {
                deque.offerLast(next);
                max = next;
                while (deque.peekFirst() != max) {
                    deque.pollFirst();
                }
            } else {
                deque.offerLast(next);
            }

            index++;
            result[index] = max;
        }

        return result;
    }
}
