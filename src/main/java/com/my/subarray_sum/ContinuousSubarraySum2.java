package com.my.subarray_sum;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by alex.bykovsky on 9/7/17.
 */
public class ContinuousSubarraySum2 {

	public static void main(String[] args) {
		System.out.println(new ContinuousSubarraySum2().checkSubarraySum(new int[] {23, 2, 4, 6, 7}, 6));
		//TRUE, 2, 4 = 6
		System.out.println(new ContinuousSubarraySum2().checkSubarraySum(new int[] {23, 2, 6, 4, 7}, 6));
		//TRUE 23, 2, 6, 4, 7 = 42
		System.out.println(new ContinuousSubarraySum2().checkSubarraySum(new int[] {23, 2, 6, 4, 7, 10}, 6));
		//TRUE 23, 2, 6, 4, 7 = 42
		System.out.println(new ContinuousSubarraySum2().checkSubarraySum(new int[] {0, 0}, 0));
		//TRUE, 0, 0 = 0
		System.out.println(new ContinuousSubarraySum2().checkSubarraySum(new int[] {23, 2, 4, 6, 7}, -6));
		//TRUE 2, 4 = 6
		System.out.println(new ContinuousSubarraySum2().checkSubarraySum(new int[] {0}, -1));
		//FALSE
		System.out.println(new ContinuousSubarraySum2().checkSubarraySum(new int[] {0, 0}, -1));
		//TRUE 0, 0 = 0
		System.out.println(new ContinuousSubarraySum2().checkSubarraySum(new int[] {5, 2, 4}, 5));
		//FALSE
		System.out.println(new ContinuousSubarraySum2().checkSubarraySum(new int[] {1, 5, 5}, 5));
		//TRUE 5, 5 = 10
		System.out.println(new ContinuousSubarraySum2().checkSubarraySum(new int[] {42, 7}, 7));
		//TRUE 5, 5 = 10
		System.out.println(new ContinuousSubarraySum2().checkSubarraySum(new int[] {11, 5, 3, 2}, 10));
		//TRUE 5, 3, 2 = 10
		System.out.println(new ContinuousSubarraySum2().checkSubarraySum(new int[] {323, 358, 291, 176}, 75));
		//TRUE 358, 291, 176 = 825
		System.out.println(new ContinuousSubarraySum2().checkSubarraySum(new int[] {470,161,377,184,118,91,413,73,224,167,505,413,521,5,7,372,393,523,211,479,90,516,238,467,410,51,337,31,442,297,100,75,260,33,490,477,21,374,233,41,215,87,84,153,271,241,169,275,323,358,291,176,423,426,296,175,413,423,387,416,27,266,257,136,27,155,77,142,60,335,401,443,52,153,345,71,117,443,177,238,433,464,323,79,156,429,79,327,64,335,92,147,350,480,277,335,97,317,420,453}, 75));
		//TRUE
		System.out.println(new ContinuousSubarraySum2().checkSubarraySum(new int[] {422,224,184,178,189,290,196,236,281,464,351,193,49,76,0,298,193,176,158,514,312,143,475,322,206,67,524,424,76,99,473,256,364,292,141,186,190,69,433,205,93,72,476,393,512,468,160,201,226,394,47,140,389,51,142,135,349,244,16,356,440,188,16,133,58,394,7,517,56,480,400,146,169,439,102,374,370,242,4,264,120,218,196,173,215,411,501,321,319,147,369,458,319,174,379,46,129,353,330,101}, 479));
		//TRUE
	}

	public boolean checkSubarraySum(int[] nums, int k) {

		if(nums.length <= 1) return false;

		if(k == 0) {
			for(int i = 0; i < nums.length - 1; i++) {
				if(nums[i] == 0 && nums[i + 1] == 0) return true;
			}
		} else {

			Set<Integer> sumsIndex = new HashSet<>();
			sumsIndex.add(nums[0] % k);
			k = Math.abs(k);

			int partialSum = nums[0];

			for(int i = 1; i < nums.length; i++) {
				partialSum += nums[i];
				int number = partialSum % k;
				if(number == 0 || sumsIndex.contains(number)) return true;
				sumsIndex.add(number);
			}
		}

		return false;
	}
}
