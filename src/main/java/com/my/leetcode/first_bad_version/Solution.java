package com.my.leetcode.first_bad_version;

public class Solution extends VersionControl {

    public int firstBadVersion(int n) {
        int lo = 0;
        int hi = n;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (isBadVersion(mid)) {
                hi = mid;
            } else {
                lo = mid;
            }

        }
        return isBadVersion(lo)? lo: hi;
    }
}
