package com.yoidukigembu.sql.where;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.yoidukigembu.sql.enums.WhereDelimiter;

/**
 * WHEREオブジェクト
 * @author hilo
 *
 */
public interface Where {
	
	/** ホルダリストの取得 */
	List<WhereHolder> getHolderList();
	


	/**
	 * クエリの追加
	 * @param delimiter AND・ORなどのデリミタ
	 * @param query クエリ
	 * @param param パラメータ
	 */
	public default void addQuery(WhereDelimiter delimiter, String query, Object param) {
		getHolderList().add(new WhereHolder(delimiter, query, Optional.ofNullable(param)));
		
	}
	
	/**
	 * IS NOT NULL クエリをANDで追加
	 * @param column カラム名
	 * @return 自身
	 */
	public default Where isNotNull(String column) {
		return isNotNull(WhereDelimiter.AND, column);
	}
	
	/**
	 * IS NOT NULL クエリをORで追加<br>
	 * @param column カラム名
	 * @return 自身
	 */
	public default Where orIsNotNull(String column) {
		return isNotNull(WhereDelimiter.OR, column);
	}
	
	/**
	 * IS NOT NULL クエリを追加
	 * @param delimiter デリミタ
	 * @param column カラム名
	 * @return 自身
	 */
	public default Where isNotNull(WhereDelimiter delimiter, String column) {
		getHolderList().add(
				new WhereHolder(delimiter, 
						String.format("%s IS NOT NULL", column),
						Optional.empty(),
						true));
		return this;
	}
	
	
	/**
	 * IS NULL を AND で追加
	 * @param column カラム名
	 * @return 自身
	 */
	public default Where isNull(String column) {
		return isNull(WhereDelimiter.AND, column);
	}
	
	/**
	 * IS NULL を OR で追加
	 * @param column カラム名
	 * @return 自身
	 */
	public default Where orIsNull(String column) {
		return isNull(WhereDelimiter.OR, column);
	}
	
	
	
	/**
	 * IS NULL を追加
	 * @param delimiter デリミタ
	 * @param column カラム名
	 * @return 自身
	 */
	public default Where isNull(WhereDelimiter delimiter, String column) {
		getHolderList().add(new WhereHolder(delimiter, 
				String.format("%s IS NULL", column),
				Optional.empty(),
				true));
		return this;
	}
	
	
	/**
	 * column = ? をANDで追加
	 * @param column カラム名
	 * @return 自身
	 */
	public default Where eq(String column, Object param) {
		return eq(WhereDelimiter.AND, column, param);
	}
	
	/**
	 * column = ? をORで追加
	 * @param column カラム名
	 * @return 自身
	 */
	public default Where orEq(String column, Object param) {
		return eq(WhereDelimiter.OR, column, param);
	}
	
	/**
	 * column = ? を追加
	 * @param delimiter デリミタ
	 * @param column カラム名
	 * @return 自身
	 */
	public default Where eq(WhereDelimiter delimiter, String column, Object param) {
		getHolderList()
			.add(new WhereHolder(delimiter,
					String.format("%s = ?", column),
					Optional.ofNullable(param)));
		return this;
	}
	
	
	/**
	 * column != ? をANDで追加
	 * @param column カラム名
	 * @return 自身
	 */
	public default Where ne(String column, Object param) {
		return ne(WhereDelimiter.AND, column, param);
	}
	
	/**
	 * column != ? をORで追加
	 * @param column カラム名
	 * @return 自身
	 */
	public default Where orNe(String column, Object param) {
		return ne(WhereDelimiter.OR, column, param);
	}
	
	/**
	 * column != ? を追加
	 * @param delimiter デリミタ
	 * @param column カラム名
	 * @return 自身
	 */
	public default Where ne(WhereDelimiter delimiter, String column, Object param) {
		getHolderList()
			.add(new WhereHolder(delimiter,
					String.format("%s != ?", column),
					Optional.ofNullable(param)));
		return this;
	}
	
	/**
	 * column > ? をANDで追加
	 * @param column カラム名
	 * @return 自身
	 */
	public default Where gt(String column, Object param) {
		return gt(WhereDelimiter.AND, column, param);
	}
	
	
	/**
	 * column > ? をORで追加
	 * @param column カラム名
	 * @return 自身
	 */
	public default Where orGt(String column, Object param) {
		return gt(WhereDelimiter.OR, column, param);
	}
	
	/**
	 * column > ? を追加
	 * @param delimiter デリミタ
	 * @param column カラム名
	 * @return 自身
	 */
	public default Where gt(WhereDelimiter delimiter, String column, Object param) {
		getHolderList()
			.add(new WhereHolder(delimiter,
					String.format("%s > ?", column),
					Optional.ofNullable(param)));
		return this;
	}
	
	
	/**
	 * column >= ? をANDで追加
	 * @param column カラム名
	 * @return 自身
	 */
	public default Where ge(String column, Object param) {
		return ge(WhereDelimiter.AND, column, param);
	}
	
	
	/**
	 * column >= ? をORで追加
	 * @param column カラム名
	 * @return 自身
	 */
	public default Where orGe(String column, Object param) {
		return ge(WhereDelimiter.OR, column, param);
	}
	
	/**
	 * column >= ? を追加
	 * @param delimiter デリミタ
	 * @param column カラム名
	 * @return 自身
	 */
	public default Where ge(WhereDelimiter delimiter, String column, Object param) {
		getHolderList()
			.add(new WhereHolder(delimiter,
					String.format("%s >= ?", column),
					Optional.ofNullable(param)));
		return this;
	}
	
	
	/**
	 * column < ? をANDで追加
	 * @param column カラム名
	 * @return 自身
	 */
	public default Where lt(String column, Object param) {
		return lt(WhereDelimiter.AND, column, param);
	}
	
	
	/**
	 * column < ? をORで追加
	 * @param column カラム名
	 * @return 自身
	 */
	public default Where orLt(String column, Object param) {
		return lt(WhereDelimiter.OR, column, param);
	}
	
	/**
	 * column < ? を追加
	 * @param delimiter デリミタ
	 * @param column カラム名
	 * @return 自身
	 */
	public default Where lt(WhereDelimiter delimiter, String column, Object param) {
		getHolderList()
			.add(new WhereHolder(delimiter,
					String.format("%s < ?", column),
					Optional.of(param)));
		return this;
	}
	
	
	/**
	 * column <= ? をANDで追加
	 * @param column カラム名
	 * @return 自身
	 */
	public default Where le(String column, Object param) {
		return le(WhereDelimiter.AND, column, param);
	}
	
	
	/**
	 * column <= ? をORで追加
	 * @param column カラム名
	 * @return 自身
	 */
	public default Where orLe(String column, Object param) {
		return le(WhereDelimiter.OR, column, param);
	}
	
	/**
	 * column <= ? を追加
	 * @param delimiter デリミタ
	 * @param column カラム名
	 * @return 自身
	 */
	public default Where le(WhereDelimiter delimiter, String column, Object param) {
		getHolderList()
			.add(new WhereHolder(delimiter,
					String.format("%s <= ?", column),
					Optional.of(param)));
		return this;
	}
	
	public static String createQuestions(Collection<?> col) {
		return StringUtils.join(
				IntStream.range(1, col.size())
				.boxed()
				.map(i -> "?"), 
				",");
	}
	
	
	
	/**
	 * column IN (?, ?,...) をANDで追加
	 * @param column カラム名
	 * @return 自身
	 */
	public default Where in(String column, Collection<?> params) {
		return in(WhereDelimiter.AND, column, params);
	}
	
	/**
	 * column IN (?, ?,...) をORで追加
	 * @param column カラム名
	 * @return 自身
	 */
	public default Where orIn(String column, Collection<?> params) {
		return in(WhereDelimiter.AND, column, params);
	}
	
	/**
	 * column IN (?, ?,...) を追加
	 * @param delimiter デリミタ
	 * @param column カラム名
	 * @return 自身
	 */
	public default Where in(WhereDelimiter delimiter, String column, Collection<?> params) {
		getHolderList()
			.add(new WhereHolder(delimiter,
					String.format("%s IN (%s)", column, createQuestions(params))
					, 
					Optional.ofNullable(params)));
		return this;
	}
	
	
	/**
	 * column NOT IN (?, ?,...) をANDで追加
	 * @param column カラム名
	 * @return 自身
	 */
	public default Where notIn(String column, Collection<?> params) {
		return notIn(WhereDelimiter.AND, column, params);
	}
	
	/**
	 * column NOT IN (?, ?,...) をORで追加
	 * @param column カラム名
	 * @return 自身
	 */
	public default Where orNotIn(String column, Collection<?> params) {
		return notIn(WhereDelimiter.OR, column, params);
	}
	
	/**
	 * column NOT IN (?, ?,...) を追加
	 * @param delimiter デリミタ
	 * @param column カラム名
	 * @return 自身
	 */
	public default Where notIn(WhereDelimiter delimiter, String column, Collection<?> params) {
		getHolderList()
			.add(new WhereHolder(delimiter,
					String.format("%s NOT IN (%s)", column, createQuestions(params))
					, 
					Optional.ofNullable(params)));
		return this;
	}
	
	
	/**
	 * column LIKE 'str%' をANDで追加
	 * @param column カラム名
	 * @return 自身
	 */
	public default Where beginWith(String column, String param) {
		return beginWith(WhereDelimiter.AND, column, param);
	}
	
	/**
	 * column LIKE 'str%' をORで追加
	 * @param column カラム名
	 * @return 自身
	 */
	public default Where orBeginWith(String column, String param) {
		return beginWith(WhereDelimiter.OR, column, param);
	}
	
	
	/**
	 * column LIKE 'str%' を追加
	 * @param delimiter デリミタ
	 * @param column カラム名
	 * @return 自身
	 */
	public default Where beginWith(WhereDelimiter delimiter, String column, String param) {
		getHolderList()
			.add(new WhereHolder(delimiter, 
					String.format("%s LIKE ?", column),
					Optional.ofNullable(param)
								.map(s -> s.concat("%"))));
		
		return this;
	}
	
	/**
	 * column LIKE '%str' ANDで追加
	 * @param column カラム名
	 * @return 自身
	 */
	public default Where endsWith(String column, String param) {
		return endsWith(WhereDelimiter.AND, column, param);
	}
	
	/**
	 * column LIKE '%str' ORで追加
	 * @param column カラム名
	 * @return 自身
	 */
	public default Where orEndsWith(String column, String param) {
		return endsWith(WhereDelimiter.OR, column, param);
	}
	
	/**
	 * column LIKE '%str' を追加
	 * @param delimiter デリミタ
	 * @param column カラム名
	 * @return 自身
	 */
	public default Where endsWith(WhereDelimiter delimiter, String column, String param) {
		getHolderList()
			.add(new WhereHolder(delimiter, 
					String.format("%s LIKE ?", column),
					Optional.ofNullable(param)
								.map(s -> "%".concat(s))));
		
		return this;
	}
	
	/**
	 * column LIKE '%str%' をANDで追加
	 * @param column カラム名
	 * @return 自身
	 */
	public default Where contains(String column, String param) {
		return contains(WhereDelimiter.AND, column, param);
	}
	
	/**
	 * column LIKE '%str%' をORで追加
	 * @param column カラム名
	 * @return 自身
	 */
	public default Where orContains(String column, String param) {
		return contains(WhereDelimiter.OR, column, param);
	}
	
	/**
	 * column LIKE '%str%' を追加
	 * @param delimiter デリミタ
	 * @param column カラム名
	 * @return 自身
	 */
	public default Where contains(WhereDelimiter delimiter, String column, String param) {
		getHolderList()
			.add(new WhereHolder(delimiter, 
					String.format("%s LIKE ?", column),
					Optional.ofNullable(param)
								.map(s -> String.format("%%%s%%", s))));
		
		return this;
	}
	
	public void build(Consumer consumer);
	
	
	
	
	/**
	 * WHERE条件を保持するクラス
	 * @author hilo
	 *
	 */
	public static class WhereHolder {
		/** デリミタ */
		private final WhereDelimiter delimiter;
		
		/** クエリ */
		private final String query;
		
		/** パラメータ */
		private final Optional<?> param;
		
		/**
		 * 空フラグ
		 * IS NULL など値を指定しない時にtrue
		 */
		private final boolean blankFlg;
		
		public WhereHolder(WhereDelimiter delimiter, String query, Optional<?> param) {
			this(delimiter, query, param, false);
			
		}
		public WhereHolder(WhereDelimiter delimiter, String query, Optional<?> param, boolean blankFlg) {
			this.delimiter = delimiter;
			this.query = query;
			this.param = param;
			this.blankFlg = blankFlg;
		}
		
		
		
		
		public WhereDelimiter getDelimiter() {
			return delimiter;
		}
		public String getQuery() {
			return query;
		}
		public Optional<?> getParam() {
			return param;
		}
		public boolean isBlankFlg() {
			return blankFlg;
		}
		@Override
		public String toString() {
			return ToStringBuilder.reflectionToString(this);
		}
		
	}
	
	@FunctionalInterface
	public interface Consumer {
		public void consume(String where, List<Object> params);
	}
	
}
