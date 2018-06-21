package com.my.code_jam.y2017.round1.round1a;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Created by alex.bykovsky on 4/29/18.
 */
public class AlphabetCake {

	public static void main(String[] args) {
		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		int t = in.nextInt();
		for (int i = 1; i <= t; ++i) {

			int R = in.nextInt();
			int C = in.nextInt();

			char[][] m = new char[R][C + 1];
			for (int r = 1; r <= R; r++) {
				String line = in.next();
				m[r - 1][C] = '?';
				for (int c = 1; c <= C; c++) {
					m[r - 1][c - 1] = line.charAt(c - 1);
					if (m[r - 1][c - 1] != '?' && m[r - 1][C] == '?') {
						m[r - 1][C] = m[r - 1][c - 1];
					}
				}
			}
			solve(m);
			System.out.println("Case #" + i + ":");
			println(m);
		}
	}

	private static void println(char[][] m) {
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[0].length - 1; j++) {
				System.out.print(m[i][j]);
			}
			System.out.println();
		}
	}

	private static void solve(char[][] m) {
		int c = m[0].length;
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < c - 1; j++) {
				if (i == 0 && m[i][c - 1] == '?') {
					int ii = 1;
					while (m[ii][c - 1] == '?') {
						ii++;
					}
					for (int jj = 0; jj < c - 1; jj++) {
						if (jj == 0 && m[ii][jj] == '?') {
							m[ii][jj] = m[ii][c - 1];
						} else if (jj > 0 && m[ii][jj] == '?') {
							m[ii][jj] = m[ii][jj - 1];
						}
					}
					for (int jj = 0; jj < c - 1; jj++) {
						m[0][jj] = m[ii][jj];
					}
					continue;
				} else if (i > 0 && m[i][c - 1] == '?') {
					m[i][j] = m[i - 1][j];
				} else if (j == 0 && m[i][j] == '?') {
					m[i][j] = m[i][c - 1];
				} else if (j > 0 && m[i][j] == '?') {
					m[i][j] = m[i][j - 1];
				}
			}
		}
	}
}
