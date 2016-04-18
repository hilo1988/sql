package com.yoidukigembu.sql.select;

import java.util.List;
import java.util.Optional;

import javax.persistence.Table;

import com.yoidukigembu.sql.orderBy.OrderBy;
import com.yoidukigembu.sql.select.impl.SelectImpl;
import com.yoidukigembu.sql.where.Where;

/**
 * セレクト
 * @author hilo
 *
 */
public interface Select<T> {
	
	public static <T> Select<T> from(Class<T> entityClass) {
		return from(entityClass, null);
	}
	
	public static <T> Select<T> from(Class<T> entityClass, String alias) {
		Table table = entityClass.getAnnotation(Table.class);
		return new SelectImpl<>(table.schema(), alias, table.name());
	}
	
	
	/**
	 * セレクト対象のカラムを指定
	 */
	public Select<T> columns(String...columns);
	
	/**
	 * セレクト対象のカラムを指定
	 */
	public Select<T> columns(List<String> columns);
	
	/**
	 * 条件を指定
	 */
	public Select<T> where(Where where);

	/**
	 * limitを指定
	 */
	public Select<T> limit(int limit);
	
	/**
	 * offsetを指定
	 * @param offset
	 * @return
	 */
	public Select<T> offset(int offset);
	
	/**
	 * GROUP BY を指定
	 */
	public Select<T> groupBy(String... columns);

	/**
	 * GROUP BY を指定
	 */
	public Select<T> groupBy(List<String> columns);
	
	/**
	 * ORDER BYを指定
	 */
	public Select<T> orderBy(OrderBy orderBy);
	
	/**
	 * ORDER BYを指定
	 */
	public Select<T> orderBy(CharSequence orderBy);
	
	public default String alias(Optional<String> alias, String column) {
		return alias.map(a -> String.format("%s.%s", a, column))
				.orElse(column);
	}
	
	/**
	 * SQL文を生成
	 */
	public void generate(QueryGenerator generator);
	
	@FunctionalInterface
	public interface QueryGenerator {
		public void generate(String sql, List<Object> params);
	}
}
