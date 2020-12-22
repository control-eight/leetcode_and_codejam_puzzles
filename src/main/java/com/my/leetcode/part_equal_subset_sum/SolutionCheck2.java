package com.my.leetcode.part_equal_subset_sum;

import java.util.Arrays;

public class SolutionCheck2 {

    public static void main(String[] args) {
        // warm up
        warmUp();

        //test1();
        test2();

    }

    private static void warmUp() {
        int[] numsGood = new int[]{
                94, 95,     // = 189
                60, 63, 66, // = 189
                3, 6, 9, 12
        };
        canPartitionTimedEx(numsGood, 1_000_000, true);
        int[] numsBad = new int[]{
                94, 96,     // = 190
                60, 63, 66, // = 189
                3, 6, 9, 12
        };
        canPartitionTimedEx(numsBad, 1_000_000, true);
    }

    private static void test1() {
        int[] baseGood = new int[]{
                4, 5,     // = 9
                0, 3, 6,  // = 9
        };
        for (int j = 0; j < 10; j++) {
            int[] nums = new int[baseGood.length + 4 * j];
            System.arraycopy(baseGood, 0, nums, 0, baseGood.length);
            int add1 = 3 * 4 * j + 9;
            int add2 = 2 * 4 * j + 6;
            if (2 * add1 != 3 * add2) {
                throw new RuntimeException("AAAAAAAA");
            }

            nums[0] += add1;
            nums[1] += add1;
            nums[2] += add2;
            nums[3] += add2;
            nums[4] += add2;

            for (int i = 0, pos = baseGood.length; i < 4 * j; i++, pos++) {
                nums[pos] = 3 * (i + 1);
            }
            canPartitionTimed(nums);
        }
    }

    private static void test2() {
        int[] baseGood = new int[]{
                10,
                3, 7
        };
        // 10 makes it run for too long
        // even 6 is noticeably long
        for (int j = 0; j < 10; j++) {
        //for (int j = 0; j < 5; j++) {
            int[] nums = new int[baseGood.length + 4 * j];
            System.arraycopy(baseGood, 0, nums, 0, baseGood.length);
            int add1 = 2 * 3 * 4 * j + 12;
            int add2 = 3 * 4 * j + 6;
            if (add1 != 2 * add2) {
                throw new RuntimeException("AAAAAAAA");
            }

            nums[0] += add1;
            nums[1] += add2;
            nums[2] += add2;

            for (int i = 0, pos = baseGood.length; i < 4 * j; i++, pos++) {
                nums[pos] = 3 * (i + 1);
            }
            canPartitionTimed(nums);
        }
    }


    public static void canPartitionTimed(int[] nums) {
        canPartitionTimedEx(nums, 1, false);
    }

    public static void canPartitionTimedEx(int[] nums, int times, boolean skipLogs) {
        int[] copy = Arrays.copyOf(nums, nums.length);
        boolean res = canPartition(copy);
        final long start = System.nanoTime();
//        final int times = 100;
        for (int i = 0; i < times; i++) {
            res |= canPartition(copy);
        }
        final long end = System.nanoTime();
        if (!skipLogs) {
            System.out.printf("Partitioned in %,3d times = %,3d, _dbgCnt = %,3d, res = %s, len = %d: %s\n",
                    (end - start), times, _dbgCnt,
//                    res, nums.length, Arrays.toString(nums));
                    res, nums.length, Arrays.toString(copy));
//            System.out.println(Arrays.toString(_dbgSolution));
        }
    }

    static int _dbgCnt = 0;
    static int[] _dbgSolution;
    static int _dbgSolutionPos;

    public static boolean canPartition(int[] nums) {
        _dbgCnt = 0;
        _dbgSolution = new int[nums.length];
        _dbgSolutionPos = 0;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 == 1) return false;
        Arrays.sort(nums);
        int[] prefixSums = new int[nums.length];
        prefixSums[0] = nums[0];
        for (int i = 1; i < prefixSums.length; i++) {
            prefixSums[i] = prefixSums[i - 1] + nums[i];
        }
        return dfs(nums, sum/2, nums.length - 1, prefixSums);
    }

    private static boolean dfs(int[] nums, int target, int index, int[] prefixSums) {
        _dbgCnt++;
        if (target == 0) return true;
        if(target < 0 || index < 0) return false;
        index = Arrays.binarySearch(nums, 0, index + 1, target);
        if(index >= 0) {
            return true;
        } else if(index == -1) {
            return false;
        } else {
            index = - (index + 2);
            if (prefixSums[index] < target) return false;

            _dbgSolution[_dbgSolutionPos] = nums[index];
            _dbgSolutionPos++;
            if (dfs(nums, target - nums[index], index - 1, prefixSums)) {
                return true;
            }
            _dbgSolutionPos--;
            _dbgSolution[_dbgSolutionPos] = 0;
            return dfs(nums, target, index - 1, prefixSums);
        }
    }
}
