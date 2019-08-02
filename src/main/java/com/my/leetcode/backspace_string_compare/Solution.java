package com.my.leetcode.backspace_string_compare;

public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().backspaceCompare("nzp#o#g", "b#nzp#o#g"));
        System.out.println(new Solution().backspaceCompare("ab#c", "ad#c"));
        System.out.println(new Solution().backspaceCompare("ab##", "c#d#"));
        System.out.println(new Solution().backspaceCompare("a##c", "#a#c"));
        System.out.println(new Solution().backspaceCompare("a#c", "b"));
        System.out.println(new Solution().backspaceCompare("bxj##tw", "bxj###tw"));
    }

    public boolean backspaceCompare(String S, String T) {
        int pointerS = S.length() - 1;
        int pointerT = T.length() - 1;
        while (pointerS >= 0 && pointerT >= 0) {
            pointerS = skip(S, pointerS);
            pointerT = skip(T, pointerT);
            if (pointerS < 0 || pointerT < 0) {
                return pointerS < 0 && pointerT < 0;
            }
            if (S.charAt(pointerS--) != T.charAt(pointerT--)) {
                return false;
            }
        }
        pointerS = skip(S, pointerS);
        pointerT = skip(T, pointerT);
        return pointerS < 0 && pointerT < 0;
    }

    private int skip(String str, int pointer) {
        int skip = 0;
        do {
            while (pointer >= 0 && str.charAt(pointer) != '#' && skip > 0) {
                skip--;
                pointer--;
            }
            if (pointer >= 0 && str.charAt(pointer) == '#') {
                skip++;
                pointer--;
            }
        } while (pointer >= 0 && skip > 0);
        return pointer;
    }
}
