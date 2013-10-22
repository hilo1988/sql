package com.yoidukigembu.sql.query;

import java.util.ArrayList;
import java.util.List;

import com.yoidukigembu.sql.entity.AbstractBaseEntity;

public abstract class AbstractUpdateBaseQuery<E extends AbstractBaseEntity> extends AbstractQuery<E> {

	/** エンティティ */
	protected E entity;

	/** NULLを含まないフラグ */
	protected boolean excludesNullFlg;

	/** 更新を含むカラム名リスト */
	protected List<String> includesColumnNameList;

	/** 更新を含まないカラム名リスト */
	protected List<String> excludesColumnNameList;

	/** NULLを許可するカラム名リスト */
	protected List<String> nullableColumnNameList;

	/**
	 * エンティティクラスと、エンティティオブジェクトからインスタンスを作成
	 * @param entityClass エンティティクラス
	 * @param entity エンティティオブジェクト
	 */
	public AbstractUpdateBaseQuery(Class<E> entityClass, E entity) {
		super(entityClass);
		this.entity = entity;
	}

	/**
	* 指定のプロパティのみを更新対象とします。
	*
	* @param propertyNames
	*            更新対象とするプロパティ名の並び
	* @return このインスタンス自身
	*/
	public AbstractUpdateBaseQuery<E> includes(CharSequence... columnNames) {
		includesColumnNameList = convertCharSequenceArrayToStringList(columnNames);
		return this;
	}

	/**
	 * 指定のプロパティを更新対象から除外します。
	 *
	 * @param propertyNames
	 *            更新対象から除外するプロパティ名の並び
	 * @return このインスタンス自身
	 */
	public AbstractUpdateBaseQuery<E> excludes(CharSequence... columnNames) {
		excludesColumnNameList = convertCharSequenceArrayToStringList(columnNames);
		return this;
	}

	/**
	 * NULLを更新対象外に設定
	 * @return このインスタンス自身
	 */
	public AbstractUpdateBaseQuery<E> excludesNull() {
		excludesNullFlg = true;
		return this;
	}

	/**
	 * NULLを対象にする。
	 * @param NULLを許可するカラム名
	 * @return このインスタンス自身
	 */
	public AbstractUpdateBaseQuery<E> includesNull(CharSequence... nullableColumnNames) {
		nullableColumnNameList = convertCharSequenceArrayToStringList(nullableColumnNames);

		return this;
	}

	/**
	 * 更新を行います。
	 *
	 * @return 更新した行数。
	 */
	public abstract int execute();

	/**
	 * CharSequence配列をStringのリストに変換します
	 * @param charSecenceArray CharSequence配列
	 * @return Stringリスト
	 */
	private static List<String> convertCharSequenceArrayToStringList(CharSequence[] charSecenceArray) {
		List<String> retList = new ArrayList<String>();
		if (charSecenceArray == null) {
			return retList;
		}

		for (CharSequence chars : charSecenceArray) {
			if (chars != null) {
				retList.add(chars.toString());
			}
		}

		return retList;
	}

}
