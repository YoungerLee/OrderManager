package com.order.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.order.factory.BasicFactory;
import com.order.service.OrderService;

public class SendServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		OrderService service = BasicFactory.getFactory().getService(
				OrderService.class);
		// 1.获取订单和快递员信息
		String order_id = request.getParameter("id");
		String courier = request.getParameter("courier");
		service.sendOrder(order_id, courier);
		// 2.提示成功，返回主页
		out.println("<script language='javascript'>alert('发货成功!');window.location='"
				+ request.getContextPath() + "/admin.jsp'</script>");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
