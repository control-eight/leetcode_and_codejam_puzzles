package com.my.leetcode.generics;

/**
 * Created by alex.bykovsky on 7/13/17.
 */
public class Product implements IDomainObject<Integer> {

	private Integer id;

	@Override
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
