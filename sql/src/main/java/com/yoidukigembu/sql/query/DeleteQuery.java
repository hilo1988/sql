package com.yoidukigembu.sql.query;

import com.yoidukigembu.sql.entity.AbstractBaseEntity;

/**
 * DELETE用クエリクラス
 * @author Takehiro Nakamori
 *
 * @param <E> エンティティ
 */
public abstract class DeleteQuery<E extends AbstractBaseEntity> extends UpdateQuery<E> {



	public DeleteQuery(Class<E> entityClass, E entity) {
		super(entityClass, entity);
	}

	@Deprecated
	@Override
	public  DeleteQuery<E> includes(CharSequence... columnNames) {
		throw new UnsupportedOperationException("DeleteQueryでは、includesメソッドはサポートしていません。");
	}

	@Deprecated
	@Override
	public DeleteQuery<E> excludes(CharSequence... columnNames) {
		throw new UnsupportedOperationException("DeleteQueryでは、excludesメソッドはサポートしていません。");
	}

	@Deprecated
	@Override
	public DeleteQuery<E> excludesNull() {
		throw new UnsupportedOperationException("DeleteQueryでは、excludesNullメソッドはサポートしていません。");
	}

	@Deprecated
	@Override
	public DeleteQuery<E> includesNull(CharSequence... nullableColumnNames) {
		throw new UnsupportedOperationException("DeleteQueryでは、includesNullメソッドはサポートしていません。");
	}

}
