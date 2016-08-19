<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
   	<table border="1">
   		<tr>
   			<td>加班明细\日期</td>
   			<td>星期一</td>
   			<td>星期二</td>
   			<td>星期三</td>
   			<td>星期四</td>
   			<td>星期五</td>
   			<td>星期六</td>
   			<td>星期日</td>
   			<td>合计</td>
   		</tr>
   		<c:forEach items="${map}" var="item" varStatus="state">
   			<tr>
   				<td rowspan="1">${item.key }</td>
   				
   				<c:forEach items="${item.value}" var="val">
   					<td>
   					<table border="1">
   					<c:if test="${state.index == 0}">
   						<tr><td><fmt:formatDate value="${val[1] }" pattern="yyyy-MM-dd"/></td></tr>
   					</c:if>
   					<tr><td>${val[2] }</td></tr>
   					<tr><td>${val[3] }</td></tr>
   					<tr><td>${val[4] }</td></tr></table>
   					
   					</td>	
   				</c:forEach>
   							
   			</tr>
   		</c:forEach>
   	</table>
  </body>
</html>
