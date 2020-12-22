package com.my.misc;

public class Test2 {

    public static void main(String[] args) {
        Node head = new Node(1);
        Node next = head;
        for (int i = 2; i <= 10; i++) {
            next.next = new Node(i);
            next = next.next;
        }
        print(head);
        print(new Test2().reverse(head));
    }

    private static void print(Node head) {
        while (head != null) {
            System.out.print(head.value + " ");
            head = head.next;
        }
        System.out.println();
    }

    public Node reverse(Node head) {
        if (head.next == null) return head;

        Node tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }

        tail.next = head;
        head = head.next;
        tail.next.next = null;
        while (head != tail) {
            Node tmp = head.next;
            head.next = tail.next;
            tail.next = head;
            head = tmp;
        }
        return tail;
    }

    private static class Node {

        private int value;

        private Node next;

        public Node(int value) {
            this.value = value;
        }
    }
}
