package com.my.leetcode.anagrams;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by alex.bykovsky on 10/19/17.
 */
public class FindAllAnagramsString {

	public static void main(String[] args) {
		System.out.println(new FindAllAnagramsString().findAnagrams("baa","aa"));
		System.out.println(new FindAllAnagramsString().findAnagrams("cbaebabacd","abc"));
		System.out.println(new FindAllAnagramsString().findAnagrams("abab","ab"));
	}

	public List<Integer> findAnagrams(String s, String p) {

		if(p.length() > s.length()) return Collections.emptyList();

		List<Integer> result = new ArrayList<>();

		Map<Character, Integer> anagram = new HashMap<>();
		for (char c : p.toCharArray()) {
			if(!anagram.containsKey(c)) anagram.put(c, 0);
			anagram.put(c, anagram.get(c) + 1);
		}

		Map<Character, Integer> original = new HashMap<>();
		for(int i = 0; i < s.length(); i++) {
			Character c = s.charAt(i);

			if(!original.containsKey(c)) original.put(c, 0);
			original.put(c, original.get(c) + 1);

			if(i > p.length() - 1) {
				original.put(s.charAt(i - p.length()), original.get(s.charAt(i - p.length())) - 1);
			}
			if(i >= p.length() - 1) {
				if(equals(original, anagram)) result.add(i - (p.length() - 1));
			}
		}

		return result;
	}

	private boolean equals(Map<Character, Integer> original, Map<Character, Integer> anagram) {

		for (Map.Entry<Character, Integer> entry : anagram.entrySet()) {
			if(!(original.containsKey(entry.getKey()) && entry.getValue() > 0)) return false;
			if(!original.get(entry.getKey()).equals(entry.getValue())) return false;
		}

		return true;
	}
}
