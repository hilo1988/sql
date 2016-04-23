package com.yoidukigembu.sql.where;

import com.yoidukigembu.sql.where.enums.WhereType;

abstract class AbstractWhere implements Where {

	/**
	 * パラメータの変換
	 * @param type Whereタイプ
	 * @param obj 値
	 * @return 変換されたオブジェクト
	 */
	protected Object convertParam(WhereType type, Object obj) {
		switch (type) {
		case BEGIN_WITH:
			return String.format("%s%%", obj);
		case ENDS_WITH:
			return String.format("%%%s", obj);
		case CONTAINS:
			return String.format("%%%s%%", obj);
		default:
			return obj;
		}
	}
}
