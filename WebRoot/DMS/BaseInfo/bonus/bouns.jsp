<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
			<link rel="stylesheet" type="text/css" href="css/style_admin.css">
	</head>

	<body>
	<table width="70%"cellspacing="1" cellpadding="4"  class="tableborder">
		<form action="dMS.do?actionType=showBonus" method="post">
			<tr>
				
				<td align="center" class="header" >
					版本(yyyy-mm) ：<input type="text" name="version">
				</td>
				<td align="center" class="header" >
					<input type="submit" value="查询">
				</td>
			</tr>
		</form>
	</table>
	
		<table width="70%"cellspacing="1" cellpadding="4"  class="tableborder">
			<tr>
				<td align="center" class="header" >
					奖金
				</td>
				<td align="center" class="header">
					区域
				</td>
				<td align="center" class="header">
					版本
				</td>
			</tr>
			<c:forEach items="${rs}" var="item">
				<tr>
					<td align="center" class="altbg1">
						<input type="text" value="${item.bonus }">
					</td>
					<td align="center" class="altbg1">
						${item.area }
					</td>
					<td align="center" class="altbg1">
						${item.version }
					</td>
				</tr>
			</c:forEach>
		</table>
	</body>
</html>
