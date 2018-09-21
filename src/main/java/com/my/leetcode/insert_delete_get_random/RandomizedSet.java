package com.my.leetcode.insert_delete_get_random;

import java.util.*;

public class RandomizedSet {

    private Map<Integer, Integer> valueMap;

    private Map<Integer, Integer> keyMap;

    private Random random = new Random();

    private int index;

    /** Initialize your data structure here. */
    public RandomizedSet() {
        valueMap = new HashMap<>();
        keyMap = new HashMap<>();
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (!valueMap.containsKey(val)) {
            valueMap.put(val, keyMap.size());
            keyMap.put(keyMap.size(), val);
            return true;
        }
        return false;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (valueMap.containsKey(val)) {
            int index = valueMap.remove(val);
            if (index != keyMap.size() - 1) {
                int value = keyMap.get(keyMap.size() - 1);
                keyMap.put(index, value);
                keyMap.remove(keyMap.size() - 1);
                valueMap.put(value, index);
            } else {
                keyMap.remove(index);
            }
            return true;
        }
        return false;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        /*int index = (int) (random.nextDouble() * keyMap.size());
        return keyMap.get(index);*/

        if (index >= keyMap.size()) {
            index = 0;
        }
        if (index < 0) {
            index = keyMap.size() - 1;
        }
        Integer integer = keyMap.get(index);

        if (random.nextDouble() > 0.5) {
            index++;
        } else {
            index--;
        }

        return integer;
    }

    public static void main(String[] args) {
        /*RandomizedSet obj = new RandomizedSet();
        System.out.println(obj.insert(1));
        System.out.println(obj.insert(2));
        System.out.println(obj.getRandom());
        System.out.println(obj.remove(1));
        System.out.println(obj.getRandom());
        System.out.println(obj.insert(3));
        System.out.println(obj.insert(4));
        System.out.println(obj.insert(5));
        System.out.println(obj.remove(3));
        System.out.println(obj.getRandom());
        System.out.println(obj.remove(2));
        System.out.println(obj.getRandom());
        System.out.println(obj.insert(1));
        System.out.println(obj.insert(2));
        System.out.println(obj.insert(3));
        System.out.println(obj.insert(4));
        System.out.println(obj.insert(5));
        System.out.println(obj.remove(1));
        System.out.println(obj.remove(3));
        System.out.println(obj.remove(5));
        System.out.println(obj.getRandom());*/

        RandomizedSet obj = new RandomizedSet();
        obj.insert(1);
        obj.insert(10);
        obj.insert(20);
        obj.insert(30);

        Map<Integer, Integer> countMap = new HashMap<>();
        for (int i = 0; i < 1e8; i++) {
            int val = obj.getRandom();
            if (!countMap.containsKey(val)) {
                countMap.put(val, 0);
            }
            countMap.put(val, countMap.get(val) + 1);
        }
        System.out.println(countMap);
    }
}