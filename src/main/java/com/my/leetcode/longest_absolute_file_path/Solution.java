package com.my.leetcode.longest_absolute_file_path;

import java.util.LinkedList;

//  "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext"
/*dir
    subdir1
    subdir2
        file.ext*/
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().lengthLongestPath("a"));
        System.out.println(new Solution().lengthLongestPath("dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext"));
        System.out.println(new Solution().lengthLongestPath("dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext"));
    }

    public int lengthLongestPath(String input) {
        Cursor cursor = new Cursor(0);
        StringBuilder sb = new StringBuilder();
        int prevLevel = -1;
        int max = 0;
        LinkedList<String> words = new LinkedList<>();
        while (cursor.pos < input.length()) {
            readNext(input, cursor);
            if (cursor.currentLevel <= prevLevel) {
                int lengthToDelete = 0;
                int level = prevLevel;
                while (level >= cursor.currentLevel) {
                    lengthToDelete += words.pollLast().length();
                    level--;
                }
                sb.delete(sb.length() - lengthToDelete, sb.length());
            }
            words.offerLast(cursor.currentEntity + (cursor.currentEntity.contains(".")? "": "."));
            sb.append(words.peekLast());

            if (cursor.currentEntity.contains(".")) {
                max = Math.max(max, sb.length());
            }
            prevLevel = cursor.currentLevel;
        }
        return max;
    }

    private void readNext(String input, Cursor cursor) {
        cursor.currentEntity = null;
        cursor.currentLevel = 0;
        if ("\n".equals(input.substring(cursor.pos, cursor.pos + 1))) {
            cursor.pos += 1;
            while ((cursor.pos + 1) < input.length()) {
                if ("\t".equals(input.substring(cursor.pos, cursor.pos + 1))) {
                    cursor.pos += 1;
                    cursor.currentLevel++;
                } else {
                    break;
                }
            }
        }
        int start = cursor.pos;
        while (cursor.pos < input.length() && !(cursor.pos + 1 < input.length()
                && "\n".equals(input.substring(cursor.pos, cursor.pos + 1)))) {
            cursor.pos++;
        }
        cursor.currentEntity = input.substring(start, cursor.pos);
    }

    private static class Cursor {
        private int currentLevel;
        private String currentEntity;
        private int pos;
        public Cursor(int pos) {
            this.pos = pos;
        }
    }
}
