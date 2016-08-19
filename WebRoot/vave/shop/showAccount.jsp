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
  <!-- 
   private Integer id;
     private Goods goods;
     private Tempolyee emp= new Tempolyee();
     private Date datetime;
     private Integer quantity;
     private String beginTime;
     private String endTime;
   -->
  <body>
   <table>
   	<tr>
   		<td>日期</td>
   		<td>员工名</td>
   		<td>状态<br></td>  		
   	</tr>
   	<c:forEach items="${rs}" var="item">
   		<tr>
	   		<td><a href="account.do?actionType=showAccountGoods&accountId=${item.id }">${item.datetime }</a></td>
	   		<td>${item.emp.name }</td>
	   		<td>${item.stateNames }</td>  		
   		</tr>
   	</c:forEach>
   </table>
  </body>
</html>
