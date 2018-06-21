package com.my.code_jam.y2018.round1.round1c.a_whole_new_word;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by alex.bykovsky on 5/5/18.
 */
public class Solution {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		for (int t = 1; t <= T; t++) {
			int N = in.nextInt();
			int L = in.nextInt();

			List<String> words = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				words.add(in.next());
			}

			System.out.println("Case #"+t+": " + solve(L, words));
		}
	}

	private static String solve(int L, List<String> words) {

		Set<String> set = new HashSet<>();
		set.addAll(words);

		boolean[][] chars = new boolean[L][26];
		for (int i = 0; i < L; i++) {
			for (String word : words) {
				chars[i][word.charAt(i) - 'A'] = true;
			}
		}
		return findNewWord(chars, "", set, 0, L);
	}

	private static String findNewWord(boolean[][] chars, String newWord, Set<String> set, int i, int L) {

		if (i == L) {
			if (!set.contains(newWord)) {
				return newWord;
			} else {
				return "-";
			}
		}

		for (int j = 0; j < 26; j++) {
			if (chars[i][j]) {
				String result = findNewWord(chars, newWord + (char)('A' + j), set, i + 1, L);
				if (!"-".equals(result)) {
					return result;
				}
			}
		}
		return "-";
	}
}
