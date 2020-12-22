package com.my.leetcode.part_equal_subset_sum;

public class SolutionDPOpt implements CanPartition {

    public static void main(String[] args) {
        System.out.println(new SolutionDPOpt().canPartition(new int[] {1, 5, 11, 5}));
        System.out.println(new SolutionDPOpt().canPartition(new int[] {1, 2, 3, 5}));
        System.out.println(new SolutionDPOpt().canPartition(new int[] {10, 10, 10, 10}));
        System.out.println(new SolutionDPOpt().canPartition(new int[] {10, 10, 10, 5}));
        System.out.println(new SolutionDPOpt().canPartition(new int[] {1, 2, 5}));
        System.out.println(new SolutionDPOpt().canPartition(new int[] {28,63,95,30,39,16,36,44,37,100,61,73,32,71,100,2,37,60,23,71,53,70,69,82,97,43,16,33,29,5,97,32,29,78,93,59,37,88,89,79,75,9,74,32,81,12,34,13,16,15,16,40,90,70,17,78,54,81,18,92,75,74,59,18,66,62,55,19,2,67,30,25,64,84,25,76,98,59,74,87,5,93,97,68,20,58,55,73,74,97,49,71,42,26,8,87,99,1,16,79}));
    }

    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 == 1) return false;

        boolean[] dp = new boolean[sum/2 + 1];
        dp[0] = true;
        int maxSumSoFar = 0;
        for (int coin : nums) {
            maxSumSoFar += coin;
            int end = Math.min(sum/2, maxSumSoFar);
            for (int i = end; i >= coin; i--) {
                dp[i] |= dp[i - coin];
            }
            if (dp[sum/2]) return true;
        }
        return false;
    }
}
