package com.my.leetcode.swap_adjacent_in_lr_string;

public class Solution {

    public static void main(String[] args) {
        /*System.out.println(new Solution().canTransform("RX", "XR"));
        System.out.println(new Solution().canTransform("RXXL", "XRLX"));
        System.out.println(new Solution().canTransform("RXXLRX", "XRLXXR"));
        System.out.println(new Solution().canTransform("RXXLRXR", "XRLXXRR"));
        System.out.println(new Solution().canTransform("RXXLRXRXL", "XRLXXRRLX"));
        System.out.println(new Solution().canTransform("RLX", "RXL"));
        System.out.println(new Solution().canTransform("XXRXXLXXXX", "XXXXRXXLXX"));
        System.out.println(new Solution().canTransform("XXLXRXRXXR", "LRXXRXXRXX"));
        System.out.println(new Solution().canTransform("XLXRRXXRXX", "LXXXXXXRRR"));
        System.out.println(new Solution().canTransform("XXXXXLXXXX", "LXXXXXXXXX"));*/
        System.out.println(new Solution().canTransform("XLLR", "LXLX"));
    }

    public boolean canTransform(String start, String end) {
        char[] start1 = start.toCharArray();
        char[] end1 = end.toCharArray();
        if (!checkOrder(start1, end1)) return false;
        int countR = 0;
        for (int i = 0; i < start1.length; i++) {
            if (start1[i] == 'R') countR++;
            if (end1[i] == 'R') countR--;
            if (countR < 0) return false;
        }
        int countL = 0;
        for (int i = start1.length - 1; i >= 0; i--) {
            if (start1[i] == 'L') countL++;
            if (end1[i] == 'L') countL--;
            if (countL < 0) return false;
        }
        return countL == 0;
    }

    private boolean checkOrder(char[] start, char[] end) {
        int s = 0;
        for (int e = 0; e < end.length; e++) {
            if (end[e] != 'X') {
                boolean found = false;
                while (s < end.length) {
                    if (start[s] != 'X') {
                        if (start[s] != end[e]) return false;
                        else {
                            found = true;
                            s++;
                            break;
                        }
                    } else {
                        s++;
                    }
                }
                if (!found) return false;
            }
        }
        while (s < end.length) {
            if (start[s++] != 'X') return false;
        }
        return true;
    }
}
