package com.my.leetcode.generics;

/**
 * Created by alex.bykovsky on 7/13/17.
 */
public class MainService {

	public static void main(String[] args) {

		String userId = new User().getId();
		Integer productId = new Product().getId();

		String processedUserId = new UserService().processEntity(new User());
		Integer processedProductId = new ProductService().processEntity(new Product());
	}
}
