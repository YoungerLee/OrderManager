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

public class RegistServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserService service = BasicFactory.getFactory().getService(
				UserService.class);
		try {
			PrintWriter out = response.getWriter();
			// 1.校验验证码
			String valistr1 = request.getParameter("valistr").trim()
					.toLowerCase();
			String valistr2 = (String) request.getSession().getAttribute(
					"valistr");
			if (valistr1 == null || valistr2 == null
					|| !valistr1.equals(valistr2)) {
				out.write("<script language='javascript'>alert('验证码不正确！');window.location='"
						+ request.getContextPath() + "/regist.jsp'</script>");
				return;
			}
			// 2.封装数据&校验数据
			User user = new User();
			BeanUtils.populate(user, request.getParameterMap());
			user.setPassword(MD5Utils.md5(user.getPassword()));
			// 3.调用Service注册用户
			service.regist(user);

			// 4.回到主页
			out.write("<script language='javascript'>alert('注册成功！请去主页面登录');window.location='"
					+ request.getContextPath() + "/index.jsp'</script>");
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