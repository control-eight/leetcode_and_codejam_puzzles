package com.my.misc;

import org.apache.commons.math3.distribution.EnumeratedIntegerDistribution;
import org.apache.commons.math3.distribution.IntegerDistribution;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Test_IG_Benchmark {

    private static final int MAX_NUMBER_REQUESTS_PER_CLIENT = 1000;
    private static int MAX_REQUESTS = 40_000_000;

    public static void main(String[] args) {
        int[] singletons = new int[MAX_NUMBER_REQUESTS_PER_CLIENT];
        for (int i = 1; i < singletons.length; i++) {
            singletons[i - 1] = i;
        }
        double[] probabilities = defineDs2();
        System.out.println("MAX_REQUESTS = " + MAX_REQUESTS);
        System.out.println("MAX_NUMBER_REQUESTS_PER_CLIENT = " + MAX_NUMBER_REQUESTS_PER_CLIENT);

        EnumeratedIntegerDistribution d = new EnumeratedIntegerDistribution(singletons, probabilities);

        Test_IG_11 test_ig_11 = new Test_IG_11();
        Test_IG_12 test_ig_12 = new Test_IG_12();
        Test_IG_13 test_ig_13 = new Test_IG_13(MAX_NUMBER_REQUESTS_PER_CLIENT);

        System.out.println("Test NodeList With Queue Buckets (Order is not preserved)");
        test(test_ig_11::add, test_ig_11::get, d);
        System.out.println("Test Plain PriorityQueue without further opts");
        test(test_ig_12::add, test_ig_12::get, d);
        System.out.println("Test PriorityQueue with Queue Buckets and Bucket index");
        test(test_ig_13::add, test_ig_13::get, d);
    }

    private static double[] defineDs1() {
        double[] probabilities = new double[MAX_NUMBER_REQUESTS_PER_CLIENT];
        probabilities[0] = 0.8469376;
        probabilities[1] = 0.1048576;
        probabilities[2] = 0.0131072;
        probabilities[3] = 0.0065536;
        probabilities[4] = 0.0016384;
        probabilities[5] = 8.192E-4;
        probabilities[6] = 4.096E-4;
        probabilities[7] = 2.048E-4;
        probabilities[8] = 1.024E-4;
        for (int i = 9; i < probabilities.length; i++) {
            probabilities[i] = 2.56E-5;
        }
        return probabilities;
    }

    private static double[] defineDs2() {
        double[] probabilities = new double[MAX_NUMBER_REQUESTS_PER_CLIENT];
        probabilities[0] = 0.2443;
        probabilities[1] = 0.215;
        probabilities[2] = 0.174;
        probabilities[3] = 0.132;
        probabilities[4] = 0.11;
        probabilities[5] = 0.08;
        probabilities[6] = 0.03;
        probabilities[7] = 0.003;
        probabilities[8] = 0.0016304;
        double sum = 0;
        for (int i = 0; i <= 8; i++) {
            sum += probabilities[i];
        }
        double left = 1.0 - sum;
        double equalPortion = left/(probabilities.length - 9);
        for (int i = 9; i < probabilities.length; i++) {
            probabilities[i] = equalPortion;
            sum += equalPortion;
        }
        return probabilities;
    }

    private static void test(Consumer<Integer> add, Supplier<Integer> get, IntegerDistribution d) {
        int requests = 0;
        int currentClient = 1;
        Map<Integer, Integer> clientCounter = new HashMap<>();
        int stat_max = 0;
        while (requests < MAX_REQUESTS) {
            int requestsForClient = d.sample();
            requests += requestsForClient;
            clientCounter.put(currentClient, requestsForClient);
            currentClient++;
            stat_max = Math.max(stat_max, requestsForClient);
        }
        System.out.println("stat_number_of_clients = " + clientCounter.size());
        System.out.println("stat_max_requests_per_client = " + stat_max);
        System.out.println("stat_avg = " + ((double) requests / clientCounter.size()));
        long start = System.currentTimeMillis();
        for (Map.Entry<Integer, Integer> entry : clientCounter.entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                add.accept(entry.getKey());
            }
        }
        try {
            for (int i = 1; i <= 1e100; i++) {
                get.get();
            }
        } catch (RuntimeException e) {}
        System.out.println((System.currentTimeMillis() - start) + "ms");
    }
}
