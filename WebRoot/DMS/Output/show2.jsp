<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<script type='text/javascript' src='dwr/interface/TempleteService.js'></script>
		<script type='text/javascript' src='dwr/engine.js'></script>
		<script type='text/javascript' src='vave/proposal.js'></script>
		<style type="text/css" media="screen">@import url(css/vave.css);</style>
	</head>
	<script type="text/javascript">
		function goback(){
			window.location.href='/bpp/dMS.do?actionType=doGetUserInfo&d=4score'
		}
	</script>
	<body>
		<table>
			<tr>
				<td>
					区域：${emp.district }
				</td>
				<td>
					组别：${emp.group }
				</td>
			</tr>
		</table>
		
		<table>
			<tr>
				<td width="70px">
					员工姓名
				</td>
				<c:forEach items="${title.indicates}" var="item">
					<td width="30px">
						${item.name }
					</td>
					<input type="hidden" value="${item.id}" name="indicateid">
					<input type="hidden" value="${item.weight}" name="weight">
					<input type="hidden" value="${item.calc}" name="calcMethod">
				</c:forEach>
			</tr>
			<c:forEach items="${templetes}" var="item">
				<tr>
					<td width="100px">
						${item.emp.name }
					</td>
					<input type="hidden" value="${item.emp.uid}" name="uid">
					<c:forEach items="${item.results}" var="item2">
						<td width="30px">
							${item2.score}
						</td>
					</c:forEach>
				</tr>
			</c:forEach>
			<tr>
				<td>
					<input type="button" value="返回" onclick="goback()">
				</td>
			</tr>
			</form>
		</table>
	</body>
</html>
