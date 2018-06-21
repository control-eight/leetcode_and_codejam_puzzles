package com.my.leetcode.anagrams;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by alex.bykovsky on 10/19/17.
 */
public class FindAllAnagramsString2 {

	public static void main(String[] args) {
		System.out.println(new FindAllAnagramsString2().findAnagrams("cbaebabacd","abc"));
		System.out.println(new FindAllAnagramsString2().findAnagrams("baa","aa"));
		System.out.println(new FindAllAnagramsString2().findAnagrams("abab","ab"));
	}

	public List<Integer> findAnagrams(String s, String p) {

		if(p.length() > s.length()) return Collections.emptyList();

		List<Integer> result = new ArrayList<>();

		int[] anagram = new int[26];
		int count = 0;
		int[] charSet = new int[26];
		for (char c : p.toCharArray()) {
			anagram[c - (int)'a']++;
			count++;
			charSet[c - (int)'a']++;
		}

		for(int i = 0; i < s.length(); i++) {

			char c = s.charAt(i);
			int key = c - (int) 'a';

			if(i > (p.length() - 1)) {
				int prevKey = s.charAt(i - p.length()) - (int) 'a';
				if(charSet[s.charAt(i - p.length()) - (int) 'a'] > 0) {
					anagram[prevKey]++;
					if(anagram[prevKey] > 0) {
						count++;
					}
				}
			}

			if(charSet[c - (int)'a'] > 0) {
				anagram[key]--;
				if(anagram[key] >= 0) {
					count--;
				}
			}

			if(count == 0) result.add(i - (p.length() - 1));
		}

		return result;
	}
}
