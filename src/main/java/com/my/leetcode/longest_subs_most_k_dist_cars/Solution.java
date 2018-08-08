package com.my.leetcode.longest_subs_most_k_dist_cars;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().lengthOfLongestSubstringKDistinct("cacbcaaccbccaccccccbcaba", 2));
        System.out.println(new Solution().lengthOfLongestSubstringKDistinct("aba", 1));
        System.out.println(new Solution().lengthOfLongestSubstringKDistinct("aba", 2));
        System.out.println(new Solution().lengthOfLongestSubstringKDistinct("aa", 0));
        System.out.println(new Solution().lengthOfLongestSubstringKDistinct("a", 0));
        System.out.println(new Solution().lengthOfLongestSubstringKDistinct("eceba", 2));
    }

    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int[] current = new int[256];
        int currentSize = 0;
        int start = 0;
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            current[s.charAt(i)]++;
            if (current[s.charAt(i)] == 1) currentSize++;
            if (currentSize > k) {
                max = Math.max(max, i - start);

                int lastIndexOf = start + 1;
                for (int j = start; j < i - 1; j++) {
                    current[s.charAt(j)]--;
                    if (current[s.charAt(j)] == 0) {
                        currentSize--;
                    }
                    if (currentSize == k) {
                        lastIndexOf = j + 1;
                        break;
                    }
                }
                start = lastIndexOf;
            }
        }
        max = Math.max(max, s.length() - start);
        return max;
    }
}
