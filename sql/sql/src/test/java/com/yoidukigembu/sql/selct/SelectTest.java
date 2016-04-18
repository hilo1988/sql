package com.yoidukigembu.sql.selct;

import java.util.Arrays;

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
		Select.from(DummyEntity.class)
			.generate((sql, params) -> {
				assertEquals("SELECT T.* FROM dummy T", sql);
				assertEquals(0, params.size());
			});
	}

	@Test
	public void エンティティクラスとエイリアスのテスト() {
		Select.from(DummyEntity.class, "alias")
			.generate((sql, params) -> {
				assertEquals("SELECT alias.* FROM dummy alias", sql);
				assertEquals(0, params.size());
			});
	}

	@Test
	public void カラム指定テスト_単発() {
		Select.from(DummyEntity.class)
			.columns("id")
			.generate((sql, params) -> {
				assertEquals("SELECT T.id FROM dummy T", sql);
				assertEquals(0, params.size());
			});

		Select.from(DummyEntity.class)
			.columns(Arrays.asList("id"))
			.generate((sql, params) -> {
				assertEquals("SELECT T.id FROM dummy T", sql);
				assertEquals(0, params.size());
			});
	}
	
	@Test
	public void カラム指定テスト_複数() {
		String[] columns = {"id", "name", "address", "mailAddress"};
		Select.from(DummyEntity.class)
			.columns(columns)
			.generate((sql, params) -> {
				assertEquals("SELECT T.id, T.name, T.address, T.mailAddress FROM dummy T", sql);
				assertEquals(0, params.size());
			});

		Select.from(DummyEntity.class)
			.columns(Arrays.asList(columns))
			.generate((sql, params) -> {
				assertEquals("SELECT T.id, T.name, T.address, T.mailAddress FROM dummy T", sql);
				assertEquals(0, params.size());
			});
	}
}
