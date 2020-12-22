package com.my.misc.string_hash;

/**
 * Author: Oleksandr Bykovskyi
 * Date: 2020-05-17
 */
public class StringHash {

    public static long nonPossibleCollisionHits;
    public static long allGets;

    private int capacity;
    private char start;
    private StringBuilder str;
    private long hashValue;
    private boolean possibleCollision;

    public StringHash(int capacity, char start) {
        this.capacity = capacity;
        this.start = start;
        this.str = new StringBuilder();
    }

    public StringHash(int capacity, char start, String str) {
        this(capacity, start);
        for (char c : str.toCharArray()) {
            add(c);
        }
    }

    public void add(char c) {
        this.str.append(c);
        this.hashValue = capacity * this.hashValue + (c - start + 1);
        if (!possibleCollision && this.hashValue < 0) {
            this.possibleCollision = true;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StringHash that = (StringHash) o;

        allGets++;
        if (possibleCollision) {
            return sbEquals(that);
        } else {
            nonPossibleCollisionHits++;
            return hashValue == that.hashValue;
        }
    }

    private boolean sbEquals(StringHash that) {
        return (str == that.str) || (str != null && equals(str, that.str));
    }

    private boolean equals(StringBuilder str, StringBuilder that) {
        if (that == null) return false;
        if (str.length() != that.length()) return false;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != that.charAt(i)) return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(hashValue);
    }

    @Override
    public String toString() {
        return str + " " + hashValue;
    }
}
