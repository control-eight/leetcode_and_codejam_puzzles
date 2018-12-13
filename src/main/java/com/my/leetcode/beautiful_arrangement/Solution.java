package com.my.leetcode.beautiful_arrangement;

import java.util.Arrays;

public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().countArrangement(1));
        //1
        System.out.println(new Solution().countArrangement(2));
        //2
        System.out.println(new Solution().countArrangement(3));
        //3
        System.out.println(new Solution().countArrangement(4));
        //8
        System.out.println(new Solution().countArrangement(5));
        //10
        System.out.println(new Solution().countArrangement(6));
        //36
        System.out.println(new Solution().countArrangement(7));
        //41
        System.out.println(new Solution().countArrangement(8));
        //132
    }

    public int countArrangement(int N) {
        int[] arr = new int[N];
        for (int i = 1; i <= arr.length; i++) {
            arr[i - 1] = i;
        }
        return count(arr, 0);
    }

    private int count(int[] arr, int i) {
        if (i == arr.length) {
            System.out.println(Arrays.toString(arr));
            return 1;
        }

        int count = 0;
        for (int j = i ; j < arr.length; j++) {
            if (arr[j] % (i+1) == 0 || (i+1) % arr[j] == 0) {
                swap(arr, i, j);
                count += count(arr, i + 1);
                swap(arr, j, i);
            }
        }
        return count;
    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[j];
        arr[j] = arr[i];
        arr[i] = tmp;
    }
}