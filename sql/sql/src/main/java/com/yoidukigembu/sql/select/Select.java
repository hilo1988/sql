package com.yoidukigembu.sql.select;

import java.util.Optional;

import javax.persistence.Table;

/**
 * セレクト
 * @author hilo
 *
 */
public class Select {
	
	public static SelectBuilder from(Class<?> entityClass) {
		Table table = entityClass.getAnnotation(Table.class);
		return from("".equals(table.schema()) ? null : table.schema(), "T", entityClass);
	}
	
	public static SelectBuilder from(String alias, Class<?> entityClass) {
		Table table = entityClass.getAnnotation(Table.class);
		return from("".equals(table.schema()) ? null : table.schema(), alias, entityClass);
	}
	
	public static SelectBuilder from(String schema, String alias, Class<?> entityClass) {
		Table table = entityClass.getAnnotation(Table.class);
		return from(schema, "T", table.name());
	}
	
	public static SelectBuilder from(String tableName) {
		return from(null, "T", tableName);
	}
	
	public static SelectBuilder from(String schema, String tableName) {
		return from(schema, "T", tableName);
	}
	
	public static SelectBuilder from(String schema, String alias, String tableName) {
		return new SelectBuilderImpl(Optional.ofNullable(schema), Optional.ofNullable(alias), tableName);
	}
}
