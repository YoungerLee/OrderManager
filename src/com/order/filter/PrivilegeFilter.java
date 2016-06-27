package com.young.filter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PrivilegeFilter implements Filter {
	private List<String> teacher_list = new ArrayList<String>();
	private List<String> student_list = new ArrayList<String>();

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		String uri = request.getRequestURI();
		User user = (User) request.getSession().getAttribute("user");
		// System.out.println(uri);
		if (teacher_list.contains(uri) || student_list.contains(uri)) {
			// 说明当前资源需要权限
			if (request.getSession(false) == null || user == null) {// 当前资源需要登录
				response.getWriter().println(
						"<script language='javascript'>alert('当前资源需要权限，请先登录！');window.location='"
								+ request.getContextPath()
								+ "/jsp/login.jsp'</script>");
				return;
			} else {// 当前已经登录，过滤角色权限
				// 0表示学生，1表示老师
				if (teacher_list.contains(uri) && user.getType() == 1) {// 当前资源符合教师权限，放行
					chain.doFilter(req, resp);
				} else if (student_list.contains(uri) && user.getType() == 0) {// 当前资源符合学生权限，放行
					chain.doFilter(req, resp);
				} else {// 当前资源不符合角色权限
					if (user.getType() == 0) {// 若当前用户是学生，则返回学生主页
						response.getWriter().println(
								"<script language='javascript'>alert('您不具有对应的权限！');window.location='"
										+ request.getContextPath()
										+ "/jsp/student.jsp'</script>");
						return;
					} else {// 若当前用户是教师，则返回教师页面
						response.getWriter().println(
								"<script language='javascript'>alert('您不具有对应的权限！');window.location='"
										+ request.getContextPath()
										+ "/jsp/teacher.jsp'</script>");
						return;
					}
				}
			}
		} else {// 当前资源不需要权限
			chain.doFilter(req, resp);
		}
	}

	@SuppressWarnings("resource")
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		ServletContext context = filterConfig.getServletContext();
		try {
			BufferedReader teacherReader = new BufferedReader(new FileReader(
					context.getRealPath("WEB-INF/teacher.txt")));
			String line = null;
			while ((line = teacherReader.readLine()) != null) {
				teacher_list.add(line);
			}

			BufferedReader studentReader = new BufferedReader(new FileReader(
					context.getRealPath("WEB-INF/student.txt")));
			line = null;
			while ((line = studentReader.readLine()) != null) {
				student_list.add(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
