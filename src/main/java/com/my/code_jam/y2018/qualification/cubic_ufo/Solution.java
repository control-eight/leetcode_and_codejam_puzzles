package com.my.code_jam.y2018.qualification.cubic_ufo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Scanner;

/**
 * Created by alex.bykovsky on 4/7/18.
 */
public class Solution {

	public static void main(String[] args) {

		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		int t = in.nextInt();
		for (int i = 1; i <= t; ++i) {
			double A = in.nextDouble();
			solve(i, A);
		}
	}

	private static void solve(int testCase, double A) {

		Point p1 = new Point(0.5, 0.0, 0.0);
		Point p2 = new Point(0.0, 0.5, 0.0);
		Point p3 = new Point(0.0, 0.0, 0.5);

		double angle = Math.asin(BigDecimal.valueOf(A).pow(2)
				.setScale(5, BigDecimal.ROUND_HALF_UP).doubleValue() - 1)/2; //radians
		p1 = calcRotation(p1, angle);
		p2 = calcRotation(p2, angle);

		System.out.println("Case #" + testCase + ":");
		System.out.println(p1);
		System.out.println(p2);
		System.out.println(p3);
	}

	private static Point calcRotation(Point p, double angle) {
		Point result = new Point();

		result.x = p.x * Math.cos(angle) - p.y * Math.sin(angle);
		result.y = p.x * Math.sin(angle) + p.y * Math.cos(angle);
		result.z = p.z;

		return result;
	}

	private static class Point {
		double x;double y;double z;
		public Point() {
		}
		public Point(double x, double y, double z) {
			this.x = x;this.y = y;this.z = z;
		}
		@Override
		public String toString() {
			return x + " " + y + " " + z;
		}
	}
}
