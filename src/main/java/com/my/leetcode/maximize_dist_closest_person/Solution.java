package com.my.leetcode.maximize_dist_closest_person;

public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().maxDistToClosest(new int[] {0,1}));
        System.out.println(new Solution().maxDistToClosest(new int[] {1,0,0,0}));
        System.out.println(new Solution().maxDistToClosest(new int[] {0,0,0,1}));
        System.out.println(new Solution().maxDistToClosest(new int[] {1,0,0,0,1,0,1}));
        System.out.println(new Solution().maxDistToClosest(new int[] {1,0,0,0}));
        System.out.println(new Solution().maxDistToClosest(new int[] {0,0,0,1}));
        System.out.println(new Solution().maxDistToClosest(new int[] {0,0,0,1,0,0,0,0}));
    }

    public int maxDistToClosest(int[] seats) {
        int longest0Seq = 0;
        int zeroStart = -1;
        int zeroEnd = -1;
        for (int i = 0; i < seats.length; i++) {
            int seat = seats[i];
            if (seat == 0) {
                if (zeroStart == -1) {
                    zeroStart = i;
                    zeroEnd = zeroStart + 1;
                } else {
                    zeroEnd++;
                }
            } else {
                longest0Seq = checkLength(seats, longest0Seq, zeroStart, zeroEnd);
                zeroStart = -1;
            }
        }
        longest0Seq = checkLength(seats, longest0Seq, zeroStart, zeroEnd);
        return (longest0Seq  % 2 == 0? longest0Seq - 1: longest0Seq) / 2 + 1;
    }

    private int checkLength(int[] seats, int longest0Seq, int zeroStart, int zeroEnd) {
        if (zeroStart > -1) {
            int length = zeroStart == 0 || zeroEnd == seats.length ?
                    (zeroEnd - zeroStart) * 2 : zeroEnd - zeroStart;
            longest0Seq = Math.max(longest0Seq, length);
        }
        return longest0Seq;
    }
}
