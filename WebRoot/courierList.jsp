<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	</head>
 	<body style="text-align: center;">
 		<h1>快递员管理</h1><hr>
 		<a href="${pageContext.request.contextPath }/addCourier.jsp">添加快递员</a>
			<table border="1" width="100%">
				<tr>
				  	<th>姓名</th>
				 	<th>联系方式</th>
				  	<th>修改</th>
				  	<th>删除</th>			  	
				</tr>
				<c:forEach items="${requestScope.courierList}" var="courier">
	  				<tr>
					  	<td><c:out value="${courier.username }"/></td>
					  	<td><c:out value="${courier.telNum }"/></td>
	  					<td><a href="${pageContext.request.contextPath }/sys/courierInfo.do?id=${courier.id }">修改</a></td>
					  	<td><a href="${pageContext.request.contextPath }/sys/delCourier.do?id=${courier.id }">删除</a></td>
	  				</tr>
  				</c:forEach>
			</table>
			<a href="${pageContext.request.contextPath }/admin.jsp">返回主页</a>	
	</body>
</html>
