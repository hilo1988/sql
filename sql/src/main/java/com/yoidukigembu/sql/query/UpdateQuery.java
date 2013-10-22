package com.yoidukigembu.sql.query;

import com.yoidukigembu.sql.builder.WhereBuilder;
import com.yoidukigembu.sql.entity.AbstractBaseEntity;

/**
 * UPDATE用クエリクラス
 * @author Takehiro Nakamori
 *
 * @param <E>
 */
public abstract class UpdateQuery<E extends AbstractBaseEntity> extends AbstractUpdateBaseQuery<E> {

	/** WHERE */
	protected WhereBuilder where;

	public UpdateQuery(Class<E> entityClass, E entity) {
		super(entityClass, entity);
	}

	/**
	 * WHEREを設定します。
	 * @param where WHERE
	 * @return
	 */
	public UpdateQuery<E> where(WhereBuilder where) {
		this.where = where;
		return this;

	}

	/**
	* 指定のプロパティのみを更新対象とします。
	*
	* @param propertyNames
	*            更新対象とするプロパティ名の並び
	* @return このインスタンス自身
	*/
	@Override
	public UpdateQuery<E> includes(CharSequence... columnNames) {
		super.includes(columnNames);
		return this;
	}


	/**
	 * 指定のプロパティを更新対象から除外します。
	 *
	 * @param propertyNames
	 *            更新対象から除外するプロパティ名の並び
	 * @return このインスタンス自身
	 */
	@Override
	public UpdateQuery<E> excludes(CharSequence... columnNames) {
		super.excludes(columnNames);
		return this;
	}


	/**
	 * NULLを更新対象外に設定
	 * @return このインスタンス自身
	 */
	@Override
	public UpdateQuery<E> excludesNull() {
		super.excludesNull();
		return this;
	}

	/**
	 * NULLを対象にする。
	 * @param NULLを許可するカラム名
	 * @return このインスタンス自身
	 */
	@Override
	public UpdateQuery<E> includesNull(CharSequence... nullableColumnNames) {
		super.includesNull(nullableColumnNames);
		return this;
	}

}
