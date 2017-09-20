package com.my.generics;

/**
 * Created by alex.bykovsky on 7/13/17.
 */
public class UserService implements IService<User, String> {

	@Override
	public String processEntity(User entity) {
		return entity.getId();
	}
}
