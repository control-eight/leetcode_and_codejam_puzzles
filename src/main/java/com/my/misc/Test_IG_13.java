package com.my.misc;

import java.util.*;

public class Test_IG_13 {

    public static void main(String[] args) {
        test1();
        test2();
        test3();
        test4();
        test5();
        test6();
        //testN();
        testN1();
    }

    private static void test1() {
        System.out.println("test1");
        Test_IG_13 t1 = new Test_IG_13(1000);
        t1.add(3);
        t1.add(3);
        t1.add(1);
        t1.add(2);
        System.out.println(t1.get());
        System.out.println(t1.get());
        System.out.println(t1.get());
        System.out.println(t1.get());
    }

    private static void test2() {
        System.out.println("test2");
        Test_IG_13 t1 = new Test_IG_13(1000);
        t1.add(1);
        System.out.println(t1.get());
        t1.add(2);
        System.out.println(t1.get());
        t1.add(3);
        System.out.println(t1.get());
        t1.add(3);
        t1.add(4);
        t1.add(5);
        System.out.println(t1.get());
        System.out.println(t1.get());
        System.out.println(t1.get());
    }

    private static void test3() {
        System.out.println("test3");
        Test_IG_13 t1 = new Test_IG_13(1000);
        t1.add(4);
        t1.add(4);
        t1.add(4);
        t1.add(5);
        System.out.println(t1.get());
        System.out.println(t1.get());
        System.out.println(t1.get());
        System.out.println(t1.get());
    }

    private static void test4() {
        System.out.println("test4");
        Test_IG_13 t1 = new Test_IG_13(1000);
        t1.add(1);
        t1.add(3);
        t1.add(3);
        t1.add(3);
        System.out.println(t1.get());
        System.out.println(t1.get());
        System.out.println(t1.get());
        t1.add(4);
        System.out.println(t1.get());
        System.out.println(t1.get());
    }

    private static void test5() {
        System.out.println("test5");
        Test_IG_13 t1 = new Test_IG_13(1000);
        t1.add(1);
        t1.add(2);
        System.out.println(t1.get());
        System.out.println(t1.get());
        t1.add(2);
        t1.add(3);
        System.out.println(t1.get());
        System.out.println(t1.get());
    }

    private static void test6() {
        System.out.println("test6");
        Test_IG_13 t1 = new Test_IG_13(1000);
        t1.add(1);
        t1.add(1);
        System.out.println(t1.get());
        System.out.println(t1.get());
        t1.add(1);
        t1.add(2);
        System.out.println(t1.get());
        System.out.println(t1.get());
    }

    /**
     * 200 x add 1
     * 100 x get
     * 10 x add 2
     * add 1
     * 10 x add 2
     * ALL x get
     */
    private static void testN() {
        System.out.println("testN");
        Test_IG_13 t1 = new Test_IG_13(1000);
        for (int i = 1; i <= 200; i++) {
            t1.add(1);
        }
        for (int i = 1; i <= 100; i++) {
            System.out.println(t1.get());
            //t1.get();
        }
        for (int i = 1; i <= 10; i++) {
            t1.add(2);
        }
        t1.add(1);
        for (int i = 1; i <= 10; i++) {
            t1.add(2);
        }
        try {
            for (int i = 1; i <= 1e100; i++) {
                System.out.println(t1.get());
            }
        } catch (RuntimeException e) {}
    }

    /**
     * 20 x add 1
     * 10 x get
     * 5 x add 2
     * 5 x get
     * 2 x add 3
     * add 2
     * add 3
     * ALL x get
     */
    private static void testN1() {
        System.out.println("testN1");
        Test_IG_13 t1 = new Test_IG_13(1000);
        for (int i = 1; i <= 20; i++) {
            t1.add(1);
        }
        for (int i = 1; i <= 10; i++) {
            System.out.println(t1.get());
        }
        for (int i = 1; i <= 5; i++) {
            t1.add(2);
        }
        for (int i = 1; i <= 5; i++) {
            System.out.println(t1.get());
        }
        for (int i = 1; i <= 2; i++) {
            t1.add(3);
        }
        t1.add(2);
        t1.add(3);
        try {
            for (int i = 1; i <= 1e100; i++) {
                System.out.println(t1.get());
            }
        } catch (RuntimeException e) {}
    }



    private final int rateLimit;
    private Map<Integer, Integer> priorities = new HashMap<>();
    private PriorityQueue<Pair<Queue<Integer>, Integer>> requests = new PriorityQueue<>();
    private ArrayList<Queue<Integer>> index;

    public Test_IG_13(int rateLimit) {
        this.rateLimit = rateLimit;
        this.index = new ArrayList<>(Collections.nCopies(rateLimit, null));
    }

    public void add(int client_id) {
        int newPriority = priorities.getOrDefault(client_id, -1) + 1;

        if (newPriority > rateLimit) {
            throw new RuntimeException("Rate limit: " + rateLimit + " for client = " + client_id);
        }

        priorities.put(client_id, newPriority);
        if (index.get(newPriority) == null) {
            LinkedList<Integer> newQueue = new LinkedList<>();
            requests.add(new Pair<>(newQueue, newPriority));
            index.set(newPriority, newQueue);
        }
        index.get(newPriority).offer(client_id);
    }

    public int get() {
        if (requests.isEmpty()) {
            throw new RuntimeException("Get before add");
        }

        int result = requests.peek().key.poll();
        if (requests.peek().key.isEmpty()) {
            index.set(requests.peek().value, null);
            requests.poll();
        }
        return result;
    }

    private static class Pair<K, V extends Comparable<V>> implements Comparable<Pair<K, V>> {
        private K key;
        private V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public int compareTo(Pair<K, V> o) {
            return value.compareTo(o.value);
        }
    }
}
