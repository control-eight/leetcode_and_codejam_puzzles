package com.my.leetcode.unique_word_abbreviation;

import java.awt.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ValidWordAbbr {

    public static void main(String[] args) {
        String[] dictionary = new String[] {"deer", "door", "cake", "card"};
        ValidWordAbbr validWordAbbr = new ValidWordAbbr(dictionary);
        System.out.println(validWordAbbr.isUnique("dear"));
        System.out.println(validWordAbbr.isUnique("cart"));
        System.out.println(validWordAbbr.isUnique("cane"));
        System.out.println(validWordAbbr.isUnique("make"));
    }

    private Map<String, Container> abbreviation;

    public ValidWordAbbr(String[] dictionary) {
        this.abbreviation = new HashMap<>();

        for (String s : dictionary) {
            String key = applyAbbreviation(s);
            Container c = this.abbreviation.get(key);
            if (c == null) {
                this.abbreviation.put(key, new Container(s, true));
            } else {
                this.abbreviation.put(key, new Container(s, c.word.equals(s)));
            }
        }
    }

    private String applyAbbreviation(String s) {
        if (s.length() <= 2) {
            return s;
        } else {
            return s.charAt(0) + Integer.toString(s.length() - 2) + s.charAt(s.length() - 1);
        }
    }

    public boolean isUnique(String word) {
        Container container = abbreviation.get(applyAbbreviation(word));
        if (container == null) return true;
        return container.unique && container.word.equals(word);
    }

    private static class Container {
        private String word;
        private boolean unique;

        public Container(String word, boolean unique) {
            this.word = word;
            this.unique = unique;
        }
    }
}
