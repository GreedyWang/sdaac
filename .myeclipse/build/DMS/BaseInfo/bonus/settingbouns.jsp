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
<script type="text/javascript">
	<!-- 创建新一月的考核--->
	function create(){
		var ele = document.getElementById('version')
	  	var re =/^(\d{4})-(\d{2})$/; 
	    if(re.test(ele.value)){
	    //判断日期格式符合YYYY-MM-DD标准
	   		document.getElementById('f1').action='dMS.do?actionType=settingBonus' 
			document.getElementById('f1').submit();	
		}else{
			alert('请按照YYYY-MM格式来创建!')
			return false;
		}	
	}
</script>
	<body>
	<table width="70%"cellspacing="1" cellpadding="4"  class="tableborder">
		<form id="f1" action="dMS.do?actionType=showBonus&type=2" method="post">
			<tr>
				
				<td align="center" class="header" >
					版本(yyyy-mm) ：<input type="text" name="version" id="version">
				</td>
				<td align="center" class="header" >
					<input type="submit" value="查询">
				</td>
				<td align="center" class="header" >
					<input type="button" value="创建" onclick="create()">
				</td>
			</tr>
		</form>
	</table>
	
	<form action="dMS.do?actionType=updateBonus" method="post">
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
						<input type="text" name="bonus" value="${item.bonus }">
					</td>
					<td align="center" class="altbg1">
						${item.area }
					</td>
					<td align="center" class="altbg1">
						${item.version }
					</td>
						<input type="hidden" name="id" value="${item.id }">
				</tr>
			</c:forEach>
			<tr>
				<td>
					<input type="submit" value="修改">
				</td>
			</tr>
		</table>
	</form>
	</body>
</html>
