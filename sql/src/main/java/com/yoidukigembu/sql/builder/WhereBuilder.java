package com.yoidukigembu.sql.builder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * WHEREのクエリビルダークラス
 * WHERE 以降のクエリを作成します。(WHERE を含む)
 * @author hiro
 *
 */
public class WhereBuilder {

	/** 値に変換される文字 */
	private static final String REPLACE_VALUE = "?";

	/** クエリ */
	private StringBuffer query;

	/** パラメータ */
	private List<Object> params;

	/** 変換値(デフォルトは「?」 */
	private String replaceValue;

	/** 条件が追加されたフラグ */
	private boolean addFlg;

	/**
	 * プレフィックス <br />
	 * AND とか OR とか
	 * @author hiro
	 *
	 */
	public enum Prefix {
		AND("AND"),
		OR("OR");

		String prefix;

		Prefix(String prefix) {
			this.prefix = prefix;
		}

		public String value() {
			return prefix;
		}

	}

	/**
	 * {@link WhereBuilder}のインスタンスを作成します。
	 */
	public WhereBuilder() {
		this(REPLACE_VALUE);
	}

	/**
	 * whereFlg、replaceValueからインスタンスを作成します。
	 * @param whereFlg 頭にWHEREをつけるフラグ
	 * @param replaceValue 値の変わりに代入する文字(普通は「?」)
	 */
	public WhereBuilder(String replaceValue) {
		this.query = new StringBuffer(0);
		this.params = new ArrayList<Object>(0);
		this.replaceValue = replaceValue;
		this.addFlg = false;
	}

	/**
	 * クエリを取得します。
	 * @return クエリ
	 */
	public String getQuery() {
		return query.toString();
	}

	/**
	 * パラメータを取得します。
	 * @return パラメータ
	 */
	public Object[] getParams() {
		return params.toArray();
	}

	/**
	 * = ? を追加します。
	 * @param columnName カラム名
	 * @param value 値
	 * @param prefix プレフィックス
	 * @return QueryBuilder
	 */
	public WhereBuilder eq(String columnName, Object value, Prefix prefix) {
		if (canNotAddQuery(columnName, value)) {
			return this;
		}

		addPrefix(prefix);
		appendColumnName(columnName);
		query.append(" = ")
		.append(replaceValue);
		params.add(value);

		setAddFlg();
		return this;
	}

	/**
	 * = を追加します。
	 * @param columnName カラム名
	 * @param value 値
	 * @return QueryBuilder
	 */
	public WhereBuilder eq(String columnName, Object value) {
		return eq(columnName, value, Prefix.AND);
	}

	/**
	 * != を追加します
	 * @param columnName カラム名
	 * @param value 値
	 * @param prefix プレフィックス
	 * @return {@link WhereBuilder}
	 */
	public WhereBuilder ne(String columnName, Object value, Prefix prefix) {
		if (canNotAddQuery(columnName, value)) {
			return this;
		}

		addPrefix(prefix);
		appendColumnName(columnName);
		query.append(" != ")
			.append(replaceValue);
		params.add(value);
		setAddFlg();
		return this;
	}

	/**
	 * != を追加します
	 * @param columnName カラム名
	 * @param value 値
	 * @return {@link WhereBuilder}
	 */
	public WhereBuilder ne(String columnName, Object value) {
		return ne(columnName, value, Prefix.AND);
	}

	/**
	 * columnName > value を追加します。
	 * @param columnName カラム名
	 * @param value 値
	 * @param prefix プレフィックス
	 * @return {@link WhereBuilder}
	 */
	public WhereBuilder gt(String columnName, Object value, Prefix prefix) {
		if (canNotAddQuery(columnName, value)) {
			return this;
		}

		addPrefix(prefix);
		appendColumnName(columnName);
		query.append(" > ")
			.append(replaceValue);
		params.add(value);
		setAddFlg();
		return this;
	}

	/**
	 * columnName > value を追加します。
	 * @param columnName カラム名
	 * @param value 値
	 * @return {@link WhereBuilder}
	 */
	public WhereBuilder gt(String columnName, Object value) {
		return gt(columnName, value, Prefix.AND);
	}


	/**
	 * columnName >= value を追加します。
	 * @param columnName カラム名
	 * @param value 値
	 * @param prefix プレフィックス
	 * @return {@link WhereBuilder}
	 */
	public WhereBuilder ge(String columnName, Object value, Prefix prefix) {
		if (canNotAddQuery(columnName, value)) {
			return this;
		}
		addPrefix(prefix);
		appendColumnName(columnName);
		query.append(" >= ")
			.append(replaceValue);
		params.add(value);
		setAddFlg();
		return this;
	}


	/**
	 * columnName >= value を追加します。
	 * @param columnName カラム名
	 * @param value 値
	 * @return {@link WhereBuilder}
	 */
	public WhereBuilder ge(String columnName, Object value) {
		return ge(columnName, value, Prefix.AND);
	}

	/**
	 * columnName < value を追加します。
	 * @param columnName カラム名
	 * @param value 値
	 * @param prefix プレフィックス
	 * @return {@link WhereBuilder}
	 */
	public WhereBuilder lt(String columnName, Object value, Prefix prefix) {
		if (canNotAddQuery(columnName, value)) {
			return this;
		}
		addPrefix(prefix);
		appendColumnName(columnName);
		query.append(" < ")
			.append(replaceValue);
		params.add(value);
		setAddFlg();
		return this;
	}


	/**
	 * columnName < value を追加します。
	 * @param columnName カラム名
	 * @param value 値
	 * @param prefix プレフィックス
	 * @return {@link WhereBuilder}
	 */
	public WhereBuilder lt(String columnName, Object value) {
		return lt(columnName, value, Prefix.AND);
	}


	/**
	 * columnName <= value を追加します。
	 * @param columnName カラム名
	 * @param value 値
	 * @param prefix プレフィックス
	 * @return {@link WhereBuilder}
	 */
	public WhereBuilder le(String columnName, Object value, Prefix prefix) {
		if (canNotAddQuery(columnName, value)) {
			return this;
		}
		addPrefix(prefix);
		appendColumnName(columnName);
		query.append(" <= ")
			.append(replaceValue);
		params.add(value);
		setAddFlg();
		return this;
	}


	/**
	 * columnName <= value を追加します。
	 * @param columnName カラム名
	 * @param value 値
	 * @param prefix プレフィックス
	 * @return {@link WhereBuilder}
	 */
	public WhereBuilder le(String columnName, Object value) {
		return le(columnName, value, Prefix.AND);
	}

	/**
	 * IN を追加します。
	 * @param columnName カラム名
	 * @param values 値
	 * @param prefix プレフィックス
	 * @return {@link WhereBuilder}
	 */
	public WhereBuilder in(String columnName, List<?> values, Prefix prefix) {
		if (canNotAddQuery(columnName, values)) {
			return this;
		}
		addPrefix(prefix);
		appendColumnName(columnName);
		query.append(" IN ( ");
		createQuestionMarks(values.size());
		query.append(" ) ");
		params.addAll(values);
		setAddFlg();
		return this;
	}

	/**
	 * IN を追加します。
	 * @param columnName カラム名
	 * @param values 値
	 * @param prefix プレフィックス
	 * @return {@link WhereBuilder}
	 */
	public WhereBuilder in(String columnName, Object[] values, Prefix prefix) {
		if (values == null || values.length == 0) {
			return this;
		}

		return in(columnName, Arrays.asList(values), prefix);
	}

	/**
	 * IN を追加します。
	 * @param columnName カラム名
	 * @param values 値
	 * @return {@link WhereBuilder}
	 */
	public WhereBuilder in(String columnName, List<?> values) {
		return in(columnName, values, Prefix.AND);
	}

	/**
	 * IN を追加します。
	 * @param columnName カラム名
	 * @param values 値
	 * @return {@link WhereBuilder}
	 */
	public WhereBuilder in(String columnName, Object[] values) {
		return in(columnName, values, Prefix.AND);
	}







	/**
	 * IN を追加します。
	 * @param columnName カラム名
	 * @param values 値
	 * @param prefix プレフィックス
	 * @return {@link WhereBuilder}
	 */
	public WhereBuilder notIn(String columnName, List<?> values, Prefix prefix) {
		if (canNotAddQuery(columnName, values)) {
			return this;
		}
		addPrefix(prefix);
		appendColumnName(columnName);
		query.append(" NOT IN ( ");
		createQuestionMarks(values.size());
		query.append(" ) ");
		params.addAll(values);
		setAddFlg();
		return this;
	}

	/**
	 * IN を追加します。
	 * @param columnName カラム名
	 * @param values 値
	 * @param prefix プレフィックス
	 * @return {@link WhereBuilder}
	 */
	public WhereBuilder notIn(String columnName, Object[] values, Prefix prefix) {
		if (values == null || values.length == 0) {
			return this;
		}

		return notIn(columnName, Arrays.asList(values), prefix);
	}

	/**
	 * IN を追加します。
	 * @param columnName カラム名
	 * @param values 値
	 * @return {@link WhereBuilder}
	 */
	public WhereBuilder notIn(String columnName, List<?> values) {
		return notIn(columnName, values, Prefix.AND);
	}

	/**
	 * IN を追加します。
	 * @param columnName カラム名
	 * @param values 値
	 * @return {@link WhereBuilder}
	 */
	public WhereBuilder notIn(String columnName, Object[] values) {
		return in(columnName, values, Prefix.AND);
	}




	/**
	 * IS NULL を追加します。
	 * @param columnName カラム名
	 * @param prefix プレフィックス
	 * @return {@link WhereBuilder}
	 */
	public WhereBuilder isNull(String columnName, Prefix prefix) {
		if (columnName == null) {
			return this;
		}

		addPrefix(prefix);

		appendColumnName(columnName);
		query.append(" IS NULL ");
		return this;
	}


	/**
	 * IS NULL を追加します。
	 * @param columnName カラム名
	 * @return {@link WhereBuilder}
	 */
	public WhereBuilder isNull(String columnName) {
		return isNull(columnName, Prefix.AND);
	}


	/**
	 * IS NOT NULL を追加します。
	 * @param columnName カラム名
	 * @param prefix プレフィックス
	 * @return {@link WhereBuilder}
	 */
	public WhereBuilder isNotNull(String columnName, Prefix prefix) {
		if (columnName == null) {
			return this;
		}

		addPrefix(prefix);
		appendColumnName(columnName);
		query.append(" IS NOT NULL ");

		return this;
	}



	/**
	 * IS NOT NULL を追加します。
	 * @param columnName カラム名
	 * @return {@link WhereBuilder}
	 */
	public WhereBuilder isNotNull(String columnName) {
		return isNotNull(columnName);
	}







	private void createQuestionMarks(int valueLength) {
		query.append(" ")
			.append(replaceValue)
			.append(" ");

		for (int i = 1; i < valueLength; i++) {
			query.append(", ")
				.append(replaceValue)
				.append(" ");
		}
	}




	/**
	 * プレフィックスを追加
	 * @param prefix プレフィックス
	 */
	private void addPrefix(Prefix prefix) {
		if (existQuery()) {
			return;
		}

		query.append(" ")
			.append(prefix.value())
			.append(" ");
	}

	/**
	 * クエリを追加できないかどうか
	 * @param columnName カラム名
	 * @param value 値
	 * @return 追加できない場合にtrue
	 */
	private boolean canNotAddQuery(String columnName, Object value) {
		// カラム名・値どちらかがnullの場合は追加しない。
		if (columnName == null || value == null) {
			return true;
		}

		if (value instanceof List<?>
				&& ((List<?>) value).isEmpty()) {
			return true;
		}

		return false;
	}



	/**
	 * クエリが存在するかどうか
	 * @return クエリがある場合にtrue
	 */
	public boolean existQuery() {
		return addFlg;
	}


	/**
	 * カラム名をアペンドします。
	 * @param columnName カラム名
	 */
	private void appendColumnName(String columnName) {
		query.append(" ")
			.append(columnName)
			.append(" ");
	}

	/**
	 * addFlgをセット
	 */
	private void setAddFlg() {
		addFlg = true;
	}

}
