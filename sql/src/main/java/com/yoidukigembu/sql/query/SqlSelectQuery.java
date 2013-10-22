package com.yoidukigembu.sql.query;

import java.util.List;

public abstract class SqlSelectQuery<E> extends AbstractSelectBaseQuery<E> {

	/** SQL */
	protected String sql;

	/** WHERE用パラメータ */
	protected Object[] params;


	public SqlSelectQuery(Class<E> entityClass, String sql, Object... params) {
		super(entityClass);
		this.sql = sql;
		if (params == null) {
			this.params = new Object[0];
		} else {
			this.params = params;
		}
	}

	public SqlSelectQuery(Class<E> entityClass, CharSequence charSequence, List<Object> params) {
		super(entityClass);
		this.sql = charSequence.toString();
		if (params == null) {
			this.params = new Object[0];
		} else {
			this.params = params.toArray();
		}
	}



	@Override
	public SqlSelectQuery<E> orderBy(CharSequence orderBy) {
		return (SqlSelectQuery<E>) super.orderBy(orderBy);
	}

	@Override
	public SqlSelectQuery<E> limit(int limit) {
		return (SqlSelectQuery<E>) super.limit(limit);
	}

	@Override
	public SqlSelectQuery<E> offset(int offset) {
		return (SqlSelectQuery<E>) super.offset(offset);
	}

}
