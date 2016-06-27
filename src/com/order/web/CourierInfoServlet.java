package com.order.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.order.domain.User;
import com.order.factory.BasicFactory;
import com.order.service.UserService;

public class CourierInfoServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserService service = BasicFactory.getFactory().getService(
				UserService.class);
		// 1.获取要查询的快递员信息
		Integer id = Integer.parseInt(request.getParameter("id"));
		// 2.调用Service中根据id查找快递员的方法
		User courier = service.findUserById(id);
		if (courier == null) {
			throw new RuntimeException("找不到该用户!");
		}
		// 3.将查找到的商品信息存入request域中,请求转发到updateCourier.jsp页面展示
		request.setAttribute("courier", courier);
		request.getRequestDispatcher("/updateCourier.jsp").forward(request,
				response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
