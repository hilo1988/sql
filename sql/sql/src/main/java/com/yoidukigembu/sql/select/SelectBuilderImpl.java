package com.yoidukigembu.sql.select;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.yoidukigembu.sql.orderBy.OrderBy;
import com.yoidukigembu.sql.where.Where;

public class SelectBuilderImpl implements SelectBuilder {
	
	/** スキーマ */
	private final Optional<String> schema;
	
	/** エイリアス */
	private final Optional<String> alias;
	
	/** テーブル名 */
	private final String tableName;
	
	/** セレクトするカラムリスト */
	private List<String> columnList;
	
	/** 検索条件 */
	private Optional<Where> where = Optional.empty();
	
	/** limit */
	private Optional<Integer> limit = Optional.empty();
	
	/** offset */
	private Optional<Integer> offset = Optional.empty();
	
	/** GROUP BY のリスト */
	private Optional<List<String>> groupByList = Optional.empty();
	
	/** ORDER BY */
	private Optional<CharSequence> orderBy;

	private List<Object> params;
	
	SelectBuilderImpl(Optional<String> schema, Optional<String> alias, String tableName) {
		this.schema = schema;
		this.alias = alias;
		this.tableName = tableName;
	}

	public Optional<String> getSchema() {
		return schema;
	}

	public Optional<String> getAlias() {
		return alias;
	}

	public String getTableName() {
		return tableName;
	}

	@Override
	public SelectBuilder columns(String... columns) {
		return columns(Arrays.asList(columns));
	}

	@Override
	public SelectBuilder columns(List<String> columns) {
		this.columnList = columns;
		return this;
	}

	@Override
	public SelectBuilder where(Where where) {
		this.where = Optional.of(where);
		return this;
	}

	@Override
	public SelectBuilder limit(int limit) {
		this.limit = Optional.of(limit);
		return this;
	}

	@Override
	public SelectBuilder offset(int offset) {
		this.offset = Optional.of(offset);
		return this;
	}

	@Override
	public SelectBuilder groupBy(String... columns) {
		return groupBy(Arrays.asList(columns));
	}

	@Override
	public SelectBuilder groupBy(List<String> columns) {
		this.groupByList = Optional.of(columns);
		return this;
	}

	@Override
	public SelectBuilder orderBy(OrderBy orderBy) {
		return orderBy(orderBy.getOrder(alias));
	}

	@Override
	public SelectBuilder orderBy(CharSequence orderBy) {
		this.orderBy = Optional.ofNullable(orderBy);
		return this;
	}
	
	
	@Override
	public void generate(QueryGenerator generator) {
		StringBuilder sql = new StringBuilder("SELECT ");
		sql.append(createColumn())
			.append(" FROM ")
			.append(tableName);
		
		addWhere(sql);
		
		addGroupBy(sql);
		
		addOrderBy(sql);
		
		addLimit(sql);
		
		addOffset(sql);
		
		generator.generate(sql.toString(), params);
	}
	
	private CharSequence createColumn() {
		if (columnList == null || columnList.isEmpty()) {
			return "*";
		}
		
		StringBuilder sb = new StringBuilder();
		int index = 0;
		for (String column : columnList) {
			if (index++ > 0) {
				sb.append(", ");
			}
			sb.append(column);
		}
		
		return sb;
	}
	
	private void addWhere(StringBuilder sql) {
		where.ifPresent(w -> {
			w.build((query, params) -> {
				sql.append(" WHERE ").append(query);
				SelectBuilderImpl.this.params = params;
			});
		});
	}
	
	private void addGroupBy(StringBuilder sql) {
		groupByList.ifPresent(list -> {
			sql.append(" GROUP BY ");
			int index = 0;
			for (String group : list) {
				if (index++ > 0) {
					sql.append(", ");
				}
				sql.append(group);
			}
		});
	}
	
	private void addOrderBy(StringBuilder sql) {
		orderBy.ifPresent(order -> sql.append("ORDER BY ").append(order));
	}
	
	private void addLimit(StringBuilder sql) {
		limit.ifPresent(limit -> sql.append(" LIMIT ").append(limit));
	}
	
	private void addOffset(StringBuilder sql) {
		offset.ifPresent(offset -> sql.append(" OFFSET ").append(offset));
	}
	
	
	

}
