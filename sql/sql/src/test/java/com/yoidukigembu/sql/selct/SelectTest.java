package com.yoidukigembu.sql.selct;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.yoidukigembu.sql.entity.DummyEntity;
import com.yoidukigembu.sql.select.Select;

import junit.framework.TestCase;

@RunWith(JUnit4.class)
public class SelectTest extends TestCase {

	@Test
	public void エンティティクラスのみのテスト() {
		Select<DummyEntity> select = Select.from(DummyEntity.class);

		select.generate((sql, params) -> {
			assertEquals("SELECT * FROM dummy", sql);
			assertEquals(0, params.size());
		});
	}

	@Test
	public void エンティティクラスとエイリアスのテスト() {
		Select<DummyEntity> select = Select.from(DummyEntity.class, "alias");

		select.generate((sql, params) -> {
			assertEquals("SELECT alias.* FROM dummy alias", sql);
			assertEquals(0, params.size());
		});
	}
}
