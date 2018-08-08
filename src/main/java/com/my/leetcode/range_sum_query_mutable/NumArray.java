package com.my.leetcode.range_sum_query_mutable;

public class NumArray {

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3,4,5};
        NumArray numArray = new NumArray(nums);
        System.out.println(numArray.sumRange(0, 4));
        System.out.println(numArray.sumRange(2, 3));
        System.out.println(numArray.sumRange(1, 3));
        numArray.update(2, 10);
        System.out.println(numArray.sumRange(0, 4));
        System.out.println(numArray.sumRange(2, 3));
        System.out.println(numArray.sumRange(1, 3));

        int[] nums2 = new int[]{1,3,5};
        NumArray numArray2 = new NumArray(nums2);
        System.out.println(numArray2.sumRange(0, 2));
        numArray2.update(1, 2);
        System.out.println(numArray2.sumRange(0, 2));
    }

    private int[] tree;

    private int[] nums;

    public NumArray(int[] nums) {
        this.nums = new int[nums.length];
        this.tree = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            update(i, nums[i]);
        }
        this.nums = nums;
    }

    public void update(int i, int val) {
        int diff = val - nums[i];
        nums[i] = val;
        i++;
        while (i < tree.length) {
            tree[i] += diff;
            i += i & (-i);
        }
    }

    private int sumRange(int i) {
        if (i < 0) return 0;
        i++;
        int result = 0;
        while (i > 0) {
            result += tree[i];
            i -= i & (-i);
        }
        return result;
    }

    public int sumRange(int i, int j) {
        return sumRange(j) - sumRange(i - 1);
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(i,val);
 * int param_2 = obj.sumRange(i,j);
 */
