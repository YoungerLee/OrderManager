package com.order.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.order.factory.BasicFactory;
import com.order.service.OrderService;

public class DelOrderServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		OrderService service = BasicFactory.getFactory().getService(
				OrderService.class);
		// 1.获取订单id
		String id = request.getParameter("id");
		// 2.调用Service中根据删除订单的方法
		service.delOrderByID(id);
		// 3.回到订单列表页面
		out.println("<script language='javascript'>alert('订单删除成功！');window.location='"
				+ request.getContextPath() + "/sys/orderList.do'</script>");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
