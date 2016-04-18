package com.yoidukigembu.sql.entity;

import javax.persistence.Table;

@Table(name = DummyEntity.TABLE_NAME, schema = DummyEntity.SCHEMA)
public class DummyEntity {

	
	public static final String TABLE_NAME = "dummy";
	
	public static final String SCHEMA = "schema";
}
