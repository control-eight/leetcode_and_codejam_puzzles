package com.my.leetcode.read_n_char_read4_I;

public class Solution extends Reader4 {

    /**
     * @param buf Destination buffer
     * @param n   Number of characters to read
     * @return The number of actual characters read
     */
    public int read(char[] buf, int n) {
        char[] buf4 = new char[4];
        int result = 0;
        while (result < n) {
            int read = read4(buf4);
            for (int i = 0; i < read && result < n; i++, result++) {
                buf[result] = buf4[i];
            }
            if (read < 4) {
                break;
            }
        }
        return Math.min(result, n);
    }
}
