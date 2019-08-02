package com.my.leetcode.online_election;

import java.util.TreeMap;

public class TopVotedCandidate {

    public static void main(String[] args) {
        TopVotedCandidate topVotedCandidate = new TopVotedCandidate(
                new int[] {0,1,1,0,0,1,0}, new int[] {0,5,10,15,20,25,30}
        );
        System.out.println(topVotedCandidate.q(3));
        System.out.println(topVotedCandidate.q(12));
        System.out.println(topVotedCandidate.q(25));
        System.out.println(topVotedCandidate.q(15));
        System.out.println(topVotedCandidate.q(24));
        System.out.println(topVotedCandidate.q(8));
        //0,1,1,0,0,1
    }

    private TreeMap<Integer, Integer> map = new TreeMap<>();

    public TopVotedCandidate(int[] persons, int[] times) {
        int[] votes = new int[persons.length];
        int maxCount = 0;
        int maxCandidate = -1;

        for (int i = 0; i < persons.length; i++) {
            votes[persons[i]]++;
            if (votes[persons[i]] >= maxCount) {
                maxCount = votes[persons[i]];
                maxCandidate = persons[i];
            }
            map.put(times[i], maxCandidate);
        }
    }

    public int q(int t) {
        return map.floorEntry(t).getValue();
    }
}
