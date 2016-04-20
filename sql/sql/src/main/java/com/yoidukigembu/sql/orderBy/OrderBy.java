package com.yoidukigembu.sql.orderBy;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.yoidukigembu.sql.enums.OrderType;
import com.yoidukigembu.sql.util.SqlUtil;

/**
 * ORDER BY
 * @author hilo
 *
 */
public class OrderBy {

	/** ORDER BY のリスト */
	private List<OrderHolder> orderList = new ArrayList<>();
	
	public OrderBy() {
	}
	
	public OrderBy(String column) {
		this(column, OrderType.ASC);
	}
	
	public OrderBy(String column, OrderType orderType) {
		add(column, orderType);
	}
	
	public OrderBy(String alias, String column, OrderType orderType) {
	}
	
	/**
	 * ASC
	 */
	public OrderBy asc(String column) {
		return asc(null, column);
	}
	
	/**
	 * ASC
	 */
	public OrderBy asc(String alias, String column) {
		return add(alias, column, OrderType.ASC);
	}
	
	/**
	 * DESC
	 */
	public OrderBy desc(String column) {
		return desc(null, column);
	}

	/**
	 * DESC
	 */
	public OrderBy desc(String alias, String column) {
		return add(alias, column, OrderType.DESC);
	}
	
	
	/**
	 * ORDER BY を追加
	 */
	private OrderBy add(String column, OrderType type) {
		return add(null, column, type);
	}
	
	/**
	 * ORDER BY を追加
	 */
	private OrderBy add(String alias, String column, OrderType type) {
		orderList.add(new OrderHolder(alias, column, type));
		return this;
	}
	
	/**
	 * ORDER BY の条件を保持するクラス
	 * @author hilo
	 *
	 */
	private class OrderHolder {
		/** カラム名 */
		private final String column;
		
		/** ORDER BY のタイプ */
		private final OrderType type;
		
		private final Optional<String> alias;
		
		private OrderHolder(String column, OrderType type) {
			this(null, column, type);
		}
		private OrderHolder(String alias, String column, OrderType type) {
			this.column = column;
			this.type = type;
			this.alias = Optional.ofNullable(alias);
		}
		
		@Override
		public String toString() {
			return String.format("%s %s",
					SqlUtil.alias(alias, column), type.getValue());
		}
		
	}
	
	/**
	 * ORDER BY の文字列を取得
	 */
	public CharSequence getOrder() {
		StringBuilder sb = new StringBuilder();
		int index = 0;
		for (OrderHolder order : this.orderList) {
			if (index++ > 0) {
				sb.append(", ");
			}
			sb.append(order.toString());
		}
		
		return sb;
	}

}
