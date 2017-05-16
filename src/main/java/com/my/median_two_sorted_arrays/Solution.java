package com.my.median_two_sorted_arrays;

/**
 * Created by alex.bykovsky on 4/4/17.
 */
public class Solution {

	public double findMedianSortedArrays(int[] nums1, int[] nums2) {

		if(nums1.length == 0) {
			return findMedian(nums2);
		}

		if(nums2.length == 0) {
			return findMedian(nums1);
		}

		int[] less;
		int[] bigger;

		if(nums1[nums1.length/2] <= nums2[nums2.length/2]) {
			less = nums1;
			bigger = nums2;
		} else {
			less = nums2;
			bigger = nums1;
		}

		int medianIndex = (nums1.length + nums2.length) / 2 ;
		boolean medianEven = (nums1.length + nums2.length) % 2 == 0;

		int startLessIndex;

		int correctness = 0;
		if(nums1[nums1.length/2] == nums2[nums2.length/2]) {
			startLessIndex = less.length/2 - 1;
			if(startLessIndex < 0) {
				startLessIndex = 0;
			}
			correctness = 1;
		} else {
			startLessIndex = less.length/2;
		}

		int endLessIndex = less.length;
		int midLessIndex = startLessIndex/* + (endLessIndex - startLessIndex)/2*/;

		int startBiggerIndex = 0;
		int endBiggerIndex = bigger.length/2 + 1;

		if(bigger[0] > less[less.length - 1]) {

			if(bigger.length < less.length) {
				if(medianEven) {
					return ((double) less[medianIndex] + less[medianIndex - 1]) / 2;
				} else {
					return less[medianIndex];
				}
			} else if (bigger.length == less.length) {
				return ((double) less[less.length - 1] + bigger[0]) / 2;
			} else {
				if(medianEven) {
					return ((double) bigger[medianIndex - less.length] + bigger[medianIndex - less.length - 1]) / 2;
				} else {
					return bigger[medianIndex - less.length];
				}
			}
		}

		while (true) {

			int indexInBigger = binarySearch(bigger, startBiggerIndex, endBiggerIndex, less[midLessIndex], endBiggerIndex);

			if(indexInBigger == -1) {
				endLessIndex = midLessIndex;
				midLessIndex = startLessIndex + (endLessIndex - startLessIndex)/2;
			} else {

				//one plus right bound in bigger
				int allIndex = midLessIndex + indexInBigger + 1;

				if(allIndex == medianIndex) {
					int previous = 0;
					int next;
					if(medianEven) {
						previous = (indexInBigger == 0)? less[midLessIndex]: Math.max(bigger[indexInBigger - 1], less[midLessIndex]);
						next = Math.min(midLessIndex == endLessIndex - 1? Integer.MAX_VALUE:
								less[midLessIndex + 1], bigger[indexInBigger]);
					} else {
						next = bigger[indexInBigger];
					}

					int indexInLess = binarySearch(less, midLessIndex, endLessIndex, next, endLessIndex);
					if(indexInLess - midLessIndex > 1) {
						next = less[midLessIndex + 1];
					}

					return calcMedian(medianEven, previous, next);
				} else if(allIndex - 1 == medianIndex) {
					int previous = 0;
					if(medianEven) {
						previous = (indexInBigger == 0)? less[midLessIndex - 1]: Math.max(bigger[indexInBigger - 1], less[midLessIndex - 1]);
					}
					int next = less[midLessIndex];
					return calcMedian(medianEven, previous, next);
				} else if (allIndex + correctness < medianIndex) {
					int previous = 0;
					int next;

					if(medianEven) {

						int biggestInLessBefore = binarySearch(less, midLessIndex, endLessIndex, bigger[medianIndex - midLessIndex - 2], endLessIndex);
						if(biggestInLessBefore == -1) {
							biggestInLessBefore = endLessIndex - 1;
						} else {
							biggestInLessBefore--;
						}

						previous = bigger[medianIndex - biggestInLessBefore - 1];
						next = Math.min(biggestInLessBefore == endLessIndex - 1? Integer.MAX_VALUE:
								less[biggestInLessBefore], bigger[medianIndex - biggestInLessBefore]);
					} else {

						int biggestInLessBefore = binarySearch(less, midLessIndex, endLessIndex, bigger[medianIndex - midLessIndex - 1], endLessIndex);
						if(biggestInLessBefore == -1) {
							biggestInLessBefore = endLessIndex - 1;
						} else {
							biggestInLessBefore--;
						}

						next = bigger[medianIndex - biggestInLessBefore];
					}

					return calcMedian(medianEven, previous, next);
				}

				endLessIndex = midLessIndex;
				endBiggerIndex = indexInBigger + 1;

				midLessIndex = startLessIndex + (endLessIndex - startLessIndex)/2;
			}
		}
	}

	private int binarySearch(int[] arr, int start, int end, int value, int fEnd) {

		while (start < end) {

			int mid = start + (end - start)/2;

			if(arr[mid] <= value) {
				if(mid == fEnd - 1) {
					if(arr[mid] == value) {
						return mid;
					}
					return -1;
				} else if(arr[mid + 1] >= value) {
					return mid + 1;
				}
				if(arr[mid] == value) {
					return mid;
				}
			}

			if(arr[mid] >= value) {
				if(mid == 0) {
					return mid;
				} else if(mid == end - 1 && arr[mid - 1] < value) {
					return mid;
				} else if(arr[mid - 1] <= value) {
					return mid;
				}
				if(arr[mid] == value) {
					return mid;
				}
			}

			if(arr[mid] < value) {
				return binarySearch(arr, mid, end, value, fEnd);
			} else {
				return binarySearch(arr, start, mid, value, fEnd);
			}
		}

		return -1;
	}

	private double findMedian(int[] nums2) {
		if(nums2.length % 2 == 0) {
			return ((double) nums2[nums2.length/2] + nums2[nums2.length/2-1])/2;
		} else {
			return (double) nums2[nums2.length/2];
		}
	}

	private double calcMedian(boolean medianEven, int previous, int next) {
		if(medianEven) {
			return ((double)previous + next)/2;
		} else {
			return next;
		}
	}

	public static void main(String[] args) {

		/*System.out.println(new Solution().binarySearch(new int[]{1,2,3}, 0, 3, 2));
		System.out.println(new Solution().binarySearch(new int[]{1,2,4}, 0, 3, 3));
		System.out.println(new Solution().binarySearch(new int[]{2,4,6}, 0, 3, 7));
		System.out.println(new Solution().binarySearch(new int[]{2,4,6}, 0, 3, 1));
		System.out.println(new Solution().binarySearch(new int[]{1,2,7,8,12,15}, 0, 6, 9));*/

		System.out.println(new Solution().findMedianSortedArrays(new int[]{7,8,9},new int[]{1,2,3,4,5,6,10}));

		System.out.println(new Solution().findMedianSortedArrays(new int[]{4,7,8,9},new int[]{1,2,3,5,6}));

		System.out.println(new Solution().findMedianSortedArrays(new int[]{5,6,7},new int[]{1,2,3,4,8}));
		System.out.println(new Solution().findMedianSortedArrays(new int[]{1,2,4},new int[]{3,5,6}));
		System.out.println(new Solution().findMedianSortedArrays(new int[]{1,2,4},new int[]{3,5,6,7,8,9}));
		System.out.println(new Solution().findMedianSortedArrays(new int[]{1,3,4},new int[]{2,5,6}));
		System.out.println(new Solution().findMedianSortedArrays(new int[]{1,2,3},new int[]{4,5,6,7,8}));
		System.out.println(new Solution().findMedianSortedArrays(new int[]{1,2,3},new int[]{4,5,6,7}));
		System.out.println(new Solution().findMedianSortedArrays(new int[]{1,2,3},new int[]{4,5,6}));
		System.out.println(new Solution().findMedianSortedArrays(new int[]{5},new int[]{1,2,3,4,6}));
		System.out.println(new Solution().findMedianSortedArrays(new int[]{2},new int[]{1,3,4,5,6}));
		System.out.println(new Solution().findMedianSortedArrays(new int[]{1,1,3,3},new int[]{1,1,3,3}));
		System.out.println(new Solution().findMedianSortedArrays(new int[]{1,2,2},new int[]{1,2,3}));
		System.out.println(new Solution().findMedianSortedArrays(new int[]{1},new int[]{1}));
		System.out.println(new Solution().findMedianSortedArrays(new int[]{1,5},new int[]{2,3,4}));
		System.out.println(new Solution().findMedianSortedArrays(new int[]{4},new int[]{1,2,3}));
		System.out.println(new Solution().findMedianSortedArrays(new int[]{5},new int[]{1,2,3,4}));
		System.out.println(new Solution().findMedianSortedArrays(new int[]{2},new int[]{1,3,4,5}));
		System.out.println(new Solution().findMedianSortedArrays(new int[]{1},new int[]{2,3,4,5}));
		System.out.println(new Solution().findMedianSortedArrays(new int[]{1},new int[]{2,3}));
		System.out.println(new Solution().findMedianSortedArrays(new int[]{1,3},new int[]{2}));
		System.out.println(new Solution().findMedianSortedArrays(new int[]{1,3},new int[]{2,4,5}));
		System.out.println(new Solution().findMedianSortedArrays(new int[]{1},new int[]{2,3,4,5}));
		System.out.println(new Solution().findMedianSortedArrays(new int[]{},new int[]{1}));
		System.out.println(new Solution().findMedianSortedArrays(new int[]{3,4,7,9,11,13,15,16,17},new int[]{1,2,5,6,8,10,12,14}));

		System.out.println(new Solution().findMedianSortedArrays(new int[]{4},new int[]{1,2,3}));
		System.out.println(new Solution().findMedianSortedArrays(new int[]{1,3},new int[]{2,4}));
		System.out.println(new Solution().findMedianSortedArrays(new int[]{1,2},new int[]{3,4}));
		System.out.println(new Solution().findMedianSortedArrays(new int[]{1,2,5},new int[]{3,4,6}));
	}
}
