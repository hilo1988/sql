package com.yoidukigembu.sql.query;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import com.yoidukigembu.sql.entity.AbstractBaseEntity;
import com.yoidukigembu.sql.exception.YSqlException;

/**
 * クエリ作成用マネージャ
 * @author hiro
 *
 */
public abstract class AbstractDao<E extends AbstractBaseEntity> {
	
	/** エンティティクラス */
	protected Class<E> entityClass;
	
	public AbstractDao(Class<E> entityClass) {
		this.entityClass = entityClass;
	}
	
	/**
	 * インサートを行います。
	 * @param entity エンティティ
	 * @return インサートしたレコード数
	 */
	public abstract long insert(E entity);
	
	/**
	 * UPDATEを行います。
	 * @param entity エンティティ
	 * @return UPDATEしたレコード数
	 */
	public abstract long update(E entity);

	
	/**
	 * クラスからテーブル名を取得します。
	 * @param clazz クラス
	 * @return
	 */
	protected String getTable() {
		Field field;
		try {
			field = entityClass.getDeclaredField("TABLE_NAME");
		} catch (NoSuchFieldException e) {
			throw new YSqlException("スタティック変数「TABLE_NAME」を実装してください。", e);
		} catch (SecurityException e) {
			throw new YSqlException("スタティック変数「TABLE_NAME」を実装してください。", e);
		}
		
		
		int modifiers = field.getModifiers();
		if (!Modifier.isFinal(modifiers)
				|| !Modifier.isStatic(modifiers)) {
			throw new YSqlException("「TABLE_NAME」はstatic finalで宣言してください。");
		}
		
		field.setAccessible(true);
		
		try {
			return String.valueOf(field.get(null));
		} catch (IllegalArgumentException e) {
			throw new YSqlException("「TABLE_NAME」の取得に失敗しました。", e);
		} catch (IllegalAccessException e) {
			throw new YSqlException("「TABLE_NAME」の取得に失敗しました。", e);
		}
		
	}
}
