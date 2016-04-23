package com.yoidukigembu.sql.where;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.yoidukigembu.sql.exception.WhereException;
import com.yoidukigembu.sql.util.SqlUtil;

/**
 * Whereの実装クラス
 * 
 * @author hilo
 *
 */
public class BaseWhere extends AbstractWhere implements Where {

	/** 条件ホルダリスト */
	private final List<Where.WhereHolder> holderList;

	public BaseWhere() {
		this.holderList = new ArrayList<>();
	}

	/*
	 * (non-Javadoc)
	 * @see com.yoidukigembu.sql.where.Where#getHolderList()
	 */
	@Override
	public List<Where.WhereHolder> getHolderList() {
		return holderList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.yoidukigembu.sql.where.Where#build(com.yoidukigembu.sql.where.Where.Consumer)
	 */
	@Override
	public void build(Consumer consumer) {
		
		boolean addFlg = false;
		
		StringBuilder where = new StringBuilder("");
		List<Object> params = new ArrayList<>();
		for (WhereHolder holder : holderList) {
			if (addFlg) {
				where.append(holder.getDelimiter().getValue())
					.append(" ");
			}
			
			addQuery(holder, where, params);
			where.append(" ");
			addFlg = true;
		}
		
		consumer.consume(where.toString(), params);
	}
	
	
	/**
	 * クエリの追加
	 * @param holder WHERE条件ホルダ
	 * @param where WHERE文
	 * @param params 値リスト
	 */
	private void addQuery(WhereHolder holder, StringBuilder where, List<Object> params) {
		switch (holder.getType()) {
		case IS_NOT_NULL:
		case IS_NULL:
			addNoValueQuery(holder, where);
			return;
		
		case IN:
		case NOT_IN:
			addMultiValueQuery(holder, where, params);
			return;

		default:
			addSingleValueQuery(holder, where, params);
			return;
		}
	}
	
	/**
	 * IS NULL など値を指定しないクエリを追加
	 * @param holder WHERE条件ホルダ
	 * @param where WHERE文
	 */
	private void addNoValueQuery(WhereHolder holder, StringBuilder where) {
		String query = String.format(holder.getType().getQueryFormat(), holder.getAliasColumn());
		where.append(query);
	}
	
	/**
	 * IN / NOT IN など、値を複数指定するクエリを追加
	 * @param holder WHERE条件ホルダ
	 * @param where WHERE文
	 * @param params 値リスト
	 */
	private void addMultiValueQuery(WhereHolder holder, StringBuilder where, List<Object> params) {
		Collection<?> values =  holder.getParam()
								.map(p -> (Collection<?>) p)
								.filter(p -> !p.isEmpty())
								.orElseThrow(() -> 
								new WhereException(String.format("value must not be EMPTY. query:[%s]", String.format(holder.getType().getQueryFormat(), holder.getAliasColumn(), ""))));
		
		
		String questions = SqlUtil.createQuestions(values);
		where.append(String.format(holder.getType().getQueryFormat(), holder.getAliasColumn(), questions));
		params.addAll(values);
	}
	
	
	/**
	 * 単体の検索条件を追加 (=, != など)
	 * @param holder WHERE条件ホルダ
	 * @param where WHERE文
	 * @param params 値リスト
	 */
	private void addSingleValueQuery(WhereHolder holder, StringBuilder where, List<Object> params) {
		String query = String.format(holder.getType().getQueryFormat(), holder.getAliasColumn());
		Object value = holder.getParam()
						.orElseThrow(() -> new WhereException(String.format("value must not be NULL. query:[%s]", query)));
		
		where.append(query);
		params.add(convertParam(holder.getType(), value));
	}


}
