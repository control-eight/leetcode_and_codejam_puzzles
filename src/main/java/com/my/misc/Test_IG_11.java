package com.my.misc;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Test_IG_11 {

    public static void main(String[] args) {
        /*test1();
        test2();
        test3();
        test4();
        test5();
        test6();
        testN();*/
        testN1();
    }

    private static void test1() {
        System.out.println("test1");
        Test_IG_11 t1 = new Test_IG_11();
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
        Test_IG_11 t1 = new Test_IG_11();
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
        Test_IG_11 t1 = new Test_IG_11();
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
        Test_IG_11 t1 = new Test_IG_11();
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
        Test_IG_11 t1 = new Test_IG_11();
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
        Test_IG_11 t1 = new Test_IG_11();
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
        Test_IG_11 t1 = new Test_IG_11();
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
        for (int i = 1; i <= 1e100; i++) {
            System.out.println(t1.get());
        }
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
        Test_IG_11 t1 = new Test_IG_11();
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
        for (int i = 1; i <= 1e100; i++) {
            System.out.println(t1.get());
        }
    }

    private Map<Integer, Integer> priorities = new HashMap<>();
    private Map<Integer, NodeList.Node> buckets = new HashMap<>();
    private NodeList nodeList = new NodeList();

    public void add(int ip) {
        Integer newPriority = priorities.getOrDefault(ip, -1) + 1;
        priorities.put(ip, newPriority);

        NodeList.Node newNode = buckets.getOrDefault(newPriority, null);
        if (newNode == null) {
            newNode = nodeList.insertAfter(buckets
                    .getOrDefault(Math.max(newPriority - 1, 0), null), newPriority);
        }

        buckets.putIfAbsent(newPriority, newNode);
        buckets.get(newPriority).requests.offer(ip);
    }

    public int get() {
        if (nodeList.head == null) {
            throw new RuntimeException("Get before add");
        }
        int result = nodeList.head.requests.poll();
        if (nodeList.head.requests.isEmpty()) {
            buckets.remove(nodeList.head.priority);
            nodeList.deleteHead();
        }
        return result;
    }

    private class NodeList {

        Node head;

        class Node {

            int priority;
            Queue<Integer> requests = new LinkedList<>();
            Node next;

            Node(int priority) {
                this.priority = priority;
            }
        }

        // Method to insert a new node
        public Node insertAfter(Node node, int priority) {
            Node newNode = new Node(priority);
            //head is empty
            if (head == null) {
                head = newNode;
            //before head
            } else if (node == null) {
                Node tmp = head;
                head = newNode;
                head.next = tmp;
            //after head
            } else {
                Node tmp = node.next;
                node.next = newNode;
                newNode.next = tmp;
            }
            return newNode;
        }

        public void deleteHead() {
            head = head.next;
        }
    }
}
