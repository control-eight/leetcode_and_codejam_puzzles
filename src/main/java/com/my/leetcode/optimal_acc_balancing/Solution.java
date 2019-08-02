package com.my.leetcode.optimal_acc_balancing;

import java.util.*;

public class Solution {

    public static void main(String[] args) {
        /*System.out.println(new Solution().minTransfers(new int[][]{
                {10,11,6}, {12,13,7}, {14,15,2}, {14,16,2}, {14,17,2}, {14,18,2}
        }));
        //6
        System.out.println(new Solution().minTransfers(new int[][]{
                {0, 1, 10}, {2, 0, 5}
        }));
        //2
        System.out.println(new Solution().minTransfers(new int[][]{
                {0, 1, 10}, {1, 0, 1}, {1, 2, 5}, {2, 0, 5}
        }));
        //1
        System.out.println(new Solution().minTransfers(new int[][]{
                {2,0,5}, {3,4,4}
        }));
        //2
        System.out.println(new Solution().minTransfers(new int[][]{
                {1,5,8}, {8,9,8}, {2,3,9}, {4,3,1}
        }));
        //4
        System.out.println(new Solution().minTransfers(new int[][]{
                {0,2,4}, {1,2,4}, {3,4,5}
        }));
        //3*/
        System.out.println(new Solution().minTransfers(new int[][]{
                {1,0,4}, {0,3,1}, {2,3,1}, {3,4,4}
        }));
        //3
    }

    public int minTransfers(int[][] transactions) {
        Map<Integer, Integer> acc = new HashMap<>();
        for (int[] transaction : transactions) {
            acc.put(transaction[0], acc.getOrDefault(transaction[0], 0) - transaction[2]);
            acc.put(transaction[1], acc.getOrDefault(transaction[1], 0) + transaction[2]);
        }
        List<Integer> deb = new ArrayList<>();
        List<Integer> cre = new ArrayList<>();
        for (Integer value : acc.values()) {
            if (value > 0) {
                deb.add(value);
            } else if (value < 0) {
                cre.add(-value);
            }
        }

        int result = 0;

        if (cre.size() > deb.size()) {
            List<Integer> tmp = cre;
            cre = deb;
            deb = tmp;
        }

        Collections.sort(cre, (o1, o2) -> Integer.compare(o2, o1));

        for (Integer integer : cre) {
            List<Value> bestSplit = findBestSplit(integer, deb);
            for (Value value : bestSplit) {
                deb.set(value.i, value.value);
            }
            for (int i = 0; i < deb.size(); i++) {
                if (deb.get(i) == 0) {
                    deb.remove(i);
                    i--;
                }
            }
            result += bestSplit.size();
        }
        return result;
    }

    private List<Value> findBestSplit(int target, List<Integer> deb) {
        if (target == 0) {
            return new ArrayList<>();
        }
        int size = Integer.MAX_VALUE;
        int nonZero = Integer.MAX_VALUE;
        List<Value> result = new ArrayList<>();
        for (int i = 0; i < deb.size(); i++) {
            Integer v = deb.remove(i);
            List<Value> r = findBestSplit(target < v? 0: target - v, deb);
            r.add(new Value(i, target > v? 0: v - target));
            if (calcNonZero(r) <= nonZero && r.size() <= size) {
                result = r;
                size = result.size();
                nonZero = calcNonZero(r);
            }
            deb.add(i, v);
        }
        return result;
    }

    private int calcNonZero(List<Value> r) {
        int c = 0;
        for (Value value : r) {
            if (value.value != 0) c++;
        }
        return c;
    }

    private static class Value {
        private int i;
        private int value;

        public Value(int i, int value) {
            this.i = i;
            this.value = value;
        }
    }
}
