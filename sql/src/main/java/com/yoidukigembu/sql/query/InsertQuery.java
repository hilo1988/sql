package com.yoidukigembu.sql.query;

import com.yoidukigembu.sql.entity.AbstractBaseEntity;

/**
 * インサート用クエリクラス
 * @author Takehiro Nakamori
 *
 * @param <E>
 */
public abstract class InsertQuery<E extends AbstractBaseEntity> extends AbstractUpdateBaseQuery<E> {


	public InsertQuery(Class<E> entityClass, E entity) {
		super(entityClass, entity);
	}


	/**
	 * 指定のプロパティのみを挿入対象とします。
	 *
	 * @param propertyNames
	 *            挿入対象とするプロパティ名の並び
	 * @return このインスタンス自身
	 */
	@Override
	public InsertQuery<E> includes(CharSequence... columnNames) {
		super.includes(columnNames);
		return this;
	}


	/**
	 * 指定のプロパティを挿入対象から除外します。
	 *
	 * @param propertyNames
	 *            挿入対象から除外するプロパティ名の並び
	 * @return このインスタンス自身
	 */
	@Override
	public InsertQuery<E> excludes(CharSequence... columnNames) {
		super.excludes(columnNames);
		return this;
	}


	 /**
     * <code>null</code>値のプロパティを挿入対象から除外します。
     *
     * @return このインスタンス自身
     */
	@Override
	public InsertQuery<E> excludesNull() {
		super.excludesNull();
		return this;
	}


	/**
	 * NULLを対象にする。
	 * @param NULLを許可するカラム名
	 * @return
	 */
	@Override
	public InsertQuery<E> includesNull(CharSequence... nullableColumnNames) {
		super.includesNull(nullableColumnNames);
		return this;
	}




	/**
     * インサートを行います。
     *
     * @return インサートした行数。
     */
    public abstract int execute();
}
