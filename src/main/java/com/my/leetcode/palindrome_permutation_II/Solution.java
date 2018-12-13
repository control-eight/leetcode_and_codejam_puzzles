package com.my.leetcode.palindrome_permutation_II;

import java.util.*;

public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().generatePalindromes("aabb"));
        System.out.println(new Solution().generatePalindromes("abc"));
        System.out.println(new Solution().generatePalindromes("aba"));
        System.out.println(new Solution().generatePalindromes("aaaabbddd"));
        System.out.println(new Solution().generatePalindromes("aaaaaaaaaaaaaaaaaaaaaaaaa"));
    }

    public List<String> generatePalindromes(String s) {

        int[] chars = new int[128];
        int check = 0;
        for (char c : s.toCharArray()) {
            chars[c]++;
        }
        for (int aChar : chars) {
            check += aChar % 2 == 1? 1: 0;
        }
        if (check > 1) return Collections.emptyList();

        Collection<String> acc = new ArrayList<>();
        if (s.length() % 2 == 0) {
            generatePalindromes(chars, s.length(), acc, "");
        } else {
            for (int i = 0; i < chars.length; i++) {
                if (chars[i] % 2 == 1) {
                    char c = (char) i;
                    chars[i]--;
                    generatePalindromes(chars, s.length(), acc, c + "");
                    chars[i]--;
                }
            }
        }
        return new ArrayList<>(acc);
    }

    private void generatePalindromes(int[] chars, int length, Collection<String> acc, String str) {
        if (str.length() == length) {
            acc.add(str);
            return;
        }

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] > 0) {
                char c = (char) i;
                chars[i] -= 2;
                generatePalindromes(chars, length, acc, c + str + c);
                chars[i] += 2;
            }
        }
    }
}
