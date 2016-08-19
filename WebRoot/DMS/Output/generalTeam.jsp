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


			<c:forEach items="${templetes}" var="item">
				<tr>
					<td width="30px">
						${item.uid }
					</td>
					<td width="30px">
						${item.score1 }
					</td>
					<td width="30px">
						${item.score2 }
					</td>
					<td width="30px">
						${item.score3 }
					</td>
					<td width="30px">
						${item.score4 }
					</td>
					<td width="30px">
						${item.score5 }
					</td>
					<td width="30px">
						${item.score6 }
					</td>
					<td width="30px">
						${item.score7 }
					</td>
					<td width="30px">
						${item.score8 }
					</td>
					

					
					<td width="30px">
						${item.totalScore }
					</td>
					<td width="30px">
						${item.peopelCount }
					</td>
					<td width="30px">
						${item.totalBouns }
					</td>
				</tr>
			</c:forEach>
			
		</table>
		<table>
			<tr>
				<td>
					<input type="button" value="返回" onclick="goback()">
				</td>
			</tr>
				</table>
	</body>
</html>
