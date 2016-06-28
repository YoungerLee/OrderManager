package com.order.service.impl;

import com.order.dao.OrderDao;
import com.order.dao.ProdDao;
import com.order.dao.UserDao;
import com.order.domain.Order;
import com.order.domain.User;
import com.order.factory.BasicFactory;
import com.order.service.PayService;

public class PayServiceImpl implements PayService {
	OrderDao orderDao = BasicFactory.getFactory().getDao(OrderDao.class);
	ProdDao prodDao = BasicFactory.getFactory().getDao(ProdDao.class);
	UserDao userDao = BasicFactory.getFactory().getDao(UserDao.class);

	@Override
	public void pay(String order_id, Double money) {
		try {
			// 1.获取订单信息
			Order order = orderDao.findOrderById(order_id);
			// 2.扣取客户金额，同时增加卖家相等金额
			User customer = userDao.findUserById(order.getUser_id());
			User admin = userDao.findUserById(order.getAdmin_id());
			userDao.decCash(customer.getId(), money);
			userDao.incCash(admin.getId(), money);
			// 3.更改支付状态为1
			orderDao.changePayState(order_id, 1);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
