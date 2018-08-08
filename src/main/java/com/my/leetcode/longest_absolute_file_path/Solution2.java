package com.my.leetcode.longest_absolute_file_path;

import java.util.Deque;
import java.util.LinkedList;

public class Solution2 {

    public static void main(String[] args) {
        System.out.println(new Solution().lengthLongestPath("a"));
        System.out.println(new Solution().lengthLongestPath("dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext"));
        System.out.println(new Solution().lengthLongestPath("dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext"));
    }

    public int lengthLongestPath(String input) {
        Deque<Integer> stack = new LinkedList<>();
        int max = 0;
        stack.push(0);
        for (String f : input.split("\n")) {
            int lvl = f.lastIndexOf("\t") + 1;
            while (lvl + 1 < stack.size()) stack.pop(); // find parent
            int len = stack.peek() + input.length() - lvl + 1; // remove "/t", add"/"
            stack.push(len);
            // check if it is file
            if(input.contains(".")) max = Math.max(max, len - 1);
        }
        return max;
    }
}
