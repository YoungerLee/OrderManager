package com.order.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.order.domain.PageProd;
import com.order.factory.BasicFactory;
import com.order.service.ProdService;

public class ProdListServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ProdService service = BasicFactory.getFactory().getService(
				ProdService.class);
		// 1.获取当前要显示的页和每页记录数
		int thispage = Integer.parseInt(request.getParameter("thispage"));
		int rowperpage = 5;
		// 2.调用Service中分页查询设备的方法,查找出设备信息
		PageProd pageProd = service.pageProd(thispage, rowperpage);
		// 3.存入request域中,带到pageList.jsp页面中进行显示
		request.setAttribute("pageProd", pageProd);
		request.getRequestDispatcher("/prodList.jsp")
				.forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
