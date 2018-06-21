package com.my.leetcode.implement_strStr;

/**
 * Created by alex.bykovsky on 4/1/18.
 */
public class Solution {

	public int strStr(String haystack, String needle) {
		if (haystack.length() < needle.length()) return -1;
		if (needle.isEmpty()) return 0;

		int needleIndex = 0;
		for (int i = 0; i < haystack.length(); i++) {
			if (haystack.charAt(i) == needle.charAt(needleIndex)) {
				needleIndex++;
				if (needleIndex == needle.length()) {
					return i - (needleIndex - 1);
				}
			} else {
				i -= needleIndex;
				needleIndex = 0;
			}
		}
		return -1;
	}
}
