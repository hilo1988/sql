package com.yoidukigembu.sql.query.context;

import java.io.Serializable;

/**
 * 結果用コンテキスト
 * @author Takehiro Nakamori
 *
 */
public class SelectContext implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -5155437349237079202L;


	/** 終了フラグ */
	private boolean exitFlg;


	public boolean isExitFlg() {
		return exitFlg;
	}


	public void setExitFlg(boolean exitFlg) {
		this.exitFlg = exitFlg;
	}

}
