package com.my.leetcode.letter_comb_phone_number;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by alex.bykovsky on 4/2/18.
 */
public class Solution {

	public static void main(String[] args) {
		System.out.println(new Solution().letterCombinations("23"));
	}

	public List<String> letterCombinations(String digits) {

		if (digits.isEmpty()) return Collections.emptyList();

		Map<Character, List<Character>> digitToLetters = new HashMap<>();
		digitToLetters.put('1', Arrays.asList('^'));
		digitToLetters.put('2', Arrays.asList('a','b','c'));
		digitToLetters.put('3', Arrays.asList('d','e','f'));
		digitToLetters.put('4', Arrays.asList('g','h','i'));
		digitToLetters.put('5', Arrays.asList('j','k','l'));
		digitToLetters.put('6', Arrays.asList('m','n','o'));
		digitToLetters.put('7', Arrays.asList('p','q','r', 's'));
		digitToLetters.put('8', Arrays.asList('t','u','v'));
		digitToLetters.put('9', Arrays.asList('w','x','y', 'z'));
		digitToLetters.put('0', Arrays.asList(' '));

		List<String> result = new ArrayList<>();
		letterCombinations(digits, 0, "", digitToLetters, result);
		return result;
	}

	private void letterCombinations(String digits, int index, String str, Map<Character, List<Character>> digitToLetters, List<String> result) {
		if (index >= digits.length()) {
			result.add(str);
			return;
		}

		List<Character> chars = digitToLetters.get(digits.charAt(index));

		for (Character aChar : chars) {
			letterCombinations(digits, index + 1, str + aChar, digitToLetters, result);
		}
	}
}
