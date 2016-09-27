<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	request.setAttribute("basePath", basePath);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>winaudit小工具</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" type="text/css" href="css/style_admin.css">
		<link rel="stylesheet" type="text/css" href="ext/resources/css/ext-all.css">
		<script type="text/javascript" src="ext/adapter/ext/ext-base.js"></script>
		<script type="text/javascript" src="ext/ext-all.js"></script>
		<script type="text/javascript" src="audit/auditManager.js"></script>
		<script type='text/javascript'src='/bpp/dwr/interface/auditDailyManager.js'></script>
		<script type='text/javascript' src='/bpp/dwr/engine.js'></script>
		<script type='text/javascript' src='/bpp/dwr/util.js'></script>
		<script type="text/javascript" src='js/util/wforms.js'></script>
	</head>
	<script type="text/javascript">
	function selectEntry(param){
	
		var computer=document.getElementById('computer').value
		var categoryValue=document.getElementById('category').value
			auditDailyManager.selectEntry(computer,categoryValue,param, function(data) {
				
				DWRUtil.removeAllOptions('item');
				DWRUtil.addOptions("item", data);
			})
	}
	</script>
	<body>
		<form action="audit.do?actionType=select" method="post">
			<table>
				<tr>

					<td>
						起始日期:
						<input type="text" id="start" name="audit.start">
					</td>
					<td>
						结束日期:
						<input type="text" id="end" name="audit.end">
					</td>
				</tr>
				<tr>
					<td>
						计算机名:
				
					</td>
					<td>
						搜索类型:					
					</td>

					<td>
						详细条目:			
					</td>

					<td>
						范围
					</td>

					<td>
						值1:
						
					</td>
					<td>
						搜索
					</td>
				</tr>
				<tr>
					<td>
						
						<input type="text" onblur="init(this.value)">
					
					</td>
					<td>
						
						
					</td>

					<td>
						<input id="entry"   onblur="selectEntry(this.value)"/>
					</td>

					<td>
						
					</td>

					<td>
						
						
					</td>
					<td>
						
					</td>
				</tr>
				<tr>
					<td>
						
						
						<select id="computer" name="audit.computer"></select>
					</td>
					<td>
						
						<select id="category" name="audit.category"
							onblur="showItem(this.value)">
							<option value="">
								All
							</option>
							<option value="Groups and Users">
								Groups and Users
							</option>
							<option value="Installed Software ">
								Installed Software
							</option>
							<option value="Network TCP/IP ">
								Network TCP/IP
							</option>
							<option value="System Overview">
								System Overview
							</option>
						</select>
					</td>

					<td>
						<select id="item"  name="audit.itemName"></select>
					</td>

					<td>
						<select id="item" name="audit.operator">
							<option value="lager">
								大于
							</option>
							<option value="small">
								小于
							</option>
							<option value="=">
								等于
							</option>
							<option value="like">
								相似
							</option>
						</select>
					</td>

					<td>
						
						<input type="text" name="audit.itemValue1">
					</td>
					<td>
						<input type="submit" value="search">
					</td>
				</tr>
			</table>
		</form>
		<table>
			<tr class="header">
				<td>
					计算机名
				</td>
				<td>
					数据日期
				</td>
				<td>
					查询类型
				</td>
				<td>
					查询结果
				</td>
				<td>
					详细
				</td>
			</tr>
			<c:forEach items="${rs}" var="item">
				<tr class="altbg1">

					<td>
						${item.computer }
					</td>
					<td>
						<fmt:formatDate type="date" value="${item.dateTimeDb }" />
					</td>
					<td>
						${item.itemName }
					</td>
					<td>
						${item.itemValue1 },${item.itemValue2 }
					</td>

				
				</tr>
			</c:forEach>
		</table>

	</body>
</html>
