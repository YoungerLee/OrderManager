package com.order.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.order.factory.BasicFactory;
import com.order.service.ProdService;

@SuppressWarnings("serial")
public class DelProdServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		ProdService service = BasicFactory.getFactory().getService(
				ProdService.class);
		// 1.获取要删除的商品id
		String id = request.getParameter("id");
		// 2.调用Servcie中根据id删除商品的方法
		service.delProdById(id);
		// 3.请求转发到商品列表页面
		out.println("<script language='javascript'>alert('删除成功！');window.location='"
				+ request.getContextPath()
				+ "/sys/pageProdList.do?thispage=1'</script>");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
