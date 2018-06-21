package com.my.leetcode.generics;

/**
 * Created by alex.bykovsky on 7/13/17.
 */
public class ProductService implements IService<Product, Integer> {

	@Override
	public Integer processEntity(Product entity) {
		return entity.getId();
	}
}
