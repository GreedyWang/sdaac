<%@ page language="java" pageEncoding="utf-8"%>
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
		<table>
			<form action="mainData.do?actionType=doApprove" method="post">
				<input type="hidden" name="form.uuid" value="${rs.uuid}">
				<input type="hidden" name="form.state" value="${rs.state}">
			<tr>
				<td>
					物料号 ：
				</td>
				<td>
					<input type="text" name="form.materialSn">
				</td>
			</tr>
			<tr>
				<td>
					1. 是否做库存管理
					<input id="t1" type="checkbox" name="form.isStorageManage" value="yes">
				</td>

				<td>
					2. 收货就费用化
					<input id="t2" type="checkbox" name="form.isExpense" value="yes">
				</td>




			<tr>
				<td>
					<select name="apForm.isApproved">
						<option value="1">
							批准
						</option>
						<option value="3">
							需解释
						</option>
						<option value="2">
							不批准
						</option>
						<option value="4">回到上步</option>
					</select>
				</td>
				<td>
					<textarea rols="6" cols="160" name="apForm.context"></textarea>
				</td>

			</tr>
			<tr>
				<td>
					<input type="submit" value="提交/submit">
				</td>
			</tr>
			</form>
		</table>
	</body>
</html>
