package com.order.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.order.dao.OrderDao;
import com.order.dao.ProdDao;
import com.order.dao.UserDao;
import com.order.domain.Order;
import com.order.domain.OrderItem;
import com.order.domain.Product;
import com.order.domain.User;
import com.order.factory.BasicFactory;
import com.order.form.OrderListForm;
import com.order.service.OrderService;

public class OrderServiceImpl implements OrderService {
	OrderDao orderDao = BasicFactory.getFactory().getDao(OrderDao.class);
	ProdDao prodDao = BasicFactory.getFactory().getDao(ProdDao.class);
	UserDao userDao = BasicFactory.getFactory().getDao(UserDao.class);

	public void addOrder(Order order) {
		try {
			// 1.生成订单
			orderDao.addOrder(order);
			// 2.生成订单项/扣除商品数量
			for (OrderItem item : order.getList()) {
				orderDao.addOrderItem(item);
				prodDao.delPnum(item.getProduct_id(), item.getBuynum());
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public List<OrderListForm> findOrdersByCust(Integer user_id) {
		try {
			List<OrderListForm> olfList = new ArrayList<OrderListForm>();
			// 1.根据用户id查询这个id用户的所有订单
			List<Order> list = orderDao.findOrderByUserId(user_id);
			// 2.遍历订单 生成orderListForm对象,存入List中
			for (Order order : list) {
				// --设置订单信息
				OrderListForm olf = new OrderListForm();
				olf.setId(order.getId());
				olf.setMoney(order.getMoney());
				olf.setAddress(order.getAddress());
				olf.setPayState(order.getPayState());
				olf.setOrderTime(order.getOrderTime());
				olf.setSendTime(order.getSendTime());
				olf.setSignTime(order.getSignTime());
				olf.setSendFlag(order.getSendFlag());
				olf.setSignFlag(order.getSignFlag());
				// --设置结束
				// --设置用户名
				olf.setUsername(userDao.findUsernameById(order.getUser_id())
						.getUsername());
				// --设置用户管理员姓名
				olf.setAdmin(userDao.findUsernameById(order.getAdmin_id())
						.getUsername());
				// --设置快递员姓名
				if (order.getCourier_id() != null) {
					olf.setCourier(userDao.findUsernameById(
							order.getCourier_id()).getUsername());
				} else {
					olf.setCourier(null);
				}
				// --设置商品信息
				// ----查询当前订单所有订单项
				List<OrderItem> itemList = orderDao.findOrderItems(order
						.getId());
				// ----遍历所有订单项,获取商品id,查找对应的商品,存入list
				Map<Product, Integer> prodMap = new HashMap<Product, Integer>();
				for (OrderItem item : itemList) {
					String prod_id = item.getProduct_id();
					Product prod = prodDao.findProdById(prod_id);
					prodMap.put(prod, item.getBuynum());
				}
				olf.setProdMap(prodMap);
				olfList.add(olf);
			}
			return olfList;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<OrderListForm> findOrdersByAdmin(Integer id) {
		try {
			List<OrderListForm> olfList = new ArrayList<OrderListForm>();
			// 1.根据用户id查询这个id用户的所有订单
			List<Order> list = orderDao.findOrderByAdminId(id);
			// 2.遍历订单 生成orderListForm对象,存入List中
			for (Order order : list) {
				// --设置订单信息
				OrderListForm olf = new OrderListForm();
				olf.setId(order.getId());
				olf.setMoney(order.getMoney());
				olf.setAddress(order.getAddress());
				olf.setPayState(order.getPayState());
				olf.setOrderTime(order.getOrderTime());
				olf.setSendTime(order.getSendTime());
				olf.setSignTime(order.getSignTime());
				olf.setSendFlag(order.getSendFlag());
				olf.setSignFlag(order.getSignFlag());
				// --设置结束
				// --设置用户名
				olf.setUsername(userDao.findUsernameById(order.getUser_id())
						.getUsername());
				// --设置用户管理员姓名
				olf.setAdmin(userDao.findUsernameById(order.getAdmin_id())
						.getUsername());
				// --设置快递员姓名
				if (order.getCourier_id() != null) {
					olf.setCourier(userDao.findUsernameById(
							order.getCourier_id()).getUsername());
				} else {
					olf.setCourier(null);
				}
				// --设置商品信息
				// ----查询当前订单所有订单项
				List<OrderItem> itemList = orderDao.findOrderItems(order
						.getId());
				// ----遍历所有订单项,获取商品id,查找对应的商品,存入list
				Map<Product, Integer> prodMap = new HashMap<Product, Integer>();
				for (OrderItem item : itemList) {
					String prod_id = item.getProduct_id();
					Product prod = prodDao.findProdById(prod_id);
					prodMap.put(prod, item.getBuynum());
				}
				olf.setProdMap(prodMap);
				olfList.add(olf);
			}
			return olfList;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public void delOrderByID(String id) {
		// 1.根据id查询出所有订单项
		List<OrderItem> list = orderDao.findOrderItems(id);
		// 2.遍历订单项,将对应prod_id的商品的库存加回去
		for (OrderItem item : list) {
			prodDao.addPnum(item.getProduct_id(), item.getBuynum());
		}
		// 3.删除订单项
		orderDao.delOrderItem(id);
		// 4.删除订单
		orderDao.delOrder(id);
	}

	public void changePayState(String order, int i) {
		orderDao.changePayState(order, i);
	}

	public Order findOrderById(String order_id) {
		return orderDao.findOrderById(order_id);
	}

	@Override
	public void sendOrder(String order_id, String courier) {
		// 1.获取订单和快递员信息
		Order order = orderDao.findOrderById(order_id);
		User courierUser = userDao.findUserByName(courier);
		// 2.更新发货状态和发货时间
		order.setSendFlag(1);
		order.setSendTime(new java.sql.Timestamp(System.currentTimeMillis()));
		order.setCourier_id(courierUser.getId());
		orderDao.updateSend(order);
	}

	@Override
	public List<OrderListForm> findOrdersSending(Integer id) {
		try {
			List<OrderListForm> olfList = new ArrayList<OrderListForm>();
			// 1.根据用户id查询这个id用户的所有订单
			List<Order> list = orderDao.findOrderBySending(id);
			// 2.遍历订单 生成orderListForm对象,存入List中
			for (Order order : list) {
				// --设置订单信息
				OrderListForm olf = new OrderListForm();
				olf.setId(order.getId());
				olf.setMoney(order.getMoney());
				olf.setAddress(order.getAddress());
				olf.setPayState(order.getPayState());
				olf.setOrderTime(order.getOrderTime());
				olf.setSendTime(order.getSendTime());
				olf.setSignTime(order.getSignTime());
				olf.setSendFlag(order.getSendFlag());
				olf.setSignFlag(order.getSignFlag());
				// --设置结束
				// --设置用户名
				olf.setUsername(userDao.findUsernameById(order.getUser_id())
						.getUsername());
				// --设置用户管理员姓名
				olf.setAdmin(userDao.findUsernameById(order.getAdmin_id())
						.getUsername());
				// --设置快递员姓名
				if (order.getCourier_id() != null) {
					olf.setCourier(userDao.findUsernameById(
							order.getCourier_id()).getUsername());
				} else {
					olf.setCourier(null);
				}
				// --设置商品信息
				// ----查询当前订单所有订单项
				List<OrderItem> itemList = orderDao.findOrderItems(order
						.getId());
				// ----遍历所有订单项,获取商品id,查找对应的商品,存入list
				Map<Product, Integer> prodMap = new HashMap<Product, Integer>();
				for (OrderItem item : itemList) {
					String prod_id = item.getProduct_id();
					Product prod = prodDao.findProdById(prod_id);
					prodMap.put(prod, item.getBuynum());
				}
				olf.setProdMap(prodMap);
				olfList.add(olf);
			}
			return olfList;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public void signOrder(String order_id) {
		// 1.获取订单信息
		Order order = orderDao.findOrderById(order_id);
		// 2.更新发货状态和发货时间
		order.setSignFlag(1);
		order.setSignTime(new java.sql.Timestamp(System.currentTimeMillis()));
		orderDao.updateSign(order);
	}

	@Override
	public List<OrderListForm> findOrdersSent(Integer id) {
		try {
			List<OrderListForm> olfList = new ArrayList<OrderListForm>();
			// 1.根据用户id查询这个id用户的所有订单
			List<Order> list = orderDao.findOrderBySent(id);
			// 2.遍历订单 生成orderListForm对象,存入List中
			for (Order order : list) {
				// --设置订单信息
				OrderListForm olf = new OrderListForm();
				olf.setId(order.getId());
				olf.setMoney(order.getMoney());
				olf.setAddress(order.getAddress());
				olf.setPayState(order.getPayState());
				olf.setOrderTime(order.getOrderTime());
				olf.setSendTime(order.getSendTime());
				olf.setSignTime(order.getSignTime());
				olf.setSendFlag(order.getSendFlag());
				olf.setSignFlag(order.getSignFlag());
				// --设置结束
				// --设置用户名
				olf.setUsername(userDao.findUsernameById(order.getUser_id())
						.getUsername());
				// --设置用户管理员姓名
				olf.setAdmin(userDao.findUsernameById(order.getAdmin_id())
						.getUsername());
				// --设置快递员姓名
				if (order.getCourier_id() != null) {
					olf.setCourier(userDao.findUsernameById(
							order.getCourier_id()).getUsername());
				} else {
					olf.setCourier(null);
				}
				// --设置商品信息
				// ----查询当前订单所有订单项
				List<OrderItem> itemList = orderDao.findOrderItems(order
						.getId());
				// ----遍历所有订单项,获取商品id,查找对应的商品,存入list
				Map<Product, Integer> prodMap = new HashMap<Product, Integer>();
				for (OrderItem item : itemList) {
					String prod_id = item.getProduct_id();
					Product prod = prodDao.findProdById(prod_id);
					prodMap.put(prod, item.getBuynum());
				}
				olf.setProdMap(prodMap);
				olfList.add(olf);
			}
			return olfList;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
