package com.yoidukigembu.sql.selct;

import com.yoidukigembu.sql.entity.DummyEntity;
import com.yoidukigembu.sql.select.Select;

import junit.framework.TestCase;

public class SelectTest extends TestCase {

	
	public void testFrom() {
		Select<DummyEntity> select = Select.from(DummyEntity.class);
		
		select.generate((sql, params) -> {
			System.out.println(sql);
		});
	}
}
