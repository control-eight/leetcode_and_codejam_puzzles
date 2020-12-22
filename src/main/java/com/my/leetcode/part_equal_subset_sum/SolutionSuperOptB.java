package com.my.leetcode.part_equal_subset_sum;

import java.util.Arrays;
import java.util.Random;

public class SolutionSuperOptB implements CanPartition {

    public static void main(String[] args) {
        tests();
        //generator();
        print();
    }

    private static void generator() {
        Random random = new Random(5555);
        int[] arr = new int[20];
        for (int i = 0; i < 2_000_000_000; i++) {
            for (int n = 0; n < arr.length; n++) {
                arr[n] = (int) (random.nextDouble() * 20) + 1;
            }
            new SolutionSuperOptB().canPartition(arr);
        }
    }

    private static void print() {
        int[] nums = (int[]) maxV[0];
        int ops = (int) maxV[1];
        boolean result = (boolean) maxV[2];
        System.out.println("N = " + nums.length + " ops: " + ops + " result = " + result + " nums = " + Arrays.toString(nums));
    }

    private static void tests() {
        System.out.println(new SolutionSuperOptB().canPartition(new int[] {8, 8, 8, 10, 10, 10, 12, 12, 12, 14, 14, 14, 16, 16, 16, 18, 18, 18, 20, 20}));
        System.out.println(new SolutionSuperOptB().canPartition(new int[] {2, 4, 4, 4, 4, 6, 6, 8, 8, 10, 10, 12, 14, 14, 16, 16, 18, 18, 20, 20}));
        System.out.println(new SolutionSuperOptB().canPartition(new int[] {2, 2, 4, 4, 6, 6, 6, 8, 8, 10, 10, 12, 12, 14, 14, 16, 18, 18, 18, 18, 18, 20, 22, 22, 24, 24, 26, 27, 28, 29}));
        System.out.println(new SolutionSuperOptB().canPartition(new int[] {3, 6, 9, 12, 15, 18, 21, 24, 27, 30, 33, 36, 39, 42, 45, 48, 51, 54, 57, 60, 69, 73, 142}));
        System.out.println(new SolutionSuperOptB().canPartition(new int[] {3, 6, 9, 12, 15, 18, 21, 24, 27, 30, 33, 36, 39, 42, 45, 48, 51, 54, 57, 60, 63, 66, 69, 72, 81, 85, 166}));
    }

    private static int max = 0;
    private static Object[] maxV;

    public boolean canPartition(int[] nums) {
        ops = 0;
        boolean result = canPartitionWrap(nums, false).isResult();
        if (ops > max) {
            max = ops;
            maxV = new Object[] {Arrays.copyOf(nums, nums.length), ops, result};
        }
        return result;
    }

    private int ops;

    public Hybrid.Result canPartitionWrap(int[] nums, boolean limited) {
        int sum = 0;
        for (int num: nums){
            sum += num;
        }
        //first pruning
        if (sum % 2 != 0){
            return new Hybrid.Result(false, true);
        }
        //second pruning
        Arrays.sort(nums);
        int[] prefixSums = new int[nums.length];
        prefixSums[0] = nums[0];
        for (int i = 1; i < prefixSums.length; i++) {
            prefixSums[i] = prefixSums[i - 1] + nums[i];
        }

        long limit = Long.MAX_VALUE;
        if (limited) {
             limit = nums.length * sum * 2;
        }
        return dfs(nums, sum / 2, nums.length - 1, prefixSums, limit, 0);
    }

    private Hybrid.Result dfs(int[] nums, int target, int index, int[] prefixSums, final long limit, long count) {
        if (count > limit) {
            return new Hybrid.Result(false, false);
        }
        ops++;
        if (target == 0){
            return new Hybrid.Result(true, true);
        }
        //prefixSums check, third pruning
        if (index < 0 || target < 0 || prefixSums[index] < target){
            return new Hybrid.Result(false, true);
        }
        // skip elements greater than a reminder, fourth pruning
        if (nums[index] > target) {
            index = Arrays.binarySearch(nums, 0, index + 1, target);
            count += Math.log(index + 1)/Math.log(2);
            if (index >= 0) {
                return new Hybrid.Result(true, true);
            } else if (index == -1) {
                return new Hybrid.Result(false, true);
            } else {
                index = -(index + 2);
            }
            if (prefixSums[index] < target) return new Hybrid.Result(false, true);
        }
        Hybrid.Result dfs = dfs(nums, target - nums[index], index - 1, prefixSums, limit, count + 1);
        if (!dfs.isFinished()) {
            return dfs;
        }
        if (dfs.isResult()) {
            return new Hybrid.Result(true, true);
        }
        // skip equal elements in a row, fifth pruning
        if (index > 0 && nums[index - 1] == nums[index]) {
            index = binarySearchLastRepeatedFromTheLeft(nums, 0, index, nums[index] - 0.01) - 1;
            count += Math.log(index)/Math.log(2);
        } else {
            index--;
        }
        return dfs(nums, target, index, prefixSums, limit, count + 1);
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
