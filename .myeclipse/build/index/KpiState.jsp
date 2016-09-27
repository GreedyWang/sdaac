<%@ page language="java"  pageEncoding="utf-8"%>
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
	<link rel="stylesheet" type="text/css" href="ext/resources/css/ext-all.css">
	<script type="text/javascript" src="ext/adapter/ext/ext-base.js"></script>
	<script type="text/javascript" src="ext/ext-all.js"></script>
	<script type="text/javascript" src="index/util/xx.js"></script>
	<link rel="stylesheet" type="text/css" href="css/style_admin.css">
  </head>
  <script type="text/javascript">
	//指标清零
	function reset(){
		window.location.href='index.do?actionType=resetIndexState'
	}
  </script>
  <body>
  	<table>
  		<tr class="menu" >
  		<td>部门名称</td>
  		<td >KPI状态</td>
  		<td >KPI状态说明</td>
  		</tr>
  		<c:forEach items="${item}" var="diState" varStatus="index">
	  		<tr class="altbg1">
	  			<td>${diState.department.name }</td>
	  			<td>${diState.stateName }</td>
	  			<td>${diState.context }<input type="hidden" id="context[${index.index }]" value="${diState.context }"> </td>
	  			<td><input type="button" value="确认" onclick="doSubmit('${diState.id }','${index.index }')"> </td>
	  		</tr>
  		</c:forEach>
  		<tr>
  			<td><input type="button" value="reset" onclick="reset()"> </td>
  		</tr>
  	</table>
  </body>
</html>
