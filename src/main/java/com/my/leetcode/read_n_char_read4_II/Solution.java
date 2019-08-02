package com.my.leetcode.read_n_char_read4_II;

import java.util.Arrays;

/**
 * The read4 API is defined in the parent class Reader4.
 *     int read4(char[] buf);
 */
public class Solution extends Reader4 {

    private char[] buff = new char[4];
    private int buffPtr;
    private int buffCount;

    public int read(char[] buf, int n) {
        int ptr = 0;
        while (ptr < n) {
            if (buffPtr == 0) buffCount = read4(buff);
            if (buffCount == 0) break;
            int length = Math.min(n - ptr, buffCount - buffPtr);
            System.arraycopy(buff, buffPtr, buf, ptr, length);
            ptr += length;
            buffPtr += length;
            if (buffPtr == buffCount) buffPtr = 0;
        }
        return ptr;
    }
}