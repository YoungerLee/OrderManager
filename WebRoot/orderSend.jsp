<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  </head>
  <body>
  	<h1>查看需要派送的订单</h1><hr>
  	<c:forEach items="${requestScope.list}" var="olf">
  		<h3>
  		订单号:${olf.id }<br>
  		</h3>
		买家名称 :${olf.username }<br>
		卖家名称 :${olf.admin }<br>
		订单金额 :${olf.money }<br>
		支付状态 :
			<c:if test="${olf.payState == 0}">
				<font color="red">等待买家支付</font>
			</c:if>
			<c:if test="${olf.payState == 1}">
				<font color="blue">已支付</font><br>
				发货状态 : 
				<c:if test="${olf.sendFlag == 0 }">
					<font color="red">未发货</font><br>
				</c:if>
				<c:if test="${olf.sendFlag == 1 }">
					<font color="blue">已发货</font><br>
					发货时间:${olf.sendTime }<br>
					签收状态:
					<c:if test="${olf.signFlag == 0 }">
						<font color="red">未签收</font>
						<form action="${pageContext.request.contextPath }/sys/sign.do" method="post">
						<input type="hidden" value="${olf.id }" name="id"/>
						<input type="submit" value="确认签收">
						</form>
					</c:if>
					<c:if test="${olf.signFlag == 1 }">
						<font color="blue">已签收</font><br>
						签收时间:${olf.signTime }
					</c:if>
				</c:if>
			</c:if>
		<br>
		收货地址 :${olf.address }<br>
		下单时间 :${olf.orderTime }<br>
		<table width="100%" border="1">
			<tr>
				<th>商品图片</th>
				<th>商品名称</th>
				<th>购买数量</th>
				<th>商品单价</th>
				<th>总金额</th>
			</tr>	
			<c:forEach items="${olf.prodMap}" var="entry">
				<tr>
					<td><img src="${pageContext.request.contextPath}/sys/prodImg.do?imgurl=${entry.key.imgurls }" width="40%"/></td>
					<td>${entry.key.name }</td>
					<td>${entry.value }</td>
					<td>${entry.key.price }</td>
					<td>${entry.key.price * entry.value }</td>
				</tr>
			</c:forEach>
		</table>
		<hr>
  	</c:forEach>
  	<a href="${pageContext.request.contextPath }/index.jsp">返回主页</a>
  </body>
</html>
