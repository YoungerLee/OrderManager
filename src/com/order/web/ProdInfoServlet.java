package com.order.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.order.domain.Product;
import com.order.factory.BasicFactory;
import com.order.service.ProdService;

@SuppressWarnings("serial")
public class ProdInfoServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ProdService service = BasicFactory.getFactory().getService(
				ProdService.class);
		// 1.获取要查询的商品id
		String id = request.getParameter("id");
		// 2.调用Service中根据id查找商品的方法
		Product prod = service.findProdById(id);
		if (prod == null) {
			throw new RuntimeException("找不到该商品!");
		}
		// 3.将查找到的商品信息存入request域中,请求转发到updateProd.jsp页面展示
		request.setAttribute("prod", prod);
		request.getRequestDispatcher("/updateProd.jsp").forward(request,
				response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
