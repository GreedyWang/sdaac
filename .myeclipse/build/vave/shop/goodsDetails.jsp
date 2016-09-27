<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
		<tr>
			<td><img src="${rs.img }"> </td>
		</tr>
		<tr>
	   		<td>名称</td>
	   		<td>${rs.name }</td>
	   	</tr>
		<tr>  	
	   		<td>价格</td>
	   		<td>${item.price }</td>
	   	</tr>
	   	<tr>	  
	   		<td>当前数量</td>
	   		<td>${item.quantity }</td>
	   	</tr>
	   	<form action="account.do?actionType=buy" method="post">
		<input name="goodsId" type="hidden" value="${rs.id }">
		<tr><td>数量</td><td><input name="quantity" id="quantity"> </td></tr>
		<tr><td><input type="submit" value="buy" > </td></tr>
		</form>
	</table>
  </body>
</html>
