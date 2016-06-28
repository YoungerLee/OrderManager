package com.order.web;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.order.domain.Product;
import com.order.factory.BasicFactory;
import com.order.service.ProdService;

@SuppressWarnings("serial")
public class ChangeCartServlet extends HttpServlet {

	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ProdService service = BasicFactory.getFactory().getService(
				ProdService.class);
		// 1.获取id,根据id查找出商品
		String id = request.getParameter("id");
		Product prod = service.findProdById(id);
		// 2.获取购物车,修改数量
		Map<Product, Integer> cartmap = (Map<Product, Integer>) request
				.getSession().getAttribute("cartmap");
		cartmap.put(prod, Integer.parseInt(request.getParameter("buynum")));
		// 3.重定向回到购物车页面
		response.sendRedirect(request.getContextPath() + "/cart.jsp");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
