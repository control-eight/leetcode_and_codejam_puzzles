package com.my.hackerrank.chief_hopper;

import java.math.BigDecimal;

/**
 * Created by alex.bykovsky on 5/12/18.
 */
public class Solution3 {

	public static void main(String[] args) {
		BigDecimal sum = BigDecimal.valueOf(0);
		BigDecimal pow = BigDecimal.valueOf(Math.pow(2, -20));
		for (int i = 0; i < 1e5; i++) {
			sum = sum.add(BigDecimal.valueOf(1e5).multiply(pow));
			pow = pow.multiply(BigDecimal.valueOf(0.5));
		}
		System.out.println(sum);
	}
}
