package com.yoidukigembu.sql.dao;

import com.yoidukigembu.sql.entity.AbstractBaseEntity;
import com.yoidukigembu.sql.query.DeleteQuery;
import com.yoidukigembu.sql.query.InsertQuery;
import com.yoidukigembu.sql.query.SelectQuery;
import com.yoidukigembu.sql.query.SqlSelectQuery;
import com.yoidukigembu.sql.query.UpdateQuery;

public abstract class AbstractDao<E extends AbstractBaseEntity> {

	Class<E> entityClass;

	public AbstractDao(Class<E> enityClass) {
		this.entityClass = enityClass;
	}


	/**
	 * インサートを行うクエリクラスを返します。
	 * @param インサートするエンティティ
	 * @return
	 */
	public abstract InsertQuery<E> insert(E entity);


	/**
	 * 更新を行うクエリクラスを返します。
	 * @param entity 更新を行うエンティティ
	 * @return
	 */
	public abstract UpdateQuery<E> update(E entity);

	/**
	 * 削除を行うクエリクラスを返します。
	 */
	public abstract DeleteQuery<E> delete(E entity);


	/**
	 * セレクトを行うクエリクラスを返します。
	 * @return
	 */
	public abstract SelectQuery<E> select();

	/**
	 * SQL文でセレクトを行うクエリクラスを返します。
	 * @param clazz 返り値を格納するクラス
	 * @return
	 */
	public abstract SqlSelectQuery<E> sqlSelect(Class<?> clazz);

}
