<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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

		<title>管理人员-管理</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" type="text/css" href="css/style_admin.css">
		<script type='text/javascript' src='/bpp/dwr/interface/EmpManager.js'></script>
		<script type='text/javascript' src='/bpp/dwr/engine.js'></script>

		<script type="text/javascript">

  
  function init()
  {
  	var select=document.getElementById('departList')
  	var dpName='${empList.tdepartment.name }'
  	//alert(dpName)
  	if(select!=null)
  	{
  		for(var i=0;i<select.options.length;i++)
  		{
  			if(select.options[i].text==dpName)
  			{
  				select.options[i].selected=true;
  			}
  		}
  	}
  	
  	var ck=document.getElementById('is')
  	var flag='${empList.isSeparation }'
  	if(flag==0)
  	{
  		ck.checked=true;
  	}
  	
  }
  </script>

	</head>

	<body onload="init()">
		<table>
			<tr>
				<form id="1" action="employee.do?actionType=selectEmp"
					name="selectCustomerOrder" method="post">
				<td>
					员工编号
				</td>
				<td>
					<input type="text" name="item.uid">
				</td>
				<td>
					员工姓名
				</td>
				<td>
					<input type="text" name="item.name">
				</td>
				<td>
					<input type="submit" value="查询">
				</td>
				<td>
					<input type="reset" value="重置">
				</td>
				</form>
			</tr>

		</table>
		<table cellspacing="1" cellpadding="4" width="90%" class="tableborder"
			id="table3">
			<thead>
				<tr>
					<td colspan="11" class="header">
						员工管理
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
						<b>部门</b>
					</td>
					<td align="center" class="altbg1">
						<b>直系领导</b>
					</td>
					<td align="center" class="altbg1">
						<b>档案工资</b>
					</td>
					<td align="center" class="altbg1">
						<b>档案工资等级</b>
					</td>

					<td align="center" class="altbg1">
						<b>区域</b>
					</td>
					<td align="center" class="altbg1">
						<b>NetID</b>
					</td>
					<td align="center" class="altbg1">
						<b>PR审批范围</b>
					</td>
					<td align="center" class="altbg1">
						<b>负责部门</b>
					</td>
					<td align="center" class="altbg1">
						<b>是否离职</b>
					</td>
					<td align="center" class="altbg1">
						<b>操作</b>
					</td>
				</tr>
			</thead>
			<tbody>
				<c:if test="${empList!=null }">

					<form id="2" action="employee.do?actionType=updateEmp"
						method="post">
					<tr>
						<td align="center" class="altbg1">
							${empList.uid }
							<input name="item.uid" type="hidden" value="${empList.uid }">
						</td>
						<td align="center" class="altbg1">
							<input name="item.name" type="text" value="${empList.name }">
						</td>
						<td align="center" class="altbg1">
							<select id="departList" name="item.tdepartment.id">
								<c:forEach items="${departs}" var="item">
									<option value="${item.id }">
										${item.name }
									</option>
								</c:forEach>
							</select>
						</td>
						<td align="center" class="altbg1">
							<input name="item.leaderID" type="text"
								value="${empList.leaderID }">
						</td>
						<td align="center" class="altbg1">
							<input name="item.baseSalary" type="text"
								value="${empList.baseSalary }">
						</td>
						<td align="center" class="altbg1">
							${empList.salaryType }
						</td>

						<td align="center" class="altbg1">
							<input name="item.area" type="text" size="8" value="${empList.area }">
						</td>
						<td align="center" class="altbg1">
							<input name="item.netid" type="text" size="8" value="${empList.netid }">
						</td>
						<td align="center" class="altbg1">
							<input name="item.eprownerarea" type="text" size="8" value="${empList.eprownerarea }">
						</td>
						<td align="center" class="altbg1">
							<input name="item.managedms" type="text" size="8" value="${empList.managedms }">
						</td>
						<td align="center" class="altbg1">
							<input type="checkbox" name="item.isSeparation" id="is" value="0">
							已离职
						</td>
						<td align="center" class="altbg1">
							<input type="submit" value="修改">
						</td>
					</tr>
					</form>
				</c:if>

				<tr>
				</tr>
			</tbody>
		</table>

	</body>
</html>
