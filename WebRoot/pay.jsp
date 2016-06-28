<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  </head>
  <body style="text-align: center;">
  		<h1>在线支付</h1><hr>
        <form action="${pageContext.request.contextPath }/sys/pay.do" method="post">
      	<table width="60%" border="1" align="center">
			<tr>  
				<td >
			 		订单号：${requestScope.order_id }<input type="hidden" value="${requestScope.order_id }" name="id"/>
			 		支付金额：${requestScope.payMoney }元<input type="hidden" value="${requestScope.payMoney }" name="payMoney"/>
			 	</td>
			</tr>
			<tr>
				<td>
					您的账户余额:${requestScope.cash }元
					<c:if test="${requestScope.cash < requestScope.payMoney }">
						<font color="red">账户余额不足！</font>
					</c:if>
				</td>
			</tr>
			<tr>
				<c:if test="${requestScope.cash < requestScope.payMoney}">
						<td><font color="red">请先充值！</font></td>
				</c:if>
				<c:if test="${requestScope.cash > requestScope.payMoney}">
					 <td><INPUT TYPE="submit" value="确定支付"></td>
				</c:if>
			</tr>
     	</table>
   		</form>
  </body>
</html>
