package com.my.leetcode.strings;

import java.util.Random;

/**
 * Created by alex.bykovsky on 9/29/17.
 */
public class StringRotation {

	public static void main(String[] args) {
		assertFalse("wwwbbbc", "wwwbbcb");
		assertTrue("wawaawa", "wawawaa");
		assertTrue("waterbottle", "erbottlewat");
		assertTrue("waapcwaappp", "waapppwaapc");
		assertTrue("oe", "eo");
		assertTrue("one", "eon");
		assertTrue("ababbaba", "ababbaba");
		assertTrue("waacwaap", "waapwaac");
		assertTrue("wawawawawawazbcwa", "wawawawawawawazbc");
		assertTrue("wabcwwwwwww", "wwwwwwwabcw");
		assertFalse("babaac", "bababaa");
		assertFalse("wwwbbbcccz", "zbbbccc");

		randomAssertCompareTime();
	}

	public boolean isRotation(String str1, String str2) {

		if(str1.length() != str2.length() || str1.length() == 0) return false;

		//from left to right
		int lrIndex = -1;
		for(int i = 0; i < str1.length(); i++) {
			if(str1.charAt(i) == str2.charAt(lrIndex + 1)) {
				lrIndex++;
			}
		}
		if(lrIndex == -1) return false;
		if(lrIndex == str1.length() - 1) return true;

		return isSubstring(str2 + str2.substring(0, lrIndex + 1), str1);
	}

	public boolean isRotationSimple(String str1, String str2) {
		if(str1.length() != str2.length() || str1.length() == 0) return false;
		return isSubstring(str2+str2, str1);
	}

	private boolean isSubstring(String str1, String str2) {
		return str1.contains(str2);
	}

	private static void randomAssert() {
		int size = 1000000;
		int length = 10;
		Random random = new Random(size);
		int trueCount = 0;
		int falseCount = 0;
		for(int i = 0; i < size; i++) {
			String str1 = generateString(length, random);

			int y2 = (int) (random.nextDouble() * length) + 1;
			String str2;
			if(y2 == length) {
				str2 = generateString(length, random);
			} else {
				str2 = str1.substring(y2) + str1.substring(0, y2);
			}

			try {
				boolean expected = new StringRotation().isRotationSimple(str1, str2);
				if(expected) trueCount++;
				else falseCount++;
				assertEquals(expected, new StringRotation().isRotation(str1, str2));
			} catch (RuntimeException e) {
				System.out.println(e.getMessage() + " str1 = " + str1 + " str2 = " + str2);
			}
		}
		System.out.println(trueCount + " " + falseCount);
	}

	private static void randomAssertCompareTime() {
		int size = 1000000000;
		int length = 100;
		Random random = new Random(size);
		int trueCount = 0;
		int falseCount = 0;
		long start = System.currentTimeMillis();
		for(int i = 0; i < size; i++) {
			String str1 = generateString(length, random);

			int y2 = (int) (random.nextDouble() * length) + 1;
			String str2;
			if(y2 == length) {
				str2 = generateString(length, random);
			} else {
				str2 = str1.substring(y2) + str1.substring(0, y2);
			}

			try {
				boolean expected = new StringRotation().isRotationSimple(str1, str2);
				if(expected) trueCount++;
				else falseCount++;
			} catch (RuntimeException e) {
				System.out.println(e.getMessage() + " str1 = " + str1 + " str2 = " + str2);
			}
		}
		System.out.println(System.currentTimeMillis() - start + "ms. " + trueCount + " " + falseCount);

		random = new Random(size);
		trueCount = 0;
		falseCount = 0;
		start = System.currentTimeMillis();
		for(int i = 0; i < size; i++) {
			String str1 = generateString(length, random);

			int y2 = (int) (random.nextDouble() * length) + 1;
			String str2;
			if(y2 == length) {
				str2 = generateString(length, random);
			} else {
				str2 = str1.substring(y2) + str1.substring(0, y2);
			}

			try {
				boolean expected = new StringRotation().isRotation(str1, str2);
				if(expected) trueCount++;
				else falseCount++;
			} catch (RuntimeException e) {
				System.out.println(e.getMessage() + " str1 = " + str1 + " str2 = " + str2);
			}
		}
		System.out.println(System.currentTimeMillis() - start + "ms. " + trueCount + " " + falseCount);
	}

	private static String generateString(int length, Random random) {
		char[] arr = new char[length];
		for(int j = 0; j < arr.length; j++) {
			int a = 97 + (int) (random.nextDouble() * 10);
			arr[j] = (char) a;
		}

		return String.valueOf(arr);
	}

	private static void assertTrue(String str1, String str2) {
		boolean result = new StringRotation().isRotation(str1, str2);
		if(!result) throw new RuntimeException(str1 + " " + str2 + " = " + false);
	}

	private static void assertFalse(String str1, String str2) {
		boolean result = new StringRotation().isRotation(str1, str2);
		if(result) throw new RuntimeException(str1 + " " + str2 + " = " + true);
	}

	private static void assertEquals(boolean expected, boolean actual) {
		if(expected != actual) throw new RuntimeException("expected = " + expected + ", actual = " + actual);
	}
}
