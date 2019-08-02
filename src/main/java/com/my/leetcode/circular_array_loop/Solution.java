package com.my.leetcode.circular_array_loop;

import java.util.HashSet;
import java.util.Set;

public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().circularArrayLoop(new int[] {2,-1,1,2,2}));
        //true
        System.out.println(new Solution().circularArrayLoop(new int[] {-1,2}));
        //false
        System.out.println(new Solution().circularArrayLoop(new int[] {-2,1,-1,-2,-2}));
        //false
        System.out.println(new Solution().circularArrayLoop(new int[] {1,1}));
        //true
        System.out.println(new Solution().circularArrayLoop(new int[] {-1}));
        //false
    }

    public boolean circularArrayLoop(int[] nums) {
        Set<Integer> globalVisitedFwd = new HashSet<>();
        Set<Integer> globalVisitedBwd = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            Set<Integer> visited = new HashSet<>();
            visited.add(i);
            int prev = i;
            boolean forwardMovement = nums[prev] >= 0;
            if (!add(prev, forwardMovement, globalVisitedBwd, globalVisitedFwd)) continue;
            while (true) {
                if (forwardMovement != nums[prev] >= 0) {
                    break;
                }
                int next = wrap(prev, prev + nums[prev], nums.length);
                if (next == prev) break;
                if (!visited.add(next)) {
                    return true;
                }
                if (!add(next, forwardMovement, globalVisitedBwd, globalVisitedFwd)) break;
                prev = next;
            }
        }
        return false;
    }

    private boolean add(int next, boolean forwardMovement, Set<Integer> globalVisitedBwd, Set<Integer> globalVisitedFwd) {
        return forwardMovement? globalVisitedFwd.add(next): globalVisitedBwd.add(next);
    }

    private int wrap(int prev, int i, int length) {
        if (i < 0 && Math.abs(i) % length == 0) {
            return prev;
        }
        return i < 0? length - (-i % length): i >= length? i % length: i;
    }
}
