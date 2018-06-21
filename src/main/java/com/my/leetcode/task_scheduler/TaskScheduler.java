package com.my.leetcode.task_scheduler;

/**
 *
 * 		Input: tasks = ["A","A","A","B","B","B"], n = 2
 *		Output: 8
 *		Explanation: A -> B -> idle -> A -> B -> idle -> A -> B.
 *
 * Created by alex.bykovsky on 11/29/17.
 */
public class TaskScheduler {

	public static void main(String[] args) {
		System.out.println(new TaskScheduler().leastInterval(new char[] {'A','A','A','B','B','B'}, 2));
		System.out.println(new TaskScheduler().leastInterval(new char[] {'A','A','A','B','B','B'}, 50));
		System.out.println(new TaskScheduler().leastInterval(new char[] {'A','A','A','A','A','A','B','C','D','E','F','G'}, 2));
		//22 - 16
	}

	//A BC A DE A FG A

	public int leastInterval(char[] tasks, int n) {
		if (n == 0) return tasks.length;

		int[] m = new int[26];
		int ordA = (int) 'A';
		for (char task : tasks) {
			m[task - ordA]++;
		}

		int most = 0;
		for (int i : m) {
			most = Math.max(most, i);
		}

		int lastCorrection = 0;
		for (int i : m) {
			if(i == most) lastCorrection++;
		}

		int height = most;
		int width = (n + 1);
		int  res1 = height * width - (width - lastCorrection);

		height = tasks.length / n;
		width = n;
		int  res2 = height * width + tasks.length % width;

		if (res1 > res2) return res1;
		return res2;
	}
}
