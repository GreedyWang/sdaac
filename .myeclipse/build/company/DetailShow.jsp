<%@ page language="java"  pageEncoding="utf-8"%>
<%@taglib uri="/WEB-INF/c.tld" prefix="c" %>
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

		<title>详细信息</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<style type="text/css">
			table {
				font-size: 16;
			}
		</style>

	</head>

	<body>
		<table>
			<tr><td><h3 align="center">${item.title }</h3></td></tr>
			<tr><td>${item.context }</td></tr>
			<tr><td align="right">	${item.releaseEmployeeId.name }-- ${item.releaseTime }</td></tr>
		</table>
		<c:if test="${item.commentses!=null}">
		<hr><label>评论</label>
			<c:forEach items="${item.commentses}" var="coms">
				${coms.context }<p>
			</c:forEach>
		</c:if>

	</body>
</html>
