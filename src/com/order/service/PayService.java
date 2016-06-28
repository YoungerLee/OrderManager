package com.order.service;

import com.order.annotation.Tran;

public interface PayService extends Service {
	/**
	 * 支付订单的方法
	 * 
	 * @param order_id
	 * @param money
	 */
	@Tran
	void pay(String order_id, Double money);
}
