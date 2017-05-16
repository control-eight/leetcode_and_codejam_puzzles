package com.my.code_jam.round1b;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

/**
 * Created by alex.bykovsky on 4/22/17.
 */
public class StableNeighbors {

	public static void main(String[] args) {

		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		int t = in.nextInt();
		for (int i = 1; i <= t; ++i) {

			//6 2 0 2 0 2 0
			//N, R, O, Y, G, B, V

			int size = in.nextInt();
			int R = in.nextInt();
			int O = in.nextInt();
			int Y = in.nextInt();
			int G = in.nextInt();
			int B = in.nextInt();
			int V = in.nextInt();

			int r = R;
			int y = Y;
			int b = B;

			//O = G = V = 0

			//R Y B

			System.out.print("Case #" + i + ": ");

			if(size == 0) {
				System.out.println("");
				continue;
			}

			List<Character> sb = new ArrayList<>();

			PriorityQueue<Pair> priorityQueue = new PriorityQueue<>();
			priorityQueue.add(new Pair('R', R));
			priorityQueue.add(new Pair('Y', Y));
			priorityQueue.add(new Pair('B', B));

			Queue<Pair> queue = new LinkedList<>();
			queue.offer(priorityQueue.poll());
			queue.offer(priorityQueue.poll());
			queue.offer(priorityQueue.poll());

			while (!queue.isEmpty()) {

				Pair first = queue.poll();

				Pair second = queue.poll();

				if (checkPrintAdd(sb, queue, first)) break;

				if(second != null) {
					if (checkPrintAdd(sb, queue, second)) break;
				}

				Pair third = queue.poll();

				if(third != null && third.count > second.count) {
					if (third.count > first.count) {
						if (checkPrintAdd(sb, queue, third)) break;
					}
				}

				if(third != null && third.count > second.count) {
					if(third.count > first.count) {
						offer(queue, third);
						offer(queue, first);
						offer(queue, second);
					} else {
						offer(queue, first);
						offer(queue, third);
						offer(queue, second);
					}
				} else {
					offer(queue, first);
					if(second != null) {
						offer(queue, second);
					}
					if(third != null) {
						offer(queue, third);
					}
				}
			}

			if(sb.isEmpty() || sb.get(0) == sb.get(sb.size() - 1)) {
				System.out.println("IMPOSSIBLE");
				continue;
			}

			printSb(sb);

			System.out.println("");
		}
	}

	private static void offer(Queue<Pair> queue, Pair pair) {
		if(pair.count > 0) {
			queue.offer(pair);
		}
	}

	private static boolean checkPrintAdd(List<Character> sb, Queue<Pair> priorityQueue, Pair pair) {
		if(checkProperty(sb, pair.c)) return true;
		sb.add(pair.c);
		pair.count--;
		return false;
	}

	private static void printSb(List<Character> sb) {
		for (Character character : sb) {
			System.out.print(character);
		}
	}

	private static boolean checkProperty(List<Character> sb, char b) {
		boolean result = !sb.isEmpty() && (sb.get(sb.size() - 1) == b);
		if(result) {
			sb.clear();
		}
		return result;
	}

	private static class Pair implements Comparable<Pair> {

		private char c;

		private int count;

		public Pair(char c, int count) {
			this.c = c;
			this.count = count;
		}

		@Override
		public int compareTo(Pair o) {
			return o.count - count;
		}
	}
}
