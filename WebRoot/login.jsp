<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<script type="text/javascript">
		window.onload=function(){
	  		var str = decodeURI('${cookie.remname.value}');
	  		document.getElementsByName("username")[0].value = str;
  		}
  	</script>
  </head>
  <body>
  <div align="center">
  	<h1>登录</h1><hr>
  	<font color="red">${msg }</font>
  	<form action="${pageContext.request.contextPath}/sys/login.do" method="POST">
  		<table>
  			<tr>
  				<td>用户名:</td>
  				<td><input type="text" name="username" value="" /></td>
  			</tr>
  			<tr>
  				<td>密码:</td>
  				<td><input type="password" name="password" /></td>
  			</tr>
  			<tr>
  				<td>登录类型:</td>
  				<td>
	  				<input name="role" type="radio"  value="0" />管理员
					<input name="role" type="radio"  value="1" />快递员
	  				<input name="role" type="radio"  value="2" />顾客
	  			</td>
  			</tr>
  			<tr>
  				<td><input type="checkbox" name="remname" value="true" 
  					<c:if test="${cookie.remname != null }">
  						checked = 'checked'
  					</c:if>
  				/>记住用户名</td>
  				<td><input type="checkbox" name="autologin" value="true" />一周内自动登陆</td>
  			</tr>	
  			<tr>
  				<td colspan="2"><input type="submit" value="登录" /></td>
  			</tr>
  		</table>
  	</form>
  </div>
  </body>
</html>
  