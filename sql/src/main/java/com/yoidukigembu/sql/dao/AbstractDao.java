package com.yoidukigembu.sql.dao;

import com.yoidukigembu.sql.entity.AbstractBaseEntity;

public class AbstractDao<E extends AbstractBaseEntity> {

	Class<E> entityClass;

	public AbstractDao(Class<E> enityClass) {
		this.entityClass = enityClass;
	}


}
