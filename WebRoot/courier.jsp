<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  </head>
  <body>
	  <div align="center">
	  	<h1>我的主页</h1><hr>
	  	<a href="${pageContext.request.contextPath}/sys/orderSend.do">查看要派送的订单</a>
	  	<a href="${pageContext.request.contextPath}/sys/orderSent.do">查看历史派送订单</a>
	  	<a href="${pageContext.request.contextPath}/sys/logout.do">注销</a>
	  </div>
  </body>
</html>
  