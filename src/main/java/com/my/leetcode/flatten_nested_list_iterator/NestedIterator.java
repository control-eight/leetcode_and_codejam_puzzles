package com.my.leetcode.flatten_nested_list_iterator;

import java.util.Iterator;
import java.util.List;

public class NestedIterator implements Iterator<Integer> {

    private List<NestedInteger> nestedList;

    private NestedIterator current;

    private int index;

    public NestedIterator(List<NestedInteger> nestedList) {
        this.nestedList = nestedList;
    }

    @Override
    public Integer next() {
        if (nestedList.get(index).isInteger()) {
            return nestedList.get(index++).getInteger();
        } else {
            return current.next();
        }
    }

    @Override
    public boolean hasNext() {
        if (index == nestedList.size()) return false;

        if (nestedList.get(index).isInteger()) {
            return true;
        } else {
            if (current == null) {
                current = new NestedIterator(nestedList.get(index).getList());
            }
            while (index < nestedList.size() - 1 && !current.hasNext()) {
                this.index++;
                if (nestedList.get(index).isInteger()) {
                    current = null;
                    return true;
                }
                current = new NestedIterator(nestedList.get(index).getList());
            }
            return index < nestedList.size() - 1 || current.hasNext();
        }
    }
}
/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */
