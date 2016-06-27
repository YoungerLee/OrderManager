package com.order.domain;

import java.io.Serializable;

public class Privilege implements Serializable {
	private Integer role_id;
	private Integer func_id;

	public Integer getRole_id() {
		return role_id;
	}

	public void setRole_id(Integer role_id) {
		this.role_id = role_id;
	}

	public Integer getFunc_id() {
		return func_id;
	}

	public void setFunc_id(Integer func_id) {
		this.func_id = func_id;
	}
}
