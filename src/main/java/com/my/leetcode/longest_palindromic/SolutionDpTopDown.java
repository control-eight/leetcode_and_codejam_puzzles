package com.my.leetcode.longest_palindromic;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by alex.bykovsky on 7/10/17.
 */
public class SolutionDpTopDown {

	public static void main(String[] args) {
		System.out.println(new SolutionDpTopDown().longestPalindrome("babad"));
		//bab, aba
		System.out.println(new SolutionDpTopDown().longestPalindrome("cbbd"));
		//bb
		System.out.println(new SolutionDpTopDown().longestPalindrome("abcba"));
		//abcba
		System.out.println(new SolutionDpTopDown().longestPalindrome("a"));
		//a
		System.out.println(new SolutionDpTopDown().longestPalindrome("aa"));
		//aa
		System.out.println(new SolutionDpTopDown().longestPalindrome("aaa"));
		//aaa
		System.out.println(new SolutionDpTopDown().longestPalindrome("abac"));
		//aba

		sss = 0;
		yyy = 0;
		long start = System.currentTimeMillis();
		System.out.println(new SolutionDpTopDown().longestPalindrome("babaddtattarrattatddetartrateedredividerb"));
		//ddtattarrattatdd
		System.out.println(System.currentTimeMillis() - start);
		System.out.println(sss + " from " + yyy);

		sss = 0;
		yyy = 0;
		start = System.currentTimeMillis();
		System.out.println(new SolutionDpTopDown().longestPalindrome("civilwartestingwhetherthatnaptionoranynartionsoconceivedandsodedicatedcanlongendureWeareqmetonagreatbattlefiemldoftzhatwarWehavecometodedicpateaportionofthatfieldasafinalrestingplaceforthosewhoheregavetheirlivesthatthatnationmightliveItisaltogetherfangandproperthatweshoulddothisButinalargersensewecannotdedicatewecannotconsecratewecannothallowthisgroundThebravelmenlivinganddeadwhostruggledherehaveconsecrateditfaraboveourpoorponwertoaddordetractTgheworldadswfilllittlenotlenorlongrememberwhatwesayherebutitcanneverforgetwhattheydidhereItisforusthelivingrathertobededicatedheretotheulnfinishedworkwhichtheywhofoughtherehavethusfarsonoblyadvancedItisratherforustobeherededicatedtothegreattdafskremainingbeforeusthatfromthesehonoreddeadwetakeincreaseddevotiontothatcauseforwhichtheygavethelastpfullmeasureofdevotionthatweherehighlyresolvethatthesedeadshallnothavediedinvainthatthisnationunsderGodshallhaveanewbirthoffreedomandthatgovernmentofthepeoplebythepeopleforthepeopleshallnotperishfromtheearth"));
		//ranynar
		System.out.println(System.currentTimeMillis() - start);
		System.out.println(sss + " from " + yyy);

		start = System.currentTimeMillis();
		System.out.println(new SolutionDpTopDown().longestPalindrome("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
		//aaa...aaa
		System.out.println(System.currentTimeMillis() - start);
	}

	private Map<String, int[]> cache = new HashMap<>();

	private static int sss = 0;
	private static int yyy = 0;

	public String longestPalindrome(String s) {
		int[] res = longestPalindrome(s, 0, s.length() - 1);
		return s.substring(res[0], res[1] + 1);
	}

	private int[] longestPalindrome(String s, int start, int end) {

		String key = s.substring(start, end + 1);
		int[] cachedValue = cache.get(key);
		yyy++;
		if(cachedValue != null) {
			sss++;
			return cachedValue;
		}

		System.out.println(key);

		if(end - start == 0) {
			return new int[]{start, end};
		}

		int longest = 0;
		int[] res = new int[]{0,0};

		if(end - start == 1) {
			if(s.charAt(start) == s.charAt(end)) {
				return new int[] {start, end};
			} else {
				return null;
			}
		}

		if(s.charAt(start) == s.charAt(end)) {
			int[] resInter = longestPalindrome(s, start + 1, end - 1);

			if(resInter != null) {
				if(resInter[0] - start == 1 && end - resInter[1] == 1) {
					resInter[0]--;
					resInter[1]++;
				}

				if(resInter[1] - resInter[0] > longest) {
					longest = resInter[1] - resInter[0];
					res = resInter;
				}
			}
		}

		int[] resInter1 = longestPalindrome(s, start, end - 1);
		if(resInter1 != null && resInter1[1] - resInter1[0] > longest) {
			longest = resInter1[1] - resInter1[0];
			res = resInter1;
		}

		int[] resInter2 = longestPalindrome(s, start + 1, end);
		if(resInter2 != null && resInter2[1] - resInter2[0] > longest) {
			res = resInter2;
		}

		cache.put(key, res);

		return res;
	}
}
