package com.my.leetcode.part_equal_subset_sum;

public class Hybrid implements CanPartition {

    public boolean canPartition(int[] nums) {
        Result result = new SolutionSuperOptB().canPartitionWrap(nums, true);
        if (result.finished) return result.result;
        return new SolutionDPOpt().canPartition(nums);
    }

    public static class Result {
        private boolean result;
        private boolean finished;

        public Result(boolean result, boolean finished) {
            this.result = result;
            this.finished = finished;
        }

        public boolean isResult() {
            return result;
        }

        public boolean isFinished() {
            return finished;
        }
    }
}
