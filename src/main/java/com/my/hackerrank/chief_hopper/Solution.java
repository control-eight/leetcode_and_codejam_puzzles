package com.my.hackerrank.chief_hopper;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

/**
 * Created by alex.bykovsky on 5/12/18.
 */
public class Solution {

	// Complete the chiefHopper function below.
	static int chiefHopper(int[] arr) {
		BigDecimal sum = BigDecimal.valueOf(0);
		BigDecimal pow = BigDecimal.valueOf(0.5);
		for (int i = 0; i < Math.min(20, arr.length); i++) {
			sum = sum.add(BigDecimal.valueOf(arr[i]).multiply(pow));
			pow = pow.multiply(BigDecimal.valueOf(0.5));
		}
		System.out.println(sum);
		return sum.setScale(0, BigDecimal.ROUND_CEILING).intValue();
	}

	public static void main(String[] args) {


		int[] arr = new int[(int) 1e5];
		//int s = (int) 1e5;
		int s = 1;
		for (int i = 0; i < arr.length; i++) {
			//arr[i] = s--;
			//arr[i] = s;
			arr[i] = s++;
		}
		System.out.println(Solution.chiefHopper(arr));
		System.out.println(Solution.chiefHopper(new int[] {477,1931,3738,3921,2306,1823,3328,2057,661,3993,2967,3520,171,1739,1525,1817,209,3475,1902,2666,518,3283,3412,3040,3383,2331,1147,1460,1452,1800,1327,2280,82,1416,2200,2388,3238,1879,796,250,1872,114,121,2042,1853,1645,211,2061,1472,2464,726,1989,1746,489,1380,1128,2819,2527,2939,622,678,265,2902,1111,2032,1453,3850,1621}));

		System.out.println(Solution.chiefHopper(new int[] {4 ,4, 4}));
		System.out.println(Solution.chiefHopper(new int[] {3}));
		System.out.println(Solution.chiefHopper(new int[] {2}));
		System.out.println(Solution.chiefHopper(new int[] {1, 6, 4}));
		System.out.println(Solution.chiefHopper(new int[] {3, 4, 3, 2, 4}));
		System.out.println(Solution.chiefHopper(new int[] {4, 4, 4}));
	}
}
