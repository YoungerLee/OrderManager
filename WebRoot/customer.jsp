<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<script type="text/javascript">
  	</script>
  </head>
  <body>
	  <div align="center">
	  	<h1>我的主页</h1><hr>
	  	<a href="${pageContext.request.contextPath}/sys/prodList.do?thispage=1">查看商品</a>
	  	<a href="${pageContext.request.contextPath}/cart.jsp">查看购物车</a>
	  	<a href="${pageContext.request.contextPath}/sys/orderList.do">查看订单</a>
	  	<a href="${pageContext.request.contextPath}/sys/logout.do">注销</a>
	  </div>
  </body>
</html>
  