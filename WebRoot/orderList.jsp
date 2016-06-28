<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  </head>
  <body>
  	<h1>订单列表</h1><hr>
  	<c:forEach items="${requestScope.list}" var="olf">
  		<h3>
  		订单号:${olf.id }<br>
  		</h3>
		用户名称 :${olf.username }<br>
		卖家名称 :${olf.admin }<br>
		订单金额 :${olf.money }<br>
		支付状态 :
			<c:if test="${olf.payState == 0}">
				<font color="red">未支付</font>
				<a href="${pageContext.request.contextPath }/sys/delOrder.do?id=${olf.id }">订单删除</a>
				<a href="${pageContext.request.contextPath }/sys/payInfo.do?id=${olf.id }&money=${olf.money }">在线支付</a>
			</c:if>
			<c:if test="${olf.payState == 1}">
				<font color="blue">已支付</font><br>
				发货状态 : 
				<c:if test="${olf.sendFlag == 0 }">
					<font color="red">未发货</font>
				</c:if>
				<c:if test="${olf.sendFlag == 1 }">
					<font color="blue">已发货</font><br>
					发货时间:${olf.sendTime }<br>
					您的货品将由快递员<font color="red">${olf.courier }</font>派送<br>
					签收状态:
					<c:if test="${olf.signFlag == 0 }">
						<font color="red">未签收</font>
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
  </body>
  <a href="${pageContext.request.contextPath }/index.jsp">返回主页</a>
</html>
