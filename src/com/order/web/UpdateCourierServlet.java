package com.order.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.order.factory.BasicFactory;
import com.order.form.Courier;
import com.order.service.UserService;

public class UpdateCourierServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		UserService service = BasicFactory.getFactory().getService(
				UserService.class);
		try {
			// 1.封装数据&校验数据
			Courier courier = new Courier();
			BeanUtils.populate(courier, request.getParameterMap());
			// 2.调用Service修改用户信息
			service.updateCourier(courier);
			// 3.提示成功,回到主页
			out.println("<script language='javascript'>alert('修改成功！');window.location='"
					+ request.getContextPath() + "/admin.jsp'</script>");
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
