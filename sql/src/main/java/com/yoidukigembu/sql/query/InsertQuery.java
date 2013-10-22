package com.yoidukigembu.sql.query;

import com.yoidukigembu.sql.entity.AbstractBaseEntity;

/**
 * インサート用クエリクラス
 * @author Takehiro Nakamori
 *
 * @param <E>
 */
public abstract class InsertQuery<E extends AbstractBaseEntity> extends AbstractUpdateBaseQuery<E> {

	public InsertQuery(Class<E> entityClass) {
		super(entityClass);
	}


	 /**
     * <code>null</code>値のプロパティを挿入対象から除外します。
     *
     * @return このインスタンス自身
     */
	public abstract InsertQuery<E> excludesNull();

	/**
     * 指定のプロパティのみを挿入対象とします。
     *
     * @param propertyNames
     *            挿入対象とするプロパティ名の並び
     * @return このインスタンス自身
     */
	public abstract InsertQuery<E> includes(CharSequence... propertyNames);

    /**
     * 指定のプロパティを挿入対象から除外します。
     *
     * @param propertyNames
     *            挿入対象から除外するプロパティ名の並び
     * @return このインスタンス自身
     */
	public abstract InsertQuery<E> excludes(CharSequence... propertyNames);


	/**
     * インサートを行います。
     *
     * @return インサートした行数。
     */
    public abstract int execute();
}
