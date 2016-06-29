package com.order.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.order.factory.BasicFactory;
import com.order.service.UserService;

public class DelCourierServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		UserService service = BasicFactory.getFactory().getService(
				UserService.class);
		// 1.获取要删除的快递员id
		Integer id = Integer.parseInt(request.getParameter("id"));
		// 2.调用Servcie中根据id删除商品的方法
		service.delUserByID(id);
		// 3提示成功，返回主页
		out.println("<script language='javascript'>alert('删除成功！');window.location='"
				+ request.getContextPath() + "/admin.jsp'</script>");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
