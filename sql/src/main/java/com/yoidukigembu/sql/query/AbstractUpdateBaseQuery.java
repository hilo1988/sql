package com.yoidukigembu.sql.query;

import com.yoidukigembu.sql.entity.AbstractBaseEntity;

public abstract class AbstractUpdateBaseQuery<E extends AbstractBaseEntity> extends AbstractQuery<E> {

	public AbstractUpdateBaseQuery(Class<E> entityClass) {
		super(entityClass);
	}


	 /**
     * 指定のプロパティのみを更新対象とします。
     *
     * @param propertyNames
     *            更新対象とするプロパティ名の並び
     * @return このインスタンス自身
     */
	public abstract AbstractUpdateBaseQuery<E> includes(CharSequence... propertyNames);

    /**
     * 指定のプロパティを更新対象から除外します。
     *
     * @param propertyNames
     *            更新対象から除外するプロパティ名の並び
     * @return このインスタンス自身
     */
	public abstract AbstractUpdateBaseQuery<E> excludes(CharSequence... propertyNames);

	/**
	 * NULLを更新対象外
	 * @return
	 */
	public abstract AbstractUpdateBaseQuery<E> excludesNull();

	/**
	 * NULLを対象にする。
	 * @return
	 */
	public abstract AbstractUpdateBaseQuery<E> includesNull();

	/**
     * 更新を行います。
     *
     * @return 更新した行数。
     */
    public abstract int execute();

}
