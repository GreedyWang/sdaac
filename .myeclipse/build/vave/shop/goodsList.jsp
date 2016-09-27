<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="/WEB-INF/c.tld" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">   
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  
  <body>
   <table>

   	<c:forEach items="${rs}" var="item" varStatus="state">
	  <c:if test="${state.index%4==0}"> <tr></c:if>
	  	 <td>
	  	 <table>
		   	<tr>
		   		<td rowspan="3"><img src="images/vave/souvenirs/${item.img }" onclick="goods.do?actionType=showDetails"> </td>
		   		<td>名称</td>
		   		<td><a href="goods.do?actionType=showDetails&goodsId=${item.id }">${item.name }</a> </td>
		   	</tr>
		   	<tr>  	
		   		<td>价格</td>
		   		<td>${item.price }</td>
		   	</tr>
		   	<tr>	  
		   		<td>当前数量</td>
		   		<td>${item.quantity }</td>
		   	</tr>
	   		</table>
	   	</td>
	   <c:if test="${state.index%4==3}"> </tr></c:if>
   	</c:forEach>
   		</tr>
   </table>
   
  </body>
</html>
