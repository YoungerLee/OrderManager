<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<script type="text/javascript">
		window.onload=function() {
	  		var str = decodeURI('${cookie.remname.value}');
	  		document.getElementsByName("username")[0].value = str;
  		}
  	</script>
  </head>
  <body>
	  <div align="center">
	  	<h1>我的主页</h1><hr>
	  	<a href="${pageContext.request.contextPath}/addProd.jsp">添加商品</a>
	  	<a href="${pageContext.request.contextPath}/sys/pageProdList.do?thispage=1">商品管理</a>
	  	<a href="${pageContext.request.contextPath}/sys/orderList.do">订单列表</a>
	  	<a href="${pageContext.request.contextPath}/sys/courierList.do">查看快递员</a>
	  	<a href="${pageContext.request.contextPath}/sys/logout.do">注销</a>
	  </div>
  </body>
</html>
  