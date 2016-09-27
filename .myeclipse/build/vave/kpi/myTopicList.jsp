<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt.tld" %>
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
 <!-- myTopicList.jsp -->
  <body>
   <table>
   	<tr>
   		<td>课题</td>
   		<td>发布人</td>
   		<td>日期</td>
   		<td></td>
   		<td></td>
   	</tr>
   	<c:forEach items="${rs}" var="item">
   		<tr>
   			<td>
   			<a href="businessTopic.do?actionType=doSelectDetails&id=${item.id }">${item.businessTitle }</a>
   			</td>  			
   		<td>${item.publisher.name }</td>
   		<td><fmt:formatDate pattern="yyyy-MM-dd" value="${item.createTime }"></fmt:formatDate> </td>
   		<td>
   			<a href="businessTopic.do?actionType=doDelete&id=${item.id }">删除</a>
   		</td>
   		</tr>
   	</c:forEach>
   </table>
  </body>
</html>
