package com.order.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.order.dao.OrderDao;
import com.order.domain.Order;
import com.order.domain.OrderItem;
import com.order.util.TransactionManager;

public class OrderDaoImpl implements OrderDao {

	public void addOrder(Order order) throws SQLException {
		String sql = "insert into orders values (?,?,?,?,?,null,?,null,null,?,?,?)";
		QueryRunner runner = new QueryRunner(TransactionManager.getSource());
		runner.update(sql, order.getId(), order.getMoney(), order.getAddress(),
				order.getPayState(), order.getAdmin_id(), order.getOrderTime(),
				order.getSendFlag(), order.getSignFlag(), order.getUser_id());
	}

	public void addOrderItem(OrderItem item) throws SQLException {
		String sql = "insert into orderitem values (?,?,?)";
		QueryRunner runner = new QueryRunner(TransactionManager.getSource());
		runner.update(sql, item.getOrder_id(), item.getProduct_id(),
				item.getBuynum());
	}

	public List<Order> findOrderByUserId(int user_id) {
		String sql = "select * from orders where user_id = ?";
		try {
			QueryRunner runner = new QueryRunner(TransactionManager.getSource());
			return runner.query(sql, new BeanListHandler<Order>(Order.class),
					user_id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Order> findOrderByAdminId(Integer id) {
		String sql = "select * from orders where admin_id = ?";
		try {
			QueryRunner runner = new QueryRunner(TransactionManager.getSource());
			return runner.query(sql, new BeanListHandler<Order>(Order.class),
					id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Order> findOrderByCourier(Integer id) {
		String sql = "select * from orders where courier_id = ?";
		try {
			QueryRunner runner = new QueryRunner(TransactionManager.getSource());
			return runner.query(sql, new BeanListHandler<Order>(Order.class),
					id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public List<OrderItem> findOrderItems(String order_id) {
		String sql = "select * from orderitem where order_id = ?";
		try {
			QueryRunner runner = new QueryRunner(TransactionManager.getSource());
			return runner.query(sql, new BeanListHandler<OrderItem>(
					OrderItem.class), order_id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public void delOrder(String id) {
		String sql = "delete from orders where id = ?";
		try {
			QueryRunner runner = new QueryRunner(TransactionManager.getSource());
			runner.update(sql, id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public void delOrderItem(String order_id) {
		String sql = "delete from orderitem where order_id = ?";
		try {
			QueryRunner runner = new QueryRunner(TransactionManager.getSource());
			runner.update(sql, order_id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public void changePayState(String order_id, int i) {
		String sql = "update orders set paystate = ? where id = ?";
		try {
			QueryRunner runner = new QueryRunner(TransactionManager.getSource());
			runner.update(sql, i, order_id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public Order findOrderById(String order_id) {
		String sql = "select * from orders where id = ?";
		try {
			QueryRunner runner = new QueryRunner(TransactionManager.getSource());
			return runner.query(sql, new BeanHandler<Order>(Order.class),
					order_id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public void updateSend(Order order) {
		String sql = "update orders set sendFlag = ?, sendTime = ?, courier_id = ? where id = ?";
		try {
			QueryRunner runner = new QueryRunner(TransactionManager.getSource());
			runner.update(sql, order.getSendFlag(), order.getSendTime(),
					order.getCourier_id(), order.getId());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Order> findOrderBySending(Integer id) {
		String sql = "select * from orders where courier_id = ? and sendFlag = 1 and signFlag = 0";
		try {
			QueryRunner runner = new QueryRunner(TransactionManager.getSource());
			return runner.query(sql, new BeanListHandler<Order>(Order.class),
					id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public void updateSign(Order order) {
		String sql = "update orders set signFlag = ?, signTime = ? where id = ?";
		try {
			QueryRunner runner = new QueryRunner(TransactionManager.getSource());
			runner.update(sql, order.getSignFlag(), order.getSignTime(),
					order.getId());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Order> findOrderBySent(Integer id) {
		String sql = "select * from orders where courier_id = ? and sendFlag = 1 and signFlag = 1";
		try {
			QueryRunner runner = new QueryRunner(TransactionManager.getSource());
			return runner.query(sql, new BeanListHandler<Order>(Order.class),
					id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
