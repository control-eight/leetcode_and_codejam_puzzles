package com.my.leetcode.anagrams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by alex.bykovsky on 10/19/17.
 */
public class GroupAnagrams {

	public static void main(String[] args) {

	}

	public List<List<String>> groupAnagrams(String[] strs) {

		Map<String, List<String>> solutions = new HashMap<>();


		for (String str : strs) {
			char[] chars = str.toCharArray();
			Arrays.sort(chars);

			String key = new String(chars);
			if(!solutions.containsKey(key)) {
				solutions.put(key, new ArrayList<>());
			}
			solutions.get(key).add(str);
		}

		List<List<String>> result = new ArrayList<>();
		for (Map.Entry<String, List<String>> entry : solutions.entrySet()) {
			result.add(entry.getValue());
		}
		return result;
	}
}
