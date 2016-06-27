package com.order.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.order.factory.BasicFactory;
import com.order.form.Courier;
import com.order.service.UserService;

public class CourierListServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			UserService service = BasicFactory.getFactory().getService(
					UserService.class);
			// 1.获取所有快递员信息
			List<Courier> list = service.findAllCouriers();
			// 2.把结果集存入request域
			request.setAttribute("courierList", list);
			// 3.请求转发到courierList.jsp页面展示
			request.getRequestDispatcher("/courierList.jsp").forward(request,
					response);
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
