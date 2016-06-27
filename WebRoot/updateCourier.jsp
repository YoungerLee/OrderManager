<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<script type="text/javascript">
  	</script>
  </head>
  <body style="text-align: center;">
  	<h1>修改快递员</h1><hr>
  	<form action="${pageContext.request.contextPath}/sys/updateCourier.do" method="POST" onsubmit="return checkData()">
  		<input type="hidden" name="id" value="${requestScope.courier.id }">
  		<table border="1" align="center">
  			<tr>
  				<td>姓名</td>
  				<td><input type="text" name="username" value="${requestScope.courier.username }" readonly="readonly"/></td>
  			</tr>
  			<tr>
  				<td>联系方式</td>
  				<td><input type="text" name="telNum" value="${requestScope.courier.telNum }"/></td>
  			</tr>
  			<tr>
  				<td colspan="2"><input type="submit" value="修改快递员"></td>
  			</tr>
  		</table>
  	</form>
  </body>
</html>
