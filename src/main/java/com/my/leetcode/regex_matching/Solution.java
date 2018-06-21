package com.my.leetcode.regex_matching;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Created by alex.bykovsky on 5/9/18.
 */
public class Solution {

	public static void main(String[] args) {
		/*System.out.println(new Solution().isMatch("bcca", ".*c.c*."));
		System.out.println(new Solution().isMatch("caca", ".*a.*."));
		System.out.println(new Solution().isMatch("bbca", "a*b.*"));
		System.out.println(new Solution().isMatch("bccc", "..*b.*"));
		System.out.println(new Solution().isMatch("acbbcbcbcbaaacaac", "ac*.a*ac*.*ab*b*ac"));
		System.out.println(new Solution().isMatch("ab", ".*.."));
		System.out.println(new Solution().isMatch("aab", "c*a*b"));
		System.out.println(new Solution().isMatch("aasdfasdfasdfasdfas", "aasdf.*asdf.*asdf.*asdf.*s"));
		System.out.println(new Solution().isMatch("bbcbbcbcbbcaabcacb", "a*.*ac*a*a*.a..*.*"));
		System.out.println(new Solution().isMatch("abacacccbbbcbcbb", ".*.*.*ab*.*ab.*c*"));
		System.out.println(new Solution().isMatch("aabccbcbacabaab", ".*c*a*b.*a*ba*bb*"));
		System.out.println(new Solution().isMatch("abbaaaabaabbcba", "a*.*ba.*c*..a*.a*."));
		System.out.println(new Solution().isMatch("aabcbcbcaccbcaabc", ".*a*aa*.*b*.c*.*a*"));
		System.out.println(new Solution().isMatch("bbbba", ".*a*a"));
		System.out.println(new Solution().isMatch("aaa", "ab*ac*a"));
		System.out.println(new Solution().isMatch("aa", "a*"));
		System.out.println(new Solution().isMatch("ab", ".*"));
		System.out.println(new Solution().isMatch("aa", "a"));
		System.out.println(new Solution().isMatch("aaa", "aa"));
		System.out.println(new Solution().isMatch("abcd", "d*"));
		System.out.println(new Solution().isMatch("mississippi", "mis*is*p*."));
		System.out.println(new Solution().isMatch("", "c*ab"));
		System.out.println(new Solution().isMatch("ccccc", "a.*"));*/


		generate();
	}

	private static void generate() {
		int N = (int) 1e6;
		int L = 3;
		int P = 3;
		Random random = new Random(N);
		int positive = 0;
		for (int n = 0; n < N; n++) {

			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < L; i++) {
				sb.append((char) ((int) (random.nextDouble() * 5) + 'a'));
			}

			List<Character> symbols = new ArrayList<>();
			List<Character> possible = Arrays.asList('a', 'b', 'c', 'd', 'e', '.', 'A', 'B', 'C', 'D', 'E', ',');
			for (int i = 0; i < P; i++) {
				int index = (int) (random.nextDouble() * 13);
				if (index > 0) {
					symbols.add(possible.get(index - 1));
				}
			}

			boolean res1 = new Solution().solve(sb.toString(), symbols);

			List<String> s = symbols.stream().map(c -> {
				switch (c) {
					case 'a': return c + "";
					case 'b': return c + "";
					case 'c': return c + "";
					case 'd': return c + "";
					case 'e': return c + "";
					case '.': return c + "";
					case 'A': return "a*";
					case 'B': return "b*";
					case 'C': return "c*";
					case 'D': return "d*";
					case 'E': return "e*";
					case ',': return ".*";
				}
				return "";
			}).collect(Collectors.toList());

			boolean res2 = sb.toString().matches(String.join("", s));

			positive += res1 ? 1: 0;

			if (res1 != res2) {
				System.out.println(sb.toString() + " " + String.join("", s));
			}
		}
		System.out.println(positive);
	}

	public boolean isMatch(String s, String p) {
		if (s.isEmpty() && p.isEmpty()) return true;
		if (!s.isEmpty() && p.isEmpty()) return false;
		return solve(s, constructSymbols(p));
	}

	private boolean solve(String s, List<Character> symbols) {
		if (!s.isEmpty() && symbols.isEmpty()) return false;
		if (s.isEmpty() && allWildcards(symbols)) return true;
		if (s.isEmpty()) return false;

		List<Pair> prevSolutions = new ArrayList<>(s.length());

		boolean allWildcards = isWildcard(symbols.get(0));
		for (int i = 0; i < s.length(); i++) {
			boolean localMatch = isLocalMatch(s.charAt(i), symbols.get(0));
			if (i == 0 && !localMatch && !isWildcard(symbols.get(0))) {
				return false;
			}
			if (localMatch) {
				prevSolutions.add(isWildcard(symbols.get(0))? Pair.TRUE_WILDCARD: Pair.TRUE);
				if (!isWildcard(symbols.get(0))) break;
			} else {
				break;
			}
		}

		for (int i = prevSolutions.size(); i < s.length(); i++) {
			prevSolutions.add(Pair.FALSE);
		}

		for (int i = 1; i < symbols.size(); i++) {

			List<Pair> currentSolutions = new ArrayList<>(s.length());

			int lastMatch = -1;
			boolean currentWildcard = isWildcard(symbols.get(i));
			for (int j = 0; j < s.length(); j++) {
				if (currentWildcard && prevSolutions.get(j).isMatch) {
					currentSolutions.add(prevSolutions.get(j));
					lastMatch = j;
				} else if (isLocalMatch(s.charAt(j), symbols.get(i))) {
					if (j == 0 && allWildcards && !prevSolutions.get(j).isMatch) {
						currentSolutions.add(currentWildcard? Pair.TRUE_WILDCARD: Pair.TRUE);
						lastMatch = j;
					} else if (j > 0 && prevSolutions.get(j - 1).isMatch) {
						currentSolutions.add(currentWildcard? Pair.TRUE_WILDCARD: Pair.TRUE);
						lastMatch = j;
					} else if (j > 0 && currentWildcard && currentSolutions.get(j - 1).isMatch) {
						currentSolutions.add(Pair.TRUE_WILDCARD);
						lastMatch = j;
					} else if (j >= 0 && prevSolutions.get(j).wildcardMatch && prevSolutions.get(j).isMatch) {
						currentSolutions.add(currentWildcard? Pair.TRUE_WILDCARD: Pair.TRUE);
						lastMatch = j;
					} else {
						currentSolutions.add(Pair.FALSE);
					}
				} else {
					currentSolutions.add(Pair.FALSE);
				}
			}

			if (lastMatch < 0 && !currentWildcard) {
				return false;
			}
			prevSolutions = currentSolutions;
			allWildcards &= currentWildcard;
		}

		return prevSolutions.get(prevSolutions.size() - 1).isMatch;
	}

	private boolean allWildcards(List<Character> symbols) {
		for (Character symbol : symbols) {
			if (!isWildcard(symbol)) return false;
		}
		return true;
	}

	private boolean isLocalMatch(char s, char p) {
		if (p == '.') {
			return true;
		} else if (p >= 'a' && p <= 'z' && s == p) {
			return true;
		} else if (p == ',' || ((p - 'A') + 'a') == s) {
			return true;
		}
		return false;
	}

	private boolean isWildcard(char p) {
		return p == ',' || p >= 'A' && p <= 'Z';
	}

	private List<Character> constructSymbols(String p) {
		List<Character> symbols = new ArrayList<>();

		for (int i = 0; i < p.length(); i++) {
			if (i < p.length() - 1 && p.charAt(i + 1) == '*') {
				if (p.charAt(i) == '.') {
					symbols.add(',');
				} else {
					symbols.add((char) ((int)'A' + (p.charAt(i) - 'a')));
				}
				i++;
			} else {
				symbols.add(p.charAt(i));
			}
		}
		return symbols;
	}

	private static class Pair {

		private static final Pair FALSE = new Pair(false, false);
		private static final Pair TRUE = new Pair(true, false);
		private static final Pair TRUE_WILDCARD = new Pair(true, true);

		private boolean isMatch;
		private boolean wildcardMatch;
		private Pair(boolean isMatch, boolean wildcardMatch) {
			this.isMatch = isMatch;
			this.wildcardMatch = wildcardMatch;
		}
		@Override
		public String toString() {
			return "Pair{" +
					"isMatch=" + isMatch +
					", wildcardMatch=" + wildcardMatch +
					'}';
		}
	}
}
