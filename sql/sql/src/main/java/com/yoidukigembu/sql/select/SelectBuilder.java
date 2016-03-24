package com.yoidukigembu.sql.select;

import java.util.List;

import com.yoidukigembu.sql.orderBy.OrderBy;
import com.yoidukigembu.sql.where.Where;

/**
 * セレクト文のビルダ
 * @author hilo
 *
 */
public interface SelectBuilder {

	/**
	 * セレクト対象のカラムを指定
	 */
	public SelectBuilder columns(String...columns);
	
	/**
	 * セレクト対象のカラムを指定
	 */
	public SelectBuilder columns(List<String> columns);
	
	/**
	 * 条件を指定
	 */
	public SelectBuilder where(Where where);

	/**
	 * limitを指定
	 */
	public SelectBuilder limit(int limit);
	
	/**
	 * offsetを指定
	 * @param offset
	 * @return
	 */
	public SelectBuilder offset(int offset);
	
	/**
	 * GROUP BY を指定
	 */
	public SelectBuilder groupBy(String... columns);

	/**
	 * GROUP BY を指定
	 */
	public SelectBuilder groupBy(List<String> columns);
	
	/**
	 * ORDER BYを指定
	 */
	public SelectBuilder orderBy(OrderBy orderBy);
	
	/**
	 * ORDER BYを指定
	 */
	public SelectBuilder orderBy(CharSequence orderBy);
	
	/**
	 * SQL文を生成
	 */
	public void generate(QueryGenerator generator);
	
	@FunctionalInterface
	public interface QueryGenerator {
		public void generate(String sql, List<Object> params);
	}
}
