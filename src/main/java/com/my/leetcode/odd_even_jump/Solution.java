package com.my.leetcode.odd_even_jump;

import java.util.TreeMap;
import java.util.TreeSet;

public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().oddEvenJumps(new int[] {10,13,12,14,15}));
        System.out.println(new Solution().oddEvenJumps(new int[] {2,3,1,1,4}));
        System.out.println(new Solution().oddEvenJumps(new int[] {5,1,3,4,2}));
        System.out.println(new Solution().oddEvenJumps(new int[] {1,2,3,2,1,4,4,5}));
    }

    public int oddEvenJumps(int[] A) {

        int[] index = new int[100000];

        boolean[] evenJumpSolutions = new boolean[A.length];
        boolean[] oddJumpSolutions = new boolean[A.length];

        evenJumpSolutions[A.length - 1] = true;
        oddJumpSolutions[A.length - 1] = true;
        index[A[A.length - 1]] = A.length - 1;

        TreeSet<Integer> treeMap = new TreeSet<>();
        treeMap.add(A[A.length - 1]);

        for (int i = A.length - 2; i >= 0; i--) {
            Integer higherKey = treeMap.higher(A[i] - 1);
            Integer lowerKey = treeMap.lower(A[i] + 1);
            if (higherKey == null) {
                oddJumpSolutions[i] = false;
            } else {
                oddJumpSolutions[i] = evenJumpSolutions[index[higherKey]];
            }
            if (lowerKey == null) {
                evenJumpSolutions[i] = false;
            } else {
                evenJumpSolutions[i] = oddJumpSolutions[index[lowerKey]];
            }
            treeMap.add(A[i]);
            index[A[i]] = i;
        }
        int result = 0;
        for (int i = 0; i < A.length; i++) {
            if (oddJumpSolutions[i]) {
                result++;
            }
        }
        return result;
    }
}
