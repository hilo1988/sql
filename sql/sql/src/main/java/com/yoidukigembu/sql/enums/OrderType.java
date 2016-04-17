package com.yoidukigembu.sql.enums;

/**
 * ORDER BY のタイプ
 * @author hilo
 *
 */
public enum OrderType {

	/** 昇順 */
	ASC("ASC"),
	/** 降順 */
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
