<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'cwMainframe.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

  </head>
  <style>
  	table td {font-size: 12 }
  	.context { background-color: #D2D9E3}
	.title { background-color: #b2b9E3}
	.title2 { background-color: #C2C9C4}
  </style>
  <body>
  <table border="0">
  <form id="txtInfoForm" action="cw.do?actionType=remove" method="post">
   	<c:forEach items="${cwrs}" var="item">
   	<tr class="title">
   		<td>No:</td>
	   	<td>开票日期</td>
	   	<td>名称</td>
	   	<td>纳税人别名</td>
	   	<td>地址、电话</td>
	   	<td>开户行及帐号</td>
	   	<td>备注</td>
	   	<td></td>
   	</tr>
   		<tr class="context">
   		<td><input type="checkbox" name="no" value="${item.no }" ><a href="cw.do?actionType=showDetials&no=${item.no }" target="blank">${item.no }</a> </td>
   		<td>${item.createtime }</td>
   		<td>${item.buyer.name }</td>
   		<td>${item.buyer.taxpayersCode }</td>
   		<td>${item.buyer.address }</td>
   		<td>${item.buyer.account }</td>
   		<td>${item.seller.remarks }</td>
   		</tr>
   		<tr class="title2">
   	 	<td>货物名称</td>
	   	<td>规格</td>
	   	<td>单位</td>
	   	<td>数量</td>
	   	<td>单价</td>
	   	<td>金额</td>
	   	<td>税率</td>
	   	<td>税额</td>
   		</tr>
   	
   		<c:forEach items="${item.taxItems}" var="items">
   		<tr class="context">
   		<td>${items.produceName }</td>
   		<td>${items.standardType }</td>
   		<td>${items.unit }</td>
   		<td>${items.quantity }</td>
   		<td>${items.price }</td>
   		<td> ${items.sumPrice }</td>
   		<td>${items.fax }</td>
   		<td>${items.faxPrice }</td>
   		</tr>
   		</c:forEach>
   		
   	</c:forEach>
  </form>
  </table>
  </body>
</html>
