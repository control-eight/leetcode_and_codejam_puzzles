package com.my.leetcode.ransom_note;

/**
 * Created by alex.bykovsky on 3/30/18.
 */
public class Solution {

	public static void main(String[] args) {

	}

	public boolean canConstruct(String ransomNote, String magazine) {
		int[] dict = new int[26];
		int a = 'a';
		char[] charArray = magazine.toCharArray();
		for (int i = 0; i < charArray.length; i++) {
			dict[charArray[i] - a]++;
		}
		charArray = ransomNote.toCharArray();
		for (int i = 0; i < charArray.length; i++) {
			dict[charArray[i] - a]--;
			if (dict[charArray[i] - a] < 0) return false;
		}
		return true;
	}
}
