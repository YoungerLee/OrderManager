package com.order.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.order.factory.BasicFactory;
import com.order.service.PayService;

public class PayServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		PayService service = BasicFactory.getFactory().getService(
				PayService.class);
		// 1.调用Service中的支付方法
		String order_id = request.getParameter("id");
		Double money = Double.parseDouble(request.getParameter("payMoney"));
		service.pay(order_id, money);
		// 2.提示成功，返回主页
		out.println("<script language='javascript'>alert('支付成功!');window.location='"
				+ request.getContextPath() + "/customer.jsp'</script>");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
