<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	request.setAttribute("basePath", basePath);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>winaudit小工具</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" type="text/css" href="css/style_admin.css">
		<link rel="stylesheet" type="text/css"
			href="ext/resources/css/ext-all.css">
		<script type="text/javascript" src="ext/adapter/ext/ext-base.js"></script>
		<script type="text/javascript" src="ext/ext-all.js"></script>
		<script type="text/javascript" src="audit/auditManager.js"></script>
		<script type='text/javascript'
			src='/bpp/dwr/interface/auditDailyManager.js'></script>
		<script type='text/javascript' src='/bpp/dwr/engine.js'></script>
		<script type='text/javascript' src='/bpp/dwr/util.js'></script>
	</head>
	<body>
		<form action="audit.do?actionType=select" method="post">
			<table>
				<tr>

					<td>
						起始日期:
						<input type="text" id="start" name="audit.start">
					</td>
					<td>
						结束日期:
						<input type="text" id="end" name="audit.end">
					</td>
				</tr>
				<tr>
					<td>
						计算机名:
						<input type="text" id="computer"  value="${param.computer }">
						

					</td>
					<td>
						搜索类型:
						<select id="category" name="audit.category"
							onblur="showItem(this.value)">

							<option value="Groups and Users">
								Groups and Users
							</option>
							<option value="Installed Software ">
								Installed Software
							</option>
							<option value="Network TCP/IP ">
								Network TCP/IP
							</option>
							<option value="System Overview">
								System Overview
							</option>
						</select>
					</td>

					<td>
						<select id="item" name="audit.item"
							onclick="showResult(this.value)"></select>
					</td>

					<td>
						值1:
						<select id="rs" size="1" multiple="multiple">
						</select>
					</td>
				</tr>
			</table>
		</form>

	
	</body>
</html>
