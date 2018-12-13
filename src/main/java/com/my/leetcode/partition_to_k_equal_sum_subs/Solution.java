package com.my.leetcode.partition_to_k_equal_sum_subs;

import java.util.*;

public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().canPartitionKSubsets(new int[] {4, 3, 2, 3, 5, 2, 1}, 4));
        System.out.println(new Solution().canPartitionKSubsets(new int[] {17, 1}, 2));
        System.out.println(new Solution().canPartitionKSubsets(new int[] {605,454,322,218,8,19,651,2220,175,
                710,2666,350,252,2264,327,1843}, 4));
    }

    public boolean canPartitionKSubsets(int[] nums, int k) {
        if (nums.length == 0) return k == 0;

        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % k > 0) return false;

        List<Integer> arr = new ArrayList<>();
        for (int num : nums) {
            arr.add(num);
        }
        return canPartitionKSubsets(arr, k, sum / k, 0, new HashSet<>());
    }

    private boolean canPartitionKSubsets(List<Integer> arr, int k, int target, int current, Set<Key> cache) {
        if (k == 0) return true;

        for (int i = 0; i < arr.size(); i++) {
            int newCurrent = current + arr.get(i);

            if (newCurrent <= target) {
                Integer tmp = arr.remove(i);

                Key key = new Key(arr, newCurrent == target ? k - 1 : k, target, newCurrent == target ? 0 : newCurrent);
                if (cache.contains(key)) {
                    arr.add(i, tmp);
                    continue;
                }

                boolean res = canPartitionKSubsets(arr, newCurrent == target ? k - 1 : k, target, newCurrent == target ? 0 : newCurrent, cache);
                if (res) {
                    return true;
                } else {
                    cache.add(key);
                }
                arr.add(i, tmp);
            }
        }
        return false;
    }

    private static class Key {
        private List<Integer> arr;
        private int k;
        private int target;
        private int current;

        public Key(List<Integer> arr, int k, int target, int current) {
            this.arr = new ArrayList<>(arr);
            this.k = k;
            this.target = target;
            this.current = current;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Key key = (Key) o;
            return k == key.k &&
                    target == key.target &&
                    current == key.current &&
                    Objects.equals(arr, key.arr);
        }

        @Override
        public int hashCode() {
            return Objects.hash(arr, k, target, current);
        }
    }
}