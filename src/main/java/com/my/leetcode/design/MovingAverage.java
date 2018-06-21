package com.my.leetcode.design;

/**
 * Created by alex.bykovsky on 9/21/17.
 */
public class MovingAverage {

	private int size;

	private double sum;

	private int[] elements;

	private int currentSize;

	private int first;

	private int last;

	/** Initialize your data structure here. */
	public MovingAverage(int size) {
		this.size = size;
		elements = new int[size];
		this.first = -1;
		this.last = -1;
	}

	public double next(int val) {

		if(first == -1) first = 0;

		if(currentSize == size) {
			sum -= elements[first];
			first = (first + 1) == size? 0: first + 1;
		} else {
			currentSize++;
		}

		last = (last + 1) == size? 0: last + 1;
		elements[last] = val;
		sum += val;
		return sum/currentSize;
	}

	public static void main(String[] args) {

		//[[3],[1],[10],[3],[5]]

		MovingAverage movingAverage = new MovingAverage(3);
		System.out.println(movingAverage.next(1));
		System.out.println(movingAverage.next(10));
		System.out.println(movingAverage.next(3));
		System.out.println(movingAverage.next(5));

		movingAverage = new MovingAverage(1);
		System.out.println(movingAverage.next(-1));
		System.out.println(movingAverage.next(5));
	}
}

/**
 * Your MovingAverage object will be instantiated and called as such:
 * MovingAverage obj = new MovingAverage(size);
 * double param_1 = obj.next(val);
 */
