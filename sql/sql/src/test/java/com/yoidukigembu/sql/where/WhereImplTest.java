package com.yoidukigembu.sql.where;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import junit.framework.TestCase;

@RunWith(JUnit4.class)
public class WhereImplTest extends TestCase {
	
	private static final String NULL_FORMAT = "value must not be NULL. query:[%s]";

	@Test
	public void andIsNotNull() {
		Where where = new WhereImpl();
		where.isNotNull("id");
		where.build((query, params) -> {
			assertEquals("id IS NOT NULL", query.trim());
			assertTrue(params.isEmpty());
		});
		
		
		where.isNotNull("name");
		where.build((query, params) -> {
			assertEquals("id IS NOT NULL AND name IS NOT NULL", query.trim());
			assertTrue(params.isEmpty());
		});
	}
	
	
	@Test
	public void andIsNotNullAlias() {
		Where where = new WhereImpl();
		where.isNotNull("ALIAS", "id");
		where.build((query, params) -> {
			assertEquals("ALIAS.id IS NOT NULL", query.trim());
			assertTrue(params.isEmpty());
		});
		
		
		where.isNotNull("ALIAS", "name");
		where.build((query, params) -> {
			assertEquals("ALIAS.id IS NOT NULL AND ALIAS.name IS NOT NULL", query.trim());
			assertTrue(params.isEmpty());
		});
	}
	
	
	@Test
	public void orIsNotNull() {
		Where where = new WhereImpl();
		where.orIsNotNull("id");
		where.build((query, params) -> {
			assertEquals("id IS NOT NULL", query.trim());
			assertTrue(params.isEmpty());
		});
		
		
		where.orIsNotNull("name");
		where.build((query, params) -> {
			assertEquals("id IS NOT NULL OR name IS NOT NULL", query.trim());
			assertTrue(params.isEmpty());
		});
	}
	
	
	@Test
	public void orIsNotNullAlias() {
		Where where = new WhereImpl();
		where.orIsNotNull("ALIAS", "id");
		where.build((query, params) -> {
			assertEquals("ALIAS.id IS NOT NULL", query.trim());
			assertTrue(params.isEmpty());
		});
		
		
		where.orIsNotNull("ALIAS", "name");
		where.build((query, params) -> {
			assertEquals("ALIAS.id IS NOT NULL OR ALIAS.name IS NOT NULL", query.trim());
			assertTrue(params.isEmpty());
		});
	}
	
	
	
	@Test
	public void andIsNull() {
		Where where = new WhereImpl();
		where.isNull("id");
		where.build((query, params) -> {
			assertEquals("id IS NULL", query.trim());
			assertTrue(params.isEmpty());
		});
		
		
		where.isNull("name");
		where.build((query, params) -> {
			assertEquals("id IS NULL AND name IS NULL", query.trim());
			assertTrue(params.isEmpty());
		});
	}
	
	
	@Test
	public void andIsNullAlias() {
		Where where = new WhereImpl();
		where.isNull("ALIAS", "id");
		where.build((query, params) -> {
			assertEquals("ALIAS.id IS NULL", query.trim());
			assertTrue(params.isEmpty());
		});
		
		
		where.isNull("ALIAS", "name");
		where.build((query, params) -> {
			assertEquals("ALIAS.id IS NULL AND ALIAS.name IS NULL", query.trim());
			assertTrue(params.isEmpty());
		});
	}
	
	@Test
	public void orIsNull() {
		Where where = new WhereImpl();
		where.orIsNull("id");
		where.build((query, params) -> {
			assertEquals("id IS NULL", query.trim());
			assertTrue(params.isEmpty());
		});
		
		
		where.orIsNull("name");
		where.build((query, params) -> {
			assertEquals("id IS NULL OR name IS NULL", query.trim());
			assertTrue(params.isEmpty());
		});
	}
	
	
	@Test
	public void orIsNullAlias() {
		Where where = new WhereImpl();
		where.orIsNull("ALIAS", "id");
		where.build((query, params) -> {
			assertEquals("ALIAS.id IS NULL", query.trim());
			assertTrue(params.isEmpty());
		});
		
		
		where.orIsNull("ALIAS", "name");
		where.build((query, params) -> {
			assertEquals("ALIAS.id IS NULL OR ALIAS.name IS NULL", query.trim());
			assertTrue(params.isEmpty());
		});
	}
	
	@Test
	public void andEq() {
		Where where = new WhereImpl();
		where.eq("id", 1);
		where.build((query, params) -> {
			assertEquals("id = ?", query.trim());
			assertEquals(1, params.size());
			assertEquals(1, params.get(0));
		});
		
		
		where.eq("name", "hilo");
		where.build((query, params) -> {
			assertEquals("id = ? AND name = ?", query.trim());
			assertEquals(2, params.size());
			assertEquals(1, params.get(0));
			assertEquals("hilo", params.get(1));
		});
		
		where.eq("ex", null);
		try {
			where.build((query, params) -> {});
			fail("NullPointerException must be occured");
		} catch (NullPointerException e) {
			assertEquals(String.format(NULL_FORMAT, "ex = ?"), e.getMessage());
		}
	}
	
	@Test
	public void andEqAlias() {
		Where where = new WhereImpl();
		where.eq("ALIAS", "id", 1);
		where.build((query, params) -> {
			assertEquals("ALIAS.id = ?", query.trim());
			assertEquals(1, params.size());
			assertEquals(1, params.get(0));
		});
		
		
		where.eq("ALIAS", "name", "hilo");
		where.build((query, params) -> {
			assertEquals("ALIAS.id = ? AND ALIAS.name = ?", query.trim());
			assertEquals(2, params.size());
			assertEquals(1, params.get(0));
			assertEquals("hilo", params.get(1));
		});
		
		where.eq("ALIAS", "ex", null);
		try {
			where.build((query, params) -> {});
			fail("NullPointerException must be occured");
		} catch (NullPointerException e) {
			assertEquals(String.format(NULL_FORMAT, "ALIAS.ex = ?"), e.getMessage());
		}
	}
	
	
	@Test
	public void orEq() {
		Where where = new WhereImpl();
		where.orEq("id", 1);
		where.build((query, params) -> {
			assertEquals("id = ?", query.trim());
			assertEquals(1, params.size());
			assertEquals(1, params.get(0));
		});
		
		
		where.orEq("name", "hilo");
		where.build((query, params) -> {
			assertEquals("id = ? OR name = ?", query.trim());
			assertEquals(2, params.size());
			assertEquals(1, params.get(0));
			assertEquals("hilo", params.get(1));
		});
		
		where.orEq("ex", null);
		try {
			where.build((query, params) -> {});
			fail("NullPointerException must be occured");
		} catch (NullPointerException e) {
			assertEquals(String.format(NULL_FORMAT, "ex = ?"), e.getMessage());
		}
	}
	
	@Test
	public void orEqAlias() {
		Where where = new WhereImpl();
		where.orEq("ALIAS", "id", 1);
		where.build((query, params) -> {
			assertEquals("ALIAS.id = ?", query.trim());
			assertEquals(1, params.size());
			assertEquals(1, params.get(0));
		});
		
		
		where.orEq("ALIAS", "name", "hilo");
		where.build((query, params) -> {
			assertEquals("ALIAS.id = ? OR ALIAS.name = ?", query.trim());
			assertEquals(2, params.size());
			assertEquals(1, params.get(0));
			assertEquals("hilo", params.get(1));
		});
		
		where.orEq("ALIAS", "ex", null);
		try {
			where.build((query, params) -> {});
			fail("NullPointerException must be occured");
		} catch (NullPointerException e) {
			assertEquals(String.format(NULL_FORMAT, "ALIAS.ex = ?"), e.getMessage());
		}
	}
	
	
	@Test
	public void andNe() {
		Where where = new WhereImpl();
		where.ne("id", 1);
		where.build((query, params) -> {
			assertEquals("id != ?", query.trim());
			assertEquals(1, params.size());
			assertEquals(1, params.get(0));
		});
		
		
		where.ne("name", "hilo");
		where.build((query, params) -> {
			assertEquals("id != ? AND name != ?", query.trim());
			assertEquals(2, params.size());
			assertEquals(1, params.get(0));
			assertEquals("hilo", params.get(1));
		});
		
		where.ne("ex", null);
		try {
			where.build((query, params) -> {});
			fail("NullPointerException must be occured");
		} catch (NullPointerException e) {
			assertEquals(String.format(NULL_FORMAT, "ex != ?"), e.getMessage());
		}
	}
	
	
	@Test
	public void andNeAlias() {
		Where where = new WhereImpl();
		where.ne("ALIAS", "id", 1);
		where.build((query, params) -> {
			assertEquals("ALIAS.id != ?", query.trim());
			assertEquals(1, params.size());
			assertEquals(1, params.get(0));
		});
		
		
		where.ne("ALIAS", "name", "hilo");
		where.build((query, params) -> {
			assertEquals("ALIAS.id != ? AND ALIAS.name != ?", query.trim());
			assertEquals(2, params.size());
			assertEquals(1, params.get(0));
			assertEquals("hilo", params.get(1));
		});
		
		where.ne("ALIAS", "ex", null);
		try {
			where.build((query, params) -> {});
			fail("NullPointerException must be occured");
		} catch (NullPointerException e) {
			assertEquals(String.format(NULL_FORMAT, "ALIAS.ex != ?"), e.getMessage());
		}
	}
	
	
	@Test
	public void orNe() {
		Where where = new WhereImpl();
		where.orNe("id", 1);
		where.build((query, params) -> {
			assertEquals("id != ?", query.trim());
			assertEquals(1, params.size());
			assertEquals(1, params.get(0));
		});
		
		
		where.orNe("name", "hilo");
		where.build((query, params) -> {
			assertEquals("id != ? OR name != ?", query.trim());
			assertEquals(2, params.size());
			assertEquals(1, params.get(0));
			assertEquals("hilo", params.get(1));
		});
		
		where.orNe("ex", null);
		try {
			where.build((query, params) -> {});
			fail("NullPointerException must be occured");
		} catch (NullPointerException e) {
			assertEquals(String.format(NULL_FORMAT, "ex != ?"), e.getMessage());
		}
	}
	
	
	@Test
	public void orNeAlias() {
		Where where = new WhereImpl();
		where.orNe("ALIAS", "id", 1);
		where.build((query, params) -> {
			assertEquals("ALIAS.id != ?", query.trim());
			assertEquals(1, params.size());
			assertEquals(1, params.get(0));
		});
		
		
		where.orNe("ALIAS", "name", "hilo");
		where.build((query, params) -> {
			assertEquals("ALIAS.id != ? OR ALIAS.name != ?", query.trim());
			assertEquals(2, params.size());
			assertEquals(1, params.get(0));
			assertEquals("hilo", params.get(1));
		});
		
		where.orNe("ALIAS", "ex", null);
		try {
			where.build((query, params) -> {});
			fail("NullPointerException must be occured");
		} catch (NullPointerException e) {
			assertEquals(String.format(NULL_FORMAT, "ALIAS.ex != ?"), e.getMessage());
		}
	}
	
	
	@Test
	public void andGt() {
		Where where = new WhereImpl();
		where.gt("id", 1);
		where.build((query, params) -> {
			assertEquals("id > ?", query.trim());
			assertEquals(1, params.size());
			assertEquals(1, params.get(0));
		});
		
		
		where.gt("age", 10);
		where.build((query, params) -> {
			assertEquals("id > ? AND age > ?", query.trim());
			assertEquals(2, params.size());
			assertEquals(1, params.get(0));
			assertEquals(10, params.get(1));
		});
		
		where.gt("ex", null);
		try {
			where.build((query, params) -> {});
			fail("NullPointerException must be occured");
		} catch (NullPointerException e) {
			assertEquals(String.format(NULL_FORMAT, "ex > ?"), e.getMessage());
		}
	}
	
	@Test
	public void andGtAlias() {
		Where where = new WhereImpl();
		where.gt("ALIAS", "id", 1);
		where.build((query, params) -> {
			assertEquals("ALIAS.id > ?", query.trim());
			assertEquals(1, params.size());
			assertEquals(1, params.get(0));
		});
		
		
		where.gt("ALIAS", "age", 10);
		where.build((query, params) -> {
			assertEquals("ALIAS.id > ? AND ALIAS.age > ?", query.trim());
			assertEquals(2, params.size());
			assertEquals(1, params.get(0));
			assertEquals(10, params.get(1));
		});
		
		where.gt("ALIAS", "ex", null);
		try {
			where.build((query, params) -> {});
			fail("NullPointerException must be occured");
		} catch (NullPointerException e) {
			assertEquals(String.format(NULL_FORMAT, "ALIAS.ex > ?"), e.getMessage());
		}
	}
	
	
	@Test
	public void orGt() {
		Where where = new WhereImpl();
		where.orGt("id", 1);
		where.build((query, params) -> {
			assertEquals("id > ?", query.trim());
			assertEquals(1, params.size());
			assertEquals(1, params.get(0));
		});
		
		
		where.orGt("age", 10);
		where.build((query, params) -> {
			assertEquals("id > ? OR age > ?", query.trim());
			assertEquals(2, params.size());
			assertEquals(1, params.get(0));
			assertEquals(10, params.get(1));
		});
		
		where.orGt("ex", null);
		try {
			where.build((query, params) -> {});
			fail("NullPointerException must be occured");
		} catch (NullPointerException e) {
			assertEquals(String.format(NULL_FORMAT, "ex > ?"), e.getMessage());
		}
	}
	
	@Test
	public void orGtAlias() {
		Where where = new WhereImpl();
		where.orGt("ALIAS", "id", 1);
		where.build((query, params) -> {
			assertEquals("ALIAS.id > ?", query.trim());
			assertEquals(1, params.size());
			assertEquals(1, params.get(0));
		});
		
		
		where.orGt("ALIAS", "age", 10);
		where.build((query, params) -> {
			assertEquals("ALIAS.id > ? OR ALIAS.age > ?", query.trim());
			assertEquals(2, params.size());
			assertEquals(1, params.get(0));
			assertEquals(10, params.get(1));
		});
		
		where.orGt("ALIAS", "ex", null);
		try {
			where.build((query, params) -> {});
			fail("NullPointerException must be occured");
		} catch (NullPointerException e) {
			assertEquals(String.format(NULL_FORMAT, "ALIAS.ex > ?"), e.getMessage());
		}
	}
	
	
	@Test
	public void andGe() {
		Where where = new WhereImpl();
		where.ge("id", 1);
		where.build((query, params) -> {
			assertEquals("id >= ?", query.trim());
			assertEquals(1, params.size());
			assertEquals(1, params.get(0));
		});
		
		
		where.ge("age", 10);
		where.build((query, params) -> {
			assertEquals("id >= ? AND age >= ?", query.trim());
			assertEquals(2, params.size());
			assertEquals(1, params.get(0));
			assertEquals(10, params.get(1));
		});
		
		where.ge("ex", null);
		try {
			where.build((query, params) -> {});
			fail("NullPointerException must be occured");
		} catch (NullPointerException e) {
			assertEquals(String.format(NULL_FORMAT, "ex >= ?"), e.getMessage());
		}
	}
	
	@Test
	public void andGeAlias() {
		Where where = new WhereImpl();
		where.ge("ALIAS", "id", 1);
		where.build((query, params) -> {
			assertEquals("ALIAS.id >= ?", query.trim());
			assertEquals(1, params.size());
			assertEquals(1, params.get(0));
		});
		
		
		where.ge("ALIAS", "age", 10);
		where.build((query, params) -> {
			assertEquals("ALIAS.id >= ? AND ALIAS.age >= ?", query.trim());
			assertEquals(2, params.size());
			assertEquals(1, params.get(0));
			assertEquals(10, params.get(1));
		});
		
		where.ge("ALIAS", "ex", null);
		try {
			where.build((query, params) -> {});
			fail("NullPointerException must be occured");
		} catch (NullPointerException e) {
			assertEquals(String.format(NULL_FORMAT, "ALIAS.ex >= ?"), e.getMessage());
		}
	}
	
	
	@Test
	public void orGe() {
		Where where = new WhereImpl();
		where.orGe("id", 1);
		where.build((query, params) -> {
			assertEquals("id >= ?", query.trim());
			assertEquals(1, params.size());
			assertEquals(1, params.get(0));
		});
		
		
		where.orGe("age", 10);
		where.build((query, params) -> {
			assertEquals("id >= ? OR age >= ?", query.trim());
			assertEquals(2, params.size());
			assertEquals(1, params.get(0));
			assertEquals(10, params.get(1));
		});
		
		where.orGe("ex", null);
		try {
			where.build((query, params) -> {});
			fail("NullPointerException must be occured");
		} catch (NullPointerException e) {
			assertEquals(String.format(NULL_FORMAT, "ex >= ?"), e.getMessage());
		}
	}
	
	@Test
	public void orGeAlias() {
		Where where = new WhereImpl();
		where.orGe("ALIAS", "id", 1);
		where.build((query, params) -> {
			assertEquals("ALIAS.id >= ?", query.trim());
			assertEquals(1, params.size());
			assertEquals(1, params.get(0));
		});
		
		
		where.orGe("ALIAS", "age", 10);
		where.build((query, params) -> {
			assertEquals("ALIAS.id >= ? OR ALIAS.age >= ?", query.trim());
			assertEquals(2, params.size());
			assertEquals(1, params.get(0));
			assertEquals(10, params.get(1));
		});
		
		where.orGe("ALIAS", "ex", null);
		try {
			where.build((query, params) -> {});
			fail("NullPointerException must be occured");
		} catch (NullPointerException e) {
			assertEquals(String.format(NULL_FORMAT, "ALIAS.ex >= ?"), e.getMessage());
		}
	}

	

	@Test
	public void andLt() {
		Where where = new WhereImpl();
		where.lt("id", 1);
		where.build((query, params) -> {
			assertEquals("id < ?", query.trim());
			assertEquals(1, params.size());
			assertEquals(1, params.get(0));
		});
		
		
		where.lt("age", 10);
		where.build((query, params) -> {
			assertEquals("id < ? AND age < ?", query.trim());
			assertEquals(2, params.size());
			assertEquals(1, params.get(0));
			assertEquals(10, params.get(1));
		});
		
		where.lt("ex", null);
		try {
			where.build((query, params) -> {});
			fail("NullPointerException must be occured");
		} catch (NullPointerException e) {
			assertEquals(String.format(NULL_FORMAT, "ex < ?"), e.getMessage());
		}
	}
	
	@Test
	public void andLtAlias() {
		Where where = new WhereImpl();
		where.lt("ALIAS", "id", 1);
		where.build((query, params) -> {
			assertEquals("ALIAS.id < ?", query.trim());
			assertEquals(1, params.size());
			assertEquals(1, params.get(0));
		});
		
		
		where.lt("ALIAS", "age", 10);
		where.build((query, params) -> {
			assertEquals("ALIAS.id < ? AND ALIAS.age < ?", query.trim());
			assertEquals(2, params.size());
			assertEquals(1, params.get(0));
			assertEquals(10, params.get(1));
		});
		
		where.lt("ALIAS", "ex", null);
		try {
			where.build((query, params) -> {});
			fail("NullPointerException must be occured");
		} catch (NullPointerException e) {
			assertEquals(String.format(NULL_FORMAT, "ALIAS.ex < ?"), e.getMessage());
		}
	}
	
	
	@Test
	public void orLt() {
		Where where = new WhereImpl();
		where.orLt("id", 1);
		where.build((query, params) -> {
			assertEquals("id < ?", query.trim());
			assertEquals(1, params.size());
			assertEquals(1, params.get(0));
		});
		
		
		where.orLt("age", 10);
		where.build((query, params) -> {
			assertEquals("id < ? OR age < ?", query.trim());
			assertEquals(2, params.size());
			assertEquals(1, params.get(0));
			assertEquals(10, params.get(1));
		});
		
		where.orLt("ex", null);
		try {
			where.build((query, params) -> {});
			fail("NullPointerException must be occured");
		} catch (NullPointerException e) {
			assertEquals(String.format(NULL_FORMAT, "ex < ?"), e.getMessage());
		}
	}
	
	@Test
	public void orLtAlias() {
		Where where = new WhereImpl();
		where.orLt("ALIAS", "id", 1);
		where.build((query, params) -> {
			assertEquals("ALIAS.id < ?", query.trim());
			assertEquals(1, params.size());
			assertEquals(1, params.get(0));
		});
		
		
		where.orLt("ALIAS", "age", 10);
		where.build((query, params) -> {
			assertEquals("ALIAS.id < ? OR ALIAS.age < ?", query.trim());
			assertEquals(2, params.size());
			assertEquals(1, params.get(0));
			assertEquals(10, params.get(1));
		});
		
		where.orLt("ALIAS", "ex", null);
		try {
			where.build((query, params) -> {});
			fail("NullPointerException must be occured");
		} catch (NullPointerException e) {
			assertEquals(String.format(NULL_FORMAT, "ALIAS.ex < ?"), e.getMessage());
		}
	}
}
