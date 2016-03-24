package com.yoidukigembu.sql.where;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class WhereBuilder implements Where {
	
	private final List<Where.WhereHolder> holderList;
	
	private List<Object> params;
	
	private String query;
	
	private boolean builtFlg;
	
	private WhereBuilder() {
		this.holderList = new ArrayList<>();
		this.builtFlg = false;
	}
	
	public static WhereBuilder newInstance() {
		WhereBuilder builder = new WhereBuilder();
		return builder;
	}

	@Override
	public List<Where.WhereHolder> getHolderList() {
		return holderList;
	}

	@Override
	public void build(Consumer consumer) {
		if (builtFlg) {
			consumer.consume(query, params);
			return;
		}
		StringBuilder where = new StringBuilder();
		params = new ArrayList<>();
		
		getHolderList().forEach(holder -> {
			if (builtFlg) {
				where.append(holder.getDelimiter().getValue())
						.append(" ");
			}
			where.append(holder.getQuery())
				.append(" ");
			
			if (!holder.isBlankFlg()) {
				Object param = holder.getParam()
						.orElseThrow(() -> new NullPointerException(
								String.format("param is NULL. query：[%s]", holder.getQuery())));
				
				if (param instanceof Collection) {
					params.addAll((Collection<?>) param);
				} else {
					params.add(param);
				}
			}
		});
		
		query = where.toString();
		consumer.consume(getWhereQuery(), getParams());
	}
	
	
	private List<Object> getParams() {
		return Optional.ofNullable(params)
					.orElse(new ArrayList<>(0));
	}
	
	private String getWhereQuery() {
		return Optional.ofNullable(query).orElse("");
	}
	

}
