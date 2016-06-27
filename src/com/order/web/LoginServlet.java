package com.order.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.order.domain.User;
import com.order.factory.BasicFactory;
import com.order.service.UserService;
import com.order.util.MD5Utils;

public class LoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		UserService service = BasicFactory.getFactory().getService(
				UserService.class);
		// 1.获取用户名密码
		String username = request.getParameter("username");
		String password = MD5Utils.md5(request.getParameter("password"));
		int role = 0;// 角色编号
		String role_str = request.getParameter("role");
		if (role_str == null || "".equals(role_str)) {
			out.println("<script language='javascript'>alert('请挑选登录类型！');window.location='"
					+ request.getContextPath() + "/login.jsp'</script>");
			return;
		} else {
			role = Integer.parseInt(role_str);
		}
		// 2.调用Service中根据用户名密码查找用户的方法
		User user = service
				.getUserByNameAndPswAndRole(username, password, role);
		if (user == null) {
			// 3.如果不正确则提示
			out.println("<script language='javascript'>alert('用户名密码不正确');window.location='"
					+ request.getContextPath() + "/login.jsp'</script>");
			return;
		} else {
			// 4.登录用户重定向到主页
			request.getSession().setAttribute("user", user);

			if ("ok".equals(request.getParameter("remname"))) {
				// 如果用户勾选过记住用户则发送cookie另浏览器保存用户名
				Cookie remNameC = new Cookie("remname", URLEncoder.encode(
						user.getUsername(), "utf-8"));
				remNameC.setPath(request.getContextPath());
				remNameC.setMaxAge(3600 * 24 * 7);
				response.addCookie(remNameC);
			} else {
				// 如果用户没有勾选记住用户名则删除记住用户名的cookie
				Cookie remNameC = new Cookie("remname", "");
				remNameC.setPath(request.getContextPath());
				remNameC.setMaxAge(0);
				response.addCookie(remNameC);
			}
			// 如果用户勾选过一周内自动登陆,发送自动登录cookie
			if ("true".equals(request.getParameter("autologin"))) {
				Cookie autologinC = new Cookie("autologin", URLEncoder.encode(
						user.getUsername() + ":" + user.getPassword(), "utf-8"));
				autologinC.setPath(request.getContextPath());
				autologinC.setMaxAge(3600 * 24 * 7);
				response.addCookie(autologinC);
			}
			// 5.重定向到主页
			if (role == 0) {// 管理员的主页
				response.sendRedirect(request.getContextPath() + "/admin.jsp");
			} else if (role == 1) {// 快递员的主页
				response.sendRedirect(request.getContextPath() + "/courier.jsp");
			} else {// 顾客的主页
				response.sendRedirect(request.getContextPath()
						+ "/customer.jsp");
			}
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}