package com.my.leetcode.peeking_iterator;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by alex.bykovsky on 3/26/18.
 */
// Java Iterator interface reference:
// https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html
class PeekingIterator implements Iterator<Integer> {

	private Integer nextBuffer;

	private Iterator<Integer> iterator;

	public PeekingIterator(Iterator<Integer> iterator) {
		// initialize any member here.
		this.iterator = iterator;
	}

	// Returns the next element in the iteration without advancing the iterator.
	public Integer peek() {
		if (nextBuffer != null) {
			return nextBuffer;
		}
		if (iterator.hasNext()) {
			return (nextBuffer = iterator.next());
		} else {
			return null;
		}
	}

	// hasNext() and next() should behave the same as in the Iterator interface.
	// Override them if needed.
	@Override
	public Integer next() {
		if (nextBuffer != null) {
			Integer res = nextBuffer;
			nextBuffer = null;
			return res;
		} else {
			return iterator.next();
		}
	}

	@Override
	public boolean hasNext() {
		if (nextBuffer != null) {
			return true;
		} else {
			return iterator.hasNext();
		}
	}

	public static void main(String[] args) {
		List<Integer> list = Arrays.asList(1, 2, 3, 4);
		PeekingIterator iterator = new PeekingIterator(list.iterator());

		System.out.println(iterator.hasNext());
		System.out.println(iterator.next());
		System.out.println(iterator.peek());
		System.out.println(iterator.hasNext());
		System.out.println(iterator.next());
		System.out.println(iterator.next());
		System.out.println(iterator.peek());
		System.out.println(iterator.hasNext());
		System.out.println(iterator.next());
		System.out.println(iterator.peek());
	}
}

