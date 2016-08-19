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
		<form action="epor.do?actionType=changeRoute" method = "post">
			<table>
				<tr>
					<td>
						流水号
					</td>
					<td>
						新节点
					</td>
				
				</tr>

				<tr>
					<td>
						<input type="text" name="ssid">
					</td>
					<td>
						<select type="text" name="stepName">
							<option name="fill in">fill in</option>
							<option name="DM">DM</option>
							<option name="capital_check">capital_check</option>
							<option name="pm">pm</option>
							<option name="dmsupervisor">dmsupervisor</option>
							<option name="cost_center_owner">cost_center_owner</option>
							<option name="fin">fin</option>
							<option name="fin Mgr">fin Mgr</option>
							<option name="Plant_Mgr">Plant_Mgr</option>
							<option name="Plant_Mgr">Eng Dr/DGM</option>							
							<option name="GM">GM</option>
							<option name="Thermal">Thermal</option>
							<option name="Fin_check">Fin_check</option>
							<option name="PCL_check">PCL_check</option>
							<option name="Buyer_check">Buyer_check</option>
						</select>
					</td>
					
					<td>
						<input type="submit" name="submit">
					</td>
				</tr>

			</table>
		</form>
	</body>
</html>
