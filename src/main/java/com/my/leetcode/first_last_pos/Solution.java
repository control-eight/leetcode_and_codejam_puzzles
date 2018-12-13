package com.my.leetcode.first_last_pos;

import java.util.Arrays;

public class Solution {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().searchRange(new int[] {}, 0)));
        System.out.println(Arrays.toString(new Solution().searchRange(new int[] {1,4}, 4)));
        System.out.println(Arrays.toString(new Solution().searchRange(new int[] {1,3}, 1)));
        System.out.println(Arrays.toString(new Solution().searchRange(new int[] {5,7,7,8,8,10}, 8)));
        System.out.println(Arrays.toString(new Solution().searchRange(new int[] {5,7,7,8,8,10}, 6)));
    }

    public int[] searchRange(int[] nums, int target) {
        if (nums.length == 0) return new int[] {-1, -1};
        if (nums[0] == target && nums[nums.length - 1] == target) return new int[] {0, nums.length - 1};

        int res = Arrays.binarySearch(nums, target);
        if (res < 0) return new int[] {-1, -1};

        return new int[] {nums[0] == target? 0: binarySearchLeft(nums, 0, res + 1, target),
                nums[nums.length - 1] == target? nums.length -1: binarySearchRight(nums, res + 1, nums.length, target)};
    }

    private int binarySearchLeft(int[] nums, int start, int end, int target) {
        int mid = start + (end - start) / 2;
        while (start < end) {
            if (nums[mid] == target && nums[mid - 1] != target) {
                return mid;
            } else if (nums[mid] == target) {
                return binarySearchLeft(nums, start, mid, target);
            } else if (nums[mid] < target) {
                return binarySearchLeft(nums, mid, end, target);
            }
        }
        return nums[mid] == target? mid: mid + 1;
    }

    private int binarySearchRight(int[] nums, int start, int end, int target) {
        int mid = start + (end - start) / 2;
        while (start < end) {
            if (nums[mid] == target && nums[mid + 1] != target) {
                return mid;
            } else if (nums[mid] == target) {
                return binarySearchRight(nums, mid, end, target);
            } else if (nums[mid] > target) {
                return binarySearchRight(nums, start, mid, target);
            }
        }
        return nums[mid] == target? mid: mid - 1;
    }
}
