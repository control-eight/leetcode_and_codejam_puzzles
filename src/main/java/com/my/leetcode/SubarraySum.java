package com.my.leetcode;

public class SubarraySum {

    public static void main(String[] args) {
        System.out.println(new SubarraySum().maxConseqSum(new int[] {-1,50,50,50,-100,50}, 2));
        System.out.println(new SubarraySum().maxConseqSum(new int[] {1,3,-4,5,6,7,1,9}, 2));
        System.out.println(new SubarraySum().maxConseqSum(new int[] {1,3,-4,100,5,6,7,1,9}, 3));
        System.out.println(new SubarraySum().maxConseqSum(new int[] {1,3,100,-4,100,5,6,7,1,9}, 3));
        //4
    }


    //17-239


    //0-n,0-n-1,...0-k

    public int maxConseqSum(int[] arr, int k) {
        int result = 0;
        int prevSum = 0;
        for (int i = 0; i < k; i++) {
            prevSum += arr[i];
        }
        int maxSum = prevSum;
        for (int i = k; i < arr.length; i++) {
            prevSum -= arr[i - k];
            prevSum += arr[i];
            if (prevSum > maxSum) {
                maxSum = prevSum;
                result = i - k + 1;
            }
        }

        for (int i = 0; i < arr.length - k; i++) {
            for (int j = k; j < arr.length; j++) {

            }
        }


        return result;
    }
}
