package com.my.leetcode.find_k_closest_elements;

import java.util.*;

public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().findClosestElements(new int[] {1}, 1, 1));
        //
        System.out.println(new Solution().findClosestElements(new int[] {1,2,3,4,5}, 4, 3));
        //[1,2,3,4]
        System.out.println(new Solution().findClosestElements(new int[] {1,2,3,4,5}, 4, -1));
        //[1,2,3,4]
        System.out.println(new Solution().findClosestElements(new int[] {0,1,1,1,2,3,6,7,8,9}, 9, 4));
        //[0,1,1,1,2,3,6,7,8]
        System.out.println(new Solution().findClosestElements(new int[] {0,1,1,1,2,3,6,7,8,9}, 5, 9));
    }

    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int index = Arrays.binarySearch(arr, x);
        if (index < 0) {
            index = Math.abs(index) - 1;
        }

        int lo = index - 1;
        int hi = index;

        Deque<Integer> loDeque = new LinkedList<>();
        LinkedList<Integer> hiList = new LinkedList<>();

        while ((hi - lo) <= k) {
            if (hi == arr.length || lo >= 0 && Math.abs(arr[lo] - x) <= Math.abs(arr[hi] - x)) {
                loDeque.offerFirst(arr[lo--]);
            } else if (hi < arr.length) {
                hiList.add(arr[hi++]);
            }
        }
        loDeque.addAll(hiList);
        return (List<Integer>) loDeque;
    }
}
