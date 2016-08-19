<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>
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
		<link rel="stylesheet" type="text/css" href="css/style_admin.css">
	</head>
	<script type="text/javascript">
		function goback(){
			window.location.href='/bpp/dMS.do?actionType=doGetUserInfo&d=4score'
		}
	</script>
	<body>
		<table>
			<tr align="center" class="header">
				<td>
					区域：${emp.district }
				</td>
				<td>
					组别：${emp.selectedGroup }
					
				</td>
				<td>
					版本：${version }
				</td>
			</tr>
		</table>
		
		<table  width="90%"cellspacing="1" cellpadding="4"  class="tableborder">
			<tr >
				<td align="center" class="header" width="50px">姓名</td>
				<td align="center" class="header">安全	</td>
				<td align="center" class="header">质量</td>
				<td align="center" class="header">生产</td>
				<td align="center" class="header">成本</td>
				<td align="center" class="header">出勤</td>
				<td align="center" class="header">纪律</td>
				<td align="center" class="header">5S</td>
				<td align="center" class="header">加分项</td> 
						
				<td align="center" class="header">总分</td>
				<td align="center" class="header">HC</td>
				<td align="center" class="header">排名</td>
				<td align="center" class="header">小组长个人奖金</td>
				<td align="center" class="header">小组奖金额度</td>
				<td align="center" class="header">人均奖金</td>
			</tr>


			<c:forEach items="${templetes}" var="item">
				<tr>
					<td width="50px" align="center" class="altbg1">
						${item.emp.name }
					</td>
		
					<td width="30px" align="center" class="altbg1">
						${item.score2 }
					</td>
			
					<td width="30px" align="center" class="altbg1">
						${item.score5 }
					</td>
					<td width="30px" align="center" class="altbg1">
						${item.score6 }
					</td>
					<td width="30px" align="center" class="altbg1">
						${item.score7 }
					</td>
		
					<td width="30px" align="center" class="altbg1">
						${item.score10 }
					</td>
	
					<td width="30px" align="center" class="altbg1">
						${item.score13 }
					</td>
					<td width="30px" align="center" class="altbg1">
						${item.score14 }
					</td>
					<td width="30px" align="center" class="altbg1">
						${item.score15 }
					</td>
		

					
					<td width="30px" align="center" class="altbg1">
						${item.totalScore }
					</td>
					<td width="30px" align="center" class="altbg1">
						${item.peopelCount }
					</td>
					<td width="30px" align="center" class="altbg1">
						${item.ranking }
					</td>
					
					<td width="30px" align="center" class="altbg1">
						<fmt:formatNumber value="${item.totalBouns/item.peopelCount*2 }" pattern="00.0"></fmt:formatNumber>
						
					</td>
					<td width="30px" align="center" class="altbg1">
						<fmt:formatNumber value="${item.totalBouns - item.totalBouns/item.peopelCount*2 }" pattern="00.0"></fmt:formatNumber>
						
					</td>
					<td width="30px" align="center" class="altbg1">
						<fmt:formatNumber value="${item.totalBouns/item.peopelCount }" pattern="00.0"></fmt:formatNumber>
						
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
