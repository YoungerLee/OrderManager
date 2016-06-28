package com.order.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.order.domain.User;
import com.order.factory.BasicFactory;
import com.order.service.UserService;

public class PayInfoServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserService us = BasicFactory.getFactory()
				.getService(UserService.class);
		String order_id = request.getParameter("id");
		Double payMoney = Double.parseDouble(request.getParameter("money"));
		User user = (User) request.getSession().getAttribute("user");
		Double cash = us.findUserById(user.getId()).getCash();
		request.setAttribute("order_id", order_id);
		request.setAttribute("payMoney", payMoney);
		request.setAttribute("cash", cash);
		request.getRequestDispatcher("/pay.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
