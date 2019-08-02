package com.my.leetcode.longest_word_dict_del;

import java.util.Arrays;
import java.util.List;

public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().findLongestWord("abpcplea", Arrays.asList("ale", "apple", "monkey", "plea")));
        System.out.println(new Solution().findLongestWord("abpcplea", Arrays.asList("a", "b", "c")));
        System.out.println(new Solution().findLongestWord("aaadaadaad", Arrays.asList("a", "aaaad", "aaaa")));
        System.out.println(new Solution().findLongestWord("babaababa", Arrays.asList("ba", "ab")));
        System.out.println(new Solution().findLongestWord("qulmaupkkwugkpfztgejujlakrnkvefbvncfzhhmciztzjzrzrzlcfkejmlkhlogtraexhgrvxitcnaacegjrkwbseomwvdawsymemhsvtqcpbfvin", Arrays.asList("qkkwtlzbbn")));
    }

    public String findLongestWord(String s, List<String> d) {
        String result = "";
        char[] chars = s.toCharArray();
        for (String word : d) {
            if (word.length() > result.length() ||
                    (word.length() == result.length() && word.compareTo(result) < 0)) {
                int matchPtr = 0;
                for (int i = 0; i < chars.length && matchPtr < word.length(); i++) {
                    if (word.charAt(matchPtr) == chars[i]) {
                        matchPtr++;
                    }
                }
                if (matchPtr == word.length()) {
                    result = word;
                }
            }
        }
        return result;
    }
}
