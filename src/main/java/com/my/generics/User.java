package com.my.generics;

/**
 * Created by alex.bykovsky on 7/13/17.
 */
public class User implements IDomainObject<String> {

	private String id;

	@Override
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
