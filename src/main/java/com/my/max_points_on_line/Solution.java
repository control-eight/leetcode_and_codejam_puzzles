package com.my.max_points_on_line;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Solution {

	public int maxPoints(Point[] points) {
		if(points.length == 0) return 0;
		if(points.length == 1) return 1;

		Integer max = 2;

		Map<MyPoint, Integer> pointsMap = new HashMap<>();
		List<Point> uniquePoints = new ArrayList<>();
		for (Point point : points) {
			MyPoint key = new MyPoint(point.x, point.y);
			if(!pointsMap.containsKey(key)) {
				pointsMap.put(key, 1);
				uniquePoints.add(point);
			} else {
				pointsMap.put(key, pointsMap.get(key) + 1);
			}
			max = Math.max(max, pointsMap.get(key));
		}

		Map<Line, Set<Point>> linePointMap = new HashMap<>();

		for (Point point1 : uniquePoints) {
			for (Point point2 : uniquePoints) {
				if(point1 != point2) {
					long a = calculateA(point1, point2);
					long b = calculateB(point1, point2);
					long c = calculateC(point1, point2);

					long gcd = gcdThing(gcdThing(a, b), c);

					Line key = new Line(a/gcd, b/gcd, c/gcd);
					linePointMap.putIfAbsent(key, new HashSet<>());
					linePointMap.get(key).add(point1);
					linePointMap.get(key).add(point2);
				}
			}
		}

		for (Set<Point> pointSet : linePointMap.values()) {
			int size = pointSet.size();
			for (Point point : pointSet) {
				size += pointsMap.get(new MyPoint(point.x, point.y)) - 1;
			}
			max = Math.max(max, size);
		}
		return max;
	}

	private static long calculateA(Point p1, Point p2) {
		if(p2.x == p1.x) {
			return -1;
		} else {
			return (long) p1.y - (long) p2.y;
		}
	}

	private static long calculateB(Point p1, Point p2) {
		if(p2.x == p1.x) {
			return p1.x;
		} else {
			return (long) p2.x - (long) p1.x;
		}
	}

	private static long calculateC(Point p1, Point p2) {
		if(p2.x == p1.x) {
			return p1.x;
		} else {
			return (long) p1.x * (long) p2.y - (long) p2.x * (long) p1.y;
		}
	}

	private static long gcdThing(long a, long b) {
		BigInteger b1 = BigInteger.valueOf(a);
		BigInteger b2 = BigInteger.valueOf(b);
		BigInteger gcd = b1.gcd(b2);
		return gcd.longValue();
	}

	private static class Line {
		private long a;
		private long b;
		private long c;

		public Line(long a, long b, long c) {
			this.a = a;
			this.b = b;
			this.c = c;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;

			Line line = (Line) o;

			if (a != line.a) return false;
			if (b != line.b) return false;
			return c == line.c;
		}

		@Override
		public int hashCode() {
			int result = (int) (a ^ (a >>> 32));
			result = 31 * result + (int) (b ^ (b >>> 32));
			result = 31 * result + (int) (c ^ (c >>> 32));
			return result;
		}
	}

	private static class MyPoint {
		int x;
		int y;

		public MyPoint(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;

			MyPoint myPoint = (MyPoint) o;

			if (x != myPoint.x) return false;
			return y == myPoint.y;
		}

		@Override
		public int hashCode() {
			int result = x;
			result = 31 * result + y;
			return result;
		}
	}

	private static class Point {
		int x;
		int y;
		Point() { x = 0; y = 0; }
		Point(int a, int b) { x = a; y = b; }
	}

	public static void main(String[] args) {
		test1();
		test2();
		test3();
		test4();
		test5();
		test6();
		test7();
		test8();
		test9();
		test10();
		test11();
	}

	private static void test1() {
		Point[] in = new Point[] {
				new Point(3, 3),
				new Point(5, 3),
				new Point(10, 3),
				new Point(1, 1),
				new Point(2, 2),
				new Point(4, 4),
		};

		System.out.println(new Solution().maxPoints(in));
	}

	private static void test2() {
		Point[] in = new Point[] {
				new Point(3, 3),
				new Point(5, 2),
				new Point(10, 3),
				new Point(1, 7),
				new Point(2, 1),
				new Point(4, 5),
		};

		System.out.println(new Solution().maxPoints(in));
	}

	private static void test3() {
		Point[] in = new Point[] {
				new Point(9, 3),
				new Point(5, 2),
				new Point(10, 3),
				new Point(1, 7),
				new Point(2, 1),
				new Point(4, 5),
		};

		System.out.println(new Solution().maxPoints(in));
	}

	private static void test4() {
		Point[] in = new Point[] {
				new Point(0, 0),
				new Point(1, 65536),
				new Point(65536, 0),
		};

		System.out.println(new Solution().maxPoints(in));
	}

	private static void test5() {
		Point[] in = new Point[] {
				new Point(0, -12),
				new Point(5, 2),
				new Point(2, 5),
				new Point(0, -5),
				new Point(1, 5),
				new Point(2, -2),
				new Point(5, -4),
				new Point(3, 4),
				new Point(-2, 4),
				new Point(-1, 4),
				new Point(0, -5),
				new Point(0, -8),
				new Point(-2, -1),
				new Point(0, -11),
				new Point(0, -9),
		};

		System.out.println(new Solution().maxPoints(in));
	}

	private static void test6() {
		Point[] in = new Point[] {
				new Point(1, 1),
				new Point(1, 1),
				new Point(1, 1),
		};

		System.out.println(new Solution().maxPoints(in));
	}

	private static void test7() {
		String input = "[-230,324],[-291,141],[34,-2],[80,22],[-28,-134],[40,-23],[-72,-149],[0,-17],[32,-32],[-207,288],[7,32],[-5,0],[-161,216],[-48,-122],[-3,39],[-40,-113],[115,-216],[-112,-464],[-72,-149],[-32,-104],[12,42],[-22,19],[-6,-21],[-48,-122],[161,-288],[16,11],[39,23],[39,30],[873,-111]";

		List<Point> in = new ArrayList<>();
		for (String part : input.split("\\]")) {

			if(part.startsWith(",")) {
				part = part.substring(1);
			}
			if(part.startsWith("[")) {
				part = part.substring(1);
			}

			String[] arr = part.split(",");

			in.add(new Point(Integer.parseInt(arr[0]), Integer.parseInt(arr[1])));
		}

		System.out.println(new Solution().maxPoints(in.toArray(new Point[in.size()])));
	}

	private static void test8() {
		Point[] in = new Point[] {
				new Point(0, 0),
				new Point(94911151, 94911150),
				new Point(94911152, 94911151),
		};

		System.out.println(new Solution().maxPoints(in));
	}

	private static void test9() {
		String input = "[560,248],[0,16],[30,250],[950,187],[630,277],[950,187],[-212,-268],[-287,-222],[53,37],[-280,-100],[-1,-14],[-5,4],[-35,-387],[-95,11],[-70,-13],[-700,-274],[-95,11],[-2,-33],[3,62],[-4,-47],[106,98],[-7,-65],[-8,-71],[-8,-147],[5,5],[-5,-90],[-420,-158],[-420,-158],[-350,-129],[-475,-53],[-4,-47],[-380,-37],[0,-24],[35,299],[-8,-71],[-2,-6],[8,25],[6,13],[-106,-146],[53,37],[-7,-128],[-5,-1],[-318,-390],[-15,-191],[-665,-85],[318,342],[7,138],[-570,-69],[-9,-4],[0,-9],[1,-7],[-51,23],[4,1],[-7,5],[-280,-100],[700,306],[0,-23],[-7,-4],[-246,-184],[350,161],[-424,-512],[35,299],[0,-24],[-140,-42],[-760,-101],[-9,-9],[140,74],[-285,-21],[-350,-129],[-6,9],[-630,-245],[700,306],[1,-17],[0,16],[-70,-13],[1,24],[-328,-260],[-34,26],[7,-5],[-371,-451],[-570,-69],[0,27],[-7,-65],[-9,-166],[-475,-53],[-68,20],[210,103],[700,306],[7,-6],[-3,-52],[-106,-146],[560,248],[10,6],[6,119],[0,2],[-41,6],[7,19],[30,250]";

		List<Point> in = new ArrayList<>();
		for (String part : input.split("\\]")) {

			if(part.startsWith(",")) {
				part = part.substring(1);
			}
			if(part.startsWith("[")) {
				part = part.substring(1);
			}

			String[] arr = part.split(",");

			in.add(new Point(Integer.parseInt(arr[0]), Integer.parseInt(arr[1])));
		}

		System.out.println(new Solution().maxPoints(in.toArray(new Point[in.size()])));
	}

	private static void test10() {
		Point[] in = new Point[] {
				new Point(0, 0),
				new Point(-1, -1),
				new Point(2, 2),
		};

		System.out.println(new Solution().maxPoints(in));
	}

	private static void test11() {
		Point[] in = new Point[] {
				new Point(4, 0),
				new Point(4, -1),
				new Point(4, 5),
		};

		System.out.println(new Solution().maxPoints(in));
	}
}
