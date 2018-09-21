package com.my.leetcode.reorganize_string;

import java.util.PriorityQueue;

public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().reorganizeString("aab"));
        System.out.println(new Solution().reorganizeString("aaab"));
        System.out.println(new Solution().reorganizeString("zhmyo"));
        System.out.println(new Solution().reorganizeString("vvvlo"));
    }

    public String reorganizeString(String S) {
        if (S.isEmpty()) return "";

        int[] letters = new int[26];
        for (char c : S.toCharArray()) {
            letters[c - 'a']++;
        }
        PriorityQueue<Container> pq = new PriorityQueue<>();
        for (int i = 0; i < letters.length; i++) {
            if (letters[i] > 0) {
                pq.offer(new Container((char)('a' + i), letters[i]));
            }
        }

        StringBuilder sb = new StringBuilder();
        Container prev = null;
        for (int i = 0; i < S.length(); i++) {
            Container c = pq.poll();
            if (prev != null && prev.count > 0) {
                pq.offer(prev);
            }
            if (c == null) {
                return "";
            }
            sb.append(c.c);
            c.count--;
            prev = c;
        }

        return sb.toString();
    }

    private static class Container implements Comparable<Container> {
        private char c;
        private int count;
        public Container(char c, int count) {
            this.c = c;
            this.count = count;
        }
        @Override
        public int compareTo(Container o) {
            return Integer.compare(o.count, count);
        }
    }
}
