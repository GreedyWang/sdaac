<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
		<meta http-equiv="description" content="This is my page">

	</head>

	<body>
		<form action="epor.do?actionType=changeApprovor" method = "post">
			<table>
				<tr>
					<td>
						流水号
					</td>
					<td>
						修改节点
					</td>
					<td>
						新审批人netid
					</td>
				</tr>

				<tr>
					<td>
						<input type="text" name="ssid">
					</td>
					<td>
						<input type="text" name="stepName">
					</td>
					<td>
						<input type="text" name="netid">
					</td>
					<td>
						<input type="submit" name="submit">
					</td>
				</tr>

			</table>
		</form>
	</body>
</html>
