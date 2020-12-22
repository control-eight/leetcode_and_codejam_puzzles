package com.my.misc;

import java.util.*;

public class Test_IG_12 {

    public static void main(String[] args) {
        test1();
        test2();
        test3();
        test4();
    }

    private static void test1() {
        System.out.println("test1");
        Test_IG_12 t1 = new Test_IG_12();
        t1.add(1);
        t1.add(2);
        t1.add(3);
        t1.add(3);
        System.out.println(t1.get());
        System.out.println(t1.get());
        System.out.println(t1.get());
        System.out.println(t1.get());
    }

    private static void test2() {
        System.out.println("test2");
        Test_IG_12 t1 = new Test_IG_12();
        t1.add(1);
        System.out.println(t1.get());
        t1.add(2);
        System.out.println(t1.get());
        t1.add(3);
        System.out.println(t1.get());
        t1.add(4);
        t1.add(5);
        t1.add(3);
        System.out.println(t1.get());
        System.out.println(t1.get());
        System.out.println(t1.get());
    }

    private static void test3() {
        System.out.println("test3");
        Test_IG_12 t1 = new Test_IG_12();
        t1.add(1);
        t1.add(2);
        t1.add(3);
        t1.add(3);
        System.out.println(t1.get());
        System.out.println(t1.get());
        System.out.println(t1.get());
        System.out.println(t1.get());
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
        Test_IG_12 t1 = new Test_IG_12();
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

    private Map<Integer, Integer> priorities = new HashMap<>();
    private PriorityQueue<Pair> requests = new PriorityQueue<>();

    public void add(int client_id) {
        int newPriority = priorities.getOrDefault(client_id, -1) + 1;
        priorities.put(client_id, newPriority);
        requests.add(new Pair(client_id, newPriority));
    }

    public int get() {
        if (requests.isEmpty()) {
            throw new RuntimeException("Get before add");
        }
        return requests.poll().clientId;
    }

    private static class Pair implements Comparable<Pair> {
        private int clientId;
        private int priority;

        public Pair(int clientId, int priority) {
            this.clientId = clientId;
            this.priority = priority;
        }

        @Override
        public int compareTo(Pair o) {
            return Integer.compare(this.priority, o.priority);
        }
    }
}
