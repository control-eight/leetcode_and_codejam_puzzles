package com.my.leetcode.strange_printer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by alex.bykovsky on 10/6/17.
 */
public class SolutionBad {

	public static void main(String[] args) {
	    long millis = System.currentTimeMillis();
        assertEquals(14, new SolutionBad().strangePrinter("dddccbdbababaddcbcaabdbdddcccddbba"));
        assertEquals(10, new SolutionBad().strangePrinter("abababaabababaabababa"));
        System.out.println(System.currentTimeMillis() - millis);
        /*assertEquals(1, new Solution().strangePrinter("aaaaaaaaaaaaaaaaaaa"));
        assertEquals(4, new Solution().strangePrinter("xasxsax"));
        assertEquals(4, new Solution().strangePrinter("xaxsax"));
        assertEquals(3, new Solution().strangePrinter("ababa"));
        assertEquals(3, new Solution().strangePrinter("abc"));
        assertEquals(2, new Solution().strangePrinter("aba"));
        assertEquals(2, new Solution().strangePrinter("aaabbb"));
        assertEquals(5, new Solution().strangePrinter("abcabc"));
		assertEquals(3, new Solution().strangePrinter("aabaabaa"));
		assertEquals(6, new Solution().strangePrinter("kgwkwgnkpk"));
		assertEquals(5, new Solution().strangePrinter("fqqptqtpq"));
		assertEquals(4, new Solution().strangePrinter("bcbcbd"));
        assertEquals(7, new Solution().strangePrinter("bdbbadabcaba"));
        assertEquals(5, new Solution().strangePrinter("aabaabaabccb"));
        assertEquals(7, new Solution().strangePrinter("bdbdbccbcjcbccb"));*/
        /*assertEquals(16, new Solution().strangePrinter("dddccbdbababaddcbcaabdbdddcccddbbaabddb"));
        assertEquals(10, new Solution().strangePrinter("baacdddaaddaaaaccbddbcabdaabdbbcdcbbbacbddcabcaaa"));*/
	}

	private static void assertEquals(int expected, int actual) {
		if(expected != actual) throw new RuntimeException(expected + " " + actual);
	}

	public int strangePrinter(String s) {
		if(s.length() == 0) return 0;
        cache = new HashMap<>();
        hits = 0;
        c = 0;
		int r = calc(s, new int[s.length()], s.length() - 1);
        System.out.println("c = " + c);
        System.out.println("hists = " + hits);
		return r;
	}

	private int c = 0;
	private int hits = 0;
	private Map<Key, Integer> cache = new HashMap<>();

    private int calc(String s, int[] counts, int index) {
        c++;
	    if (index == -1) {
	        return 0;
        }

        Key key = new Key(counts, index);
        Integer integer = cache.get(key);
        if (integer != null) {
            hits++;
	        return integer;
        }

        int result = calc(s, counts,index - 1) + 1;
        for (int i = 0; i < index; i++) {
            if (counts[i] == 0 && s.charAt(i) == s.charAt(index)) {
                while (i < index && s.charAt(i) == s.charAt(index)) {
                    i++;
                }
                i--;
                counts[i]++;
                result = Math.min(result, calc(s, counts, index - 1) + sum(counts, i + 1, index - 1));
                counts[i]--;
            }
        }
        cache.put(key, result);

        return result;
    }

    private int sum(int[] counts, int start, int end) {
	    int sum = 0;
	    for (int i = start; i <= end; i++) {
	        sum += counts[i];
        }
        return sum;
    }

    private static class Key {
	    private int[] counts;
	    private int index;

        public Key(int[] counts, int index) {
            this.counts = new int[index + 1];
            System.arraycopy(counts, 0, this.counts, 0, index + 1);
            this.index = index;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Key key = (Key) o;
            return index == key.index &&
                    Arrays.equals(counts, key.counts);
        }

        @Override
        public int hashCode() {
            int result = Objects.hash(index);
            result = 31 * result + Arrays.hashCode(counts);
            return result;
        }
    }
}
