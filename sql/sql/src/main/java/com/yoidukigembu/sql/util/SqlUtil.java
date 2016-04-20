package com.yoidukigembu.sql.util;

import java.util.Optional;

public class SqlUtil {

	private SqlUtil() {}
	
	/**
	 * エイリアス作成
	 * @param alias エイリアス
	 * @param name カラム、テーブル等の名前
	 * @return  エイリアスがついた名称
	 */
	public static String alias(Optional<String> alias, String name) {
		return alias.map(a -> String.format("%s.%s", a, name))
					.orElse(name);
	}
}
