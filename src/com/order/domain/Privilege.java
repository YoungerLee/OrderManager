package com.order.domain;

import java.io.Serializable;

public class Privilege implements Serializable {
	private Integer role_id;
	private String path;

	public Integer getRole_id() {
		return role_id;
	}

	public void setRole_id(Integer role_id) {
		this.role_id = role_id;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}
