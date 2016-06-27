<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<script type="text/javascript">
  		function changeImg(img){
  			img.src = img.src+"?time="+new Date().getTime();
  		}
  		function checkForm(){
  			var canSub = true;
  			//1.非空校验
  			canSub = checkNull("username","用户名不能为空!") && canSub;
  			canSub = checkNull("password","密码不能为空!") && canSub;
  			canSub = checkNull("password2","确认密码不能为空!") && canSub;
  			canSub = checkNull("telNum","手机号码不能为空!") && canSub;
  			canSub = checkNull("valistr","验证码不能为空!") && canSub;
  			
  			//2.两次密码一致的校验
  			var psw1 = document.getElementsByName("password")[0].value;
  			var psw2 = document.getElementsByName("password2")[0].value;
  			if(psw1 != psw2){
  				document.getElementById("password2_msg").innerHTML = "<font color='red'>两次密码不一致!</font>";
  				canSub = false;
  			}
  			
  			//3.手机号码校验 
			var email = document.getElementsByName("telNum")[0].value;
			if( telNum!= null && telNum != "" && !/^\d+$/.test(telNum)){
				document.getElementById("telNum_msg").innerHTML = "<font color='red'>手机号码必须都是数字!</font>";
  				canSub = false;
			}
  			
  			return canSub;
  		
  		}
  		function checkNull(name,msg){
  			document.getElementById(name+"_msg").innerHTML = "";
  			var objValue = document.getElementsByName(name)[0].value;
  			if(objValue == null || objValue == ""){
				document.getElementById(name+"_msg").innerHTML = "<font color='red'>"+msg+"</font>";
  				return false;
  			}
  			return true;
  		}
  	</script>
  </head>
  <body>
  <div align="center" >
  	<h1>添加快递员</h1><hr>
  	<form action="${pageContext.request.contextPath}/sys/addCourier.do" method="POST" onsubmit="return checkForm()">
  		<table>
  			<tr>
  				<td>姓名:</td>
  				<td><input type="text" name="username" value="${param.username }"/></td>
  				<td id="username_msg"></td>
  			</tr>
  			<tr>
  				<td>密码:</td>
  				<td><input type="password" name="password"/></td>
  				<td id="password_msg"></td>
  			</tr>
  			<tr>
  				<td>确认密码:</td>
  				<td><input type="password" name="password2"/></td>
  				<td id="password2_msg"></td>
  			</tr>
  			<tr>
  				<td>联系方式:</td>
  				<td><input type="text" name="telNum" value="${param.telNum }"/></td>
  				<td id="telNum_msg"></td>
  			</tr>
  			<tr>
  				<td><input type="submit" value="添加"/></td>
  			</tr>		
  		</table>
  	</form>
  	</div>
  </body>
</html>
