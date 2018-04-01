package com.my.array_nesting;

/**
 * @author abykovsky
 * @since 4/1/18
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().arrayNesting(new int[] {5,4,0,3,1,6,2}));
    }

    public int arrayNesting(int[] nums) {
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= 0) {
                int k = nums[i];
                nums[i] = -1;
                int length = 1;
                while (nums[k] >= 0) {
                    int newK = nums[k];
                    nums[k] = -1;
                    k = newK;
                    length++;
                }
                result = Math.max(result, length);
            }
        }
        return result;
    }
}
