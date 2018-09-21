package com.my.leetcode.palindrome_permutation_II;

import java.util.*;

public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().generatePalindromes("aabb"));
        System.out.println(new Solution().generatePalindromes("abc"));
        System.out.println(new Solution().generatePalindromes("aaaabbddd"));
        System.out.println(new Solution().generatePalindromes("aaaaaaaaaaaaaaaaaaaaaaaaa"));
    }

    public List<String> generatePalindromes(String s) {
        int[] count = new int[256];
        for (char c : s.toCharArray()) {
            count[(int)c]++;
        }
        int oddCount = 0;
        for (int i : count) {
            oddCount += i % 2 == 1? 1: 0;
            if (oddCount > 1) {
                return Collections.emptyList();
            }
        }

        List<Character> chars = new LinkedList<>();
        char last = ' ';
        for (int i = 0; i < count.length; i++) {
            if (count[i] % 2 == 1) {
                last = (char) i;
            }
            count[i] /= 2;
            if (count[i] > 0) {
                for (int j = 0; j < count[i]; j++) {
                    chars.add((char) i);
                }
            }
        }

        Set<String> result = new HashSet<>();
        generatePalindromes(chars, result, new char[chars.size()]);

        /*List<String> rr = new ArrayList<>(result);

        for (int i = 0; i < rr.size(); i++) {
            StringBuilder sb = new StringBuilder(rr.get(i));
            rr.set(i, sb.toString() + (oddCount > 0? last: "") + sb.reverse().toString());
        }*/

        return null;
    }

    private void generatePalindromes(List<Character> chars, Set<String> result, char[] arr) {
        if (chars.isEmpty()) {
            //result.add(new String(arr));
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            //if ((int)arr[i] == 0) {
                //Character remove = chars.remove(0);
                arr[i] = chars.get(0);
                generatePalindromes(chars, result, arr);
                //chars.add(0, arr[i]);
                arr[i] = 0;
            //}
        }
    }
}
