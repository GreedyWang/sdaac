<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt.tld" %>
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

		<title>入库数</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" type="text/css" href="css/style_admin.css">
		<script type="text/javascript" src="front/hrpost/th/checkFrom.js"></script>
		<script type='text/javascript' src='/bpp/dwr/interface/BIManager.js'></script>
		<script type='text/javascript' src='/bpp/dwr/engine.js'></script>

	</head>
	<script type="text/javascript">
		function update(id,index)
		{
			var quantity = document.getElementById(index).value;
			var bounds= {
				id:id,
				inbound:quantity
			}
			BIManager.update(bounds,function(data){	
				alert('修改成功')		
			})	
		}	
	</script>
	<body>

		<form id="1" action="figureAndPostId.do?actionType=addFigure"
			method="post" onsubmit="return checkFrom()">
			<table cellspacing="1" cellpadding="4" width="90%"
				class="tableborder" id="table1">
				<thead>

					<tr>
						<td align="center" class="altbg1">
							<b>图号<b>
						</td>
						<td align="center" class="altbg1">
							<b>入库数<b>
						</td>
						<td align="center" class="altbg1">
							<b>时间</b>
						</td>
						<td align="center" class="altbg1">
							<b>修改</b>
						</td>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${rs}" var="item" varStatus="state">
						<tr>
							<td align="center">
								${item.figureId }
							</td>
							<td align="center">
								<input id="${state.index }" value="${item.inbound }">
							</td>
							<td align="center">
							<fmt:formatDate value="${item.inboundTime }" pattern="yyyy-MM"/>
								
							</td>
							<td align="center">
								<input type="button" value="修改"
									onclick="update('${item.id }','${state.index }')">
							</td>

						</tr>
					</c:forEach>
				</tbody>
				</form>
	</body>
</html>
