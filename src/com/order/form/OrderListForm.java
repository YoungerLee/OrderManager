package com.order.form;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Map;

import com.order.domain.Product;

public class OrderListForm implements Serializable {
	private String id;
	private Double money;
	private String address;
	private Integer payState;
	private String admin;
	private String courier;
	private Timestamp orderTime;
	private Timestamp sendTime;
	private Timestamp signTime;
	private Integer sendFlag; // 1---已发货，0---未发货
	private Integer signFlag; // 1---已签收，0---未签收
	private String username;
	private Map<Product, Integer> prodMap;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getPayState() {
		return payState;
	}

	public void setPayState(Integer payState) {
		this.payState = payState;
	}

	public String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}

	public String getCourier() {
		return courier;
	}

	public void setCourier(String courier) {
		this.courier = courier;
	}

	public Timestamp getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Timestamp orderTime) {
		this.orderTime = orderTime;
	}

	public Timestamp getSendTime() {
		return sendTime;
	}

	public void setSendTime(Timestamp sendTime) {
		this.sendTime = sendTime;
	}

	public Timestamp getSignTime() {
		return signTime;
	}

	public void setSignTime(Timestamp signTime) {
		this.signTime = signTime;
	}

	public Integer getSendFlag() {
		return sendFlag;
	}

	public void setSendFlag(Integer sendFlag) {
		this.sendFlag = sendFlag;
	}

	public Integer getSignFlag() {
		return signFlag;
	}

	public void setSignFlag(Integer signFlag) {
		this.signFlag = signFlag;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Map<Product, Integer> getProdMap() {
		return prodMap;
	}

	public void setProdMap(Map<Product, Integer> prodMap) {
		this.prodMap = prodMap;
	}
}
