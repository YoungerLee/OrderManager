<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<script type="text/javascript">
  		function checkData(){
  			var price = document.getElementsByName("price")[0].value;
  			if(isNaN(price)){
  				alert("单价必须是数字!");
  				document.getElementsByName("price")[0].value = "";
  				return false;
  			}else if(price<=0){
	  			alert("单价必须大于0!")
	  			document.getElementsByName("price")[0].value = "";
	  			return false;
  			}else{
  				return true;
  			}
  		}
  	</script>
  </head>
  <body style="text-align: center;">
  	<h1>修改商品</h1><hr>
  	<form action="${pageContext.request.contextPath}/sys/updateProd.do" method="POST" enctype="multipart/form-data" onsubmit="return checkData()">
  		<input type="hidden" name="id" value="${requestScope.prod.id }">
  		<table border="1" align="center">
  			<tr>
  				<td>商品名称</td>
  				<td><input type="text" name="name" value="${requestScope.prod.name }" readonly="readonly"/></td>
  			</tr>
  			<tr>
  				<td>单价</td>
  				<td><input type="text" name="price" value="${requestScope.prod.price }"/></td>
  			</tr>
  			<tr>
  				<td>库存数量</td>
  				<td><input type="text" name="pnum" value="${requestScope.prod.pnum }"/></td>
  			</tr>
  			<tr>
  				<td>供货开始时间</td>
  				<td><input type="text" name="startTime" value="${requestScope.prod.startTime }"/></td>
  			</tr>
  			<tr>
  				<td>供货结束时间</td>
  				<td><input type="text" name="endTime" value="${requestScope.prod.endTime }"/></td>
  			</tr>
  			<tr>
  				<td>商品图片</td>
  				<td><input type="file" name="file1"/></td>
  			</tr>
  			<tr>
  				<td colspan="2"><input type="submit" value="修改商品"></td>
  			</tr>
  		</table>
  	</form>
  </body>
</html>
