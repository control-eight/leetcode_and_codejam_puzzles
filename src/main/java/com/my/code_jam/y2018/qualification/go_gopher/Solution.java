package com.my.code_jam.y2018.qualification.go_gopher;

import java.util.Scanner;

/**
 * Created by alex.bykovsky on 4/7/18.
 */
public class Solution {

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		int index = 1;

		int A = in.nextInt();
		int Aprime = calculateAPrime(A);
		boolean[][] area = new boolean[1000][1000];
		int i = 2;
		int j = 2;
		int width = Aprime / 3 - 2;
		int height = Aprime / 3 - 3;
		System.out.print(i + " " + j + "\n");
		System.out.flush();

		while (index <= T) {
			int i1 = in.nextInt();
			int j1 = in.nextInt();

			if (i1 == -1 && j1 == -1) {
				System.exit(1);
			} else if (i1 == 0 && j1 == 0) {
				index++;
				if (index > T) break;
				A = in.nextInt();
				Aprime = calculateAPrime(A);
				area = new boolean[1000][1000];
				i = 2;
				j = 2;
				width = Aprime / 3 - 2;
				height = Aprime / 3 - 3;
				System.out.print(i + " " + j + "\n");
				System.out.flush();
			} else {
				fillArea(area, i1, j1);
				if (!checkArea(area, i, j)) {
					System.out.print(i + " " + j + "\n");
					System.out.flush();
				} else {
					j += 3;
					//System.err.println(j);
					/*if (j > width + 1) {
						j = 2;
						i++;
					}*/
					System.out.print(i + " " + j + "\n");
					System.out.flush();
				}
			}
		}
	}

	private static boolean checkArea(boolean[][] area, int i, int j) {
		i--;
		j--;
		if (area[i - 1][j - 1] &&
			area[i][j - 1] &&
			area[i - 1][j] &&
			area[i][j] &&
			area[i - 1][j + 1] &&
			area[i][j + 1] &&
			area[i + 1][j - 1] &&
			area[i + 1][j] &&
			area[i + 1][j + 1]) {
			return true;
		}
		return false;
	}

	private static void fillArea(boolean[][] area, int i, int j) {
		i--;
		j--;
		area[i][j] = true;
	}

	private static int calculateAPrime(int a) {
		return a < 9? 9: (int)(Math.ceil((double) a /3) * 3);
	}
}
