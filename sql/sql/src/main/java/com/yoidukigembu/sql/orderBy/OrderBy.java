package com.yoidukigembu.sql.orderBy;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.yoidukigembu.sql.enums.OrderType;

/**
 * ORDER BY
 * @author hilo
 *
 */
public class OrderBy {

	/** ORDER BY のリスト */
	private List<OrderHolder> orderList;
	
	public OrderBy() {
		this.orderList = new ArrayList<>();
	}
	
	/**
	 * ASC
	 */
	public OrderBy asc(String column) {
		add(column, OrderType.ASC);
		return this;
	}
	

	/**
	 * DESC
	 */
	public OrderBy desc(String column) {
		add(column, OrderType.DESC);
		return this;
	}
	
	/**
	 * ORDER BY を追加
	 */
	private void add(String column, OrderType type) {
		orderList.add(new OrderHolder(column, type));
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
		
		private OrderHolder(String column, OrderType type) {
			this.column = column;
			this.type = type;
		}
		
		@Override
		public String toString() {
			return String.format("%s %s", column, type.getValue());
		}
		
	}
	
	/**
	 * ORDER BY の文字列を取得
	 */
	public CharSequence getOrder(Optional<String> alias) {
		StringBuilder sb = new StringBuilder();
		final String prefix = alias.map(a -> a.concat(".")).orElse("");
		int index = 0;
		for (OrderHolder order : this.orderList) {
			if (index++ > 0) {
				sb.append(", ");
			}
			sb.append(prefix.concat(order.toString()));
		}
		
		return sb;
	}

}
