package com.order.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.order.domain.Order;
import com.order.domain.OrderItem;
import com.order.domain.Product;
import com.order.domain.User;
import com.order.factory.BasicFactory;
import com.order.service.OrderService;

public class AddOrderServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		OrderService service = BasicFactory.getFactory().getService(
				OrderService.class);
		PrintWriter out = response.getWriter();
		try {
			// 1.将订单信息存入Order bean中
			Order order = new Order();
			// --订单编号
			order.setId(UUID.randomUUID().toString());

			// --下单时间
			order.setOrderTime(new java.sql.Timestamp(System
					.currentTimeMillis()));
			// --支付状态
			order.setPayState(0);

			// --发货状态
			order.setSendFlag(0);

			// --签收状态
			order.setSignFlag(0);

			// --收货地址
			BeanUtils.populate(order, request.getParameterMap());

			// --金额/将订单项信息存入order中
			Map<Product, Integer> cartmap = (Map<Product, Integer>) request
					.getSession().getAttribute("cartmap");
			double money = 0;
			Integer admin_id = 0;
			List<OrderItem> list = new ArrayList<OrderItem>();
			for (Map.Entry<Product, Integer> entry : cartmap.entrySet()) {
				money += entry.getKey().getPrice() * entry.getValue();
				OrderItem item = new OrderItem();
				item.setOrder_id(order.getId());
				item.setProduct_id(entry.getKey().getId());
				admin_id = entry.getKey().getAdmin_id();
				item.setBuynum(entry.getValue());
				list.add(item);
			}
			order.setAdmin_id(admin_id);
			order.setMoney(money);
			order.setList(list);

			// --客户编号
			User user = (User) request.getSession().getAttribute("user");
			order.setUser_id(user.getId());

			// 2.调用Service中添加订单的方法
			service.addOrder(order);

			// 3.清空购物车
			cartmap.clear();

			// 4.回到主页
			out.println("<script language='javascript'>alert('订单生成成功!请去支付!');window.location='"
					+ request.getContextPath() + "/index.jsp'</script>");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
