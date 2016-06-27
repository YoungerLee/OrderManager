package com.order.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.order.domain.User;
import com.order.factory.BasicFactory;
import com.order.service.UserService;
import com.order.util.MD5Utils;

public class AddCourierServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserService service = BasicFactory.getFactory().getService(
				UserService.class);
		try {
			PrintWriter out = response.getWriter();
			// 1.封装数据&校验数据
			User courier = new User();
			BeanUtils.populate(courier, request.getParameterMap());
			courier.setPassword(MD5Utils.md5(courier.getPassword()));
			// 2.调用Service添加快递员
			service.addCourier(courier);
			// 3.回到主页
			out.write("<script language='javascript'>alert('添加成功');window.location='"
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
