package com.yoidukigembu.sql.util;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.IntStream;

import org.apache.commons.lang3.StringUtils;

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
	
	public static String createQuestions(Collection<?> col) {
		return StringUtils.join(
				IntStream.rangeClosed(1, col.size())
				.boxed()
				.map(i -> "?").iterator(), 
				", ");
	}
}
