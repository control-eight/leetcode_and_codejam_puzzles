package com.my.misc;

public class Test3 {

    public static void main(String[] args) {
        Node head = new Node(1);
        Node next = head;
        for (int i = 2; i <= 10; i++) {
            next.next = new Node(i);
            next = next.next;
        }
        print(head);
        print(new Test3().reverse(head));
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

        Node first = head;
        Node next = head.next;
        while (next != null) {
            Node tmp = next.next;
            next.next = head;
            head = next;
            next = tmp;
        }
        first.next = null;
        return head;
    }

    private static class Node {

        private int value;

        private Node next;

        public Node(int value) {
            this.value = value;
        }
    }
}
