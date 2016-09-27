<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld"%>
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

		<title>My JSP 'plm.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" type="text/css" href="style/button.css">
		<link rel="stylesheet" type="text/css" href="style/icon.css">
		<script type="text/javascript" src="js/jquery.js"></script>
		<!-- 
	<script type="text/javascript" src="js/button.js"></script> -->
		<script type="text/javascript">
		function download(pk)
		{
			
			window.location.href='plm.do?actionType=plmToExcel&pmartId='+pk
		}
		function barCode(pk)
		{
			var quantity=window.prompt("请输入多少张",1);
			pk+='!'+quantity;
			window.open('plm/barCode.jsp?pmartId='+pk)
		}
		
		function changeKind(elem)
		{	
			if(document.getElementById('M').checked==true)
			{
				elem.href=	elem.href.substring(0,elem.href.length-1)		
				elem.href+='M'
			}
			if(document.getElementById('E').checked==true)
			{
				elem.href=	elem.href.substring(0,elem.href.length-1)		
				elem.href+='E'
			}
		
			
		}
	function init()
	{
		var kind='${cond[1]}'
		if(kind=='M')
		{
			document.getElementById('M').checked=true;
		}
		if(kind=='E')
		{
			document.getElementById('E').checked=true;
		}
	}
	</script>
	</head>

	<body onload="init()">
		<table>
			<form action="plm.do?actionType=select" method="post">
			<tr>
				<td>
					<input type="text" name="condition">
				</td>
				<td>
					<input type="submit" icon="icon-search">
				</td>
				<td>
					<input type="checkbox" name="isA" value="true">
					Accurate
				</td>
				<td>
					<input id="M" name="kind" type="radio" checked="checked" value="M">
					Mpart
				</td>
				<td>
					<input id="E" name="kind" type="radio" value="E">
					Epart
				</td>

			</tr>
			</form>
		</table>
		<table>
			<tr>
				<td>
					NO
				</td>
				<td>
					NAME
				</td>
				<td>
					ENAME
				</td>
				<td>
					下载
				</td>
				<td>
					条形码
				</td>
			</tr>
			<c:forEach items="${MParts}" var="item">
				<tr>
					<td>
						<a target="tree" id="aa" href="plm/TreePanel.jsp?name=${item.no }&id=${item.id };M"
							onclick="changeKind(this)">${item.no }</a>
					</td>
					<td>
						${item.name }
					</td>
					<td>
						${item.ename }
					</td>
					<td>
						<input type="button" icon="icon-excel"
							onclick="download('${item.id  }')" value="下载">
					</td>
					<td>
						<input type="button" icon="icon-excel"
							onclick="barCode('${item.no  }')" value="条形码">
					</td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="4"><jsp:include flush="true" page="/page.jsp"></jsp:include>
				<td>
			</tr>
		</table>



	</body>
</html>
