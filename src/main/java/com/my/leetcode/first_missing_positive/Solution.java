package com.my.leetcode.first_missing_positive;

/**
 * @author abykovsky
 * @since 6/13/18
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().firstMissingPositive(new int[] {1, 4, 2, 0, 3, 4, 2, 4, 2}));
        System.out.println(new Solution().firstMissingPositive(new int[] {1, 2, 0}));
        System.out.println(new Solution().firstMissingPositive(new int[] {4, 3, 4, 1, 1, 4, 1, 4}));
        System.out.println(new Solution().firstMissingPositive(new int[] {1, 1}));
        System.out.println(new Solution().firstMissingPositive(new int[] {3, 4, -1, 1}));
    }

    public int firstMissingPositive(int[] nums) {
        Integer min = calculateMinPositive(nums);
        if (min == Integer.MAX_VALUE || min > 1) return 1;

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            int index = num - min;
            if (num > 0 && index < nums.length && (index == i || nums[index] != num)) {
                int tmp = nums[index];
                nums[index] = num;
                nums[i] = tmp;
                if (tmp - min < nums.length && index != i) {
                    i--;
                }
            } else {
                nums[i] = -1;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0 && nums[i] > i + 1) {
                return i;
            }
            if (nums[i] <= 0) return nums[i - 1] + 1;
        }

        return nums[nums.length - 1] + 1;
    }

    private Integer calculateMinPositive(int[] nums) {
        Integer min = Integer.MAX_VALUE;
        for (int num : nums) {
            if (num > 0) {
                min = Math.min(min, num);
            }
        }
        return min;
    }
}
