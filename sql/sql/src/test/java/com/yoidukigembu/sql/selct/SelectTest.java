package com.yoidukigembu.sql.selct;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.yoidukigembu.sql.entity.DummyEntity;
import com.yoidukigembu.sql.enums.OrderType;
import com.yoidukigembu.sql.orderBy.OrderBy;
import com.yoidukigembu.sql.select.Select;
import com.yoidukigembu.sql.where.BasicWhere;
import com.yoidukigembu.sql.where.Where;

import junit.framework.TestCase;

@RunWith(JUnit4.class)
public class SelectTest extends TestCase {

	@Test
	public void エンティティクラスのみのテスト() {
		Select.from(DummyEntity.class)
			.generate((sql, params) -> {
				assertEquals("SELECT * FROM schema.dummy", sql);
				assertEquals(0, params.size());
				return null;
			});
	}

	@Test
	public void エンティティクラスとエイリアスのテスト() {
		Select.from(DummyEntity.class, "alias")
			.generate((sql, params) -> {
				assertEquals("SELECT alias.* FROM schema.dummy alias", sql);
				assertEquals(0, params.size());
				return null;
			});
	}

	@Test
	public void カラム指定テスト_単発() {
		Select.from(DummyEntity.class)
			.columns("id")
			.generate((sql, params) -> {
				assertEquals("SELECT id FROM schema.dummy", sql);
				assertEquals(0, params.size());
				return null;
			});

		Select.from(DummyEntity.class)
			.columns(Arrays.asList("id"))
			.generate((sql, params) -> {
				assertEquals("SELECT id FROM schema.dummy", sql);
				assertEquals(0, params.size());
				return null;
			});
	}
	
	@Test
	public void カラム指定テスト_複数() {
		String[] columns = {"id", "name", "address", "mailAddress"};
		Select.from(DummyEntity.class)
			.columns(columns)
			.generate((sql, params) -> {
				assertEquals("SELECT id, name, address, mailAddress FROM schema.dummy", sql);
				assertEquals(0, params.size());
				return null;
			});

		Select.from(DummyEntity.class)
			.columns(Arrays.asList(columns))
			.generate((sql, params) -> {
				assertEquals("SELECT id, name, address, mailAddress FROM schema.dummy", sql);
				assertEquals(0, params.size());
				return null;
			});
	}
	
	@Test
	public void limitテスト() {
		Select.from(DummyEntity.class)
			.limit(10)
			.generate((sql, params) -> {
				assertEquals("SELECT * FROM schema.dummy LIMIT 10", sql);
				assertEquals(0, params.size());
				return null;
			});
	}
	
	@Test
	public void offsetテスト() {
		Select.from(DummyEntity.class)
		.offset(10)
		.generate((sql, params) -> {
			assertEquals("SELECT * FROM schema.dummy OFFSET 10", sql);
			assertEquals(0, params.size());
			return null;
		});
	}
	
	@Test
	public void orderByテスト() {
		Select.from(DummyEntity.class)
			.orderBy("id")
			.generate((sql, params) -> {
				assertEquals("SELECT * FROM schema.dummy ORDER BY id", sql);
				assertEquals(0, params.size());
				return null;
			});
		
		
		Select.from(DummyEntity.class)
			.orderBy(new OrderBy("id"))
			.generate((sql, params) -> {
				assertEquals("SELECT * FROM schema.dummy ORDER BY id ASC", sql);
				assertEquals(0, params.size());
				return null;
			});
		
		Select.from(DummyEntity.class)
			.orderBy(new OrderBy("id", OrderType.DESC))
			.generate((sql, params) -> {
				assertEquals("SELECT * FROM schema.dummy ORDER BY id DESC", sql);
				assertEquals(0, params.size());
				return null;
			});
		
		Select.from(DummyEntity.class)
		.orderBy(new OrderBy("id", OrderType.DESC)
				.asc("name")
				.desc("gender"))
		.generate((sql, params) -> {
			assertEquals("SELECT * FROM schema.dummy ORDER BY id DESC, name ASC, gender DESC", sql);
			assertEquals(0, params.size());
			return null;
		});
	}
	
	@Test
	public void WHEREのテスト() {
		Where where = new BasicWhere();
		where.eq("id", 1)
			.contains("name", "h")
			.ge("age", 20)
			.in("type", Arrays.asList(5, 94));
		Select<DummyEntity> select = Select.from(DummyEntity.class)
				.where(where);
		
		select.generate((query, params) -> {
			assertEquals("SELECT * FROM schema.dummy WHERE id = ? AND name LIKE ? AND age >= ? AND type IN (?, ?)", query.trim());
			assertEquals(5, params.size());
			assertEquals(1, params.get(0));
			assertEquals("%h%", params.get(1));
			assertEquals(20, params.get(2));
			assertEquals(5, params.get(3));
			assertEquals(94, params.get(4));
			return null;
		});
		
			
	}
}
