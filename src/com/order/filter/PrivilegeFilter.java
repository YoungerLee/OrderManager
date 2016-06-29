package com.order.filter;

import java.io.IOException;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.order.domain.User;
import com.order.factory.BasicFactory;
import com.order.service.PrivilegeService;

public class PrivilegeFilter implements Filter {
	private PrivilegeService service = BasicFactory.getFactory().getService(
			PrivilegeService.class);
	private Set<String> adminSet;
	private Set<String> custSet;
	private Set<String> courierSet;

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
		if (adminSet.contains(uri) || custSet.contains(uri)
				|| courierSet.contains(uri)) {// 表示当前资源需要权限
			if (request.getSession(false) == null || user == null) {// 当前资源需要登录
				response.getWriter().println(
						"<script language='javascript'>alert('当前资源需要权限，请先登录！');window.location='"
								+ request.getContextPath()
								+ "/login.jsp'</script>");
				return;
			} else {// 当前已经登录，过滤角色权限
				// 0表示管理员，1表示快递员，2表示客户
				if (adminSet.contains(uri) && user.getRole_id() == 0) {// 当前资源符合管理员权限，放行
					chain.doFilter(req, resp);
				} else if (courierSet.contains(uri) && user.getRole_id() == 1) {// 当前资源符合快递员权限，放行
					chain.doFilter(req, resp);
				} else if (custSet.contains(uri) && user.getRole_id() == 2) {// 当前资源符合客户权限，放行
					chain.doFilter(req, resp);
				} else {// 当前资源不符合角色权限
					if (user.getRole_id() == 0) {// 若当前用户是管理员，则返回管理员主页
						response.getWriter().println(
								"<script language='javascript'>alert('您不具有对应的权限！');window.location='"
										+ request.getContextPath()
										+ "/admin.jsp'</script>");
						return;
					} else if (user.getRole_id() == 1) {// 若当前用户是快递员，则返回快递员页面
						response.getWriter().println(
								"<script language='javascript'>alert('您不具有对应的权限！');window.location='"
										+ request.getContextPath()
										+ "/courier.jsp'</script>");
						return;
					} else {// 若当前用户是客户，则返回客户页面
						response.getWriter().println(
								"<script language='javascript'>alert('您不具有对应的权限！');window.location='"
										+ request.getContextPath()
										+ "/customer.jsp'</script>");
						return;
					}
				}
			}
		} else {// 当前资源不需要权限
			chain.doFilter(req, resp);
		}

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		adminSet = service.getPrivilegeByRole(0);
		courierSet = service.getPrivilegeByRole(1);
		custSet = service.getPrivilegeByRole(2);
	}
}
