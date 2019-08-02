package com.my.code_jam.y2019.round1.round1a.golf_gophers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Created by alex.bykovsky on 5/4/18.
 */
public class Solution {

	private static boolean isDebug = false;

	public static void main(String[] args) throws IOException {
		File out = new File("/tmp/out");
		if (out.exists()) {
			out.delete();
			out.createNewFile();
		}

		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		int N = in.nextInt();
		int M = in.nextInt();
		int index = 1;

		try (PrintWriter debug = new PrintWriter(new FileWriter(out))) {
			solution(in, T, M, index, debug);
		}
	}

	private static void solution(Scanner in, int T,  int M, int index, PrintWriter debug) {
		while (index <= T) {

			List<Integer> integers = Arrays.asList(4, 3, 5, 7, 11, 13, 17);
			//List<Integer> integers = Arrays.asList(12, 13, 14, 15, 16, 17, 18);
			List<Integer> reminders = new ArrayList<>();
			for (int i : integers) {
				int[] arr = new int[18];
				Arrays.fill(arr, i);
				List<Integer> integers1 = toList(arr);
				send(debug, integers1);
				reminders.add(readAndSum(in, arr.length) % i);
			}
			debug.println(reminders);
			for (int i = 1; i <= M; i++) {
				boolean found = true;
				for (int j = 0; j < integers.size(); j++) {
					if (i % integers.get(j) != reminders.get(j)) {
						found = false;
						break;
					}
				}
				if (found) {
					send(debug, i);
					break;
				}
			}
			int correct = in.nextInt();
			if (correct != 1) throw new RuntimeException();
			index++;
		}
	}

	private static List<Integer> toList(int[] arr) {
		List<Integer> r = new ArrayList<>();
		for (int i : arr) {
			r.add(i);
		}
		return r;
	}

	private static int readAndSum(Scanner in, int count) {
		int result = 0;
		for (int i = 0; i < count; i++) {
			result += in.nextInt();
		}
		return result;
	}

	private static void send(PrintWriter debug, Object ... object) {
		send(debug, Arrays.asList(object));
	}

	private static void send(PrintWriter debug, List<?> object) {
		String[] strings = new String[object.size()];
		for (int i = 0; i < object.size(); i++) {
			strings[i] = object.get(i).toString();
		}
		System.out.print(String.join(" ", strings) + "\n");
		System.out.flush();

		if (isDebug) {
			debug.println(String.join(" ", strings));
			debug.flush();
		}
	}
}
