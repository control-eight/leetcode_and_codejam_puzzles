package com.my.code_jam.y2018.qualification.saving_the_universe_again;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Created by alex.bykovsky on 4/7/18.
 */
public class Solution {

	public static void main(String[] args) {

		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		int t = in.nextInt();
		for (int i = 1; i <= t; ++i) {
			int D = in.nextInt();
			String commands = in.next();
			solve(i, D, commands);
		}
	}

	private static void solve(int testCase, int D, String commands) {

		int result = 0;

		int damage = 0;
		int currentDamage = 1;
		int sAmount = 0;
		for (int i = 0; i < commands.length(); i++) {
			switch (commands.charAt(i)) {
				case 'C': currentDamage *= 2; break;
				case 'S': {
					damage += currentDamage;
					sAmount++;
				}
			}
		}
		if (damage <= D) {
			System.out.println("Case #" + testCase + ": " + result);
			return;
		}
		if (sAmount > D) {
			System.out.println("Case #" + testCase + ": IMPOSSIBLE");
			return;
		}

		int i = commands.length() - 1;
		int moves = 0;
		int endBeforeC = commands.length() - 1;
		while (damage > D) {
			if (commands.charAt(i) == 'C') {
				int possibleMoves = endBeforeC - i;
				if (possibleMoves * (currentDamage / 2) > (damage - D)) {
					moves += Math.ceil((double) (damage - D) / (currentDamage / 2));
					damage = D;
				} else {
					damage -= possibleMoves * currentDamage / 2;
					moves += possibleMoves;
				}
				endBeforeC--;
				currentDamage /= 2;
			}
			i--;
		}
		System.out.println("Case #" + testCase + ": " + moves);
	}
}
