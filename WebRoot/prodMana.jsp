<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
  		<script type="text/javascript">
  			function changePage(obj){
  				if(isNaN(obj.value)){
	  				alert("必须是数字!");
	  				obj.value=${pageProd.thispage}
	  				return;
  				}else if(obj.value<=0 || obj.value>${pageProd.countpage} ){
	  				alert("页码必须在有效范围内!");
	  				obj.value=${pageProd.thispage}
	  				return;
	  			}else{
	  				window.location.href="${pageContext.request.contextPath }/sys/pageProdList.do?thispage="+obj.value;
	  			}
  			}
  		</script>
	</head>
 	<body style="text-align: center;">
 		<h1>商品管理</h1><hr>
 		<div class="pageList">
			<table border="1" width="100%">
				<tr>
				  	<th>商品名称</th>
				  	<th>商品单价</th>
				  	<th>库存数量</th>
				  	<th>供货开始时间</th>
				  	<th>供货结束时间</th>
				  	<th>商品照片</th>
				  	<th>修改</th>
				  	<th>删除</th>			  	
				</tr>
				<c:forEach items="${requestScope.pageProd.list}" var="prod">
	  				<tr>
					  	<td><c:out value="${prod.name }"/></td>
					  	<td><c:out value="${prod.price }"/></td>
					  	<td><c:out value="${prod.pnum }"/></td>
					  	<td><c:out value="${prod.startTime }"/></td>
					  	<td><c:out value="${prod.endTime }"/></td>
				  		<td width="30%"><img src="${pageContext.request.contextPath}/sys/prodImg.do?imgurl=${prod.imgurls }" width="40%"/></td>
	  					<td><a href="${pageContext.request.contextPath }/sys/prodInfo.do?id=${prod.id }">修改</a></td>
					  	<td><a href="${pageContext.request.contextPath }/sys/delProd.do?id=${prod.id }">删除</a></td>
	  				</tr>
  				</c:forEach>
			</table>
			<div class="foot">
				<span>共${pageProd.countrow }条记录</span>
				<span>共${pageProd.countpage }页</span>
				<c:if test="${pageProd.countpage > 1 }">
					<a href="${pageContext.request.contextPath }/sys/pageProdList.do?thispage=${pageProd.firstpage }">首页</a>
					<a href="${pageContext.request.contextPath }/sys/pageProdList.do?thispage=${pageProd.prepage }">上一页</a>
  				</c:if>
				<!-- 分页逻辑开始 -->
				<c:if test="${pageProd.countpage<=5}">
					<c:set var="begin" value="1" scope="page"></c:set>
					<c:set var="end" value="${pageProd.countpage}" scope="page"></c:set>
				</c:if>
  				<c:if test="${pageProd.countpage>5}">
					<c:choose>
						<c:when test="${pageProd.thispage<=3}">
							<c:set var="begin" value="1" scope="page"></c:set>
		  					<c:set var="end" value="5" scope="page"></c:set>
						</c:when>
						<c:when test="${pageProd.thispage>=pageProd.countpage-2}">
							<c:set var="begin" value="${pageProd.countpage-4}" scope="page"></c:set>
	  						<c:set var="end" value="${pageProd.countpage}" scope="page"></c:set>
	  					</c:when>
	  					<c:otherwise>
	  						<c:set var="begin" value="${pageProd.thispage-2}" scope="page"></c:set>
	  						<c:set var="end" value="${pageProd.thispage+2}" scope="page"></c:set>
	  					</c:otherwise>
					</c:choose>
  				</c:if>
  	
  				<c:forEach begin="${begin}" end="${end}" step="1" var="i">
  					<c:if test="${i == pageProd.thispage}">
  								${i }
  					</c:if>
  					<c:if test="${i != pageProd.thispage}">
  						<a href="${pageContext.request.contextPath }/sys/pageProdList.do?thispage=${i}">${i }</a>
  					</c:if>
  				</c:forEach>
  	
				<!-- 分页逻辑结束 -->
				<c:if test="${pageProd.countpage > 1 }">
					<a href="${pageContext.request.contextPath }/sys/pageProdList.do?thispage=${pageProd.nextpage }">下一页</a>
					<a href="${pageContext.request.contextPath }/sys/pageProdList.do?thispage=${pageProd.lastpage }">尾页</a>
					跳到<input type="text" value="${pageProd.thispage }" style="width: 40px" onchange="changePage(this)"/>页
				</c:if>
			</div>
			<a href="${pageContext.request.contextPath }/admin.jsp">返回主页</a>
		</div>
	</body>
</html>
