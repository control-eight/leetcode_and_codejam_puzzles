package com.my.test;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Created by alex.bykovsky on 9/18/17.
 */
public class Calculator {

	//10000 - 100
	//50000 - 282.669
	//100000 - 500
	//200000 - 742.86385
	//300000 - 987.0866833333333
	//400000 - 1208.188905

	//5134 - 49999
	//17985 - 199999
	//25998 - 299993
	//33861 - 399989

	public static final int LENGTH = 50000;

	public static void main(String[] args) {

		int i;
		int num;
		int jj = 0;
		for (i = 1; i <= LENGTH; i++) {
			int counter = 0;
			for(num = i; num >= 1; num--) {
				if(i % num == 0) {
					counter = counter + 1;
				}
			}
			if (counter == 2){
				primes[jj++] = i;
			}
		}
		System.out.println(jj);

		System.out.println(Arrays.toString(primes));

		PriorityQueue<Value> queue = new PriorityQueue<>();
		for(int j = 0; j < LENGTH; j++) {
			count = 0;
			new Calculator().minSteps(j);
			queue.offer(new Value(count, j));
		}
		System.out.println(queue.poll());
		System.out.println(queue.poll());
		System.out.println(queue.poll());
		System.out.println(queue.poll());
		System.out.println(queue.poll());

		double sum = 0;
		for(int j = 100; j < LENGTH; j++) {
			count = 0;
			new Calculator().minSteps(j);
			sum += (j / count);
		}
		System.out.println("avg = " + (sum / LENGTH));
	}

	private static class Value implements Comparable<Value> {
		private int count;
		private int number;

		public Value(int count, int number) {
			this.count = count;
			this.number = number;
		}

		@Override
		public int compareTo(Value o) {
			return o.count - this.count;
		}

		@Override
		public String toString() {
			return "Value{" +
					"count=" + count +
					", number=" + number +
					'}';
		}
	}

	private static int[] primes = new int[(int) (LENGTH / Math.log(LENGTH)) + 5000];

	private static int count;

	public int minSteps(int n) {
		int result = 0;
		int target = n;

		while (target > 1) {
			int minDivider = 1;
			for (int i = 2, j = 0; j < primes.length; i = primes[j++]) {
				count++;
				if (target % i == 0) {
					minDivider = i;
					break;
				}
			}
			if (minDivider == 1) {
				result += target;
				target = 1;
			} else {
				result += minDivider;
				target = target / minDivider;
			}
		}

		return result;
	}
}
