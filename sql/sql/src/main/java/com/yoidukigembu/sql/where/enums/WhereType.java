package com.yoidukigembu.sql.where.enums;

/**
 * WHEREのタイプ
 * @author hilo
 *
 */
public enum WhereType {

	/** column IS NOT NULL */
	IS_NOT_NULL("%s IS NOT NULL"),
	/** column IS NULL */
	IS_NULL("%s IS NULL"),
	/** column = ? */
	EQUALS("%s = ?"),
	/** column != ? */
	NOT_EQUALS("%s != ?"),
	/** column > ? */
	GREATER_THAN("%s > ?"),
	/** column >= ? */
	GREATER_EQUALS("%s >= ?"),
	/** column < ? */
	LESS_THAN("%s < ?"),
	/** column <= ? */
	LESS_EQUALS("%s <= ?"),
	/** column IN (?,?...) */
	IN("%s IN (%s)"),
	/** column NOT IN (?,?...) */
	NOT_IN("%s NOT IN (%s)"),
	/** column LIKE 'str%' */
	BEGIN_WITH("%s LIKE ?"),
	/** column LIKE '%str' */
	ENDS_WITH("%s LIKE ?"),
	/** column LIKE '%str%' */
	CONTAINS("%s LIKE ?"),
	;
	
	private String queryFormat;
	
	private WhereType(String queryFormat) {
		this.queryFormat = queryFormat;
	}
	
	public String getQueryFormat() {
		return queryFormat;
	}
}
