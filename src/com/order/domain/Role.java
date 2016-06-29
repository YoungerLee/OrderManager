package com.order.domain;

import java.io.Serializable;

public class Role implements Serializable {
	private Integer id; // 0---管理员，1---快递员，2---普通用户
	private String roleName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}
