package com.my.leetcode.buy_sell_stock;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] args) throws IOException {
        InputStream fis = Test.class.getResourceAsStream("/1");

        BufferedReader br = new BufferedReader(new InputStreamReader(fis));

        String line;
        int i = 1;
        List<String> rows = new ArrayList<>();
        List<Double> prices = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            if (i % 9 == 0) {
                rows.add(line);
                prices.add(Double.parseDouble(line.replaceAll(" PLN", "")
                        .replaceAll("PLN ", "")));
            }
            i++;
        }
        System.out.println(prices.size());
        System.out.println(prices.stream().reduce(Double::sum).get() + 3.50);
        for (Double price : prices) {
            if (price > 10) {
                System.out.println(price);
            }
        }
    }
}
