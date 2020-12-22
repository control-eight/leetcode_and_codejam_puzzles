package com.my.leetcode.part_equal_subset_sum;

import java.util.Arrays;
import java.util.Random;

public class SolutionDfs implements CanPartition {

    public static void main(String[] args) {
        tests();
        //generator();
        print();
    }

    private static void generator() {
        Random random = new Random(5555);
        int[] arr = new int[20];
        for (int i = 0; i < 2_000_000; i++) {
            for (int n = 0; n < arr.length; n++) {
                arr[n] = (int) (random.nextDouble() * 20) + 1;
            }
            new SolutionDfs().canPartition(arr);
        }
    }

    private static void print() {
        int[] nums = (int[]) maxV[0];
        int ops = (int) maxV[1];
        boolean result = (boolean) maxV[2];
        System.out.println("N = " + nums.length + " ops: " + ops + " result = " + result + " nums = " + Arrays.toString(nums));
    }

    private static void tests() {
        System.out.println(new SolutionDfs().canPartition(new int[] {1, 2, 5}));
        /*System.out.println(new SolutionDfs().canPartition(new int[] {3, 6, 9, 12, 15, 18, 21, 24, 27, 30, 33, 36, 39, 42, 45, 48, 57, 61, 118}));
        System.out.println(new SolutionDfs().canPartition(new int[] {3, 6, 9, 12, 15, 18, 21, 24, 27, 30, 33, 36, 39, 42, 45, 48, 51, 54, 57, 60, 69, 73, 142}));*/
        //System.out.println(new SolutionDfs().canPartition(new int[] {3, 6, 9, 12, 15, 18, 21, 24, 27, 30, 33, 36, 39, 42, 45, 48, 51, 54, 57, 60, 63, 66, 69, 72, 81, 85, 166}));
        //System.out.println(new SolutionDfs().canPartition(new int[] {4, 6, 6, 8, 8, 8, 8, 8, 10, 10, 10, 12, 14, 16, 16, 16, 16, 18, 20, 20}));
        //System.out.println(new SolutionDfs().canPartition(new int[] {2, 2, 2, 2, 2, 4, 8, 10, 12, 13, 14, 16, 16, 16, 18, 18, 18, 18, 18, 19}));
        /*System.out.println(new SolutionDfsOpt().canPartition(new int[] {0, 0, 0, 0, 0, 0, 1, 1, 1, 3, 10, 11, 12, 14, 18, 19, 19, 19, 19, 19}));
        System.out.println(new SolutionDfsOpt().canPartition(new int[] {1, 5, 11, 5}));
        System.out.println(new SolutionDfsOpt().canPartition(new int[] {1, 2, 3, 5}));
        System.out.println(new SolutionDfsOpt().canPartition(new int[] {10, 10, 10, 10}));
        System.out.println(new SolutionDfsOpt().canPartition(new int[] {10, 10, 10, 5}));
        System.out.println(new SolutionDfsOpt().canPartition(new int[] {1, 2, 5}));
        System.out.println(new SolutionDfsOpt().canPartition(new int[] {28,63,95,30,16,36,44,37,100,61,73,32,71,100,2,37,60,23,71,53,70,69,82,97,43,16,33,29,5,97,32,29,78,93,59,37,88,89,79,75,9,74,32,81,12,34,13,16,15,16,40,90,70,17,78,54,81,18,92,75,74,59,18,66,62,55,19,2,67,30,25,64,84,25,76,98,59,74,87,5,93,97,68,20,58,55,73,74,97,49,71,42,26,8,87,99,1,16,79}));
        System.out.println(new SolutionDfsOpt().canPartition(new int[] {2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,99,100,101}));
        System.out.println(new SolutionDfsOpt().canPartition(new int[] {1,1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,100}));*/
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
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 == 1) return false;
        Arrays.sort(nums);
        return dfs(nums, sum/2, nums.length - 1);
    }

    private boolean dfs(int[] nums, int target, int index) {
        ops++;
        if (target == 0) return true;
        if(target < 0 || index < 0) return false;
        index = Arrays.binarySearch(nums, 0, index + 1, target);
        if(index >= 0) {
            return true;
        } else if(index == -1) {
            return false;
        } else {
            index = - (index + 2);
            if (dfs(nums, target - nums[index], index - 1)) {
                return true;
            } else {
                return dfs(nums, target, index - 1);
            }
        }
    }
}
