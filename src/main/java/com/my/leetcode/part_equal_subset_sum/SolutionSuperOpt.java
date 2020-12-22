package com.my.leetcode.part_equal_subset_sum;

import java.util.Arrays;
import java.util.Random;

public class SolutionSuperOpt implements CanPartition {

    public static void main(String[] args) {
        tests();
        //generator();
        print();
    }

    private static void generator() {
        Random random = new Random(5555);
        int[] arr = new int[20];
        for (int i = 0; i < 1_000_000_000; i++) {
            for (int n = 0; n < arr.length; n++) {
                arr[n] = (int) (random.nextDouble() * 20) + 1;
            }
            new SolutionSuperOpt().canPartition(arr);
        }
    }

    private static void print() {
        int[] nums = (int[]) maxV[0];
        int ops = (int) maxV[1];
        boolean result = (boolean) maxV[2];
        System.out.println("N = " + nums.length + " ops: " + ops + " result = " + result + " nums = " + Arrays.toString(nums));
    }

    private static void tests() {
        //System.out.println(new SolutionSuperOpt().canPartition(new int[] {3, 6, 9, 12, 21, 25, 46}));
        //System.out.println(new SolutionSuperOpt().canPartition(new int[] {3, 6, 9, 12, 15, 18, 21, 24, 27, 30, 33, 36, 39, 42, 45, 48, 57, 61, 118}));
        //System.out.println(new SolutionSuperOpt().canPartition(new int[] {3, 6, 9, 12, 15, 18, 21, 24, 27, 30, 33, 36, 39, 42, 45, 48, 51, 54, 57, 60, 69, 73, 142}));
        System.out.println(new SolutionSuperOpt().canPartition(new int[] {3, 6, 9, 12, 15, 18, 21, 24, 27, 30, 33, 36, 39, 42, 45, 48, 51, 54, 57, 60, 63, 66, 69, 72, 81, 85, 166}));
    }

    private static int max = 0;
    private static Object[] maxV;

    public boolean canPartition(int[] nums) {
        ops = 0;
        boolean result = _canPartition(nums);
        if (ops > max) {
            max = ops;
            maxV = new Object[] {Arrays.copyOf(nums, nums.length), ops, result};
        }
        return result;
    }

    private int ops;

    public boolean _canPartition(int[] nums) {
        int sum = 0;
        for (int num: nums){
            sum += num;
        }
        if (sum % 2 != 0){
            return false;
        }
        Arrays.sort(nums);
        int[] prefixSums = new int[nums.length];
        prefixSums[0] = nums[0];
        for (int i = 1; i < prefixSums.length; i++) {
            prefixSums[i] = prefixSums[i - 1] + nums[i];
        }
        return dfs(nums, sum / 2, nums.length - 1, prefixSums);
    }

    private boolean dfs(int[] nums, int target, int index, int[] prefixSums) {
        ops++;
        if (target == 0){
            return true;
        }
        if (index < 0 || target < 0 || prefixSums[index] < target){
            return false;
        }
        // skip elements greater than a reminder
        while (index >= 0 && nums[index] > target) {
            index--;
        }
        if (index < 0) {
            return false;
        }
        if (dfs(nums, target - nums[index], index - 1, prefixSums)){
            return true;
        }
        // skip equal elements in a row
        index--;
        while (index >= 0 && nums[index] == nums[index + 1]){
            index--;
        }
        return dfs(nums, target, index, prefixSums);
    }
}
