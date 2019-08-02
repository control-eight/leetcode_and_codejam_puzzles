package com.my.leetcode.exam_room;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class ExamRoom {

    public static void main(String[] args) {
        ExamRoom examRoom = new ExamRoom(10);
        System.out.println(examRoom.seat());
        //0
        System.out.println(examRoom.seat());
        //9
        System.out.println(examRoom.seat());
        //4
        System.out.println(examRoom.seat());
        //2
        examRoom.leave(4);
        System.out.println(examRoom.seat());
        //5
    }

    private Map<Integer, Distance> from = new HashMap<>();
    private Map<Integer, Distance> to = new HashMap<>();
    private PriorityQueue<Distance> pq = new PriorityQueue<>();
    private int N;

    public ExamRoom(int N) {
        this.N = N;
    }

    public int seat() {
        /*if (from.isEmpty()) {
            Distance d = new Distance(0, -1, N - 1, -1);
            from.put(0, d);
            to.put(0, d);
            pq.offer(d);
            return 0;
        } else {
            Distance d = pq.poll();
            while (d.distNext == -1) {
                d = pq.poll();
            }

            int distNext = d.distNext / 2;

            Distance prevFromD;
            Distance prevToD;
            Distance newFromD;
            Distance newToD;

            if (d.start + d.distNext == N - 1) {

                Distance newFromD


            } else {
                Distance fromD = new Distance(d.start, d.distPrev, distNext, d.prev);
                Distance toD = new Distance(d.start + distNext, distNext,
                        d.distNext - distNext, d.start);
            }





            pq.offer(fromD);
            pq.offer(toD);
            from.put(fromD.start, fromD);
            from.put(toD.start, toD);
            to.put(fromD.start, fromD);
            to.put(toD.start, toD);

            return toD.start;
        }*/
        return -1;
    }

    public void leave(int p) {

        Distance toD = to.get(p);
        if (toD != null && toD.prev != -1) {
            Distance fromD = from.get(toD.prev);
            fromD.distNext += toD.distNext;
            Distance newFromD = new Distance(fromD);
            pq.offer(newFromD);
            from.put(toD.prev, newFromD);
            fromD.distNext = -1;
        }
        to.remove(p);

        Distance fromD = from.get(p);
        if (fromD != null) {
            fromD.distNext = -1;
            from.remove(p);
        }
    }

    private static class Distance implements Comparable<Distance> {

        private int start;
        private int distPrev;
        private int distNext;
        private int prev;

        public Distance(Distance other) {
            this(other.start, other.distPrev, other.distNext, other.prev);
        }

        public Distance(int start, int distPrev, int distNext, int prev) {
            this.start = start;
            this.distPrev = distPrev;
            this.distNext = distNext;
            this.prev = prev;
        }

        @Override
        public int compareTo(Distance o) {
            return Integer.compare(Math.min(distPrev, distNext), Math.min(distPrev, distNext));
        }
    }
}
