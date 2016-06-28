package com.order.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.order.domain.User;
import com.order.factory.BasicFactory;
import com.order.form.OrderListForm;
import com.order.service.OrderService;

public class OrderSendServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		OrderService service = BasicFactory.getFactory().getService(
				OrderService.class);
		// 1.获取快递员id
		User courier = (User) request.getSession().getAttribute("user");
		Integer id = courier.getId();
		// 2.调用Service中根据用户id查询快递员需要派送的的订单的方法
		List<OrderListForm> list = service.findOrdersSending(id);
		// 3.存入request域带到页面显示
		request.setAttribute("list", list);
		request.getRequestDispatcher("/orderSend.jsp").forward(request,
				response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
