package com.yoidukigembu.sql.query;

import com.yoidukigembu.sql.builder.WhereBuilder;
import com.yoidukigembu.sql.entity.AbstractBaseEntity;

/**
 * セレクト用クエリ
 * @author Takehiro Nakamori
 *
 * @param <E> エンティティ
 */
public abstract class SelectQuery<E extends AbstractBaseEntity> extends AbstractSelectBaseQuery<E> {

	/** WHERE */
	protected WhereBuilder where;

	public SelectQuery(Class<E> entityClass) {
		super(entityClass);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	@Override
	public SelectQuery<E> orderBy(CharSequence orderBy) {
		// TODO 自動生成されたメソッド・スタブ
		return (SelectQuery<E>) super.orderBy(orderBy);
	}

	@Override
	public SelectQuery<E> limit(int limit) {
		// TODO 自動生成されたメソッド・スタブ
		return (SelectQuery<E>) super.limit(limit);
	}

	@Override
	public SelectQuery<E> offset(int offset) {
		// TODO 自動生成されたメソッド・スタブ
		return (SelectQuery<E>) super.offset(offset);
	}

	/**
	 * WHEREを設定します。
	 * @param where {@link WhereBuilder}
	 * @return
	 */
	public SelectQuery<E> where(WhereBuilder where) {
		this.where = where;
		return this;
	}

}
