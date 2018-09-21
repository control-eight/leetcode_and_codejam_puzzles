package com.my.leetcode.alien_dictionary;

import java.util.*;

public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().alienOrder(new String[] {
                "ac",
                "ab",
                "b"
        }));
        //acb
        System.out.println(new Solution().alienOrder(new String[] {
                "wrt",
                "wrf",
                "er",
                "ett",
                "rftt"
        }));
        //wertf
        System.out.println(new Solution().alienOrder(new String[] {
                "z",
                "x"
        }));
        //zx
        System.out.println(new Solution().alienOrder(new String[] {
                "z",
                "x",
                "z"
        }));
        //""
        System.out.println(new Solution().alienOrder(new String[] {
                "abc",
                "acb",
                "aed",
                "zbd",
        }));
        //abdzce
        System.out.println(new Solution().alienOrder(new String[] {
                "ab",
                "adc"
        }));
        //abcd
        System.out.println(new Solution().alienOrder(new String[] {
                "aac",
                "aabb",
                "aaba"
        }));
        //cba
        System.out.println(new Solution().alienOrder(new String[] {
                "bsusz",
                "rhn",
                "gfbrwec",
                "kuw",
                "qvpxbexnhx",
                "gnp",
                "laxutz",
                "qzxccww"
        }));
        //cba
        System.out.println("---");
    }

    public String alienOrder(String[] words) {
        if (words.length == 0) return "";

        Map<Character, Set<Character>> childToParent = new HashMap<>();
        Map<Character, Set<Character>> parentToChild = new HashMap<>();
        for (String word : words) {
            for (char c : word.toCharArray()) {
                childToParent.put(c, new HashSet<>());
                parentToChild.put(c, new HashSet<>());
            }
        }

        String prevWord = "";
        for (String word : words) {
            for (int k = 0; k < prevWord.length(); k++) {
                if (word.charAt(k) != prevWord.charAt(k)) {
                    if (containsChild(parentToChild, word.charAt(k), prevWord.charAt(k))) return "";

                    childToParent.get(word.charAt(k)).add(prevWord.charAt(k));
                    parentToChild.get(prevWord.charAt(k)).add(word.charAt(k));

                    for (Character character : parentToChild.get(word.charAt(k))) {
                        childToParent.get(character).remove(prevWord.charAt(k));
                        parentToChild.get(prevWord.charAt(k)).remove(character);
                    }
                    break;
                }
            }
            prevWord = word;
        }

        StringBuilder sb = new StringBuilder();

        Queue<Character> parents = new LinkedList<>();
        Set<Character> printed = new HashSet<>();
        for (Map.Entry<Character, Set<Character>> entry : childToParent.entrySet()) {
            if (entry.getValue().isEmpty()) {
                parents.offer(entry.getKey());
                sb.append(entry.getKey());
                printed.add(entry.getKey());
            }
        }
        for (Character parent : parents) {
            printChildren(parent, parentToChild, printed, sb);
        }
        return sb.toString();
    }

    private boolean containsChild(Map<Character, Set<Character>> parentToChild, char parent, char child) {
        if (parentToChild.get(parent).contains(child)) return true;
        for (Character character : parentToChild.get(parent)) {
            if (containsChild(parentToChild, character, child)) return true;
        }
        return false;
    }

    private void printChildren(Character parent, Map<Character, Set<Character>> parentToChild, Set<Character> printed,
                               StringBuilder sb) {
        for (Character child : parentToChild.get(parent)) {
            if (printed.add(child)) {
                sb.append(child);
                printChildren(child, parentToChild, printed, sb);
            }
        }
    }
}
