package com.yoidukigembu.sql.enums;

public enum WhereDelimiter {

	AND ("AND"),
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
