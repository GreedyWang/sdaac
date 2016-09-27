<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt.tld" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Participation Rate</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="css/table.css" rel="stylesheet" type="text/css" />
  </head>

  <body>

  	
 	<table>
 	<tr><td>提案批准情况</td></tr>
 		<tr>
 			<td>审批部门</td>
 			<td>部门负责人</td>
 			<td>VAVE部门协调员</td>
 			<td>待批提案数</td>
 			<td>延迟审批提案数</td>
 			<td>已审批的提案数</td>
 			<td>历史审批及时率</td>
 		</tr>

 		<c:forEach items="${rs}" var="item">
 			<tr>
	 			<td>${item.name }</td>
	 			<td>${item.vavePm }</td>
	 			<td >${item.vavePmName }</td>
	 			<td align="right">${item.count }</td>			
	 			<td align="right">${item.countOverC }</td>
	 			<td align="right">${item.all }	</td>
	 			<c:if test="${item.all > 0}">
	 				<td align="right"> <fmt:formatNumber pattern="0.0">${item.all7/item.all*100 }</fmt:formatNumber>%</td>
	 			</c:if>
 			</tr>
 		</c:forEach>
 	</table>
 	
 	
 	
 	
 	
	
	
  </body>
</html>
