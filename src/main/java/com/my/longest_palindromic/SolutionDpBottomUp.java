package com.my.longest_palindromic;

/**
 * Created by alex.bykovsky on 7/10/17.
 */
public class SolutionDpBottomUp {

	public static void main(String[] args) {
		System.out.println(new SolutionDpBottomUp().longestPalindrome("babad"));
		//bab, aba
		System.out.println(new SolutionDpBottomUp().longestPalindrome("cbbd"));
		//bb
		System.out.println(new SolutionDpBottomUp().longestPalindrome("abcba"));
		//abcba
		System.out.println(new SolutionDpBottomUp().longestPalindrome("a"));
		//a
		System.out.println(new SolutionDpBottomUp().longestPalindrome("aa"));
		//aa
		System.out.println(new SolutionDpBottomUp().longestPalindrome("aaa"));
		//aaa
		System.out.println(new SolutionDpBottomUp().longestPalindrome("abac"));
		//aba

		long start = System.currentTimeMillis();
		System.out.println(new SolutionDpBottomUp().longestPalindrome("babaddtattarrattatddetartrateedredividerb"));
		//ddtattarrattatdd
		System.out.println(System.currentTimeMillis() - start);

		start = System.currentTimeMillis();
		System.out.println(new SolutionDpBottomUp().longestPalindrome("civilwartestingwhetherthatnaptionoranynartionsoconceivedandsodedicatedcanlongendureWeareqmetonagreatbattlefiemldoftzhatwarWehavecometodedicpateaportionofthatfieldasafinalrestingplaceforthosewhoheregavetheirlivesthatthatnationmightliveItisaltogetherfangandproperthatweshoulddothisButinalargersensewecannotdedicatewecannotconsecratewecannothallowthisgroundThebravelmenlivinganddeadwhostruggledherehaveconsecrateditfaraboveourpoorponwertoaddordetractTgheworldadswfilllittlenotlenorlongrememberwhatwesayherebutitcanneverforgetwhattheydidhereItisforusthelivingrathertobededicatedheretotheulnfinishedworkwhichtheywhofoughtherehavethusfarsonoblyadvancedItisratherforustobeherededicatedtothegreattdafskremainingbeforeusthatfromthesehonoreddeadwetakeincreaseddevotiontothatcauseforwhichtheygavethelastpfullmeasureofdevotionthatweherehighlyresolvethatthesedeadshallnothavediedinvainthatthisnationunsderGodshallhaveanewbirthoffreedomandthatgovernmentofthepeoplebythepeopleforthepeopleshallnotperishfromtheearth"));
		//ranynar
		System.out.println(System.currentTimeMillis() - start);

		start = System.currentTimeMillis();
		System.out.println(new SolutionDpBottomUp().longestPalindrome("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
		//aaa...aaa
		System.out.println(System.currentTimeMillis() - start);
	}

	public String longestPalindrome(String s) {

		boolean[][] dp = new boolean[s.length()][s.length()];

		dp[0][0] = true;

		int[] max = new int[]{0,0};

		for(int z = 1; z < s.length(); z++) {
			for(int i = 0; i + z < s.length(); i++) {
				int j = i + z;
				if(s.charAt(i) == s.charAt(j) && (j - i <= 2 || dp[i + 1][j - 1])) {
					dp[i][j] = true;

					if(j - i > max[1] - max[0]) {
						max[0] = i;
						max[1] = j;
					}
				}
			}
		}

		return s.substring(max[0], max[1] + 1);
	}


}
