package com.my.leetcode.longest_palindromic;

/**
 * Created by alex.bykovsky on 7/10/17.
 */
public class Solution {

	public static void main(String[] args) {
		System.out.println(new Solution().longestPalindrome("babad"));
		//bab, aba
		System.out.println(new Solution().longestPalindrome("cbbd"));
		//bb
		System.out.println(new Solution().longestPalindrome("abcba"));
		//abcba
		System.out.println(new Solution().longestPalindrome("a"));
		//a
		System.out.println(new Solution().longestPalindrome("aa"));
		//aa
		System.out.println(new Solution().longestPalindrome("aaa"));
		//aaa
		System.out.println(new Solution().longestPalindrome("abac"));
		//aba
		System.out.println(new Solution().longestPalindrome("ccc"));

		System.out.println(new Solution().longestPalindrome("babaddtattarrattatddetartrateedredividerb"));
		//ddtattarrattatdd

		long start = System.currentTimeMillis();
		System.out.println(new Solution().longestPalindrome("civilwartestingwhetherthatnaptionoranynartionsoconceivedandsodedicatedcanlongendureWeareqmetonagreatbattlefiemldoftzhatwarWehavecometodedicpateaportionofthatfieldasafinalrestingplaceforthosewhoheregavetheirlivesthatthatnationmightliveItisaltogetherfangandproperthatweshoulddothisButinalargersensewecannotdedicatewecannotconsecratewecannothallowthisgroundThebravelmenlivinganddeadwhostruggledherehaveconsecrateditfaraboveourpoorponwertoaddordetractTgheworldadswfilllittlenotlenorlongrememberwhatwesayherebutitcanneverforgetwhattheydidhereItisforusthelivingrathertobededicatedheretotheulnfinishedworkwhichtheywhofoughtherehavethusfarsonoblyadvancedItisratherforustobeherededicatedtothegreattdafskremainingbeforeusthatfromthesehonoreddeadwetakeincreaseddevotiontothatcauseforwhichtheygavethelastpfullmeasureofdevotionthatweherehighlyresolvethatthesedeadshallnothavediedinvainthatthisnationunsderGodshallhaveanewbirthoffreedomandthatgovernmentofthepeoplebythepeopleforthepeopleshallnotperishfromtheearth"));
		//ranynar
		System.out.println(System.currentTimeMillis() - start);

		start = System.currentTimeMillis();
		System.out.println(new Solution().longestPalindrome("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
		//aaa...aaa
		System.out.println(System.currentTimeMillis() - start);
	}

	public String longestPalindrome(String s) {

		int[] longestInv = new int[2];

		for(int i = 0; i < s.length() - 1; i++) {
			findPalindrome(s, longestInv, i, 0);
			findPalindrome(s, longestInv, i, 1);
		}
		return s.substring(longestInv[0], longestInv[1] + 1);
	}

	private void findPalindrome(String s, int[] longestInv, int i, int rightBonus) {
		int startI = i;
		int endI = i + rightBonus;
		if(s.charAt(startI) != s.charAt(endI)) return;

		while (startI - 1 >= 0 && endI + 1 < s.length()) {
			if(s.charAt(startI - 1) != s.charAt(endI + 1)) break;

			startI -= 1;
			endI += 1;
		}
		if(endI - startI > longestInv[1] - longestInv[0]) {
			longestInv[0] = Math.max(0, startI);
			longestInv[1] = Math.min(s.length() - 1, endI);
		}
	}
}
