package com.my.misc;

public class BSTMonoid {

    public Node init(long value, int index) {
        return new Node(value, index);
    }

    public class Node {

        private Node left;
        private Node right;
        private Container c;
        private Container min = new Container(Integer.MAX_VALUE, -1);
        private Container max = new Container(Integer.MIN_VALUE, -1);

        public Node(long value, int index) {
            c = new Container(value, index);
        }
    }

    public void insert(Node parent, long newValue, int index) {
        if (newValue < parent.c.value) {
            if (newValue < parent.min.value) {
                parent.min.value = newValue;
                parent.min.index = index;
            }
            if (parent.left == null) {
                parent.left = new Node(newValue, index);
            } else {
                insert(parent.left, newValue, index);
            }
        } else if (newValue > parent.c.value) {
            if (newValue > parent.max.value) {
                parent.max.value = newValue;
                parent.max.index = index;
            }
            if (parent.right == null) {
                parent.right = new Node(newValue, index);
            } else {
                insert(parent.right, newValue, index);
            }
        } else {
            parent.c.index = index;
        }
    }

    public int findOptLessOrEqual(Node parent, long value) {
        if (parent == null) {
            return -1;
        }

        if (value >= parent.c.value) {
            if (parent.c.value < parent.min.value) {
                return parent.c.index;
            } else {
                return parent.min.index;
            }
        } else {
            return findOptLessOrEqual(parent.left, value);
        }
    }

    public int findOptGreaterOrEqual(Node parent, long value) {
        if (parent == null) {
            return -1;
        }

        if (value >= parent.c.value) {
            if (parent.c.value < parent.min.value) {
                return parent.c.index;
            } else {
                return parent.min.index;
            }
        } else {
            return findOptGreaterOrEqual(parent.left, value);
        }
    }

    private static class Container {
        private long value;
        private int index;

        public Container(long value, int index) {
            this.value = value;
            this.index = index;
        }
    }
}
