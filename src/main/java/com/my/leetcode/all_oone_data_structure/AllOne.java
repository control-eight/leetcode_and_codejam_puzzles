package com.my.leetcode.all_oone_data_structure;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AllOne {

    public static void main(String[] args) {
        //test1();
        AllOne allOne = new AllOne();
        allOne.inc("hello");
        allOne.inc("world");
        allOne.inc("hello");
        allOne.dec("world");
        allOne.inc("hello");
        allOne.inc("leet");
        System.out.println(allOne.getMaxKey());
        allOne.dec("hello");
        allOne.dec("hello");
        allOne.dec("hello");
        System.out.println(allOne.getMaxKey());
        System.out.println("---");
    }

    private static void test1() {
        AllOne allOne = new AllOne();
        allOne.inc("a");
        System.out.println(allOne.getMaxKey());
        System.out.println(allOne.getMinKey());
        allOne.dec("a");
        System.out.println(allOne.getMaxKey());
        System.out.println(allOne.getMinKey());

        allOne.inc("a");
        allOne.inc("a");
        allOne.inc("a");
        allOne.inc("b");
        System.out.println(allOne.getMaxKey());
        System.out.println(allOne.getMinKey());
    }

    private Node head;
    private Node tail;

    private Map<String, Node> keyToNode;
    private Map<Node, Set<String>> nodeToKey;

    /** Initialize your data structure here. */
    public AllOne() {
        keyToNode = new HashMap<>();
        nodeToKey = new HashMap<>();
        head = new Node(0);
        tail = head;
    }

    /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
    public void inc(String key) {
        if (!keyToNode.containsKey(key)) {
            putKey(key, addNextNode(head, 1));
        } else {
            Node node = keyToNode.get(key);
            putKey(key, addNextNode(node, node.value + 1));
            remove(key, node);
        }
    }

    /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
    public void dec(String key) {
        if (!keyToNode.containsKey(key)) {
            return;
        } else {
            Node node = keyToNode.get(key);
            if (node.value > 1) {
                Node next = addPrevNode(node, node.value - 1);
                putKey(key, next);
            } else {
                keyToNode.remove(key);
            }
            remove(key, node);
        }
    }

    private Node addNextNode(Node head, int value) {
        if (head.next != null && head.next.value == value) {
            return head.next;
        }
        Node node = new Node(value);
        Node tmpNext = head.next;

        if (head.next != null) {
            head.next.prev = node;
        }

        head.next = node;
        node.prev = head;
        node.next = tmpNext;

        if (tail == head) {
            tail = node;
        }

        return node;
    }

    private Node addPrevNode(Node head, int value) {
        if (head.prev != null && head.prev.value == value) {
            return head.prev;
        }
        Node node = new Node(value);
        Node tmpPrev = head.prev;

        if (head.prev != null) {
            head.prev.next = node;
        }

        head.prev = node;
        node.next = head;
        node.prev = tmpPrev;

        return node;
    }

    private void remove(String key, Node node) {
        nodeToKey.get(node).remove(key);
        if (nodeToKey.get(node).isEmpty()) {
            nodeToKey.remove(node);
        } else {
            return;
        }

        if (head == node) {
            throw new RuntimeException();
        }
        if (node.next != null) {
            node.next.prev = node.prev;
        } else if (tail == node) {
            tail = node.prev;
        }
        node.prev.next = node.next;
    }

    private void putKey(String key, Node node) {
        keyToNode.put(key, node);
        nodeToKey.putIfAbsent(node, new HashSet<>());
        nodeToKey.get(node).add(key);
    }

    /** Returns one of the keys with maximal value. */
    public String getMaxKey() {
        if (nodeToKey.isEmpty()) {
            return "";
        }
        return nodeToKey.get(tail).iterator().next();
    }

    /** Returns one of the keys with Minimal value. */
    public String getMinKey() {
        if (nodeToKey.isEmpty()) {
            return "";
        }
        return nodeToKey.get(head.next).iterator().next();
    }

    private static class Node {
        private int value;
        private Node next;
        private Node prev;

        public Node(int value) {
            this.value = value;
        }
    }
}
