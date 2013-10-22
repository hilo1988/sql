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

	public UpdateQuery(Class<E> entityClass) {
		super(entityClass);
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


}
