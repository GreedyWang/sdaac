<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="java.net.URLEncoder"%>
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

		<title>岗位管理</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<script type="text/javascript" src="calc/calendar.js"></script>
		<script type="text/javascript" src="calc/calendar-en.js"></script>
		<script type='text/javascript' src="js/cal.js"></script>
		<link rel="stylesheet" type="text/css" href="css/style_admin.css">
		<link type="text/css" rel="stylesheet" href="calc/calendar-system.css" />
	</head>
	<script type="text/javascript">
  function doDel(id)
  {
  	var key=window.confirm("确定删除");
  	if(key)
  	{  
	  	var url='employeeProduct.do?actionType=deleteDailyRecord&id='+id;
	  	window.location.href=url;
  	}
  }
  function saveExcel()
  {
  	window.location.href="employeeProduct.do?actionType=doSelectDailyRecordByExcel";
  }
  </script>
	<body>
		PersonalEffectSelect.jsp
		<table>
			<tr>
				<form action="employeeProduct.do?actionType=doSelectDailyRecord"
					method="post">
					<jsp:include flush="true" page="SelectToolBar.jsp"></jsp:include>
				</form>
			</tr>

		</table>
		<table cellspacing="1" cellpadding="4" width="90%" class="tableborder"
			id="table3">
			<thead>
				<tr>
					<td colspan="1" class="header">
						员工绩效管理
					</td>
					<td colspan="10" class="header">
						下载报表<img src="images/icons/edit.gif" onclick="saveExcel()">
					</td>
				</tr>
				<tr>
					<td align="center" class="altbg1">
						<b>记录类型</b>
					</td>
					<td align="center" class="altbg1">
						<b>员工编号</b>
					</td>
					<td align="center" class="altbg1">
						<b>岗位代码</b>
					</td>
					<td align="center" class="altbg1">
						<b>工作时间(小时)</b>
					</td>
					<td align="center" class="altbg1">
						<b>产出</b>
					</td>
					<td align="center" class="altbg1">
						<b>工作日期</b>
					</td>
					<td align="center" class="altbg1">
						<b>记录日期</b>
					</td>
					<td align="center" class="altbg1">
						<b>组长</b>
					</td>
					<td align="center" class="altbg1">
						<b>记录员</b>
					</td>
					<td colspan="2" align="center" class="altbg1">
						<b>操作</b>
					</td>
				</tr>
			</thead>
			<tbody>
				<c:if test="${DailyRecordQuery!=null }">
					<c:forEach items="${DailyRecordQuery}" var="item">
						<tr>
							<td align="center" class="altbg1">
								${item.typeName}
							</td>
							<td align="center" class="altbg1">
								${item.tempolyee.uid}
							</td>
							<td align="center" class="altbg1">
								${item.tpost.id}
							</td>
							<td align="center" class="altbg1">
								${item.worktime}
							</td>
							<td align="center" class="altbg1">
								${item.output}
							</td>
							<td align="center" class="altbg1">
								${item.datatime}
							</td>
							<td align="center" class="altbg1">
								${item.registerTime}
							</td>
							<td align="center" class="altbg1">
								${item.teamLeaderId}
							</td>
							<td align="center" class="altbg1">
								${item.registerID}
							</td>
							<td align="center" class="altbg1">
								<a target="blank"				
																																					
									href="front/PersonalEffectUpdate.jsp?id=${item.id}&uid=${item.tempolyee.uid}&postid=${item.tpost.id}&worktime=${item.worktime}&output=${item.output}&datatime=${item.datatime}&teamLeaderId=${item.teamLeaderId}">
									<img src="images/update.gif">
								</a>
							</td>
							<td align="center" class="altbg1">

								<img src="images/delete.gif" onclick="doDel(${item.id})">
							</td>
						</tr>
					</c:forEach>

				</c:if>
				<c:if test="${DailyRecordQuery==null}">
无此信息
 </c:if>
				<tr>
					<jsp:include flush="true" page="../page.jsp"></jsp:include>
				</tr>
			</tbody>
		</table>
	</body>
</html>
