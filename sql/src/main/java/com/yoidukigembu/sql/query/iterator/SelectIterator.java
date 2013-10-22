package com.yoidukigembu.sql.query.iterator;

/**
 * セレクト用イテレータ
 * @param <ENTITY> エンティティ
 * @param <RESULT> 結果
 * @author Takehiro Nakamori
 *
 */
public interface SelectIterator<ENTITY, RESULT>  {

	/**
	 * イテレートを行います。
	 * @param entity エンティティ
	 * @return 結果
	 */
	public RESULT iterate(ENTITY entity);
}
