package com.yoidukigembu.sql.enums;

public enum OrderType {

	ASC("ASC"),
	DESC("DESC")
	;
	
	private String value;
	
	private OrderType(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}
