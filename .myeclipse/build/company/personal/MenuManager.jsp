<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
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

		<title>My JSP 'MenuManager.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<script type="text/javascript" src='js/util/wforms.js'></script>
	</head>
	<script type="text/javascript">
  		function dc(){
  			 window.localtion.href
  		}
  	</script>
	<body>
		<form action="menu.do?actionType=deal" method="post">
			<input type="hidden" name="item.id" value="${item.id }">
			<input type="hidden" name="item.method" value="${item.method }">
			<input type="hidden" name="item.fileName" value="${item.fileName }">
			<input type="hidden" name="item.pid" value="${item.pid }">
			<table>
				<tr>
					<td>
						<font>名称</font>
					</td>
					<td colspan="2">
						<input class="required" type="text" name="item.name" value="${item.name }"
							size="20">
					</td>
				</tr>

				<c:if test="${item.isMenu==null}">
					<tr>
						<td >
							<font>链接</font>
						</td>
						<td colspan="2">
							<input class="required" type="text" name="item.url" value="${item.url }" size="20">
						</td>
					</tr>
					<tr>
						<td>
							<font>描述</font>
						</td>
						<td >
						<textarea rows="5" cols="80"  class="required"  name="item.introduce">${item.introduce }</textarea>
				
						</td>
					</tr>
				</c:if>
				<input type="submit" value="确认">
			</table>
		</form>
	</body>
</html>
