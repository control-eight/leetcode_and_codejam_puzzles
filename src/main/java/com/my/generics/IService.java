package com.my.generics;

/**
 * Created by alex.bykovsky on 7/13/17.
 */
public interface IService<T extends IDomainObject<K>, K> {

	K processEntity(T entity);
}
