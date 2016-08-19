<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
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
	<LINK href="css/common.css" type=text/css rel=stylesheet>
	<link rel="stylesheet" type="text/css" href="css/style_admin.css">
	<link type="text/css" rel="stylesheet" href="calc/calendar-system.css" />
</head>

<body>

<html:form action="personal.do?actionType=selectAll" method="post">
	<table>
		<tr>		
				<td align="center" class="altbg1">
					开始日期：
				</td>
				<td align="center" class="altbg1">
					<input type="text" name="time" id="time" onfocus="calShow('time');">
				</td>

				<td align="center" class="altbg1">
					结束日期：
				</td>
				<td align="center" class="altbg1">
					<input type="text" name="endTime" id="endTime"
						onfocus="calShow('endTime');">
				</td>

				<td align="center" class="altbg1">
					员工编号
				</td>
				<td align="center" class="altbg1">
					<input type="text" name="item.tempolyee.uid"
						id="item.tempolyee.uid">
				</td>
				<td align="center" class="altbg1"></td>
				<td>
					<input type="submit" value="查询">
				</td>
				<td>
					<input type="reset" value="重置">
				</td>
		</tr>
	</table>
</html:form>

	<table cellspacing="1" cellpadding="4" width="90%" class="tableborder"
		id="table3">
		<thead>
			<tr>
				<td colspan="1" class="header">
					岗位管理
				</td>
				<td colspan="7" class="header">
					${condition[0] }到${condition[1] }
				</td>
				<td class="header">
					<a href="personal.do?actionType=WriteToExcelAndDownLoad"><img
							src="images/save.gif">
					</a>
				</td>
			</tr>
			<tr>
				<td align="center" class="altbg1">
					<b>员工编号</b>
				</td>
				<td align="center" class="altbg1">
					<b>员工姓名</b>
				</td>
				<td align="center" class="altbg1">
					<b>档案岗位工资</b>
				</td>
				<td align="center" class="altbg1">
					<b>档案岗位等级</b>
				</td>

				<td align="center" class="altbg1">
					<b>岗位等级A(小时)</b>
				</td>
				<td align="center" class="altbg1">
					<b>岗位等级B(小时)</b>
				</td>
				<td align="center" class="altbg1">
					<b>岗位等级C(小时)</b>
				</td>
				<td align="center" class="altbg1">
					<b>岗位等级D(小时)</b>
				</td>

				<td align="center" class="altbg1">
					<b>实际岗位工资</b>
				</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${PostSalaryResults }" var="item">
				<tr>
					<td align="center" class="altbg1">
						${item.uid }
					</td>
					<td align="center" class="altbg1">
						${item.name }
					</td>
					<td align="center" class="altbg1">
						${item.baseSalary }
					</td>
					<td align="center" class="altbg1">
						${item.salaryType }
					</td>

					<td align="center" class="altbg1">
						${item.typeA }
					</td>
					<td align="center" class="altbg1">
						${item.typeB }
					</td>
					<td align="center" class="altbg1">
						${item.typeC }
					</td>
					<td align="center" class="altbg1">
						${item.typeD }
					</td>
					<td align="center" class="altbg1">
						${item.floattingSalary }
					</td>
				</tr>
			</c:forEach>


			<tr><jsp:include flush="true" page="page.jsp"></jsp:include>
			</tr>
		</tbody>
	</table>

</body>
</html:html>
