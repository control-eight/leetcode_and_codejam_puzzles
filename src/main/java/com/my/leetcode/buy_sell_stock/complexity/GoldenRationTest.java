package com.my.leetcode.buy_sell_stock.complexity;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class GoldenRationTest {

    public static void main(String[] args) {
        //(1+sqrt(5))/2
        MathContext mc = new MathContext(12, RoundingMode.HALF_UP);
        BigDecimal gd = BigDecimal.valueOf(1).add(BigDecimal.valueOf(5).sqrt(mc)).divide(BigDecimal.valueOf(2), mc);
        System.out.println(gd);
        BigDecimal gdSquared = gd.pow(2).setScale(11, RoundingMode.HALF_UP);
        System.out.println(gdSquared);

        //(3+sqrt(5))/2
        BigDecimal gdSquared2 = BigDecimal.valueOf(3).add(BigDecimal.valueOf(5).sqrt(mc)).divide(BigDecimal.valueOf(2), mc);
        System.out.println(gdSquared2);
    }
}
