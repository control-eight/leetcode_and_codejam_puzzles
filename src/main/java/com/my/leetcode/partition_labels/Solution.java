package com.my.leetcode.partition_labels;

import java.util.*;

public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().partitionLabels("qiejxqfnqceocmy"));
        System.out.println(new Solution().partitionLabels("caedbdedda"));
        System.out.println(new Solution().partitionLabels("ababcbacadefegdehijhklij"));
    }

    public List<Integer> partitionLabels(String S) {
        if (S.isEmpty()) return Collections.emptyList();

        Map<Character, Integer> lastIndexes = new HashMap<>();
        char[] sArr = S.toCharArray();
        for (int i = 0; i < sArr.length; i++) {
            lastIndexes.put(sArr[i], i);
        }

        List<Integer> result = new ArrayList<>();

        PriorityQueue<CharacterLastIndex> queue = new PriorityQueue<>();
        queue.offer(new CharacterLastIndex(sArr[0], lastIndexes.get(sArr[0])));
        int lastI = 0;
        int i = 0;
        while (i < S.length()) {
            if (queue.isEmpty()) {
                result.add(i - lastI);
                lastI = i;
                queue.offer(new CharacterLastIndex(sArr[i], lastIndexes.get(sArr[i])));
            }

            CharacterLastIndex cf = queue.poll();
            if (cf.lastIndex < i) continue;
            for (int j = i; j <= cf.lastIndex; j++) {
                queue.offer(new CharacterLastIndex(sArr[j], lastIndexes.get(sArr[j])));
            }
            i = cf.lastIndex + 1;
        }
        result.add(i - lastI);
        return result;
    }

    private static class CharacterLastIndex implements Comparable<CharacterLastIndex> {
        private char c;
        private int lastIndex;

        public CharacterLastIndex(char c, int lastIndex) {
            this.c = c;
            this.lastIndex = lastIndex;
        }

        @Override
        public int compareTo(CharacterLastIndex o) {
            return Integer.compare(o.lastIndex, lastIndex);
        }
    }
}
