package com.my.leetcode.regex_matching;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Created by alex.bykovsky on 5/9/18.
 */
public class Solution2 {

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
		int N = (int) 1e2;
		int L = 10000;
		int P = 10;
		Random random = new Random(N);
		int positive = 0;

		long time1 = 0;
		long time2 = 0;

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
			String s1 = sb.toString();

			long start = System.currentTimeMillis();
			boolean res1 = new Solution2().solve(s1, symbols);
			time1 += (System.currentTimeMillis()) - start;

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

			String join = String.join("", s);

			start = System.currentTimeMillis();
			boolean res2 = s1.matches(join);
			time2 += (System.currentTimeMillis()) - start;

			positive += res1 ? 1: 0;

			if (res1 != res2) {
				System.out.println(s1 + " " + join);
			}
		}
		System.out.println(positive);
		System.out.println(time1 + " vs " + time2);
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

		Pair[] prevSolutions = initSolutions(s);

		boolean isAllPrevWildcards = isWildcard(symbols.get(0));
		if (!processFirstSymbol(s, symbols, prevSolutions)) return false;

		for (int i = 1; i < symbols.size(); i++) {

			Pair[] currentSolutions = initSolutions(s);

			boolean isMatchExist = false;
			boolean isCurrentWildcard = isWildcard(symbols.get(i));
			for (int j = 0; j < s.length(); j++) {
				//copy match from previous if current is wildcard pattern
				if (isCurrentWildcard && prevSolutions[j].isMatch) {
					currentSolutions[j] = prevSolutions[j];
					isMatchExist = true;
				} else if (isLocalMatch(s.charAt(j), symbols.get(i))) {
					//if all previous were wildcards and there was no matching for a first char from source string
					if (j == 0 && isAllPrevWildcards && !prevSolutions[j].isMatch) {
						currentSolutions[j] = new Pair(true, isCurrentWildcard);
						isMatchExist = true;
					//if previous has a match, prolong it
					} else if (j > 0 && prevSolutions[j - 1].isMatch) {
						currentSolutions[j] = new Pair(true, isCurrentWildcard);
						isMatchExist = true;
					//if current is wildcard pattern and current j - 1 is a match, prolong it
					} else if (j > 0 && isCurrentWildcard && currentSolutions[j - 1].isMatch) {
						currentSolutions[j] = new Pair(true, true);
						isMatchExist = true;
					//if previous solutions j-th char was a match of wildcard pattern, copy it
					} else if (j >= 0 && prevSolutions[j].isWildcardMatch) {
						currentSolutions[j] = new Pair(true, isCurrentWildcard);
						isMatchExist = true;
					}
				}
			}
			if (!isMatchExist && !isCurrentWildcard) {
				return false;
			}
			prevSolutions = currentSolutions;
			isAllPrevWildcards &= isCurrentWildcard;
		}

		return prevSolutions[prevSolutions.length - 1].isMatch;
	}

	private Pair[] initSolutions(String s) {
		Pair[] currentSolutions = new Pair[s.length()];
		for (int k = 0; k < currentSolutions.length; k++) {
			currentSolutions[k] = new Pair(false, false);
		}
		return currentSolutions;
	}

	private boolean processFirstSymbol(String s, List<Character> symbols, Pair[] prevSolutions) {
		for (int i = 0; i < s.length(); i++) {
			boolean isLocalMatch = isLocalMatch(s.charAt(i), symbols.get(0));
			if (i == 0 && !isLocalMatch && !isWildcard(symbols.get(0))) {
				return false;
			}
			if (isLocalMatch) {
				prevSolutions[i] = new Pair(true, isWildcard(symbols.get(0)));
				if (!isWildcard(symbols.get(0))) break;
			} else {
				break;
			}
		}
		return true;
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

	/**
	 * a -> a
	 * ..
	 * z -> z
	 * . -> .
	 * a* -> A
	 * ..
	 * z* -> Z
	 * .* -> ,
	 */
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
		private boolean isMatch;
		private boolean isWildcardMatch;
		private Pair(boolean isMatch, boolean wildcardMatch) {
			this.isMatch = isMatch;
			this.isWildcardMatch = wildcardMatch;
		}
		@Override
		public String toString() {
			return "Pair{" +
					"isMatch=" + isMatch +
					", isWildcardMatch=" + isWildcardMatch +
					'}';
		}
	}
}
