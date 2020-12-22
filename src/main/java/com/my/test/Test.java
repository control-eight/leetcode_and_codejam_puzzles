package com.my.test;

import java.util.Arrays;

public class Test {

    public static void main(String[] args) {

    }

    public int findDuplicate(int[] nums) {
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) return nums[i];
        }
        return -1;
    }
}
