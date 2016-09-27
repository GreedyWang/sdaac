<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<jsp:directive.page
	import="org.springframework.context.ApplicationContext" />
<jsp:directive.page
	import="org.springframework.context.support.ClassPathXmlApplicationContext" />
<jsp:directive.page import="app.biz.DepartmentBiz" />
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

		<title>员工添加</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" type="text/css" href="css/style_admin.css">
	</head>
	<script type="text/javascript">
		<!--Enable ratio change-->
		function rationFlag(ele){
		
			if(ele.value == 0 || ele.value == 1){
				//document.getElementById('dmsRatio').disabled = true;
				document.getElementById('dmsRatio').disabled = false;
			}else{
				document.getElementById('dmsRatio').disabled = true;
			}			
		}
	</script>
	<body>

		<table cellspacing="1" cellpadding="4" width="90%" class="tableborder"
			id="table3">
			<thead>
				<tr>
					<td colspan="9" class="header">
						员工添加
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
						<b>部门名称</b>
					</td>
					<td align="center" class="altbg1">
						<b>直系领导工号</b>
					</td>

					<td align="center" class="altbg1">
						<b>区域</b>
					</td>
					<td align="center" class="altbg1">
						<b>组别</b>
					</td>
					
					<td align="center" class="altbg1">
						<b>综合组/员工奖金系数</b>
					</td>

					<td align="center" class="altbg1">
						<b>工厂</b>
					</td>

				</tr>
			</thead>
			<tbody>

				<form action="employee.do?actionType=doInsert" method="post">
				<tr>
					<td align="center" class="altbg1">
						<input name="item.uid" type="text" size="6">
					</td>
					<td align="center" class="altbg1">
						<input name="item.name" type="text" size="6"/>
					</td>
					<td align="center" class="altbg1">
						<select name="item.tdepartment.id">
							<c:forEach items="${departs}" var="item">
								<option value="${item.id }">
									${item.name }
								</option>
							</c:forEach>
						</select>
					</td>
					<td align="center" class="altbg1">
						<input type="text" name="item.leaderID" size="6">
					</td>

					<td align="center" class="altbg1">
						<select name="item.district">
							<option value="HVAC暖风">
								HVAC暖风
							</option>
							<option value="HVAC蒸发器">
								HVAC蒸发器
							</option>
							<option value="HVAC总装">
								HVAC总装
							</option>														
							<option value="PTC水箱">
								PTC水箱
							</option>
							<option value="PTC冷凝">
								PTC冷凝
							</option>
							<option value="PTCCRFM">
								PTCCRFM
							</option>		
							<option value="D2 Gamma">
								D2 Gamma
							</option>											
						</select>
					</td>

					<td align="center" class="altbg1">
						<select name="item.group" onchange="rationFlag(this)">
							<option value="4">
								区域经理
							</option>
							<option value="3">
								大组
							</option>
							<option value="2">
								小组
							</option>
							<option value="1">
								员工
							</option>
							<option value="0">
								综合组
							</option>
						</select>
					</td>

					<td class="altbg1">
						<input type="text" name="item.dmsratio" disabled="true" id="dmsRatio">
					</td>
						<input type="hidden" name="item.type" value="worker">
					<td class="altbg1">
						<select name="item.area">
							<option value="DT02">
								上海
							</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>
						<input type="submit" value="提交" />
					</td>
				</tr>
				</from>

				<tr>
				</tr>
			</tbody>
		</table>
	</body>
</html>
