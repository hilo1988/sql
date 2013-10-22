package com.yoidukigembu.sql.query;

import com.yoidukigembu.sql.query.iterator.SelectIterator;


/**
 * SELECT用ベースクエリ
 */
public abstract class AbstractSelectBaseQuery<E> extends AbstractQuery<E> {

	/** ORDER BY */
	protected CharSequence orderBy;

	/** LIMIT */
	protected Integer limit;

	/** OFFSET */
	protected Integer offset;

	public AbstractSelectBaseQuery(Class<E> entityClass) {
		super(entityClass);
	}


	/**
	 * ORDER BY を設定
	 * @param orderBy オーダーバイ
	 * @return
	 */
	public AbstractSelectBaseQuery<E> orderBy(CharSequence orderBy) {
		this.orderBy = orderBy;
		return this;
	}

	/**
	 * LIMITを設定
	 * @param limit LIMIT
	 * @return
	 */
	public AbstractSelectBaseQuery<E> limit(int limit) {
		this.limit = limit;
		return this;
	}


	/**
	 * OFFSETを設定
	 * @param offset
	 * @return
	 */
	public AbstractSelectBaseQuery<E> offset(int offset) {
		this.offset = offset;
		return this;
	}


	/**
	 * エンティティを取得します。
	 * @return エンティティ
	 */
	public abstract E getSingleResult();


	/**
	 * {@link SelectIterator}で結果を取得します。
	 * @param iterator イテレータ
	 * @return イテレータで設定した結果
	 */
	public abstract <RESULT> RESULT iterate(SelectIterator<E, RESULT> iterator);



}
