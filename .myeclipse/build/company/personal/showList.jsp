<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib uri="/WEB-INF/c.tld" prefix="c" %>
<%@taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>
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
		<link type="text/css" href="css/themes/base/ui.all.css"
			rel="stylesheet" />
				<link type="text/css" href="css/demos.css"
			rel="stylesheet" />
		<script type="text/javascript" src="js/jquery-1.3.2.js"></script>
		<script type="text/javascript">
	$(function() {
	
		$('#context tr').mouseover(function()
			{
				$(this).addClass('highlight2')
			}).mouseout(function(){
				$(this).removeClass('highlight2')
			})	

	});
	
	</script>
	<style type="text/css">
		.highlight2 {
        background:#bcd4ec;  /*这个将是鼠标高亮行的背景色*/
        cursor:pointer;}

		.aa{width: 27%;margin: 0px;float: right}
		.bb{font-size: 10px;}
		label, input { display:block; }
		input.text { margin-bottom:12px; width:95%; padding: .4em; }
		fieldset { padding:0; border:0; margin-top:25px; }
		h1 { font-size: 1.2em; margin: .6em 0; }
		
		
	</style>
	</head>
	<body class="bb">

	<div  style="width:70%;float: left;height: 100%;" class="ui-widget ui-widget-content">
		<table width="100%" id="context">
				<thead>
		
					<tr class="ui-widget-header ">
						<th>状态</th>
						<th>日期</th>
						<th>标题</th>
						<th>类型</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${rs}" var="item">
						<tr >
							<td>${item.stateName }</td>
							<td><fmt:parseDate pattern="yyyy-MM-dd" value="${item.releaseTime }"/></td>
							<td><a target="blank" href="news.do?actionType=showDetails&pk=${item.id }">${item.title }</a></td>
							<td>${item.typeName }</td>
						</tr>
					</c:forEach>		
				</tbody>
		</table>
	</div>
		
	</body>
</html>
