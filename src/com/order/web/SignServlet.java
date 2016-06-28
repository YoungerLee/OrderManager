package com.order.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.order.factory.BasicFactory;
import com.order.service.OrderService;

public class SignServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		OrderService service = BasicFactory.getFactory().getService(
				OrderService.class);
		// 1.获取订单信息，确认签收
		String order_id = request.getParameter("id");
		service.signOrder(order_id);
		// 2.提示成功，返回主页
		out.println("<script language='javascript'>alert('签收成功!');window.location='"
				+ request.getContextPath() + "/courier.jsp'</script>");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
