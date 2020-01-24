package com.my.leetcode.friends_of_appropriate_ages;

import java.util.Arrays;

public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().numFriendRequests(new int[] {16, 16}));
        System.out.println(new Solution().numFriendRequests(new int[] {16, 17, 18}));
        System.out.println(new Solution().numFriendRequests(new int[] {20, 30, 100, 110, 120}));
    }

    public int numFriendRequests(int[] ages) {
        Arrays.sort(ages);
        int r = 0;
        for (int i = 0; i < ages.length; i++) {
            int lo = (int) (0.5 * ages[i] + 7);
            int hi = ages[i];
            int loIndex = next(ages, lo);
            int hiIndex = prev(ages, hi + 1);
            if (loIndex > -1 && hiIndex > -1 && loIndex < hiIndex) {
                r += hiIndex - loIndex + 1;
                if (loIndex <= i && i <= hiIndex) {
                    r--;
                }
            }
        }
        return r;
    }

    private static int next(int[] arr, int target) {
        int start = 0, end = arr.length - 1;
        int ans = -1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (arr[mid] <= target) {
                start = mid + 1;
            }
            else {
                ans = mid;
                end = mid - 1;
            }
        }
        return ans;
    }

    private static int prev(int[] arr, int target) {
        int start = 0, end = arr.length - 1;
        int ans = -1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (arr[mid] < target) {
                start = mid + 1;
                ans = mid;
            }
            else {
                end = mid - 1;
            }
        }
        return ans;
    }
}
