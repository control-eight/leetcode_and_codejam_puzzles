package com.my.leetcode.fruit_into_baskets;

public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().totalFruit(new int[] {1,2,1}));
        System.out.println(new Solution().totalFruit(new int[] {0,1,2,2}));
        System.out.println(new Solution().totalFruit(new int[] {1,2,3,2,2}));
        System.out.println(new Solution().totalFruit(new int[] {3,3,3,1,2,1,1,2,3,3,4}));
    }

    public int totalFruit(int[] tree) {
        int typeOne = -1;
        int typeTwo = -1;
        int sum = 0;
        int lastType = -1;
        int lastTypeSum = 0;
        int max = 0;
        for (int i = 0; i < tree.length; i++) {

            if (typeOne == -1) {
                typeOne = tree[i];
            } else if (typeTwo == -1) {
                if (tree[i] != typeOne) {
                    typeTwo = tree[i];
                }
            } else if (tree[i] != typeOne && tree[i] != typeTwo) {
                if (typeTwo == lastType) {
                    typeOne = typeTwo;
                }
                typeTwo = tree[i];
                max = Math.max(max, sum);
                sum = lastTypeSum;
            }

            if (lastType == -1) {
                lastType = tree[i];
                lastTypeSum = 1;
            } else {
                if (lastType == tree[i]) {
                    lastTypeSum++;
                } else {
                    lastType = tree[i];
                    lastTypeSum = 1;
                }
            }
            sum++;
        }
        max = Math.max(max, sum);
        return max;
    }
}
