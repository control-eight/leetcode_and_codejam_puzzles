package com.my.leetcode.first_last_pos;

import java.util.Arrays;

public class SolutionOpt {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new SolutionOpt().searchRange(new int[] {}, 0)));
        System.out.println(Arrays.toString(new SolutionOpt().searchRange(new int[] {1,4}, 4)));
        System.out.println(Arrays.toString(new SolutionOpt().searchRange(new int[] {1,3}, 1)));
        System.out.println(Arrays.toString(new SolutionOpt().searchRange(new int[] {5,7,7,8,8,10}, 8)));
        System.out.println(Arrays.toString(new SolutionOpt().searchRange(new int[] {5,7,7,8,8,10}, 6)));
        System.out.println(Arrays.toString(new SolutionOpt().searchRange(new int[] {0,0,1,2,3,3,4}, 2)));
        /*
        [-1, -1]
        [1, 1]
        [0, 0]
        [3, 4]
        [-1, -1]
        [3, 3]
        */
    }

    public int[] searchRange(int[] nums, int target) {
        int res = Arrays.binarySearch(nums, target);
        if (res < 0) return new int[] {-1, -1};

        int res1 = binarySearchLastRepeatedFromTheLeft(nums, 0, nums.length - 1, target - 0.01);
        int res2 = binarySearchLastRepeatedFromTheLeft(nums, 0, nums.length - 1, target + 0.01);
        return new int[] {res1, res2 - 1};
    }

    private static int binarySearchLastRepeatedFromTheLeft(int[] a, int low, int high, double key) {
        while (low <= high) {
            int mid = (low + high) / 2;
            if (a[mid] < key)
                low = mid + 1;
            else if (a[mid] > key)
                high = mid - 1;
        }
        return low;  // key not found.
    }
}
