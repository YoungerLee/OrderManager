<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  </head>
  <body>
  	<h1>订单管理系统</h1><hr>
  	<c:if test="${sessionScope.user == null}">
  		欢迎光临,游客
  		<a href="${pageContext.request.contextPath}/regist.jsp">注册</a>
  		<a href="${pageContext.request.contextPath}/login.jsp">登录</a>
  	</c:if>
  	<c:if test="${sessionScope.user != null}">
  		欢迎回来,${sessionScope.user.username }
  		<c:if test="${sessionScope.user.role_id == 0}">
  			<a href="${pageContext.request.contextPath}/admin.jsp">进入主页</a>
  		</c:if>
  		<c:if test="${sessionScope.user.role_id == 1}">
  			<a href="${pageContext.request.contextPath}/courier.jsp">进入主页</a>
  		</c:if>
  		<c:if test="${sessionScope.user.role_id == 2}">
  			<a href="${pageContext.request.contextPath}/customer.jsp">进入主页</a>
  		</c:if>
  	</c:if>
  </body>
</html>
