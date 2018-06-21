package com.my.leetcode.voters;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by alex.bykovsky on 10/7/17.
 */
public class Solution {

	public static void main(String[] args) {
		System.out.println(new Solution().electCandidates(new int[][] {
				{0,2,4,1},
				{0,1,2,4},
				{0,2,1,4},
				{1,2,4,0},
				{1,0,4,2},
				{2,4,0,1}
		}));
	}

	public int electCandidates(int[][] preferableCandidates) {

		int N = preferableCandidates[0].length;

		Map<Integer, Integer> personsPrefrableListPoint = new HashMap<>();
		init(preferableCandidates, personsPrefrableListPoint);

		boolean[] eliminatedCandidates = new boolean[N];
		for(int i = 0; i <= N - 1; i++) {

			int[] sortedCandidates = new int[N];

			for(int j = 0; j < preferableCandidates.length; j++) {

				int candidate = personsPrefrableListPoint.get(j);

				while(eliminatedCandidates[candidate]) {
					candidate++;
				}
				personsPrefrableListPoint.put(j, candidate);

				sortedCandidates[candidate]++;

			}

			int min = Integer.MAX_VALUE;
			int max = 0;

			int minIndex = -1;
			int maxIndex = -1;

			for(int k = 0; k < sortedCandidates.length; k++) {
				if(!eliminatedCandidates[k]) {
					if(sortedCandidates[k] < min) {
						min = sortedCandidates[k];
						minIndex = k;
					}

					if(sortedCandidates[k] > max) {
						max = sortedCandidates[k];
						maxIndex = k;
					}
				}
			}

			if(max > preferableCandidates.length/2) {
				return maxIndex;
			}

			eliminatedCandidates[minIndex] = true;
		}
		throw new RuntimeException();
	}

	private void init(int[][] preferableCandidates, Map<Integer, Integer> personsPrefrableListPoint) {

		for(int i = 0; i < preferableCandidates.length; i++) {
			personsPrefrableListPoint.put(i, 0);
		}

	}


}
