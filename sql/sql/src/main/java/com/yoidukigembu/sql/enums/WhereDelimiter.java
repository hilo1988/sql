package com.yoidukigembu.sql.enums;

/**
 * WHEREのデリミタ
 * @author hilo
 *
 */
public enum WhereDelimiter {

	/** AND */
	AND ("AND"),
	/** OR */
	OR ("OR")
	;
	
	private String value;
	
	private WhereDelimiter(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}
