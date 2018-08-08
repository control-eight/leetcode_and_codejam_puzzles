package com.my.leetcode.robot_room_cleaner;

import java.util.*;

public class Solution {

    public enum Direction {
        LEFT,
        RIGHT,
        TOP,
        DOWN
    }

    public void cleanRoom(Robot robot) {
        robot.clean();
        startMoving(robot, new Node(), Direction.TOP, new Position(0, 0), new HashMap<>());
    }

    private void startMoving(Robot robot, Node current, Direction dir, Position pos, Map<Position, Node> visited) {
        visited.put(pos, current);
        while (true) {
            int count = current.edges.size();
            while (true) {
                if (!current.edges.containsKey(dir)) {
                    if (robot.move()) {
                        robot.clean();
                        current = createNextNode(current, dir);
                        pos = nextPos(dir, pos);
                        visited.put(pos, current);
                        linkToSibling(current, visited, pos);
                        break;
                    } else {
                        createNextClosed(current, dir);
                        robot.turnLeft();
                        dir = nextDirection(dir);
                    }
                    count++;
                } else {
                    robot.turnLeft();
                    dir = nextDirection(dir);
                }
                if (count == 4) {
                    List<Node> path = bfsUndiscovered(current);
                    if (path.isEmpty()) return;
                    Node prev = path.get(0); //== current
                    for (Node node : path.subList(1, path.size())) {
                        Direction toDir = prev.inverted.get(node);
                        dir = turnUntil(dir, toDir, robot);
                        robot.move();
                        pos = nextPos(dir, pos);
                        prev = node;
                    }
                    current = prev;
                    break;
                }
            }
        }
    }

    private void linkToSibling(Node current, Map<Position, Node> visited, Position pos) {
        for (Direction direction : Direction.values()) {
            Position possibleNext = nextPos(direction, pos);
            if (visited.containsKey(possibleNext)) {
                if (!current.edges.containsKey(direction)) {
                    addExistingNode(current, direction, visited, possibleNext);
                }
            }
        }
    }

    private void addExistingNode(Node current, Direction dir, Map<Position, Node> visited, Position possibleNext) {
        current.edges.put(dir, new Edge(current, visited.get(possibleNext)));
        current.inverted.put(visited.get(possibleNext), dir);
        Direction reversedDir = reverse(dir);
        visited.get(possibleNext).edges.put(reversedDir, new Edge(visited.get(possibleNext), current));
        visited.get(possibleNext).inverted.put(current, reversedDir);
    }

    private static List<Node> bfsUndiscovered(Node current) {
        Deque<Node> siblings = null;
        Map<Node, Integer> visited = new HashMap<>();
        Node found = null;
        int distance = 1;
        do {
            Node next;
            if (siblings == null) {
                siblings = new LinkedList<>();
                next = current;
                visited.put(current, 0);
            } else {
                next = siblings.pollFirst();
            }
            for (Edge edge : next.edges.values()) {
                if (edge.right != null && !visited.containsKey(edge.right)) {
                    visited.put(edge.right, distance);
                    siblings.offerLast(edge.right);
                    if (!edge.right.isDiscoveredFully()) {
                        siblings.clear();
                        found = edge.right;
                        break;
                    }
                }
            }
            distance++;
        } while (!siblings.isEmpty());

        if (found == null) return Collections.emptyList();

        distance = visited.get(found);
        Node target = found;
        List<Node> path = new LinkedList<>();
        path.add(target);
        while (target != current) {
            for (Edge edge : target.edges.values()) {
                if (edge.right != null && visited.containsKey(edge.right) && visited.get(edge.right) < distance) {
                    distance = visited.get(edge.right);
                    visited.remove(edge.right);
                    path.add(edge.right);
                    target = edge.right;
                    break;
                }
            }
        }
        Collections.reverse(path);
        return path;
    }

    private Node createNextNode(Node current, Direction dir) {
        Node nextNode = new Node();
        current.edges.put(dir, new Edge(current, nextNode));
        current.inverted.put(nextNode, dir);
        Direction reversedDir = reverse(dir);
        nextNode.edges.put(reversedDir, new Edge(nextNode, current));
        nextNode.inverted.put(current, reversedDir);
        return nextNode;
    }

    private void createNextClosed(Node current, Direction dir) {
        current.edges.put(dir, new Edge(current, null));
    }

    private Direction reverse(Direction dir) {
        switch (dir) {
            case TOP: return Direction.DOWN;
            case DOWN: return Direction.TOP;
            case LEFT: return Direction.RIGHT;
            case RIGHT: return Direction.LEFT;
        }
        throw new RuntimeException(dir.name());
    }

    private Position nextPos(Direction to, Position pos) {
        switch (to) {
            case TOP: return new Position(pos.r - 1, pos.c);
            case LEFT: return new Position(pos.r, pos.c - 1);
            case DOWN: return new Position(pos.r + 1, pos.c);
            case RIGHT: return new Position(pos.r, pos.c + 1);
        }
        throw new RuntimeException(to.name());
    }

    private Direction nextDirection(Direction dir) {
        switch (dir) {
            case TOP: return Direction.LEFT;
            case LEFT: return Direction.DOWN;
            case DOWN: return Direction.RIGHT;
            case RIGHT: return Direction.TOP;
        }
        throw new RuntimeException(dir.name());
    }

    private Direction turnUntil(Direction from, Direction to, Robot robot) {
        while (from != to) {
            robot.turnLeft();
            from = nextDirection(from);
        }
        return to;
    }

    private static class Node {
        private Map<Direction, Edge> edges = new HashMap<>();
        private Map<Node, Direction> inverted = new HashMap<>();
        private boolean isDiscoveredFully() {
            return edges.size() == 4;
        }
    }

    private static class Edge {
        private Node left;
        private Node right;
        public Edge(Node left, Node right) {
            this.left = left;
            this.right = right;
        }
    }

    private static class Position {
        private int r;
        private int c;

        public Position(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Position position = (Position) o;
            return r == position.r &&
                    c == position.c;
        }

        @Override
        public int hashCode() {
            return Objects.hash(r, c);
        }
    }
}


