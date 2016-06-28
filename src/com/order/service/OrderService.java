package com.order.service;

import java.util.List;

import com.order.annotation.Tran;
import com.order.domain.Order;
import com.order.form.OrderListForm;

public interface OrderService extends Service {

	/**
	 * 增加订单
	 * 
	 * @param order
	 *            订单bean
	 */
	@Tran
	void addOrder(Order order);

	/**
	 * 查询指定用户所有订单的方法
	 * 
	 * @param user_id
	 * @return 查找到的数据
	 */
	List<OrderListForm> findOrdersByCust(Integer user_id);

	/**
	 * 查询管理员所有订单的方法
	 * 
	 * @param user_id
	 * @return 查找到的数据
	 */
	List<OrderListForm> findOrdersByAdmin(Integer id);

	/**
	 * 根据订单编号删除订单
	 * 
	 * @param id
	 */
	@Tran
	void delOrderByID(String id);

	/**
	 * 根据id查询订单
	 * 
	 * @param order
	 * @return
	 */
	Order findOrderById(String order);

	/**
	 * 修改指定id订单的支付状态
	 * 
	 * @param order
	 * @param i
	 */
	void changePayState(String order, int i);

	/**
	 * 发货信息
	 * 
	 * @param order_id
	 * @param courier
	 */
	@Tran
	void sendOrder(String order_id, String courier);

	/**
	 * 查找快递员需要派送的订单
	 * 
	 * @param id
	 * @return 查找到的数据
	 */
	List<OrderListForm> findOrdersSending(Integer id);

	/**
	 * 签收订单
	 * 
	 * @param order_id
	 */
	void signOrder(String order_id);

	/**
	 * 查询已签收订单
	 * 
	 * @param id
	 * @return
	 */
	List<OrderListForm> findOrdersSent(Integer id);
}
