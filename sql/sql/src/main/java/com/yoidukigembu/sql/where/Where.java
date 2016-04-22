package com.yoidukigembu.sql.where;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.yoidukigembu.sql.util.SqlUtil;
import com.yoidukigembu.sql.where.enums.WhereDelimiter;

/**
 * WHEREオブジェクト
 * @author hilo
 *
 */
public interface Where {
	
	/** ホルダリストの取得 */
	List<WhereHolder> getHolderList();
	
	public void build(Consumer consumer);
	
	/**
	 * IS NOT NULL クエリをANDで追加
	 * @param column カラム名
	 * @return 自身
	 */
	public default Where isNotNull(String column) {
		return isNotNull(null, column);
	}
	
	/**
	 * IS NOT NULL クエリをANDで追加
	 * @param alias エイリアス
	 * @param column カラム名
	 * @return 自身
	 */
	public default Where isNotNull(String alias, String column) {
		return isNotNull(WhereDelimiter.AND, alias, column);
	}
	
	
	/**
	 * IS NOT NULL クエリをORで追加<br>
	 * @param column カラム名
	 * @return 自身
	 */
	public default Where orIsNotNull(String column) {
		return orIsNotNull(null, column);
	}
	
	/**
	 * IS NOT NULL クエリをORで追加<br>
	 * @param alias エイリアス
	 * @param column カラム名
	 * @return 自身
	 */
	public default Where orIsNotNull(String alias, String column) {
		return isNotNull(WhereDelimiter.OR, alias, column);
	}
	
	
	/**
	 * IS NOT NULL クエリを追加
	 * @param delimiter デリミタ
	 * @param alias エイリアス
	 * @param column カラム名
	 * @return 自身
	 */
	public default Where isNotNull(WhereDelimiter delimiter, String alias, String column) {
		getHolderList().add(
				new WhereHolder(delimiter, 
						alias,
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
		return isNull(null, column);
	}
	
	/**
	 * IS NULL を AND で追加
	 * @param alias エイリアス
	 * @param column カラム名
	 * @return 自身
	 */
	public default Where isNull(String alias, String column) {
		return isNull(WhereDelimiter.AND, alias, column);
	}
	
	/**
	 * IS NULL を OR で追加
	 * @param column カラム名
	 * @return 自身
	 */
	public default Where orIsNull(String column) {
		return orIsNull(null, column);
	}
	
	/**
	 * IS NULL を OR で追加
	 * @param alias エイリアス
	 * @param column カラム名
	 * @return 自身
	 */
	public default Where orIsNull(String alias, String column) {
		return isNull(WhereDelimiter.OR, alias, column);
	}
	
	
	
	/**
	 * IS NULL を追加
	 * @param delimiter デリミタ
	 * @param alias エイリアス
	 * @param column カラム名
	 * @return 自身
	 */
	public default Where isNull(WhereDelimiter delimiter, String alias, String column) {
		getHolderList().add(new WhereHolder(delimiter, 
				alias,
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
		return eq(null, column, param);
	}
	
	/**
	 * column = ? をANDで追加
	 * @param alias エイリアス
	 * @param column カラム名
	 * @return 自身
	 */
	public default Where eq(String alias, String column, Object param) {
		return eq(WhereDelimiter.AND, alias, column, param);
	}
	
	/**
	 * column = ? をORで追加
	 * @param column カラム名
	 * @return 自身
	 */
	public default Where orEq(String column, Object param) {
		return orEq(null, column, param);
	}
	
	/**
	 * column = ? をORで追加
	 * @param alias エイリアス
	 * @param column カラム名
	 * @return 自身
	 */
	public default Where orEq(String alias, String column, Object param) {
		return eq(WhereDelimiter.OR, alias, column, param);
	}
	
	/**
	 * column = ? を追加
	 * @param delimiter デリミタ
	 * @param alias エイリアス
	 * @param column カラム名
	 * @return 自身
	 */
	public default Where eq(WhereDelimiter delimiter, String alias, String column, Object param) {
		getHolderList()
			.add(new WhereHolder(delimiter,
					alias,
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
		return ne(null, column, param);
	}
	
	/**
	 * column != ? をANDで追加
	 * @param alias エイリアス
	 * @param column カラム名
	 * @return 自身
	 */
	public default Where ne(String alias, String column, Object param) {
		return ne(WhereDelimiter.AND, alias, column, param);
	}
	
	/**
	 * column != ? をORで追加
	 * @param column カラム名
	 * @return 自身
	 */
	public default Where orNe(String column, Object param) {
		return orNe(null, column, param);
	}
	
	/**
	 * column != ? をORで追加
	 * @param alias エイリアス
	 * @param column カラム名
	 * @return 自身
	 */
	public default Where orNe(String alias, String column, Object param) {
		return ne(WhereDelimiter.OR, alias, column, param);
	}
	
	/**
	 * column != ? を追加
	 * @param delimiter デリミタ
	 * @param alias エイリアス
	 * @param column カラム名
	 * @return 自身
	 */
	public default Where ne(WhereDelimiter delimiter, String alias, String column, Object param) {
		getHolderList()
			.add(new WhereHolder(delimiter,
					alias,
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
		return gt(null, column, param);
	}
	
	/**
	 * column > ? をANDで追加
	 * @param alias エイリアス
	 * @param column カラム名
	 * @return 自身
	 */
	public default Where gt(String alias, String column, Object param) {
		return gt(WhereDelimiter.AND, alias, column, param);
	}
	
	/**
	 * column > ? をORで追加
	 * @param column カラム名
	 * @return 自身
	 */
	public default Where orGt(String column, Object param) {
		return orGt(null, column, param);
	}
	
	/**
	 * column > ? をORで追加
	 * @param alias エイリアス
	 * @param column カラム名
	 * @return 自身
	 */
	public default Where orGt(String alias, String column, Object param) {
		return gt(WhereDelimiter.OR, alias, column, param);
	}
	
	/**
	 * column > ? を追加
	 * @param delimiter デリミタ
	 * @param alias エイリアス
	 * @param column カラム名
	 * @return 自身
	 */
	public default Where gt(WhereDelimiter delimiter, String alias, String column, Object param) {
		getHolderList()
			.add(new WhereHolder(delimiter,
					alias,
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
		return ge(null, column, param);
	}
	
	/**
	 * column >= ? をANDで追加
	 * @param alias エイリアス
	 * @param column カラム名
	 * @return 自身
	 */
	public default Where ge(String alias, String column, Object param) {
		return ge(WhereDelimiter.AND, alias, column, param);
	}
	
	
	/**
	 * column >= ? をORで追加
	 * @param column カラム名
	 * @return 自身
	 */
	public default Where orGe(String column, Object param) {
		return orGe(null, column, param);
	}
	
	/**
	 * column >= ? をORで追加
	 * @param alias エイリアス
	 * @param column カラム名
	 * @return 自身
	 */
	public default Where orGe(String alias, String column, Object param) {
		return ge(WhereDelimiter.OR, alias, column, param);
	}
	
	/**
	 * column >= ? を追加
	 * @param delimiter デリミタ
	 * @param alias エイリアス
	 * @param column カラム名
	 * @return 自身
	 */
	public default Where ge(WhereDelimiter delimiter, String alias, String column, Object param) {
		getHolderList()
			.add(new WhereHolder(delimiter,
					alias,
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
		return lt(null, column, param);
	}
	
	/**
	 * column < ? をANDで追加
	 * @param alias エイリアス
	 * @param column カラム名
	 * @return 自身
	 */
	public default Where lt(String alias, String column, Object param) {
		return lt(WhereDelimiter.AND, alias, column, param);
	}
	
	
	/**
	 * column < ? をORで追加
	 * @param column カラム名
	 * @return 自身
	 */
	public default Where orLt(String column, Object param) {
		return orLt(null, column, param);
	}
	
	/**
	 * column < ? をORで追加
	 * @param alias エイリアス
	 * @param column カラム名
	 * @return 自身
	 */
	public default Where orLt(String alias, String column, Object param) {
		return lt(WhereDelimiter.OR, alias, column, param);
	}
	
	/**
	 * column < ? を追加
	 * @param delimiter デリミタ
	 * @param alias エイリアス
	 * @param column カラム名
	 * @return 自身
	 */
	public default Where lt(WhereDelimiter delimiter, String alias, String column, Object param) {
		getHolderList()
			.add(new WhereHolder(delimiter,
					alias,
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
		return le(null, column, param);
	}
	
	/**
	 * column <= ? をANDで追加
	 * @param alias エイリアス
	 * @param column カラム名
	 * @return 自身
	 */
	public default Where le(String alias, String column, Object param) {
		return le(WhereDelimiter.AND, alias, column, param);
	}
	
	
	/**
	 * column <= ? をORで追加
	 * @param column カラム名
	 * @return 自身
	 */
	public default Where orLe(String column, Object param) {
		return orLe(null, column, param);
	}
	
	/**
	 * column <= ? をORで追加
	 * @param alias エイリアス
	 * @param column カラム名
	 * @return 自身
	 */
	public default Where orLe(String alias, String column, Object param) {
		return le(WhereDelimiter.OR, alias, column, param);
	}
	
	/**
	 * column <= ? を追加
	 * @param delimiter デリミタ
	 * @param alias エイリアス
	 * @param column カラム名
	 * @return 自身
	 */
	public default Where le(WhereDelimiter delimiter, String alias, String column, Object param) {
		getHolderList()
			.add(new WhereHolder(delimiter,
					alias,
					String.format("%s <= ?", column),
					Optional.of(param)));
		return this;
	}
	
	/**
	 * column IN (?, ?,...) をANDで追加
	 * @param column カラム名
	 * @return 自身
	 */
	public default Where in(String column, Collection<?> params) {
		return in(null, column, params);
	}
	
	/**
	 * column IN (?, ?,...) をANDで追加
	 * @param alias エイリアス
	 * @param column カラム名
	 * @return 自身
	 */
	public default Where in(String alias, String column, Collection<?> params) {
		return in(WhereDelimiter.AND, alias, column, params);
	}
	
	/**
	 * column IN (?, ?,...) をORで追加
	 * @param column カラム名
	 * @return 自身
	 */
	public default Where orIn(String column, Collection<?> params) {
		return orIn(null, column, params);
	}
	
	/**
	 * column IN (?, ?,...) をORで追加
	 * @param alias エイリアス
	 * @param column カラム名
	 * @return 自身
	 */
	public default Where orIn(String alias, String column, Collection<?> params) {
		return in(WhereDelimiter.OR, alias, column, params);
	}
	
	/**
	 * column IN (?, ?,...) を追加
	 * @param delimiter デリミタ
	 * @param column カラム名
	 * @return 自身
	 */
	public default Where in(WhereDelimiter delimiter, String alias, String column, Collection<?> params) {
		getHolderList()
			.add(new WhereHolder(delimiter,
					alias,
					String.format("%s IN (%s)", column, SqlUtil.createQuestions(params)),
					Optional.ofNullable(params)));
		return this;
	}
	
	
	/**
	 * column NOT IN (?, ?,...) をANDで追加
	 * @param column カラム名
	 * @return 自身
	 */
	public default Where notIn(String column, Collection<?> params) {
		return notIn(null, column, params);
	}
	
	/**
	 * column NOT IN (?, ?,...) をANDで追加
	 * @param alias エイリアス
	 * @param column カラム名
	 * @return 自身
	 */
	public default Where notIn(String alias, String column, Collection<?> params) {
		return notIn(WhereDelimiter.AND, alias, column, params);
	}
	
	/**
	 * column NOT IN (?, ?,...) をORで追加
	 * @param column カラム名
	 * @return 自身
	 */
	public default Where orNotIn(String column, Collection<?> params) {
		return orNotIn(null, column, params);
	}
	
	/**
	 * column NOT IN (?, ?,...) をORで追加
	 * @param alias エイリアス
	 * @param column カラム名
	 * @return 自身
	 */
	public default Where orNotIn(String alias, String column, Collection<?> params) {
		return notIn(WhereDelimiter.OR, alias, column, params);
	}
	
	/**
	 * column NOT IN (?, ?,...) を追加
	 * @param delimiter デリミタ
	 * @param alias エイリアス
	 * @param column カラム名
	 * @return 自身
	 */
	public default Where notIn(WhereDelimiter delimiter, String alias, String column, Collection<?> params) {
		getHolderList()
			.add(new WhereHolder(delimiter,
					alias,
					String.format("%s NOT IN (%s)", column, SqlUtil.createQuestions(params)), 
					Optional.ofNullable(params)));
		return this;
	}
	
	
	/**
	 * column LIKE 'str%' をANDで追加
	 * @param column カラム名
	 * @return 自身
	 */
	public default Where beginWith(String column, String param) {
		return beginWith(null, column, param);
	}
	
	/**
	 * column LIKE 'str%' をANDで追加
	 * @param alias エイリアス
	 * @param column カラム名
	 * @return 自身
	 */
	public default Where beginWith(String alias, String column, String param) {
		return beginWith(WhereDelimiter.AND, alias, column, param);
	}
	
	/**
	 * column LIKE 'str%' をORで追加
	 * @param column カラム名
	 * @return 自身
	 */
	public default Where orBeginWith(String column, String param) {
		return orBeginWith(null, column, param);
	}
	
	/**
	 * column LIKE 'str%' をORで追加
	 * @param alias エイリアス
	 * @param column カラム名
	 * @return 自身
	 */
	public default Where orBeginWith(String alias, String column, String param) {
		return beginWith(WhereDelimiter.OR, alias, column, param);
	}
	
	
	/**
	 * column LIKE 'str%' を追加
	 * @param delimiter デリミタ
	 * @param alias エイリアス
	 * @param column カラム名
	 * @return 自身
	 */
	public default Where beginWith(WhereDelimiter delimiter,
									String alias,
									String column, String param) {
		getHolderList()
			.add(new WhereHolder(delimiter, 
					alias,
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
		return endsWith(null, column, param);
	}
	
	/**
	 * column LIKE '%str' ANDで追加
	 * @param alias エイリアス@param alias エイリアス
	 * @param column カラム名
	 * @return 自身
	 */
	public default Where endsWith(String alias, String column, String param) {
		return endsWith(WhereDelimiter.AND, alias, column, param);
	}
	
	/**
	 * column LIKE '%str' ORで追加
	 * @param column カラム名
	 * @return 自身
	 */
	public default Where orEndsWith(String column, String param) {
		return orEndsWith(null, column, param);
	}
	
	/**
	 * column LIKE '%str' ORで追加
	 * @param alias エイリアス
	 * @param column カラム名
	 * @return 自身
	 */
	public default Where orEndsWith(String alias, String column, String param) {
		return endsWith(WhereDelimiter.OR, alias, column, param);
	}
	
	/**
	 * column LIKE '%str' を追加
	 * @param delimiter デリミタ
	 * @param alias エイリアス
	 * @param column カラム名
	 * @return 自身
	 */
	public default Where endsWith(WhereDelimiter delimiter, String alias, String column, String param) {
		getHolderList()
			.add(new WhereHolder(delimiter, 
					alias,
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
		return contains(null, column, param);
	}
	
	/**
	 * column LIKE '%str%' をANDで追加
	 * @param alias エイリアス 
	 * @param column カラム名
	 * @return 自身
	 */
	public default Where contains(String alias, String column, String param) {
		return contains(WhereDelimiter.AND, alias, column, param);
	}
	
	/**
	 * column LIKE '%str%' をORで追加
	 * @param column カラム名
	 * @return 自身
	 */
	public default Where orContains(String column, String param) {
		return orContains(null, column, param);
	}
	
	/**
	 * column LIKE '%str%' をORで追加
	 * @param alias エイリアス
	 * @param column カラム名
	 * @return 自身
	 */
	public default Where orContains(String alias, String column, String param) {
		return contains(WhereDelimiter.OR, alias, column, param);
	}
	
	/**
	 * column LIKE '%str%' を追加
	 * @param delimiter デリミタ
	 * @param alias エイリアス
	 * @param column カラム名
	 * @return 自身
	 */
	public default Where contains(WhereDelimiter delimiter, String alias, String column, String param) {
		getHolderList()
			.add(new WhereHolder(delimiter, 
					alias,
					String.format("%s LIKE ?", column),
					Optional.ofNullable(param)
								.map(s -> String.format("%%%s%%", s))));
		
		return this;
	}
	
	
	/**
	 * WHERE条件を保持するクラス
	 * @author hilo
	 *
	 */
	public static class WhereHolder {
		/** デリミタ */
		private final WhereDelimiter delimiter;
		
		/** エイリアス */
		private final Optional<String> alias;
		
		/** クエリ */
		private final String query;
		
		/** パラメータ */
		private final Optional<?> param;
		
		/**
		 * 空フラグ
		 * IS NULL など値を指定しない時にtrue
		 */
		private final boolean blankFlg;
		
		public WhereHolder(WhereDelimiter delimiter, String alias, String query, Optional<?> param) {
			this(delimiter, alias, query, param, false);
			
		}
		public WhereHolder(WhereDelimiter delimiter, String alias, String query, Optional<?> param, boolean blankFlg) {
			this.delimiter = delimiter;
			this.alias = Optional.ofNullable(alias);
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
		
		public Optional<String> getAlias() {
			return alias;
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
